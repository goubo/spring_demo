package com.bobo.demo.common.base;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * @author h-vk
 * @describe 通用请求处理
 * @since 2020/6/25
 */
@SuppressWarnings("all")
public abstract class BaseController<T extends BaseDomain, S extends IBaseService<T>> {
  
  @Resource
  protected HttpServletRequest request;
  
  @Autowired
  protected S service;
  
  /**
   * 新增
   *
   * @param domain 领域模型
   * @return {@link ResponseResult}
   */
  @ApiOperation("创建数据")
  @PostMapping("/create")
  public ResponseResult create(@RequestBody T domain) {
    // 业务逻辑
    boolean created = service.create(domain);
    if (created) {
      return new ResponseResult("创建成功");
    }
    return new ResponseResult<Object>(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
  }
  
  /**
   * 删除
   *
   * @param id {@code Long}
   * @return {@link ResponseResult}
   */
  @DeleteMapping("/remove/{id}")
  @ApiOperation("删除数据")
  public ResponseResult remove(@PathVariable Long id) {
    // 业务逻辑
    boolean deleted = service.remove(id);
    if (deleted) {
      return new ResponseResult("删除成功");
    }
    return new ResponseResult<Object>(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
  }
  
  /**
   * 修改
   *
   * @param domain 领域模型
   * @return {@link ResponseResult}
   */
  @ApiOperation("修改数据")
  @PutMapping("/update")
  public ResponseResult update(@RequestBody T domain) {
    // 业务逻辑
    boolean updated = service.update(domain);
    if (updated) {
      return new ResponseResult("修改成功");
    }
    return new ResponseResult<Object>(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
  }
  
  /**
   * 获取
   *
   * @param id {@code Long}
   * @return {@link ResponseResult}
   */
  @ApiOperation("根据ID获取")
  @GetMapping("/{id}")
  public ResponseResult get(@PathVariable Long id) {
    T domain = service.get(id);
    return new ResponseResult<T>(domain);
  }
  
  /**
   * 分页
   *
   * @param current {@code int} 页码
   * @param size    {@code int} 数量
   * @return {@link ResponseResult}
   */
  @ApiOperation("分页查询")
  @GetMapping()
  public ResponseResult page(@RequestParam(required = false, defaultValue = "1") int current,
                             @RequestParam(required = false, defaultValue = "10") int size, @ModelAttribute T domain) {
    return new ResponseResult<Page<T>>(service.page(current, size, domain));
  }
  
  /**
   * 批量删除数据
   *
   * @param ids id 数组
   * @return {@link ResponseResult}
   */
  @ApiOperation("批量删除数据")
  @DeleteMapping("/remove-batch")
  public ResponseResult removeBatch(@RequestParam(value = "ids[]") String[] ids) {
    UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
    updateWrapper.set("is_deleted", true).set("update_time", LocalDateTime.now()).in("id", Arrays.asList(ids));
    if (service.update(updateWrapper)) {
      return new ResponseResult("批量删除成功");
    }
    return new ResponseResult<Object>(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
  }
  
  /**
   * 批量修改数据
   *
   * @param domainList 数据集合
   * @return {@link ResponseResult}
   */
  @ApiOperation("批量修改数据")
  @PutMapping("/update-batch")
  public ResponseResult updateBatch(@ModelAttribute List<T> domainList) {
    boolean updated = service.updateBatchById(domainList);
    if (updated) {
      return new ResponseResult("批量修改成功");
    }
    return new ResponseResult<Object>(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
  }
  
}
