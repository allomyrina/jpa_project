package com.example.jpa.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.*;
import com.example.jpa.repository.FileUploadRepository;
import com.example.jpa.model.UploadFile;

import com.example.jpa.util.getCurrentTime;

import jakarta.servlet.http.HttpServletRequest;
import com.example.jpa.model.Picture;
import com.example.jpa.repository.PictureRepository;
import com.example.jpa.model.FileInfo;


@Controller
public class UploadController {
    @Autowired
    FileUploadRepository fileUploadRepository;
    @Autowired
    PictureRepository pictureRepository;

    @GetMapping("/upload1")
    public String upload1(){
        return "html/upload1";
    }
  
    @PostMapping("/upload1")
    @ResponseBody
    public String upload1Post(MultipartHttpServletRequest mRequest){

        String result = "";
        MultipartFile mFile = mRequest.getFile("file");
        String oName = mFile.getOriginalFilename();
        result += oName + "<br>" + mFile.getSize();
        
        String saveFolder = "C:/data/";
        File saveFile = new File(saveFolder+oName);
        try {
            mFile.transferTo(saveFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/upload2")
    public String upload2(){
        return "/html/upload2";
    }
    @PostMapping("/upload2")
    @ResponseBody
    // upload1Post(MultipartHttpServletRequest mRequest)
    public String upload2Post(@RequestParam("file") MultipartFile mFile,
                              @RequestParam("memberId") String memberId,
                              HttpServletRequest request){
        UploadFile uploadFile = new UploadFile();
        String regDate = getCurrentTime.getTime();
        String uid = UUID.randomUUID().toString();
        String oName = mFile.getOriginalFilename();
        String secretFlag = "";
        if (request.getParameter("secretFlag") == null){
            secretFlag = "1";
        } else {
            secretFlag = request.getParameter("secretFlag");
        }
        

        System.out.println(secretFlag);

        String saveFolder = "C:/work/jpa/src/main/resources/static/image/";
        // String saveFolder = "C:/data/";
        File saveFile = new File(saveFolder+uid);
        try {
            int seq = fileUploadRepository.findAll().size()+1;
            uploadFile.setOriginalFileName(oName);
            uploadFile.setUuid(uid);
            uploadFile.setSeq(seq);
            uploadFile.setRegDate(regDate);
            uploadFile.setMemberId(memberId);
            uploadFile.setSecretFlag(secretFlag);
            fileUploadRepository.save(uploadFile);
            mFile.transferTo(saveFile);
           
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return oName+" 성공적으로 저장 되었습니다";
    }

    @GetMapping("/pictureupload")
    public String pictureUpload(){
        return "html/pictureupload";
    }
    @PostMapping("/pictureupload")
    @ResponseBody
    public String pictureUploadPost(@RequestParam("file") MultipartFile mFile,
                                    @RequestParam("memberId") String memberId)
    {
        Picture picture = new Picture();       
        String updateDate = getCurrentTime.getTime();
        String fileName = mFile.getOriginalFilename();
        String memberFileName = memberId+"_"+fileName;
        String saveFolder = "C:/work/jpa/src/main/resources/static/image/";
        File saveFile = new File(saveFolder+memberFileName);
        int seq = pictureRepository.findAll().size() + 1;
        try {
            mFile.transferTo(saveFile);
            picture.setSeq(seq);
            picture.setFileName(memberFileName);
            picture.setUpdateDate(updateDate);
            picture.setUserId(memberId);
            pictureRepository.save(picture);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        } 

        return "<div><img src='/image/"+memberFileName+"'><div>";
    }

    @GetMapping("/upload3")
    public String upload3(){
        return "/html/upload3";
    }
    @PostMapping("/upload3")
    @ResponseBody
    public String upload3Post(@ModelAttribute FileInfo info){
        String result = "";
        String oName = info.getFile().getOriginalFilename();
        Long size = info.getFile().getSize();
        String type = info.getFile().getContentType();
        result += oName+":"+size+":"+type;
        return result;
    }

    @GetMapping("/upload4")
    public String upload4(){
        return "/html/upload4";
    }
    @PostMapping("/upload4")
    @ResponseBody
    public String upload4Post(@RequestParam("file") MultipartFile[] mFiles,
                              @RequestParam("memberId") String memberId
    ){
        UploadFile uploadFile = new UploadFile();
        String saveFolder = "C:/data/";
        String currentDate = getCurrentTime.getTime();

        String result="";
        for (MultipartFile mFile : mFiles){
            String uid = UUID.randomUUID().toString();
            String oName = mFile.getOriginalFilename();
            int seq = fileUploadRepository.findAll().size()+1;
            File saveFile = new File(saveFolder+uid);
            uploadFile.setMemberId(memberId);
            uploadFile.setOriginalFileName(oName);
            uploadFile.setRegDate(currentDate);
            uploadFile.setSeq(seq);
            uploadFile.setUuid(uid);
            try {
                mFile.transferTo(saveFile);
                fileUploadRepository.save(uploadFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            } 
            result += "<p>"+oName+"</p>";
        }
        return result;
    }






}
