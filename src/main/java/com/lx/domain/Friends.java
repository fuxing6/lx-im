package com.lx.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 最近联系人Entity
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
public class Friends extends DataEntity<Friends> {

  private static final long serialVersionUID = 1L;
  private String userId; // user_id
  private String friendId; // friend_id
  private Date updateTime; // update_time
  private String type; // 个人消息,群组消息


  @Length(min = 0, max = 64, message = "user_id长度必须介于 0 和 64 之间")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Length(min = 0, max = 64, message = "friend_id长度必须介于 0 和 64 之间")
  public String getFriendId() {
    return friendId;
  }

  public void setFriendId(String friendId) {
    this.friendId = friendId;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Length(min = 0, max = 10, message = "个人消息,群组消息长度必须介于 0 和 10 之间")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
