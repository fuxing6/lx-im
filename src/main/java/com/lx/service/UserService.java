package com.lx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.dao.UserDao;
import com.lx.domain.PageInfo;
import com.lx.domain.User;
import com.lx.service.base.CrudService;
import com.lx.utils.StringUtils;

/**
 * 用户信息Service
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {
  @Autowired
  private UserDao userDao;

  public List<User> findList(User tUser) {
    return super.findList(tUser);
  }

  public PageInfo<User> findPage(PageInfo<User> page, User tUser) {
    return super.findPage(page, tUser);
  }

  @Transactional(readOnly = false)
  public void save(User tUser) {
    super.save(tUser);
  }

  @Transactional(readOnly = false)
  public void delete(User tUser) {
    super.delete(tUser);
  }

  @Transactional(readOnly = false)
  public void deleteBatch(List<String> idList) {
    userDao.deleteBatch(idList);
  }

  @Transactional(readOnly = false)
  public void insertBatch(List<User> tUserList) {
    userDao.insertBatch(tUserList);
  }

  @Transactional(readOnly = false)
  public void updateNotNull(User tUser) throws Exception {
    tUser.preUpdate();
    userDao.updateNotNull(tUser);
  }

  public User getInfo(User tUser) {
    User entity = null;
    if (StringUtils.isNotBlank(tUser.getId())) {
      entity = get(tUser);
    }
    if (entity == null) {
      entity = new User();
    }
    return entity;
  }

  @Override
  protected UserDao dao() {
    return userDao;
  }
}
