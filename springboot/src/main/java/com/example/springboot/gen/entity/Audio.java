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

    public class Audio implements Serializable {

private static final long serialVersionUID = 1L;

            /**
            * 音频名
            */
    private String name;

            /**
            * 父id
            */
    private Integer pid;

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
            * 路径
            */
    private String path;

            /**
            * 大小
            */
    private Double size;

            /**
             * 状态
             */
    private String status;

}
