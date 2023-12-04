package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.jpa.repository.DemoRepository;
import com.example.jpa.model.Demo;
import java.util.*;

@RestController
public class DBController {
    @Autowired
    DemoRepository demoRepository;
    @GetMapping("/")
    public List<Demo> demoSelect(){
        return demoRepository.findAll();
    }
    @GetMapping("/save")
    public Demo demoInsert(){
        Demo data = new Demo();
        data.setSeq(4);
        data.setUser("이소운");
        demoRepository.save(data);
        
        return data;
    }

    @GetMapping("/delete")
    public String demoDelete(){
        Demo data = new Demo();
        data.setSeq(4);
        demoRepository.delete(data);
        return "지워졌습니다";
    }

}
