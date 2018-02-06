package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lx.dao.base.CrudDao;
import com.lx.domain.Group;

/**
 * 群组DAO接口
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Mapper
public interface GroupDao extends CrudDao<Group> {
  /**
   * @author wufuxing 更新非空字段
   * @param group
   */
  public void updateNotNull(Group group);

  /**
   * @author wufuxing 批量插入
   * @param group
   */
  public void insertBatch(List<Group> groupList);

  /**
   * @author wufuxing 批量删除
   * @param idList
   */
  public void deleteBatch(List<String> idList);
}
