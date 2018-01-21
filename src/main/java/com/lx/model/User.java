package com.lx.model;

import java.io.Serializable;

public class User implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7641825526819643561L;

  private String id;

  private String name;

  private String no;

  private String image;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
