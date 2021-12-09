package com.bobo.demo.common.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.exception.BusinessException;

import java.time.LocalDateTime;

/**
 * @author h-vk
 * @describe 通用业务实现
 * @since 2020/6/25
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseDomain>
  extends ServiceImpl<M, T> implements IBaseService<T> {
  
  /**
   * 检查字段：ID
   */
  protected static final String ID = "id";
  
  @Override
  public boolean create(T domain) {
    return super.save(domain);
  }
  
  @Override
  public boolean remove(Long id) {
    if (checkId(id)) {
      UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
      updateWrapper.set("is_deleted", true).set("update_time", LocalDateTime.now()).eq("id", id);
      return super.update(updateWrapper);
    }
    return false;
  }
  
  @Override
  public boolean update(T domain) {
    try {
      if (checkId(domain.getId())) {
        return super.updateById(domain);
      }
      return false;
    } catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
  }
  
  @Override
  public T get(Long id) {
    T domain = super.getById(id);
    if (null == domain) {
      throw new BusinessException(ResponseCode.NOT_FOUND);
    }
    return domain;
  }
  
  @Override
  public Page<T> page(int current, int size, T domain) {
    Page<T> page = new Page<>(current, size);
    LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>(domain);
    return super.page(page, wrapper);
  }
  
  /**
   * 检查 ID 是否存在
   *
   * @param id {@code Long} ID
   * @return {@code boolean} ID 不存在则抛出异常
   */
  protected boolean checkId(Long id) {
    if (!checkUniqueness(ID, id)) {
      throw new BusinessException(ResponseCode.PARAM_NOT_FOUND);
    }
    return true;
  }
  
  protected boolean checkUniqueness(String column, Object value) {
    QueryWrapper<T> wrapper = new QueryWrapper<>();
    wrapper.eq(column, value);
    return super.count(wrapper) > 0;
  }
}
