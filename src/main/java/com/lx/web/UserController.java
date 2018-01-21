package com.lx.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.lx.model.HttpResult;
import com.lx.model.User;
import com.lx.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

  private Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequestMapping("/info")
  public HttpResult<?> getInfo(User user) {
    try {
      if (user == null || StringUtils.isEmpty(user.getId())) {
        return HttpResult.errorResult(-1, "用户id不能为空");
      }
      User info = userService.getInfo(user);
      return HttpResult.successResult(info);
    } catch (Exception e) {
      logger.error("失败", e);
      return HttpResult.errorResult();
    }
  }

  @RequestMapping("/save")
  public HttpResult<?> save(User user) {
    try {
      if (user == null || StringUtils.isEmpty(user.getId())) {
        return HttpResult.errorResult(-1, "用户id不能为空");
      }
      userService.save(user);
      return HttpResult.successResult(user);
    } catch (Exception e) {
      logger.error("失败", e);
      return HttpResult.errorResult();
    }
  }

  @RequestMapping("/queryAll")
  public HttpResult<?> queryAll(User user) {
    try {
      List<User> all = userService.getAll(user);
      return HttpResult.successResult(all);
    } catch (Exception e) {
      logger.error("失败", e);
      return HttpResult.errorResult();
    }
  }

  @RequestMapping("/update")
  public HttpResult<?> update(User user) {
    try {
      if (user == null || StringUtils.isEmpty(user.getId())) {
        return HttpResult.errorResult(-1, "用户id不能为空");
      }
      userService.update(user);
      return HttpResult.successResult(user);
    } catch (Exception e) {
      logger.error("失败", e);
      return HttpResult.errorResult();
    }
  }

}
