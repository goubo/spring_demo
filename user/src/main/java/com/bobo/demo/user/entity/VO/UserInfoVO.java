package com.bobo.demo.user.entity.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author bo
 */
@Data
@Accessors(chain = true)
public class UserInfoVO {
  private String userName;
  private String userEmail;
  private String userAddress;
  private Long id;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;
  private Boolean deleted;
}
