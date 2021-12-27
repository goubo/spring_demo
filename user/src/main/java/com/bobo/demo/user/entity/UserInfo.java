package com.bobo.demo.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bobo.demo.common.base.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * <p>
 *
 * </p>
 *
 * @author bobo
 * @since 2021-12-08
 */
@Data
@Accessors(chain = true)
@TableName("user_info")
@ApiModel(value = "UserInfo", description = "")
public class UserInfo extends BaseDomain<UserInfo> {
  public static final String USER_NAME = "user_name";
  public static final String USER_PASSWORD = "user_password";
  public static final String USER_EMAIL = "user_email";
  public static final String USER_ADDRESS = "user_address";
  @Serial
  private static final long serialVersionUID = 1L;
  
  @ApiModelProperty("用户名")
  private String userName;
  
  @ApiModelProperty("密码")
  private String userPassword;
  
  @ApiModelProperty("邮箱")
  private String userEmail;
  
  @ApiModelProperty("地址")
  private String userAddress;
  
}
