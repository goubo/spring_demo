package com.bobo.demo.auth.entity.VO;

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
