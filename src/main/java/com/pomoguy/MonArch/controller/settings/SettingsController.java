package com.pomoguy.MonArch.controller.settings;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/settings")
//@PreAuthorize("hasAuthority('ADMIN')")
public class SettingsController {

    @GetMapping
    public String healthCheck(Map<String, Object> model){

        return "buildingAreaList";
    }


}
