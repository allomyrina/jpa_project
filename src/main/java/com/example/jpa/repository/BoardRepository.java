package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jpa.model.Board;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Integer>{
    List<Board> findByPostNumber(int postNumber);
}
