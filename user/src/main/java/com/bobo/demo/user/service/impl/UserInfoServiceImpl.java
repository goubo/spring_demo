package com.bobo.demo.user.service.impl;

import com.bobo.demo.common.base.BaseServiceImpl;
import com.bobo.demo.user.entity.UserInfo;
import com.bobo.demo.user.mapper.UserInfoMapper;
import com.bobo.demo.user.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-12-06
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
