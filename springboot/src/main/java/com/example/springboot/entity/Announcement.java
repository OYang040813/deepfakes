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

public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    /**
     * 级别
     */
    private String title;


    private String style;

    /**
     * 内容
     */
    private String mes;

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

}
