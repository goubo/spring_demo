package com.bobo.demo.user.entity.query;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author bo
 */
@Data
@Accessors(chain = true)
public class AuthQuery {
  private String userName;
  private String password;
}
