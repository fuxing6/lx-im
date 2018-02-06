package com.lx.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.utils.IdGen;
import com.lx.utils.StringUtils;


public abstract class DataEntity<T> extends BaseEntity<T> {

  private static final long serialVersionUID = 2593188381687922385L;

  protected String id;

  protected String createPin;

  protected String updatePin;

  protected Date createTime;

  protected Date updateTime;

  protected String delFlag;

  protected String version;

  protected String tenantId;

  private String remarks;

  public static final String DEL_FLAG_NORMAL = "0";
  public static final String DEL_FLAG_DELETE = "1";

  public String getId() {
    return id;
  }

  public void preInsert() {
    this.id = IdGen.uuid();
    this.createTime = new Date();
    this.updateTime = this.createTime;
    this.version = IdGen.uuid();
  }

  public void preUpdate() {
    this.updateTime = new Date();
    this.version = IdGen.uuid();
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreatePin() {
    return createPin;
  }

  public void setCreatePin(String createPin) {
    this.createPin = createPin;
  }

  public String getUpdatePin() {
    return updatePin;
  }

  public void setUpdatePin(String updatePin) {
    this.updatePin = updatePin;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public boolean isNewRecord() {
    return StringUtils.isBlank(id);
  }

  @JsonIgnore
  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

}
