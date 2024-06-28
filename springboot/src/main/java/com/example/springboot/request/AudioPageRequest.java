package com.example.springboot.request;

import lombok.Data;

@Data
public class AudioPageRequest extends BaseRequest {
    private String name;
    private String id;
}
