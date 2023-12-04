package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import com.example.jpa.repository.DemoRepository;
import com.example.jpa.model.Demo;

@Controller
public class HtmlController {
    @Autowired
    DemoRepository demoRepository;
    @GetMapping("/home")
    public String home(Model model){
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "map1");
        map.put("key2", "map2");
        map.put("key3", "map3");
        model.addAttribute("listObject", list);
        model.addAttribute("mapObject", map);
        List<Demo> demo = demoRepository.findAll();
        model.addAttribute("demoObject", demo);       
        return "html/home";
    }
    @GetMapping("/user")
    public String user(Model model){
        Map<String,Object> user = null;
        user = new HashMap<>();
        user.put("userId","z");
        user.put("userName","Zoo");
        user.put("userAge",25);
        model.addAttribute("user", user);
        return "html/user";
    }

    @GetMapping("/userlist")
    public String userList(Model model){
        List<Demo> data = demoRepository.findAll();
        model.addAttribute("userList", data);
        return "html/userlist";
    }
    @GetMapping("/mode")
    public String mode(Model model){
        Demo mode = demoRepository.findByUser("BBc");
        model.addAttribute("mode", mode);
        return "html/mode";
    }

    @GetMapping("/pagination")
    public String pagination(
        Model model,
        @RequestParam(defaultValue = "1") int page){
            int startPage = (page-1)/10 *10 +1;
            int endPage = startPage + 9;
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("page", page);
            return "html/pagination";
    }
    @GetMapping("/linkurl")
    public String linkUrl(
        Model model,
        @RequestParam(defaultValue = "1") int page){
            int startPage = (page-1)/10 *10 +1;
            int endPage = startPage + 9;
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("page", page);
            return "html/linkurl";
    } 
    

}
