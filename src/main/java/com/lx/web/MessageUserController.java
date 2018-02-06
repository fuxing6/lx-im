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
import com.lx.domain.MessageUser;
import com.lx.domain.PageInfo;
import com.lx.service.MessageUserService;
import com.lx.web.base.BaseController;

/**
 * 消息接收人Controller
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@RestController
@RequestMapping(value = "/messageUser")
public class MessageUserController extends BaseController {

  @Autowired
  private MessageUserService messageUserService;

  @RequestMapping(value = "info")
  public HttpResult<?> get(MessageUser messageUser) {
    try {
      MessageUser entity = messageUserService.getInfo(messageUser);
      return HttpResult.newSuccessResult(entity);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"pageList", ""})
  public HttpResult<?> pageList(MessageUser messageUser, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      PageInfo<MessageUser> page =
          messageUserService.findPage(new PageInfo<MessageUser>(request, response), messageUser);
      return HttpResult.newSuccessResult(page);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"list", ""})
  public HttpResult<?> list(MessageUser messageUser) {
    try {
      List<MessageUser> res = messageUserService.findList(messageUser);
      return HttpResult.newSuccessResult(res);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "save")
  public HttpResult<?> save(MessageUser messageUser) {
    try {
      messageUserService.save(messageUser);
      return HttpResult.newSuccessResult(messageUser.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "delete")
  public HttpResult<?> delete(MessageUser messageUser) {
    try {
      messageUserService.delete(messageUser);
      return HttpResult.newSuccessResult(messageUser.getId());
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
      messageUserService.deleteBatch(idList);
      return HttpResult.successResult();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

}
