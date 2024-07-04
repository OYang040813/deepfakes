package com.example.springboot.request;

import lombok.Data;

@Data
public class DectectionPageRequest extends BaseRequest {
    private String cardnum;
    private Integer pid;
}
