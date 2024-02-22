package com.demo.testo.controllers;

import com.demo.testo.models.User;
import com.demo.testo.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String loginForm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            return "welcome";
        }
        return "loginIndex";
    }

    @GetMapping("/logOff")
    public String logOff(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            session.removeAttribute("username");
        }
        return "loginIndex";
    }

    @GetMapping("/l/welcome")
    public String welcomeForm() {
        return "welcome";
    }

    @PostMapping("/loginPost")
    public String loginPost(Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userRepository.findByUsername(username);

        if (session.getAttribute("username") != null) {
            return "welcome";
        }
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("username", username); // 使用用户输入的用户名
            return "welcome"; // 登录成功后的页面
        } else {
            model.addAttribute("error", "用户名或密码错误，请重试。"); // 添加自定义错误消息
            return "loginIndex"; // 登录失败后返回登录页面
        }
    }

}
