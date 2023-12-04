package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;
import com.example.jpa.model.Member;
import com.example.jpa.repository.MemberRepository;

@Controller
public class SessionController {
    @Autowired
    MemberRepository memberRepository;
    @GetMapping("/login")
    public String login(){
        return "html/login";
    }
    // @PostMapping("/login")
    // public String loginPost(Member member, HttpSession session){
    //     session.setAttribute("user", member);
    //     return "redirect:/main";
    // }
    @PostMapping("/login")
    public String loginPost(@RequestParam("memberId") String memberId,
                            @RequestParam("memberPw") String memberPw, 
                            HttpSession session){
        Member member;
        member = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
        int count = memberRepository.findByMemberPwAndMemberId(memberPw, memberId).size();
        if (count < 1){
            return "html/loginfail";
        }

        session.setAttribute("member",member);
        return "redirect:/main";

    }

    @GetMapping("/main")
    public String main(){
        return "/html/main";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping("/register")
    public String register(HttpSession session){
        Member member = new Member();
        member.setMemberId("admin");
        session.setAttribute("member",member);
        return "/html/register";
    }
    
    @PostMapping("/register")
    // @ResponseBody
    public String registerPost(@RequestParam("memberId") String memberId,
                               @RequestParam("memberPw") String memberPw,
                               @RequestParam("memberName") String memberName,
                               HttpSession session){
            int count = memberRepository.findByMemberId(memberId).size();
            Member member = new Member();
            member.setMemberId(memberId);
            member.setMemberName(memberName);
            member.setMemberPw(memberPw);
            // "insert into member(member_id, member_name, member_pw)
            // values(memberId, memberPw, memberName)"
            
            
            session.setAttribute("member", member);
            if( count > 0){
                System.out.println(member);     
                return "html/register";
            }
            memberRepository.save(member);


           return "redirect:/main";                     
    }
}


