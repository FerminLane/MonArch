package com.pomoguy.MonArch.controller.archcatalog;



import com.pomoguy.MonArch.dao.archcatalog.BuildingAreaRepo;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.model.archcatalog.BuildingArea;
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
@RequestMapping("/buildingareas")
@PreAuthorize("hasAuthority('ADMIN')")
public class BuildingAreaController {

    @Autowired
    private BuildingAreaRepo buildingAreaRepo;

    @GetMapping
    public String buildingAreaGetList(Model model) {
        model.addAttribute("buildingareas", buildingAreaRepo.findByIsActual(true));
        return "archcatalog/buildAreaList";
    }

    @PostMapping("/add")
    public String buildingAreaAdd(@AuthenticationPrincipal User user,
                                  @RequestParam String buildingAreaName,
                                  @RequestParam String buildingAreaColor,
                                  Model model) {

        BuildingArea buildingArea = new BuildingArea(buildingAreaName, buildingAreaColor, user);
        buildingArea.setCreateDateTime();
        buildingArea.setUpdateDateTime();
        buildingArea.setUpdatedBy(user.getUsername());
        buildingArea.setActual(true);
        buildingAreaRepo.save(buildingArea);
        return "redirect:/buildingareas";
    }

    @PostMapping("/remove")
    public String buildingareaRemove(@AuthenticationPrincipal User user,
                                 @RequestParam String buildingareaId,
                                 Model model) {

        BuildingArea buildingArea = buildingAreaRepo.findById(buildingareaId).get();
        buildingArea.setUpdateDateTime();
        buildingArea.setUpdatedBy(user.getUsername());
        buildingArea.setActual(false);
        buildingAreaRepo.save(buildingArea);
        return "redirect:/buildingareas";
    }


}
