package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jpa.model.UploadFile;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadFile, Integer> {
     UploadFile findByUuid(String uid);
}
