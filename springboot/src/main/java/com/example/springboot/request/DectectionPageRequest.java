package com.example.springboot.request;

import lombok.Data;

@Data
public class DectectionPageRequest extends BaseRequest {
    private String style;
    private String status;
    private String pid;
}
