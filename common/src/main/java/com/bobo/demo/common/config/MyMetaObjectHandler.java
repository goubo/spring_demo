package com.bobo.demo.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
  
  /**
   * 通用字段：创建时间
   */
  private static final String CREATE_TIME = "createTime";
  
  /**
   * 通用字段：更新时间
   */
  private static final String UPDATE_TIME = "updateTime";
  
  /**
   * 通用字段：已删除
   */
  private static final String DELETED = "deleted";
  
  /**
   * 通用字段：创建人ID
   */
  private static final String CREATE_ID = "createId";
  
  /**
   * 通用字段：更改人ID
   */
  private static final String UPDATE_ID = "updateId";
  
  @Override
  public void insertFill(MetaObject metaObject) {
    // 判断是否有相关字段
    boolean hasCreateTime = metaObject.hasSetter(CREATE_TIME);
    boolean hasUpdateTime = metaObject.hasSetter(UPDATE_TIME);
    boolean hasDeleted = metaObject.hasSetter(DELETED);
    boolean hasCreateId = metaObject.hasSetter(CREATE_ID);
    boolean hasUpdateId = metaObject.hasSetter(UPDATE_ID);
    // 有字段则自动填充
    if (hasCreateTime) {
      strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
    if (hasUpdateTime) {
      strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
    if (hasDeleted) {
      strictInsertFill(metaObject, DELETED, Boolean.class, false);
    }
  }
  
  @Override
  public void updateFill(MetaObject metaObject) {
    Object val = getFieldValByName(UPDATE_TIME, metaObject);
    if (val == null) {
      strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
  }
}
