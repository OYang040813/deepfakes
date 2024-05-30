package com.example.springboot.gen.entity;

    import java.time.LocalDateTime;
    import java.io.Serializable;
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

    public class Admin implements Serializable {

private static final long serialVersionUID = 1L;

            /**
            * 管理员
            */
    private String name;

            /**
            * 密码
            */
    private String keynum;

            /**
            * 创建时间
            */
    private LocalDateTime createtime;

            /**
            * 更新时间
            */
    private LocalDateTime updatetime;

            /**
            * 卡号
            */
    private String cardnum;

            /**
            * 状态
            */
    private Boolean status;


}
