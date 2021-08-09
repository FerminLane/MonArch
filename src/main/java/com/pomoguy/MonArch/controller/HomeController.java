package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.dao.MessageRepo;
import com.pomoguy.MonArch.model.Message;
import com.pomoguy.MonArch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String mainPage(@RequestParam(required = false,defaultValue = "") String filter, Map<String, Object> model){
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);
        model.put("filter", filter);

        return "home";
    }


    @PostMapping
    public String addMessage(@AuthenticationPrincipal User user, @RequestParam String message, @RequestParam String tag, Map<String,Object> model){
        messageRepo.save(new Message(message,tag, user));
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "home";
    }
}