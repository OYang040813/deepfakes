package com.example.springboot.request;

import lombok.Data;

@Data
public class VideoPageRequest extends BaseRequest {
    private String name;
    private String id;
}
