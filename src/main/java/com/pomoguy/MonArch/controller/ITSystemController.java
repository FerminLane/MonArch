package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.dao.ITSystemRepo;
import com.pomoguy.MonArch.model.cmdb.ITSystem;
import com.pomoguy.MonArch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/itsystem")
//@PreAuthorize("hasAuthority('ADMIN')")
public class ITSystemController {

    @Autowired
    private ITSystemRepo itSystemRepo;

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
        itSystemRepo.save(itSystem);
        return "redirect:/itsystem/";
    }


    @GetMapping("{system}")
    public String itSystemForm(@PathVariable ITSystem system, Model model) {
        model.addAttribute("itsystem", system);
        return "itsystems/systemForm";
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
        system.setAuthor(user);
        system.setBuildingArea(buildingArea);
        system.setUpdateDateTime();



        itSystemRepo.save(system);
        return "redirect:/itsystem/" + system.getId();
    }

}