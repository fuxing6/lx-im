package com.lx.web.base;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.lx.domain.CheckParamHandler;
import com.lx.domain.HttpResult;
import com.lx.domain.HttpResultEnum;
import com.lx.utils.DateTimeUtils;
import com.lx.utils.StringUtils;

public abstract class BaseController {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
    binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) {
        if (text == null) {
          setValue(null);
        } else {
          String value = text;
          // 把用户输入的%转义
          // value = EmojiUtil.emojiConvert(value);
          setValue(value);
        }
      }

      @Override
      public String getAsText() {
        Object value = getValue();
        String returnVal = value != null ? value.toString() : "";
        return returnVal;
      }
    });
    // Date 类型转换
    binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) {
        setValue(DateTimeUtils.parseDate(text));
      }
      // @Override
      // public String getAsText() {
      // Object value = getValue();
      // return value != null ? DateUtils.formatDateTime((Date)value) : "";
      // }
    });
    binder.registerCustomEditor(int.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) {
        if (StringUtils.isEmpty(text)) {
          text = "0";
        }
        setValue(Integer.parseInt(text));
      }
      // @Override
      // public String getAsText() {
      // Object value = getValue();
      // return value != null ? DateUtils.formatDateTime((Date)value) : "";
      // }
    });
  }

  protected <T> HttpResult<?> check(CheckParamHandler<T> checkParamHandler, T t) {
    if (checkParamHandler == null || t == null) {
      return HttpResult.newErrorResult(HttpResultEnum.Failed.code, "参数对象为空");
    }
    HttpResult<?> checkResult = checkParamHandler.check(t);
    if (checkResult != null) {
      logger.error("validate param error,{}", checkResult);
    }
    return checkResult;
  }
}
