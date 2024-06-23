package com.example.springboot.request;

import lombok.Data;

@Data
public class BookPageRequest extends BaseRequest{
    private String bookNo;
    private String name;
}
