package com.lx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lx.dao.UserDao;
import com.lx.model.User;

@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public User getInfo(User user) {
    if (user == null || StringUtils.isEmpty(user.getId())) {
      return null;
    }
    return userDao.getInfo(user);
  }

  public List<User> getAll(User user) {
    return userDao.queryAll();
  }

  public void save(User user) {
    if (user == null || StringUtils.isEmpty(user.getId())) {
      return;
    }
    userDao.insert(user);
  }

  public void update(User user) {
    if (user == null || StringUtils.isEmpty(user.getId())) {
      return;
    }
    userDao.update(user);
  }

}
