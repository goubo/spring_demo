package com.bobo.demo.common.response;

import com.bobo.demo.common.enums.ResponseCode;
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
public class ResponseResult<T> implements Serializable {
  private String msg;
  private int code;
  private int httpCode;
  private T object;
  
  public ResponseResult() {
    this.code = ResponseCode.SUCCESS.getCode();
    this.httpCode = ResponseCode.SUCCESS.getHttpCode();
    this.msg = ResponseCode.SUCCESS.getMsg();
  }
  
  public ResponseResult(ResponseCode responseCode) {
    this.code = responseCode.getCode();
    this.httpCode = responseCode.getHttpCode();
    this.msg = responseCode.getMsg();
  }
  
  public ResponseResult(String msg) {
    this.code = ResponseCode.SUCCESS.getCode();
    this.httpCode = ResponseCode.SUCCESS.getHttpCode();
    this.msg = msg;
  }
  
  public ResponseResult(T t) {
    this.code = ResponseCode.SUCCESS.getCode();
    this.httpCode = ResponseCode.SUCCESS.getHttpCode();
    this.msg = ResponseCode.SUCCESS.getMsg();
    this.object = t;
  }
  
  public ResponseResult(ResponseCode responseCode, String msg) {
    this.code = responseCode.getCode();
    this.httpCode = responseCode.getHttpCode();
    this.msg = msg;
  }
  
  public boolean success() {
    return code == ResponseCode.SUCCESS.getCode();
  }
}
