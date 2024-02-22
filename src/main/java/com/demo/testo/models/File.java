package com.demo.testo.models;

import jakarta.persistence.*;
import java.sql.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String fileName;
    private String fileSize;
    private String uploader;
    private Date modificationDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getUploader() {
        return uploader;
    }

    public File(){}
    public File(Long id, String fileName, String fileSize, String uploader, Date modificationDate){
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.uploader = uploader;
        this.modificationDate = modificationDate;
    }
}
