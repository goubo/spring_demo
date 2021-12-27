package com.bobo.demo.auth.client;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.demo.auth.entity.VO.AuthParam;
import com.bobo.demo.common.entity.auth.AuthUserInfoVO;
import com.bobo.demo.common.entity.auth.AuthVO;
import com.bobo.demo.common.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  ResponseResult<Page<AuthUserInfoVO>> page(@RequestParam(defaultValue = "1", value = "current") int current,
                                            @RequestParam(defaultValue = "10", value = "size") int size);
  
  /**
   * 验证密码
   *
   * @param authParam
   * @return
   */
  @PostMapping(value = "/user-info/auth")
  ResponseResult<AuthVO> check(@RequestBody AuthParam authParam);
  
}
