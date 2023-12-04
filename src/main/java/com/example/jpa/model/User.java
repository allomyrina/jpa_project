package com.example.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    int userId;
    @JsonIgnore
    String password;
    String name;
    @ManyToOne
    
    @JoinColumn(name="post_number")
    @JsonIgnore
    Board board;
}
