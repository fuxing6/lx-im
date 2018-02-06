package com.lx.domain;

import org.hibernate.validator.constraints.Length;

/**
 * 消息内容Entity
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
public class Message extends DataEntity<Message> {

  private static final long serialVersionUID = 1L;
  private String sender; // sender
  private String cotent; // cotent
  private String contentType; // (链接,图片,附件)
  private String groupId; // group_id


  @Length(min = 0, max = 64, message = "sender长度必须介于 0 和 64 之间")
  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getCotent() {
    return cotent;
  }

  public void setCotent(String cotent) {
    this.cotent = cotent;
  }

  @Length(min = 0, max = 1, message = "(链接,图片,附件)长度必须介于 0 和 1 之间")
  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  @Length(min = 0, max = 64, message = "group_id长度必须介于 0 和 64 之间")
  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

}
