package com.lx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.domain.GroupUser;
import com.lx.domain.HttpResult;
import com.lx.domain.HttpResultEnum;
import com.lx.domain.PageInfo;
import com.lx.service.GroupUserService;
import com.lx.web.base.BaseController;

/**
 * 群组成员Controller
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@RestController
@RequestMapping(value = "/groupUser")
public class GroupUserController extends BaseController {

  @Autowired
  private GroupUserService groupUserService;

  @RequestMapping(value = "info")
  public HttpResult<?> get(GroupUser groupUser) {
    try {
      GroupUser entity = groupUserService.getInfo(groupUser);
      return HttpResult.newSuccessResult(entity);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"pageList", ""})
  public HttpResult<?> pageList(GroupUser groupUser, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      PageInfo<GroupUser> page =
          groupUserService.findPage(new PageInfo<GroupUser>(request, response), groupUser);
      return HttpResult.newSuccessResult(page);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"list", ""})
  public HttpResult<?> list(GroupUser groupUser) {
    try {
      List<GroupUser> res = groupUserService.findList(groupUser);
      return HttpResult.newSuccessResult(res);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "save")
  public HttpResult<?> save(GroupUser groupUser) {
    try {
      groupUserService.save(groupUser);
      return HttpResult.newSuccessResult(groupUser.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "delete")
  public HttpResult<?> delete(GroupUser groupUser) {
    try {
      groupUserService.delete(groupUser);
      return HttpResult.newSuccessResult(groupUser.getId());
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
      groupUserService.deleteBatch(idList);
      return HttpResult.successResult();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

}
