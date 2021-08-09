package com.pomoguy.MonArch.controller;

import com.pomoguy.MonArch.dao.CommitteeRepo;
import com.pomoguy.MonArch.model.committee.Committee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public class CommitteeController {

    @Autowired
    private CommitteeRepo committeeRepo;




    @GetMapping("/committe")
    public String mainPage(Map<String, Object> model){
        Iterable<Committee> committees = committeeRepo.findAll();
        model.put("committes", committees);
        return "committe";
    }




}
