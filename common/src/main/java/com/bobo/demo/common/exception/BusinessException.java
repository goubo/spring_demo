package com.bobo.demo.common.exception;

import com.bobo.demo.common.enums.ResponseCode;

import java.io.Serial;

/**
 * @author h-vk
 * @describe 自定义业务异常
 * @since 2020/9/2
 */
public class BusinessException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 6019857475070672120L;

  private final ResponseCode responseCode;

  public BusinessException() {
    this(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
  }

  public BusinessException(ResponseCode responseCode) {
    this(responseCode, responseCode.getMsg());
  }

  public BusinessException(String message) {
    this(ResponseCode.ERROR_INTERNAL_SERVER_ERROR, message);
  }

  public BusinessException(ResponseCode responseCode, String message) {
    super(message);
    this.responseCode = responseCode;
  }

  public ResponseCode getResponseCode() {
    return responseCode;
  }

}
