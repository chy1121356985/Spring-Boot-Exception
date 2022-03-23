package com.renderg.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常类
 */
@Data
@AllArgsConstructor  //有参数构造器
@NoArgsConstructor   //生成无参数构造
public class GuliException extends RuntimeException {

    //状态码
    private Integer code;
    //输出消息
    private String msg;

}
