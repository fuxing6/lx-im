package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lx.model.User;

@Mapper
public interface UserDao {

  public User getInfo(User user);

  public void insert(User user);

  public void update(User user);

  public List<User> queryAll();

}
