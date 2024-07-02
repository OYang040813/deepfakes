package com.example.springboot.request;

import lombok.Data;

@Data
public class AnnouncementPageRequest extends BaseRequest {
    private String mes;
    private String title;
}
