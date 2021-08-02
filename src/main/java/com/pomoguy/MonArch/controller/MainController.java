package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.model.Message;
import com.pomoguy.MonArch.dao.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String mainPage(Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }


    @PostMapping
    public @ResponseBody String addMessage(@RequestParam String message, @RequestParam String tag, Map<String,Object> model){
        messageRepo.save(new Message(message,tag));
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public @ResponseBody String filter(@RequestParam String filter, Map<String,Object> model){
        model.put("messages", messageRepo.findByTag(filter));
        return "main";
    }
}
