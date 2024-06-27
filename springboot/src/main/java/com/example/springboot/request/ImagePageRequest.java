package com.example.springboot.request;

import lombok.Data;

@Data
public class ImagePageRequest extends BaseRequest {
    private String name;
    private Integer pid;
}
