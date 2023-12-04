package com.example.jpa.model;
import java.util.*;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Board {
    @Id
    int postNumber;
    int userId;
    String title;
    String content;
    int viewCount;
    
    @OneToMany(mappedBy="board")
    List<User> users = new ArrayList<>();
    // User user;
}
