package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author oy
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;
    /**
     * 级别
     */
    private String style;

    /**
     * 内容
     */
    private String mes;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updatetime;

    /**
     * 卡号
     */
    private String isread;

}
