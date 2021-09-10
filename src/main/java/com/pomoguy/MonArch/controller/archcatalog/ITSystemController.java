package com.pomoguy.MonArch.controller.archcatalog;

import com.pomoguy.MonArch.dao.ITSystemRepo;
import com.pomoguy.MonArch.dao.ProductRepo;
import com.pomoguy.MonArch.model.archcatalog.ITSystem;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.model.archcatalog.Product;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/itsystems")
@PreAuthorize("hasAuthority('ADMIN')")
public class ITSystemController {

    @Autowired
    private ITSystemRepo itSystemRepo;
    @Autowired
    private ProductRepo productRepo;

    @PersistenceContext
    private EntityManager em;

    @GetMapping
    public String itSystemGetList(Model model) {
        model.addAttribute("itSystems", itSystemRepo.findAll());
        return "archcatalog/itsystems/itSystemList";
    }

    @GetMapping("/add")
    public String itSystemGetFormAdd(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "archcatalog/itsystems/itSystemAdd";
    }

    @PostMapping("/add")
    public String itSystemAdd(@AuthenticationPrincipal User user,
                              @RequestParam String name,
                              @RequestParam String description,

                              Model model) {

        ITSystem itSystem = new ITSystem(name, user, description);
        itSystem.setCreateDateTime();
        itSystem.setUpdateDateTime();
        itSystem.setUpdatedBy(user.getUsername());

        itSystemRepo.save(itSystem);
        return "redirect:/itsystems/";
    }


    @GetMapping("{itSystem}/profile")
    public String itSystemGetProfile(@PathVariable ITSystem itSystem, Model model) {
        model.addAttribute("itSystem", itSystem);
        return "archcatalog/itsystems/form/itSystemProfile";
    }

    @GetMapping("{itSystem}/profile/edit")
    public String itSystemGetFormEdit(@PathVariable ITSystem itSystem, Model model) {
        model.addAttribute("itSystem", itSystem);
        return "itsystems/systemEdit";
    }


    @PostMapping("{itSystem}/profile/edit")
    public String itSystemEdit(@PathVariable ITSystem itSystem,
                               @AuthenticationPrincipal User user,
                               @RequestParam String name,
                               @RequestParam String description,
                               Model model) {

        itSystem.setDescription(description);
        itSystem.setName(name);
        itSystem.setUpdateDateTime();
        itSystem.setUpdatedBy(user.getUsername());

        itSystemRepo.save(itSystem);
        return "redirect:/itsystem/" + itSystem.getId() + "/profile";
    }


    @GetMapping("{itSystem}/modules")
    public String itSystemGetModules(@PathVariable ITSystem itSystem, Model model) {
        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("itSystem", itSystem);
        return "archcatalog/itsystems/form/itSystemModules";
    }

    @PostMapping("{itSystem}/modules/add")
    public String itSystemModulesEdit(@PathVariable ITSystem itSystem,
                                      @AuthenticationPrincipal User user,
                                      @RequestParam String productId,
                                      Model model) {

        itSystem.setUpdateDateTime();
        itSystem.setUpdatedBy(user.getUsername());
        if (itSystem.getProducts().contains(productRepo.findById(productId).get())) {
            return "redirect:/itsystems/" + itSystem.getId() + "/modules";
        }
        itSystem.getProducts().add(productRepo.findById(productId).get());

        itSystemRepo.save(itSystem);
        return "redirect:/itsystems/" + itSystem.getId() + "/modules";
    }

    @PostMapping("{itSystem}/modules/remove")
    public String itSystemModulesRemove(@PathVariable ITSystem itSystem,
                                      @AuthenticationPrincipal User user,
                                      @RequestParam String productId,
                                      Model model) {
        itSystem.setUpdateDateTime();
        itSystem.setUpdatedBy(user.getUsername());

        itSystem.getProducts().remove(productRepo.findById(productId).get());
        itSystemRepo.save(itSystem);
        return "redirect:/itsystems/" + itSystem.getId() + "/modules";
    }


    @GetMapping("{itSystem}/history")
    public String itSystemGetHistory(@PathVariable ITSystem itSystem, Model model) {
        AuditQuery query = AuditReaderFactory.get(em).createQuery().forRevisionsOfEntity(ITSystem.class, false, false);
        List<Object[]> queryList = query.getResultList();
        List<Object> audit = queryList.stream().map(item -> item[0]).collect(Collectors.toList());
        model.addAttribute("itSystem", itSystem);
        model.addAttribute("audit", audit);
        return "itsystems/form/systemHistory";
    }

    @GetMapping("{itSystem}/docs")
    public String itSystemGetDocs(@PathVariable ITSystem itSystem, Model model) {
        List<Object> docs = null;
        model.addAttribute("docs", docs);
        model.addAttribute("itSystem", itSystem);
        return "itsystems/form/systemDocs";
    }

}