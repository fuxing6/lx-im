package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lx.dao.base.CrudDao;
import com.lx.domain.User;

/**
 * 用户信息DAO接口
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Mapper
public interface UserDao extends CrudDao<User> {
  /**
   * @author wufuxing 更新非空字段
   * @param tUser
   */
  public void updateNotNull(User tUser);

  /**
   * @author wufuxing 批量插入
   * @param tUser
   */
  public void insertBatch(List<User> tUserList);

  /**
   * @author wufuxing 批量删除
   * @param idList
   */
  public void deleteBatch(List<String> idList);
}
