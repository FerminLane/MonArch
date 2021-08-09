package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.model.Message;
import com.pomoguy.MonArch.dao.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping
    public String healthCheck(Map<String, Object> model){

        return "main";
    }


    @GetMapping("/main")
    public String mainPage(Map<String, Object> model){

        return "main";
    }

}
