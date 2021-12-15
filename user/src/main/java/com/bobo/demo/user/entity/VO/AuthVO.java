package com.bobo.demo.user.entity.VO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author bo
 */
@Data
@Accessors(chain = true)
public class AuthVO {
  private UserInfoVO userInfo;
}
