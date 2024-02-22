package com.demo.testo.repositories;

import com.demo.testo.models.File;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
