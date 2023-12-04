package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jpa.model.Picture;
import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer>{
    List<Picture> findByUserId(String userId);
}
