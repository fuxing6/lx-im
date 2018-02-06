package com.lx.utils;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created with IntelliJ IDEA. User: lijing3 Date: 14-12-6 Time: 下午2:32 To change this template use
 * File | Settings | File Templates.
 */
public class CookieUtil {
  /**
   * 根据名字获取cookie
   * 
   * @param request
   * @param name cookie名字
   * @return
   */
  public static Cookie getCookieByName(HttpServletRequest request, String name) {
    Map<String, Cookie> cookieMap = ReadCookieMap(request);
    if (cookieMap.containsKey(name)) {
      Cookie cookie = cookieMap.get(name);
      return cookie;
    } else {
      return null;
    }
  }

  /**
   * 将cookie封装到Map里面
   * 
   * @param request
   * @return
   */
  private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
    Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
    Cookie[] cookies = request.getCookies();
    if (null != cookies) {
      for (Cookie cookie : cookies) {
        cookieMap.put(cookie.getName(), cookie);
      }
    }
    return cookieMap;
  }

  /**
   * 删除cookie
   * 
   * @param key
   * @param response
   * @param domain
   */
  public static void delCookie(HttpServletResponse response, String key, String domain) {
    Cookie sidCookie = new Cookie(key, null);
    // 立即删除型
    sidCookie.setMaxAge(0);
    // 项目所有目录均有效，这句很关键，否则不敢保证删除
    sidCookie.setPath("/");
    if (StringUtils.isNotBlank(domain)) {
      sidCookie.setDomain(domain);
    }
    response.addCookie(sidCookie);
  }

  /**
   * 根据key获取value的值
   */
  public static String getCookieValue(HttpServletRequest request, String name) {
    final Cookie[] oCookies = request.getCookies();
    String value = null;
    if (oCookies != null) {
      for (final Cookie oItem : oCookies) {
        final String sName = oItem.getName();
        if (sName.equals(name)) {
          value = oItem.getValue();
          break;
        }
      }
    }
    return value;
  }

  public static void saveCookie(HttpServletResponse response, String cookieName, String value,
      int time, String path, String encording) throws UnsupportedEncodingException {
    Cookie cookie = new Cookie(cookieName, URLEncoder.encode(value, encording));
    // 设置Cookie的存活时间
    if (time > 0) {
      cookie.setMaxAge(time);
    }
    cookie.setPath(path);
    response.addCookie(cookie);
  }

  /**
   * 设置 Cookie（生成时间为1天）
   * 
   * @param name 名称
   * @param value 值
   */
  public static void setCookie(HttpServletResponse response, String name, String value) {
    setCookie(response, name, value, 60 * 60 * 24);
  }

  /**
   * 设置 Cookie
   * 
   * @param name 名称
   * @param value 值
   * @param maxAge 生存时间（单位秒）
   * @param uri 路径
   */
  public static void setCookie(HttpServletResponse response, String name, String value, String path) {
    setCookie(response, name, value, path, 60 * 60 * 24);
  }

  /**
   * 设置 Cookie
   * 
   * @param name 名称
   * @param value 值
   * @param maxAge 生存时间（单位秒）
   * @param uri 路径
   */
  public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
    setCookie(response, name, value, "/", maxAge);
  }

  /**
   * 设置 Cookie
   * 
   * @param name 名称
   * @param value 值
   * @param maxAge 生存时间（单位秒）
   * @param uri 路径
   */
  public static void setCookie(HttpServletResponse response, String name, String value,
      String path, int maxAge) {
    Cookie cookie = new Cookie(name, null);
    cookie.setPath(path);
    cookie.setMaxAge(maxAge);
    try {
      cookie.setValue(URLEncoder.encode(value, "utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    response.addCookie(cookie);
  }

  /**
   * 获得指定Cookie的值
   * 
   * @param name 名称
   * @return 值
   */
  public static String getCookie(HttpServletRequest request, String name) {
    return getCookie(request, null, name, false);
  }

  /**
   * 获得指定Cookie的值，并删除。
   * 
   * @param name 名称
   * @return 值
   */
  public static String getCookie(HttpServletRequest request, HttpServletResponse response,
      String name) {
    return getCookie(request, response, name, true);
  }

  /**
   * 获得指定Cookie的值
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @param name 名字
   * @param isRemove 是否移除
   * @return 值
   */
  public static String getCookie(HttpServletRequest request, HttpServletResponse response,
      String name, boolean isRemove) {
    String value = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(name)) {
          try {
            value = URLDecoder.decode(cookie.getValue(), "utf-8");
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
          if (isRemove) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
          }
        }
      }
    }
    return value;
  }
}
