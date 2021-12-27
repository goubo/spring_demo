package com.bobo.demo.common.entity.auth;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author bo
 */
@Data
@Accessors(chain = true)
@ToString
public class AuthVO implements Serializable {
  private AuthUserInfoVO userInfo;
}
