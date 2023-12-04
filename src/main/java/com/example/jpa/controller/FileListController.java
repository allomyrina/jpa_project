package com.example.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.jpa.model.UploadFile;
import com.example.jpa.repository.FileUploadRepository;
import java.util.*;


@Controller
public class FileListController {
    @Autowired
    FileUploadRepository fileUploadRepository;
    @GetMapping("/filelist")
    public String fileList(Model model){
        model.addAttribute("uploadFiles", fileUploadRepository.findAll());
        return "html/filelist";
    }
}
