package com.bobo.demo.common.exception.handler;

import com.bobo.demo.common.exception.BusinessException;
import com.bobo.demo.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * @author h-vk
 * @describe 全局处理异常返回结果
 * @since 2020/5/24
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
  
  
  /**
   * 业务异常处理
   *
   * @param ex 异常
   * @return 返回结果
   */
  @ExceptionHandler(value = BusinessException.class)
  @ResponseBody
  private ResponseResult customExceptionHandler(BusinessException ex, HttpServletResponse response) {
    log.warn("[全局业务异常]\r\n业务编码：{}\r\n异常记录：{}", ex.getResponseCode().getCode(), ex.getMessage(), ex);
    response.setStatus(ex.getResponseCode().getHttpCode());
    return ResponseResult.failure(ex.getResponseCode());
  }
  
  
  /**
   * 系统未知异常处理
   *
   * @param ex 异常
   * @return 返回结果
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  private ResponseResult exceptionHandler(Exception ex) {
    log.error(ex.getMessage(), ex);
    ResponseResult failure = ResponseResult.failure(ex.getMessage());
    return failure;
  }
}
