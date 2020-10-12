package com.easyfood.files.repository;

import com.easyfood.files.persistence.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

    Optional<FileDB> findByUsername(String username);
    FileDB findFirstByUsername(String username);

}