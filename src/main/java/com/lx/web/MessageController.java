package com.lx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.domain.HttpResult;
import com.lx.domain.HttpResultEnum;
import com.lx.domain.Message;
import com.lx.domain.PageInfo;
import com.lx.service.MessageService;
import com.lx.web.base.BaseController;

/**
 * 消息内容Controller
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

  @Autowired
  private MessageService messageService;

  @RequestMapping(value = "info")
  public HttpResult<?> get(Message message) {
    try {
      Message entity = messageService.getInfo(message);
      return HttpResult.newSuccessResult(entity);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"pageList", ""})
  public HttpResult<?> pageList(Message message, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      PageInfo<Message> page =
          messageService.findPage(new PageInfo<Message>(request, response), message);
      return HttpResult.newSuccessResult(page);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"list", ""})
  public HttpResult<?> list(Message message) {
    try {
      List<Message> res = messageService.findList(message);
      return HttpResult.newSuccessResult(res);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "save")
  public HttpResult<?> save(Message message) {
    try {
      messageService.save(message);
      return HttpResult.newSuccessResult(message.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "delete")
  public HttpResult<?> delete(Message message) {
    try {
      messageService.delete(message);
      return HttpResult.newSuccessResult(message.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "deleteBatch")
  public HttpResult<?> deleteBatch(@RequestParam(value = "idList") List<String> idList) {
    try {
      if (CollectionUtils.isEmpty(idList)) {
        logger.error("请选择数据!");
        return HttpResult.newErrorResult(HttpResultEnum.Failed);
      }
      messageService.deleteBatch(idList);
      return HttpResult.successResult();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

}
