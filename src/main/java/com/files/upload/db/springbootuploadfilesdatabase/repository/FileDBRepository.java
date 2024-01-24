package com.files.upload.db.springbootuploadfilesdatabase.repository;

import com.files.upload.db.springbootuploadfilesdatabase.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}