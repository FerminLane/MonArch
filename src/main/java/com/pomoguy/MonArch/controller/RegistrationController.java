package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.dao.settings.UserRepo;
import com.pomoguy.MonArch.model.Role;
import com.pomoguy.MonArch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;


@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @PostMapping
    public String postRegistration(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }
        if (user.getPassword() != null && !user.getPassword().equals(user.getPasswordConf())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            model.addAttribute("passwordConfError", "Пароли не совпадают");
            return "registration";
        }


        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }


}
