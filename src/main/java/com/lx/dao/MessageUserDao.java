package com.lx.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lx.dao.base.CrudDao;
import com.lx.domain.MessageUser;

/**
 * 消息接收人DAO接口
 * @author wufuxing
 * @version 2018-02-06
 */
@Mapper
public interface MessageUserDao extends CrudDao<MessageUser> {
	/**@author wufuxing
	 * 更新非空字段
	 * @param messageUser
	 */
	public void updateNotNull (MessageUser messageUser) ;
	/**@author wufuxing
	 * 批量插入
	 * @param messageUser
	 */
	public void insertBatch(List<MessageUser> messageUserList);
	/**@author wufuxing
	 * 批量删除
	 * @param idList
	 */
	public void deleteBatch(List<String> idList) ;
}