package com.lx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lx.dao.base.CrudDao;
import com.lx.domain.Message;

/**
 * 消息内容DAO接口
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Mapper
public interface MessageDao extends CrudDao<Message> {
  /**
   * @author wufuxing 更新非空字段
   * @param message
   */
  public void updateNotNull(Message message);

  /**
   * @author wufuxing 批量插入
   * @param message
   */
  public void insertBatch(List<Message> messageList);

  /**
   * @author wufuxing 批量删除
   * @param idList
   */
  public void deleteBatch(List<String> idList);
}
