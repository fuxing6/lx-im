package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lx.dao.base.CrudDao;
import com.lx.domain.Friends;

/**
 * 最近联系人DAO接口
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Mapper
public interface FriendsDao extends CrudDao<Friends> {
  /**
   * @author wufuxing 更新非空字段
   * @param friends
   */
  public void updateNotNull(Friends friends);

  /**
   * @author wufuxing 批量插入
   * @param friends
   */
  public void insertBatch(List<Friends> friendsList);

  /**
   * @author wufuxing 批量删除
   * @param idList
   */
  public void deleteBatch(List<String> idList);
}
