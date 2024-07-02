package com.example.springboot.request;

import lombok.Data;

@Data
public class MessagePageRequest extends BaseRequest {
    private String mes;
    private String style;
    private Integer pid;
    private String isread;
}
