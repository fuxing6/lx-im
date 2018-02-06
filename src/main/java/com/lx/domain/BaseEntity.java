package com.lx.domain;

import java.io.Serializable;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

public abstract class BaseEntity<T> implements Serializable {

  private static final long serialVersionUID = -1097737504162050529L;

  private PageInfo<T> page;

  private Map<String, String> sqlMap = Maps.newHashMap();

  public Map<String, String> getSqlMap() {
    return sqlMap;
  }

  public void setSqlMap(Map<String, String> sqlMap) {
    this.sqlMap = sqlMap;
  }

  public PageInfo<T> getPage() {
    return page;
  }

  public void setPage(PageInfo<T> page) {
    this.page = page;
  }

}
