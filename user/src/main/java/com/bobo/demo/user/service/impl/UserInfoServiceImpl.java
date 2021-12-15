package com.bobo.demo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bobo.demo.common.base.BaseServiceImpl;
import com.bobo.demo.user.entity.UserInfo;
import com.bobo.demo.user.entity.query.AuthQuery;
import com.bobo.demo.user.mapper.UserInfoMapper;
import com.bobo.demo.user.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobo
 * @since 2021-12-08
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
  
  @Override
  public UserInfo auth(AuthQuery authQuery) {
    LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserName,
        authQuery.getUserName())
      .eq(UserInfo::getUserPassword, authQuery.getPassword()).eq(UserInfo::getDeleted, 0);
    return super.getOne(lambdaQueryWrapper);
  }
}
