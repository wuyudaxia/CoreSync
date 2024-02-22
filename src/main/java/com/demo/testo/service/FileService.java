package com.demo.testo.service;

import com.demo.testo.models.File;
import com.demo.testo.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository; // 文件数据仓库

    public List<File> getAllFiles() {
        return fileRepository.findAll(); // 查询所有文件信息
    }

    public void saveFile(String filename, long filesize, Date date, String username){
        File file = new File(fileRepository.count() + 1, filename,convertBitsToStorage(filesize),username, date);
        fileRepository.saveAndFlush(file);
    }

    public static String convertBitsToStorage(long bits) {
        double bytes = bits;
        double kilobytes = bytes / 1024;
        double megabytes = kilobytes / 1024;
        double gigabytes = megabytes / 1024;
        double terabytes = gigabytes / 1024;

        if (terabytes >= 1) {
            return String.format("%.2f TB", terabytes);
        } else if (gigabytes >= 1) {
            return String.format("%.2f GB", gigabytes);
        } else if (megabytes >= 1) {
            return String.format("%.2f MB", megabytes);
        } else if (kilobytes >= 1) {
            return String.format("%.2f KB", kilobytes);
        } else {
            return String.format("%.2f bytes", bytes);
        }
    }
}
