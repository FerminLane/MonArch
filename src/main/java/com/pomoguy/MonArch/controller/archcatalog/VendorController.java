package com.pomoguy.MonArch.controller.archcatalog;



import com.pomoguy.MonArch.dao.archcatalog.VendorRepo;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.model.archcatalog.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vendors")
@PreAuthorize("hasAuthority('ADMIN')")
public class VendorController {

    @Autowired
    private VendorRepo vendorRepo;

    @GetMapping
    public String vendorGetList(Model model) {
        model.addAttribute("vendors", vendorRepo.findByIsActual(true));
        return "archcatalog/vendors/vendorList";
    }

    @PostMapping("/add")
    public String vendorAdd(@AuthenticationPrincipal User user,
                            @RequestParam String vendorName,
                            Model model) {

        Vendor vendor = new Vendor(vendorName, user);
        vendor.setCreateDateTime();
        vendor.setUpdateDateTime();
        vendor.setUpdatedBy(user.getUsername());
        vendor.setActual(true);
        vendorRepo.save(vendor);
        return "redirect:/vendors";
    }

    @PostMapping("/remove")
    public String vendorRemove(@AuthenticationPrincipal User user,
                               @RequestParam String vendorId,
                               Model model) {

        Vendor vendor = vendorRepo.findById(vendorId).get();
        vendor.setUpdateDateTime();
        vendor.setUpdatedBy(user.getUsername());
        vendor.setActual(false);
        vendorRepo.save(vendor);
        return "redirect:/vendors";
    }


}
