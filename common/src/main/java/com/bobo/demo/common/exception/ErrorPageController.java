package com.bobo.demo.common.exception;

import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorPageController implements ErrorController {
  
  @RequestMapping(value = "/error")
  @ResponseBody
  public ResponseResult<Object> errorHtml(HttpServletRequest request) {
    Integer attribute = (Integer) request.getAttribute("javax.servlet.error.status_code");
    return switch (attribute) {
      case 404 -> new ResponseResult<>(ResponseCode.NOT_FOUND);
      case 403 -> new ResponseResult<>(ResponseCode.FORBIDDEN);
      case 423 -> new ResponseResult<>(ResponseCode.LOCKED);
      case 504 -> new ResponseResult<>(ResponseCode.ERROR_GATEWAY_TIMEOUT);
      default -> new ResponseResult<>(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
    };
    
  }
}
