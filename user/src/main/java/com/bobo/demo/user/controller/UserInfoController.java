package com.bobo.demo.user.controller;

import com.bobo.demo.common.base.BaseController;
import com.bobo.demo.user.entity.UserInfo;
import com.bobo.demo.user.service.IUserInfoService;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2021-12-08
 */
@RestController
@RequestMapping("/user/user-info")
@ApiModel(value = "UserInfo", description = "")
public class UserInfoController extends BaseController<UserInfo, IUserInfoService> {

}
