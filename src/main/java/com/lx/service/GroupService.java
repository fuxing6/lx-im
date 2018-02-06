package com.lx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.dao.GroupDao;
import com.lx.domain.Group;
import com.lx.domain.PageInfo;
import com.lx.service.base.CrudService;
import com.lx.utils.StringUtils;

/**
 * 群组Service
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Service
@Transactional(readOnly = true)
public class GroupService extends CrudService<GroupDao, Group> {
  @Autowired
  private GroupDao groupDao;

  public List<Group> findList(Group group) {
    return super.findList(group);
  }

  public PageInfo<Group> findPage(PageInfo<Group> page, Group group) {
    return super.findPage(page, group);
  }

  @Transactional(readOnly = false)
  public void save(Group group) {
    super.save(group);
  }

  @Transactional(readOnly = false)
  public void delete(Group group) {
    super.delete(group);
  }

  @Transactional(readOnly = false)
  public void deleteBatch(List<String> idList) {
    groupDao.deleteBatch(idList);
  }

  @Transactional(readOnly = false)
  public void insertBatch(List<Group> groupList) {
    groupDao.insertBatch(groupList);
  }

  @Transactional(readOnly = false)
  public void updateNotNull(Group group) throws Exception {
    group.preUpdate();
    groupDao.updateNotNull(group);
  }

  public Group getInfo(Group group) {
    Group entity = null;
    if (StringUtils.isNotBlank(group.getId())) {
      entity = get(group);
    }
    if (entity == null) {
      entity = new Group();
    }
    return entity;
  }

  @Override
  protected GroupDao dao() {
    return groupDao;
  }
}
