package com.bobo.demo.common.enums;

/**
 * 返回的枚举
 *
 * @author bo
 */

public enum ResponseCode {
  /**
   * 200000 请求已成功，请求所希望的响应头或数据体将随此响应返回。
   */
  SUCCESS(20000, "SUCCESS", 200),
  /**
   * 202000
   * 服务器已接受请求，但尚未处理。正如它可能被拒绝一样，最终该请求可能会也可能不会被执行。在异步操作的场合下，没有比发送这个状态码更方便的做法了。
   * 返回2002状态码的响应的目的是允许服务器接受其他过程的请求（例如某个每天只执行一次的基于批处理的操作），而不必让客户端一直保持与服务器的连接直到批处理操作全部完成。
   * 在接受请求处理并返回2002状态码的响应应当在返回的实体中包含一些指示处理当前状态的信息，以及指向处理状态监视器或状态预测的指针，以便用户能够估计操作是否已经完成。
   */
  ACCEPTED_SUCCESS(20200, "ACCEPTED", 202),

  NOT_FOUND(40400, "NOT FOUND", 404),
  /**
   * 由于包含语法错误，当前请求无法被服务器理解。除非进行修改，否则客户端不应该重复提交这个请求
   */
  BAD_REQUEST(40000, "BAD REQUEST", 400),
  /**
   * 300102 传递参数时JSON格式不正确。
   */
  JSON_FORMAL_ERROR(40001, "JSON FORMAL ERROR", 400),
  /**
   * 300103 参数类型不正确（如日期格式不正确，BOOLEAN 编译）。
   */
  PARAM_FORMAL_ERROR(40002, "PARAMETER FORMAL ERROR", 400),
  /**
   * 必填参数缺少
   */
  PARAM_NOT_FOUND(40003, "PARAM NOT FOUND", 400),
  /**
   * 4003 服务器已经理解请求，但是拒绝执行它。与4001响应不同的是，身份验证并不能提供任何帮助，而且这个请求也不应该被重复提交。
   * 如果这不是一个HEAD请求，而且服务器希望能够讲清楚为何请求不能被执行，那么就应该在实体内描述拒绝的原因。
   * 当然服务器也可以返回一个4004响应，假如它不希望让客户端获得任何信息。
   */
  FORBIDDEN(40300, "FORBIDDEN", 403),
  /**
   * 4001 获取或刷新TOKEN时，获取或刷新TOKEN时，不合法的验证信息
   */
  AUTHENTICATION(40304, "AUTHENTICATION IS INVALID", 403),
  /**
   * 4002 无效的TOKEN
   */
  INVALID_TOKEN(40301, "TOKEN IS INVALID", 403),
  /**
   * 无权访问
   */
  JURISDICTION(40302, "NO PERMISSION ACCESS RESOURCE", 403),

  /**
   * 423 LOCKED , "请求资源被锁定"
   */
  LOCKED(42300, "LOCKED", 423),

  /**
   * 服务器未知异常，需要编码层面处理
   */
  ERROR_INTERNAL_SERVER_ERROR(50000, " INTERNAL SERVER ERROR", 500),
  /**
   * 数据库服务器异常
   */
  ERROR_DAO_SERVER_ERROR(50001, " DAO SERVER ERROR", 500),
  /**
   * 当服务器作为网关，不能及时得到响应时返回此错误代码。
   */
  ERROR_GATEWAY_TIMEOUT(50400, "GATEWAY TIMEOUT", 504),

  ;;


  final int code;
  final int httpCode;
  final String msg;

  ResponseCode(int code, String msg, int httpCode) {
    this.code = code;
    this.httpCode = httpCode;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public int getHttpCode() {
    return httpCode;
  }

  public String getMsg() {
    return msg;
  }
}
