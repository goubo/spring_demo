package com.bobo.demo.common.base;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
      return ResponseResult.success("创建成功");
    }
    return ResponseResult.failure();
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
      return ResponseResult.success("删除成功");
    }
    return ResponseResult.failure();
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
      return ResponseResult.success("修改成功");
    }
    return ResponseResult.failure();
  }
  
  /**
   * 获取
   *
   * @param id {@code Long}
   * @return {@link ResponseResult}
   */
  @ApiOperation("根据ID获取")
  @GetMapping("/get/{id}")
  public ResponseResult get(@PathVariable Long id) {
    T domain = service.get(id);
    return ResponseResult.success(domain);
  }
  
  /**
   * 分页
   *
   * @param current {@code int} 页码
   * @param size    {@code int} 数量
   * @return {@link ResponseResult}
   */
  @ApiOperation("分页查询")
  @GetMapping("/page")
  public ResponseResult page(@RequestParam int current, @RequestParam int size, @ModelAttribute T domain) {
    IPage<?> page = service.page(current, size, domain);
    return ResponseResult.success(page);
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
      return ResponseResult.success("批量删除成功");
    }
    return ResponseResult.failure();
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
      return ResponseResult.success("批量修改成功");
    }
    return ResponseResult.failure();
  }
  
}
