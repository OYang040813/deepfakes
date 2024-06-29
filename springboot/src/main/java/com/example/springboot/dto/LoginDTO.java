package com.example.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LoginDTO {
    private Integer id;
    private String name;
    private String sex;
    private String age;
    private String address;
    private String keynum;
    private String email;
    private String cover;
    private String phone;
    private String cardnum;
    private String token;
    private Integer isAuth;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updatetime;
}
