package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jpa.model.Demo;
import java.util.List;


@Repository
public interface DemoRepository extends JpaRepository<Demo,Long> {
    // List<Demo> findByUser(String user);
    Demo findByUser(String user);
    List<Demo> findBySeq(long seq);
}
