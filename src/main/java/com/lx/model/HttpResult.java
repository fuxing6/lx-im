package com.lx.model;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -9000268674009241958L;

  private int code = 0;// 0:success,-1:fail

  private boolean success;

  private T result;

  private String msg = "成功";

  public static <T> HttpResult<T> successResult() {
    return new HttpResult<T>();
  }

  public static <T> HttpResult<T> errorResult() {
    return new HttpResult<T>(-1, "失败");
  }

  public static <T> HttpResult<T> errorResult(int code, String msg) {
    return new HttpResult<T>(code, msg);
  }

  public static <T> HttpResult<T> successResult(T t) {
    return new HttpResult<T>(t);
  }

  public HttpResult(T result) {
    super();
    this.result = result;
  }

  public HttpResult(int code, String msg) {
    super();
    this.code = code;
    this.msg = msg;
  }

  public HttpResult() {
    super();
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public boolean isSuccess() {
    if (0 == code) {
      return true;
    }
    return false;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

}
