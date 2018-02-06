package com.lx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.dao.MessageDao;
import com.lx.domain.Message;
import com.lx.domain.PageInfo;
import com.lx.service.base.CrudService;
import com.lx.utils.StringUtils;

/**
 * 消息内容Service
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@Service
@Transactional(readOnly = true)
public class MessageService extends CrudService<MessageDao, Message> {
  @Autowired
  private MessageDao messageDao;

  public List<Message> findList(Message message) {
    return super.findList(message);
  }

  public PageInfo<Message> findPage(PageInfo<Message> page, Message message) {
    return super.findPage(page, message);
  }

  @Transactional(readOnly = false)
  public void save(Message message) {
    super.save(message);
  }

  @Transactional(readOnly = false)
  public void delete(Message message) {
    super.delete(message);
  }

  @Transactional(readOnly = false)
  public void deleteBatch(List<String> idList) {
    messageDao.deleteBatch(idList);
  }

  @Transactional(readOnly = false)
  public void insertBatch(List<Message> messageList) {
    messageDao.insertBatch(messageList);
  }

  @Transactional(readOnly = false)
  public void updateNotNull(Message message) throws Exception {
    message.preUpdate();
    messageDao.updateNotNull(message);
  }

  public Message getInfo(Message message) {
    Message entity = null;
    if (StringUtils.isNotBlank(message.getId())) {
      entity = get(message);
    }
    if (entity == null) {
      entity = new Message();
    }
    return entity;
  }

  @Override
  protected MessageDao dao() {
    return messageDao;
  }
}
