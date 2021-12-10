package com.bobo.demo.login.client;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.login.entity.VO.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bobo.demo.common.constant.ModuleInfoConstant.USER_INFO_MODULE_NAME;

/**
 * @author bo
 */
@FeignClient(USER_INFO_MODULE_NAME)
public interface UserClient {
  /**
   * 分页查询用户
   *
   * @param current 页数
   * @param size    条数
   * @return 返回 page 对象
   */
  @GetMapping(value = "/user-info")
  ResponseResult<Page<UserInfoVO>> page(@RequestParam(defaultValue = "1", value = "current") int current,
                                        @RequestParam(defaultValue = "10", value = "size") int size);
  
}
