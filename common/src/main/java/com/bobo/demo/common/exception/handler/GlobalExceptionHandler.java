package com.bobo.demo.common.exception.handler;

import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.exception.BusinessException;
import com.bobo.demo.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
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
   * sql
   *
   * @param ex 异常
   * @return 返回结果
   */
  @ExceptionHandler(value = DuplicateKeyException.class)
  @ResponseBody
  private ResponseResult<Object> sqlExceptionHandler(DuplicateKeyException ex) {
    log.warn("sql 异常:", ex);
    return new ResponseResult<>(ResponseCode.BAD_REQUEST, "数据填充重复:" + ex.getCause().getLocalizedMessage());
  }
  
  
  /**
   * 业务异常处理
   *
   * @param ex 异常
   * @return 返回结果
   */
  @ExceptionHandler(value = BusinessException.class)
  @ResponseBody
  private ResponseResult<Object> customExceptionHandler(BusinessException ex, HttpServletResponse response) {
    log.warn("[全局业务异常]\r\n业务编码：{}\r\n异常记录：{}", ex.getResponseCode().getCode(), ex.getMessage(), ex);
    response.setStatus(ex.getResponseCode().getHttpCode());
    return new ResponseResult<>(ex.getResponseCode());
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
  private ResponseResult<Object> exceptionHandler(Exception ex) {
    log.error(ex.getMessage(), ex);
    return new ResponseResult<>(ResponseCode.ERROR_INTERNAL_SERVER_ERROR, ex.getMessage());
  }
}
