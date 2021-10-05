package com.pomoguy.MonArch.controller.archcatalog;


import com.pomoguy.MonArch.dao.archcatalog.PlatformRepo;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.model.archcatalog.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/platforms")
@PreAuthorize("hasAuthority('ADMIN')")
public class PlatformController {

    @Autowired
    private PlatformRepo platformRepo;

    @GetMapping
    public String platformGetList(Model model) {
        model.addAttribute("platforms", platformRepo.findByIsActual(true));
        return "archcatalog/platforms/platformList";
    }

    @PostMapping("/add")
    public String platformAdd(@AuthenticationPrincipal User user,
                            @RequestParam String platformName,
                            Model model) {

        Platform platform = new Platform(platformName, user);
        platform.setCreateDateTime();
        platform.setUpdateDateTime();
        platform.setUpdatedBy(user.getUsername());
        platform.setActual(true);
        platformRepo.save(platform);
        return "redirect:/platforms";
    }

    @PostMapping("/remove")
    public String platformRemove(@AuthenticationPrincipal User user,
                               @RequestParam String platformId,
                               Model model) {

        Platform platform = platformRepo.findById(platformId).get();
        platform.setUpdateDateTime();
        platform.setUpdatedBy(user.getUsername());
        platform.setActual(false);
        platformRepo.save(platform);
        return "redirect:/platforms";
    }

    @PostMapping("/edit")
    public String platformEdit(@AuthenticationPrincipal User user,
                                 @RequestParam String platformId,
                                 Model model) {

        Platform platform = platformRepo.findById(platformId).get();
        platform.setUpdateDateTime();
        platform.setUpdatedBy(user.getUsername());
        platform.setActual(false);
        platformRepo.save(platform);
        return "redirect:/platforms";
    }


}
