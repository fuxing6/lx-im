package com.lx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.dao.MessageUserDao;
import com.lx.domain.MessageUser;
import com.lx.domain.PageInfo;
import com.lx.service.base.CrudService;
import com.lx.utils.StringUtils;

/**
 * 消息接收人Service
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Service
@Transactional(readOnly = true)
public class MessageUserService extends CrudService<MessageUserDao, MessageUser> {
  @Autowired
  private MessageUserDao messageUserDao;

  public List<MessageUser> findList(MessageUser messageUser) {
    return super.findList(messageUser);
  }

  public PageInfo<MessageUser> findPage(PageInfo<MessageUser> page, MessageUser messageUser) {
    return super.findPage(page, messageUser);
  }

  @Transactional(readOnly = false)
  public void save(MessageUser messageUser) {
    super.save(messageUser);
  }

  @Transactional(readOnly = false)
  public void delete(MessageUser messageUser) {
    super.delete(messageUser);
  }

  @Transactional(readOnly = false)
  public void deleteBatch(List<String> idList) {
    messageUserDao.deleteBatch(idList);
  }

  @Transactional(readOnly = false)
  public void insertBatch(List<MessageUser> messageUserList) {
    messageUserDao.insertBatch(messageUserList);
  }

  @Transactional(readOnly = false)
  public void updateNotNull(MessageUser messageUser) throws Exception {
    messageUser.preUpdate();
    messageUserDao.updateNotNull(messageUser);
  }

  public MessageUser getInfo(MessageUser messageUser) {
    MessageUser entity = null;
    if (StringUtils.isNotBlank(messageUser.getId())) {
      entity = get(messageUser);
    }
    if (entity == null) {
      entity = new MessageUser();
    }
    return entity;
  }

  @Override
  protected MessageUserDao dao() {
    return messageUserDao;
  }
}
