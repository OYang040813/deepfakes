package com.example.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LoginDTO {
    private Integer id;
    private String name;
    private String keynum;
    private String cardnum;
    private String token;
    private Integer isAuth;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updatetime;
}
