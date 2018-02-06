package com.lx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.dao.FriendsDao;
import com.lx.domain.Friends;
import com.lx.domain.PageInfo;
import com.lx.service.base.CrudService;
import com.lx.utils.StringUtils;

/**
 * 最近联系人Service
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Service
@Transactional(readOnly = true)
public class FriendsService extends CrudService<FriendsDao, Friends> {
  @Autowired
  private FriendsDao friendsDao;

  public List<Friends> findList(Friends friends) {
    return super.findList(friends);
  }

  public PageInfo<Friends> findPage(PageInfo<Friends> page, Friends friends) {
    return super.findPage(page, friends);
  }

  @Transactional(readOnly = false)
  public void save(Friends friends) {
    super.save(friends);
  }

  @Transactional(readOnly = false)
  public void delete(Friends friends) {
    super.delete(friends);
  }

  @Transactional(readOnly = false)
  public void deleteBatch(List<String> idList) {
    friendsDao.deleteBatch(idList);
  }

  @Transactional(readOnly = false)
  public void insertBatch(List<Friends> friendsList) {
    friendsDao.insertBatch(friendsList);
  }

  @Transactional(readOnly = false)
  public void updateNotNull(Friends friends) throws Exception {
    friends.preUpdate();
    friendsDao.updateNotNull(friends);
  }

  public Friends getInfo(Friends friends) {
    Friends entity = null;
    if (StringUtils.isNotBlank(friends.getId())) {
      entity = get(friends);
    }
    if (entity == null) {
      entity = new Friends();
    }
    return entity;
  }

  @Override
  protected FriendsDao dao() {
    return friendsDao;
  }
}
