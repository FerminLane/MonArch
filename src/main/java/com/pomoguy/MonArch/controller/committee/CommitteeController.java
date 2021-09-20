package com.pomoguy.MonArch.controller.committee;

import com.pomoguy.MonArch.controller.ControllerUtils;
import com.pomoguy.MonArch.dao.committee.CommitteeRepo;
import com.pomoguy.MonArch.dao.committee.CommitteeStatusRepo;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.model.committee.Committee;
import com.pomoguy.MonArch.model.committee.CommitteeStatusStale;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/committees")
@PreAuthorize("hasAuthority('ADMIN')")
public class CommitteeController {

    @Autowired
    private CommitteeRepo committeeRepo;

    @Autowired
    private CommitteeStatusRepo committeeStatusRepo;

    @PersistenceContext
    private EntityManager em;


    @GetMapping
    public String committeeGetList(Map<String, Object> model) {
        model.put("committees", committeeRepo.findAll());
        return "committee/committeeList";
    }

    @GetMapping("/add")
    public String committeeGetFormAdd(Model model) {

        return "committee/committeeAdd";
    }

    @PostMapping("/add")
    public String committeeAdd(@AuthenticationPrincipal User user,
                               @Valid Committee committee,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return committeeGetFormAdd(model);
        }
        committee.setAuthor(user);
        committee.setStatus(committeeStatusRepo.getById("cs1"));
        committee.setCreateDateTime();
        committee.setUpdateDateTime();
        committee.setUpdatedBy(user.getUsername());
        committeeRepo.save(committee);
        return "redirect:/committees/";
    }

    @GetMapping("{committee}/profile")
    public String committeeGetProfile(@PathVariable Committee committee, Model model) {

        model.addAttribute("statuses", committeeStatusRepo.findByIsAvailable(true));
        model.addAttribute("committee", committee);

        return "committee/form/committeeProfile";
    }

    @GetMapping("{committee}/profile/edit")
    public String committeeGetFormEdit(@PathVariable Committee committee, Model model) {

        model.addAttribute("committee", committee);
        return "committee/committeeEdit";
    }


    @PostMapping("{committee}/profile/edit")
    public String committeeEdit(@PathVariable Committee committee,
                                @AuthenticationPrincipal User user,
                                @ModelAttribute("committee") Committee editedCommittee,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return committeeGetFormEdit(committee, model);
        }
        committee.setUpdateDateTime();
        committee.setUpdatedBy(user.getUsername());
        committeeRepo.save(committee);
        return "redirect:/committees/" + committee.getId() + "/profile";
    }

    @PostMapping("{committee}/profile/changestatus")
    public String committeeChangeStatus(@PathVariable Committee committee,
                                        @AuthenticationPrincipal User user,
                                        @ModelAttribute("committee") Committee editedCommittee,
                                        Model model) {


        committee.setUpdateDateTime();
        committee.setUpdatedBy(user.getUsername());
        committeeRepo.save(committee);
        return "redirect:/committees/" + committee.getId() + "/profile";
    }

    @PostMapping("{committee}/profile/decision")
    public String committeeDecision(@PathVariable Committee committee,
                                    @AuthenticationPrincipal User user,
                                    @ModelAttribute("committee") Committee editedCommittee,
                                    Model model) {

        committee.setFinalDecision(editedCommittee.getFinalDecision());
        committee.setStatus(committeeStatusRepo.getById("cs2"));
        committee.setUpdateDateTime();
        committee.setUpdatedBy(user.getUsername());
        if (committee.getFinalDecision().equals("")) {
            model.addAttribute("finalDecisionError", "Обязательно к заполнению");
            return committeeGetFormEdit(committee, model);
        }

        committeeRepo.save(committee);
        return "redirect:/committees/" + committee.getId() + "/profile";
    }


    @GetMapping("{committee}/history")
    public String committeeGetHistory(@PathVariable Committee committee, Model model) {
        AuditQuery query = AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Committee.class, false, false)
                .add(AuditEntity.property("id").eq(committee.getId()));
        List audit = query.getResultList();
        Collections.reverse(audit);
        model.addAttribute("committee", committee);
        model.addAttribute("audit", audit);
        return "committee/form/committeeHistory";
    }

    @GetMapping("{committee}/history/{rev}/profile")
    public String historyCommitteeGetProfile(@PathVariable Committee committee, @PathVariable Integer rev, Model model) {
        AuditQuery query = AuditReaderFactory.get(em)
                .createQuery()
                .forEntitiesAtRevision(Committee.class, rev)
                .add(AuditEntity.property("id").eq(committee.getId()));
        ;
        committee = (Committee) query.getSingleResult();
        model.addAttribute("isHistoryObj", true);
        model.addAttribute("committee", committee);
        return "committee/form/committeeProfile";
    }

}
