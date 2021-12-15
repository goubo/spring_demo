package com.bobo.demo.user.service;

import com.bobo.demo.common.base.IBaseService;
import com.bobo.demo.user.entity.UserInfo;
import com.bobo.demo.user.entity.query.AuthQuery;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bobo
 * @since 2021-12-08
 */
public interface IUserInfoService extends IBaseService<UserInfo> {
  
  /**
   * 查询userInfo
   *
   * @param authQuery
   * @return
   */
  UserInfo auth(AuthQuery authQuery);
  
}
