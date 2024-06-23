package com.example.springboot.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String name = "";
    private String keynum = "";
}
