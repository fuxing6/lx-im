package com.lx.domain;

import org.hibernate.validator.constraints.Length;

/**
 * 群组成员Entity
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
public class GroupUser extends DataEntity<GroupUser> {

  private static final long serialVersionUID = 1L;
  private String groupId; // group_id
  private String userId; // user_id


  @Length(min = 0, max = 64, message = "group_id长度必须介于 0 和 64 之间")
  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  @Length(min = 0, max = 64, message = "user_id长度必须介于 0 和 64 之间")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
