package com.demo.testo.controllers;

import com.demo.testo.models.File;
import com.demo.testo.repositories.UserRepository;
import com.demo.testo.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
public class FileController {

    private final FileService fileService;

    private UserRepository userRepository;

    @Autowired
    public FileController(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }


    @GetMapping("/l/files")
    public String getAllFiles(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            return "redirect:/loginIndex"; // 如果未登录，重定向到登录页
        }

        List<File> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        model.addAttribute("users", userRepository);
        return "file"; // 返回文件列表视图
    }

    @PostMapping("/l/files/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || file.getOriginalFilename() == null) {
            return "redirect:/loginIndex";
        }

        if (!file.isEmpty()) {
            try {
                // 获取文件名
                String fileName = file.getOriginalFilename();

                // 获取文件大小
                long fileSize = file.getSize();

                // 获取上传者，这里可以从 session 中获取用户名
                String uploader = (String) session.getAttribute("username");

                // 处理文件保存逻辑，这里假设将文件保存到一个指定目录
                // 你可以使用 FileService 来处理文件保存逻辑
                String uploadDirectory = "C:/Desktop/CS";
                java.io.File newFile = new java.io.File(uploadDirectory + java.io.File.separator + fileName);
                file.transferTo(newFile);
                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                // 更新数据库中的文件信息
                // 你可以使用 FileService 来更新数据库
                 fileService.saveFile(fileName, fileSize, Date.valueOf(dateFormat.format(calendar.getTime())), uploader);

                // 返回适当的重定向或视图，例如重定向到文件列表页
                return "redirect:/l/files";
            } catch (Exception e) {
                e.printStackTrace();
                // 处理上传过程中的异常
                // 可以返回一个错误视图或重定向到上传失败页面
                return "redirect:/uploadError";
            }
        } else {
            // 如果没有上传文件，可以返回一个错误视图或重定向到上传失败页面
            return "redirect:/l/files";
        }
    }

    // 可以添加其他文件相关的HTTP请求处理方法，如上传文件、获取单个文件、删除文件等，也需要进行登录检查
}
