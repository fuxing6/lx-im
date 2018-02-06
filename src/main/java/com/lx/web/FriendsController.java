package com.lx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.domain.Friends;
import com.lx.domain.HttpResult;
import com.lx.domain.HttpResultEnum;
import com.lx.domain.PageInfo;
import com.lx.service.FriendsService;
import com.lx.web.base.BaseController;

/**
 * 最近联系人Controller
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@RestController
@RequestMapping(value = "/friends")
public class FriendsController extends BaseController {

  @Autowired
  private FriendsService friendsService;

  @RequestMapping(value = "info")
  public HttpResult<?> get(Friends friends) {
    try {
      Friends entity = friendsService.getInfo(friends);
      return HttpResult.newSuccessResult(entity);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"pageList", ""})
  public HttpResult<?> pageList(Friends friends, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      PageInfo<Friends> page =
          friendsService.findPage(new PageInfo<Friends>(request, response), friends);
      return HttpResult.newSuccessResult(page);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"list", ""})
  public HttpResult<?> list(Friends friends) {
    try {
      List<Friends> res = friendsService.findList(friends);
      return HttpResult.newSuccessResult(res);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "save")
  public HttpResult<?> save(Friends friends) {
    try {
      friendsService.save(friends);
      return HttpResult.newSuccessResult(friends.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "delete")
  public HttpResult<?> delete(Friends friends) {
    try {
      friendsService.delete(friends);
      return HttpResult.newSuccessResult(friends.getId());
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
      friendsService.deleteBatch(idList);
      return HttpResult.successResult();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

}
