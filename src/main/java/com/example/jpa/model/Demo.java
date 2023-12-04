package com.example.jpa.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;

@Data
@Entity
public class Demo {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private long seq;
    private String user;
}
