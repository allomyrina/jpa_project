package com.example.jpa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.jpa.repository.FileUploadRepository;

@Controller
public class DownLoadController {
    @Autowired
    FileUploadRepository fileUploadRepository;
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("uid") String uid) throws Exception {

        String downLoadFolder = "C:/data/";
        String oName = fileUploadRepository.findByUuid(uid).getOriginalFileName();
        File file = new File(downLoadFolder+uid);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
              .header("content-disposition","filename="+URLEncoder.encode(oName,"utf-8"))
              .contentLength(file.length())
              .contentType(MediaType.parseMediaType("application/octet-stream"))
              .body(resource);
    } 
}
