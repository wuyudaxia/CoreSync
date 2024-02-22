package com.demo.testo.models;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
public class User {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    @Column(name = "username")
    private String username;

    @Setter
    @Getter
    @Column(name = "password")
    private String password;

    @Column(name = "permission")
    private String permission;

    public boolean getPermission(int view){
        if(view == 1 && permission.equals("admin"))
            return true;
        return false;
    }

    // 可以添加其他属性、构造函数、getter和setter等
}
