package com.example.springboot.request;

import lombok.Data;

@Data
public class CategoryPageRequest extends BaseRequest{
    private String remark;
    private String name;
}
