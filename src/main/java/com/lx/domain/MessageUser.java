package com.lx.domain;

import org.hibernate.validator.constraints.Length;

/**
 * 消息接收人Entity
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
public class MessageUser extends DataEntity<MessageUser> {

  private static final long serialVersionUID = 1L;
  private String messageId; // message_id
  private String receiver; // receiver
  private String groupId; // group_id
  private String readFlag; // read_flag


  @Length(min = 0, max = 64, message = "message_id长度必须介于 0 和 64 之间")
  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  @Length(min = 0, max = 64, message = "receiver长度必须介于 0 和 64 之间")
  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  @Length(min = 0, max = 64, message = "group_id长度必须介于 0 和 64 之间")
  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  @Length(min = 0, max = 1, message = "read_flag长度必须介于 0 和 1 之间")
  public String getReadFlag() {
    return readFlag;
  }

  public void setReadFlag(String readFlag) {
    this.readFlag = readFlag;
  }

}
