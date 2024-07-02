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

public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 图片名
     */
    private String name;

    /**
     * 父id
     */
    private Integer pid;

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
     * 大小
     */
    private Double size;

    /**
     * 路径
     */
    private String path;

    /**
     * 状态
     */
    private String status;

    private String localpath;
}
