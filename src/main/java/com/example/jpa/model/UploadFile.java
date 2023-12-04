package com.example.jpa.model;

import org.springframework.stereotype.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UploadFile {
    @Id
    private int seq;
    private String originalFileName;
    private String uuid;
    private String memberId;
    private String secretFlag;
    private String regDate;
}
