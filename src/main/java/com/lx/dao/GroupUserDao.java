package com.lx.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lx.dao.base.CrudDao;
import com.lx.domain.GroupUser;

/**
 * 群组成员DAO接口
 * @author wufuxing
 * @version 2018-02-06
 */
@Mapper
public interface GroupUserDao extends CrudDao<GroupUser> {
	/**@author wufuxing
	 * 更新非空字段
	 * @param groupUser
	 */
	public void updateNotNull (GroupUser groupUser) ;
	/**@author wufuxing
	 * 批量插入
	 * @param groupUser
	 */
	public void insertBatch(List<GroupUser> groupUserList);
	/**@author wufuxing
	 * 批量删除
	 * @param idList
	 */
	public void deleteBatch(List<String> idList) ;
}