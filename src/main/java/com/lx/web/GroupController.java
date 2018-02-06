package com.lx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.domain.Group;
import com.lx.domain.HttpResult;
import com.lx.domain.HttpResultEnum;
import com.lx.domain.PageInfo;
import com.lx.service.GroupService;
import com.lx.web.base.BaseController;

/**
 * 群组Controller
 * 
 * @author wufuxing
 * @version 2018-02-06
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController extends BaseController {

  @Autowired
  private GroupService groupService;

  @RequestMapping(value = "info")
  public HttpResult<?> get(Group group) {
    try {
      Group entity = groupService.getInfo(group);
      return HttpResult.newSuccessResult(entity);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"pageList", ""})
  public HttpResult<?> pageList(Group group, HttpServletRequest request,
      HttpServletResponse response) {
    try {
      PageInfo<Group> page = groupService.findPage(new PageInfo<Group>(request, response), group);
      return HttpResult.newSuccessResult(page);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = {"list", ""})
  public HttpResult<?> list(Group group) {
    try {
      List<Group> res = groupService.findList(group);
      return HttpResult.newSuccessResult(res);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "save")
  public HttpResult<?> save(Group group) {
    try {
      groupService.save(group);
      return HttpResult.newSuccessResult(group.getId());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

  @RequestMapping(value = "delete")
  public HttpResult<?> delete(Group group) {
    try {
      groupService.delete(group);
      return HttpResult.newSuccessResult(group.getId());
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
      groupService.deleteBatch(idList);
      return HttpResult.successResult();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return HttpResult.newErrorResult(HttpResultEnum.Failed);
    }
  }

}
