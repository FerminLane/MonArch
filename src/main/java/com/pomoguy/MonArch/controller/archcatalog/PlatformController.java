package com.pomoguy.MonArch.controller.archcatalog;


import com.pomoguy.MonArch.dao.PlatformRepo;
import com.pomoguy.MonArch.dao.VendorRepo;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.model.archcatalog.Platform;
import com.pomoguy.MonArch.model.archcatalog.Vendor;
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
        model.addAttribute("platforms", platformRepo.findByStatus("Active"));
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
        platform.setStatus("Active");
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
        platform.setStatus("Inactive");
        platformRepo.save(platform);
        return "redirect:/platforms";
    }


}
