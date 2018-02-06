package com.lx.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 群组Entity
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
public class Group extends DataEntity<Group> {

  private static final long serialVersionUID = 1L;
  private String createUser; // create_user
  private String name; // name
  private Date createTime; // create_time
  private Date updateTime; // update_time


  @Length(min = 0, max = 64, message = "create_user长度必须介于 0 和 64 之间")
  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  @Length(min = 0, max = 200, message = "name长度必须介于 0 和 200 之间")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

}
