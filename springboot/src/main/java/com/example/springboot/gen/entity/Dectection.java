package com.example.springboot.gen.entity;

    import java.time.LocalDateTime;
    import java.io.Serializable;
    import java.util.Date;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* 
* @author oy
*
*/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

    public class Dectection implements Serializable {

private static final long serialVersionUID = 1L;

    private String name;

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
            * 检测结果
            */
    private Double result;

            /**
            * 检测类型
            */
    private Integer style;

            /**
            * 检测所用时间
            */
    private String time;

            /**
            * 父id
            */
    private Integer pid;


}
