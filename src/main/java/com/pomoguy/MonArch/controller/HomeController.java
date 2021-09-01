package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.config.MinIOConfig;
import com.pomoguy.MonArch.dao.MessageRepo;
import com.pomoguy.MonArch.model.Message;
import com.pomoguy.MonArch.model.User;
import com.pomoguy.MonArch.service.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    MinIOConfig minIOConfig = new MinIOConfig();

    @GetMapping("/home")
    public String mainPage(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> model) {
        Iterable<Message> texts;

        if (filter != null && !filter.isEmpty()) {
            texts = messageRepo.findByTag(filter);
        } else {
            texts = messageRepo.findAll();
        }

        model.put("texts", texts);
        model.put("filter", filter);

        return "home";
    }


    @PostMapping("/home")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             @RequestParam String tag,
                             Map<String, Object> model,
                             @RequestParam("file") MultipartFile file) throws Exception {
        Message message = new Message(text, tag, user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File((uploadPath));
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }


            String fileKey = UUID.randomUUID().toString().substring(0,4);
            String resultFileName = fileKey + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            message.setFileName(resultFileName);

            //MinIOService minIOService = new MinIOService(minIOConfig.minioClient());
            //minIOService.upload(file,fileKey);

        }
        messageRepo.save(message);
        Iterable<Message> texts = messageRepo.findAll();
        model.put("texts", texts);




        return "home";
    }
}