package com.lx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.dao.GroupUserDao;
import com.lx.domain.GroupUser;
import com.lx.domain.PageInfo;
import com.lx.service.base.CrudService;
import com.lx.utils.StringUtils;

/**
 * 群组成员Service
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Service
@Transactional(readOnly = true)
public class GroupUserService extends CrudService<GroupUserDao, GroupUser> {
  @Autowired
  private GroupUserDao groupUserDao;

  public List<GroupUser> findList(GroupUser groupUser) {
    return super.findList(groupUser);
  }

  public PageInfo<GroupUser> findPage(PageInfo<GroupUser> page, GroupUser groupUser) {
    return super.findPage(page, groupUser);
  }

  @Transactional(readOnly = false)
  public void save(GroupUser groupUser) {
    super.save(groupUser);
  }

  @Transactional(readOnly = false)
  public void delete(GroupUser groupUser) {
    super.delete(groupUser);
  }

  @Transactional(readOnly = false)
  public void deleteBatch(List<String> idList) {
    groupUserDao.deleteBatch(idList);
  }

  @Transactional(readOnly = false)
  public void insertBatch(List<GroupUser> groupUserList) {
    groupUserDao.insertBatch(groupUserList);
  }

  @Transactional(readOnly = false)
  public void updateNotNull(GroupUser groupUser) throws Exception {
    groupUser.preUpdate();
    groupUserDao.updateNotNull(groupUser);
  }

  public GroupUser getInfo(GroupUser groupUser) {
    GroupUser entity = null;
    if (StringUtils.isNotBlank(groupUser.getId())) {
      entity = get(groupUser);
    }
    if (entity == null) {
      entity = new GroupUser();
    }
    return entity;
  }

  @Override
  protected GroupUserDao dao() {
    return groupUserDao;
  }
}
