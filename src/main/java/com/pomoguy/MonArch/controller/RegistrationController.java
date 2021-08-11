package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.dao.UserRepo;
import com.pomoguy.MonArch.model.Role;
import com.pomoguy.MonArch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @PostMapping
    public String postRegistration(User user, Map<String, Object> model) {

        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.put("message","User exists!");
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
        }


        return "redirect:/main";
    }
}
