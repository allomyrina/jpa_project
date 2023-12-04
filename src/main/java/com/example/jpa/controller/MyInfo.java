package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jpa.model.Member;
import com.example.jpa.model.Picture;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.PictureRepository;

@Controller
public class MyInfo {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PictureRepository pictureRepository;
    @GetMapping("/myinfo")
    public String myInfo(
        Model model,
        @RequestParam("memberId") String memberId){
        Member member;
        Picture picture;
        member = memberRepository.findByMemberId(memberId).get(0);
        picture = pictureRepository.findByUserId(memberId).get(0);
        model.addAttribute("member", member);
        model.addAttribute("picture", picture);
        
        System.out.println(model);
        
        return "html/myinformation";
    }    
}
