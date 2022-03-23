package com.renderg.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenhy
 * @since 2022-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SlaveInfo")
@ApiModel(value="SlaveInfo对象", description="")
public class SlaveInfo implements Serializable {

    private String id;

    private Long arm;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
