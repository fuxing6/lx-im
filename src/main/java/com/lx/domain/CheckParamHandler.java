package com.lx.domain;


public interface CheckParamHandler<T> {

  /**
   * checkParam 校验参数前 t必须不能为null
   * 
   * @param t
   * @return
   */
  public HttpResult<?> check(T t);

}
