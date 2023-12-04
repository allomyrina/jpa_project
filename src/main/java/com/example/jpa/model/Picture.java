package com.example.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Picture {
    @Id
    private int seq;
    private String userId;
    private String fileName;
    private String updateDate;
}
