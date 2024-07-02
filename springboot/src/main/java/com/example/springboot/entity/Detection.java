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

public class Detection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    /**
     * Creation time
     */
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    /**
     * Update time
     */
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;


    /**
     * 检测结果
     */
    private Double result;

    /**
     * 检测类型
     */
    private String style;

    /**
     * 检测所用时间
     */
    private String time;

    /**
     * 父id
     */
    private Integer pid;

    private String status;

    private String path;

    private String cardnum;

    private String donepath;

}
