package com.bobo.demo.auth.entity.VO;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author bo
 */
@Data
@Accessors(chain = true)
@ToString
public class AuthVO {
  private UserInfoVO userInfo;
}
