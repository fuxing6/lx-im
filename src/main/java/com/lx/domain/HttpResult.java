package com.lx.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @Description： HTTP结果封装实体类
 * @version: 1.0
 * @author: wufuxing
 * @date: 2016年6月30日 下午2:01:57
 */
public class HttpResult<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  // 区分不同的服务，默认"0"
  private String code;
  private String msg;
  private T result;
  private String detail;
  private boolean success = true;

  private static class SingletonHolder {
    private static HttpResult<Void> instance = new HttpResult<Void>();
  }

  private HttpResult() {
    this.code = HttpResultEnum.Success.code;
    this.msg = HttpResultEnum.Success.msg;
    this.detail = "";
  }

  private HttpResult(T result) {
    this();
    this.result = result;
  }

  private HttpResult(String code, String msg, String detail) {
    this(code, msg, null, detail);
  }

  private HttpResult(String code, String msg) {
    this(code, msg, null, null);
  }

  private HttpResult(String code, String msg, T result, String detail) {
    this.code = code;
    this.msg = msg;
    this.result = result;
    this.detail = detail;
    this.success = HttpResultEnum.Success.code.equals(code);
  }

  public static HttpResult<Void> successResult() {
    return SingletonHolder.instance;
  }

  public static <R> HttpResult<R> newSuccessResult(R result) {
    return new HttpResult<R>(result);
  }

  public static HttpResult<Void> newErrorResult() {
    return new HttpResult<Void>(HttpResultEnum.Failed.code, HttpResultEnum.Failed.msg);
  }

  public static HttpResult<Void> newErrorResult(int code, String msg) {
    return new HttpResult<Void>(String.valueOf(code), msg, "");
  }

  public static HttpResult<Void> newErrorResult(HttpResultEnum resultEnum) {
    return new HttpResult<Void>(resultEnum.code, resultEnum.msg, "");
  }

  public static HttpResult<Void> newErrorResult(String code, String msg) {
    return new HttpResult<Void>(code, msg, "");
  }

  public static HttpResult<Void> newErrorResult(String code, String msg, String detail) {
    return new HttpResult<Void>(code, msg, detail);
  }

  public static <R> HttpResult<R> newResult(String code, String msg, R result, String detail) {
    return new HttpResult<R>(code, msg, result, detail);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @SuppressWarnings("unchecked")
  public T getResult() {
    if (result != null) {
      return result;
    }
    if (result instanceof String) {
      return (T) "";
    }
    return null;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getDetail() {
    return StringUtils.trimToEmpty(detail);
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  @Override
  public String toString() {
    return "HttpResult [code=" + code + ", msg=" + msg + ", result=" + result + ", detail="
        + detail + ", success=" + success + "]";
  }
}
