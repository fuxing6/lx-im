package com.lx.domain;

import org.hibernate.validator.constraints.Length;

/**
 * 用户信息Entity
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
public class User extends DataEntity<User> {

  private static final long serialVersionUID = 1L;
  private String name; // name
  private String image; // image
  private String no; // no
  private String status; // status


  @Length(min = 0, max = 200, message = "name长度必须介于 0 和 200 之间")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Length(min = 0, max = 2000, message = "image长度必须介于 0 和 2000 之间")
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Length(min = 0, max = 200, message = "no长度必须介于 0 和 200 之间")
  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  @Length(min = 0, max = 1, message = "status长度必须介于 0 和 1 之间")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
