package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.dao.ITSystemRepo;
import com.pomoguy.MonArch.model.cmdb.ITSystem;
import com.pomoguy.MonArch.model.User;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/itsystem")
//@PreAuthorize("hasAuthority('ADMIN')")
public class ITSystemController {

    @Autowired
    private ITSystemRepo itSystemRepo;

    @PersistenceContext
    private EntityManager em;

    @GetMapping
    public String itSystemList(Model model) {
        model.addAttribute("itsystems", itSystemRepo.findAll());
        return "itsystems/systemList";
    }

    @GetMapping("/add")
    public String itSystemFormAdd(Model model) {
        return "itsystems/systemEdit";
    }

    @PostMapping("/add")
    public String itSystemAdd(@AuthenticationPrincipal User user,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String buildingArea,
                              Model model) {

        ITSystem itSystem = new ITSystem(name,user, description, buildingArea);

        itSystem.setCreateDateTime();
        itSystem.setUpdateDateTime();
        itSystem.setUpdatedBy(user.getUsername());
        itSystemRepo.save(itSystem);
        return "redirect:/itsystem/";
    }


    @GetMapping("{system}/profile")
    public String itSystemProfile(@PathVariable ITSystem system, Model model) {
        model.addAttribute("itsystem", system);
        return "itsystems/form/systemProfile";
    }


    @GetMapping("{system}/history")
    public String itSystemHistory(@PathVariable ITSystem system, Model model) {
        AuditQuery query = AuditReaderFactory.get(em).createQuery().forRevisionsOfEntity(ITSystem.class,false,false);
        List<Object []> queryList = query.getResultList();
        List<Object> audit = queryList.stream().map(item -> item[0]).collect(Collectors.toList());
        model.addAttribute("itsystem", system);
        model.addAttribute("audit", audit);
        return "itsystems/form/systemHistory";
    }


    @GetMapping("/edit/{system}")
    public String itSystemFormEdit(@PathVariable ITSystem system, Model model) {
        model.addAttribute("itsystem", system);
        return "itsystems/systemEdit";
    }


    @PostMapping("/edit/{system}")
    public String itSystemEdit(@PathVariable ITSystem system,
                               @AuthenticationPrincipal User user,
                               @RequestParam String name,
                               @RequestParam String description,
                               @RequestParam String buildingArea,
                               Model model) {

        system.setDescription(description);
        system.setName(name);
        system.setBuildingArea(buildingArea);
        system.setUpdateDateTime();
        system.setUpdatedBy(user.getUsername());

        itSystemRepo.save(system);
        return "redirect:/itsystem/" + system.getId() + "/profile";
    }

}