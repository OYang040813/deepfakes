package com.example.springboot.request;

import lombok.Data;

@Data
public class VidioPageRequest extends BaseRequest {
    private String name;
    private String id;
}
