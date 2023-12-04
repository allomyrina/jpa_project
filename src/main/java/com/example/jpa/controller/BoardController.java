package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.jpa.repository.BoardRepository;
import com.example.jpa.model.Board;
import java.util.*;

@RestController
public class BoardController {
    @Autowired
    BoardRepository boardRepository;
    @GetMapping("/jpa/board")
    public List<Board> boradList(){
        // int count = boardRepository.findAll().size();
     
        return boardRepository.findByPostNumber(1);
    }
}
