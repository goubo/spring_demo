package com.bobo.demo.common.response;

import com.bobo.demo.common.enums.ResponseCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author bo
 */
@Data
@Accessors(chain = true)
public class ResponseResult {
  private String msg;
  private int code;
  private int httpCode;
  private Object object;
  
  private ResponseResult(ResponseCode responseCode) {
    this.code = responseCode.getCode();
    this.httpCode = responseCode.getHttpCode();
    this.msg = responseCode.getMsg();
  }
  
  public static ResponseResult success(String msg) {
    return success().setMsg(msg);
  }
  
  public static ResponseResult success() {
    return new ResponseResult(ResponseCode.SUCCESS);
  }
  
  public static ResponseResult success(Object object) {
    return success().setObject(object);
  }
  
  public static ResponseResult success(ResponseCode responseCode) {
    return new ResponseResult(responseCode);
  }
  
  public static ResponseResult success(ResponseCode responseCode, String msg) {
    return new ResponseResult(responseCode).setMsg(msg);
  }
  
  public static ResponseResult failure(String msg) {
    return failure().setMsg(msg);
  }
  
  public static ResponseResult failure() {
    return new ResponseResult(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
  }
  
  public static ResponseResult failure(ResponseCode responseCode) {
    return new ResponseResult(responseCode);
  }
  
  public static ResponseResult failure(ResponseCode responseCode, String msg) {
    return new ResponseResult(responseCode).setMsg(msg);
  }
  
  
}
