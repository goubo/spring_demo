package com.bobo.demo.common.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author h-vk
 * @describe 通用领域模型
 * @since 2020/6/25
 */
@Data
@Accessors(chain = true)
public abstract class BaseDomain<T> implements Serializable {
  
  public static final String USER_NAME = "id";
  public static final String CREATE_TIME = "create_time";
  public static final String UPDATE_TIME = "update_time";
  public static final String IS_DELETED = "is_deleted";
  private static final long SERIALVERSIONUID = -6912501145172883332L;
  /**
   * 主键 <br>
   * 生成策略：ASSIGN_ID
   */
  @ApiModelProperty(value = "主键 -> 生成策略：ASSIGN_ID")
  @TableId(value = "`id`", type = IdType.ASSIGN_ID)
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long id;
  
  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间 ")
  @TableField(value = "`create_time`", fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
  
  /**
   * 修改时间
   */
  @ApiModelProperty(value = "修改时间 ")
  @TableField(value = "`update_time`", fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;
  
  /**
   * 是否删除 0: 否 1: 是
   */
  @ApiModelProperty(value = "是否删除 0: 否 1: 是 ")
  @TableLogic
  @TableField(value = "`is_deleted`", fill = FieldFill.INSERT)
  private Boolean deleted;
  
  
  /**
   * @return
   */
  public Serializable pkVal() {
    return this.id;
  }
}
