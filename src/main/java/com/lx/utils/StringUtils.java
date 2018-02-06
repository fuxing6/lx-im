/**
 * huijin
 */
package com.lx.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * 
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

  private static final char SEPARATOR = '_';
  private static final String CHARSET_NAME = "UTF-8";
  private static final char UNDERLINE = '_';

  public static boolean isAllNotEmpty(String... strs) {
    if (strs == null || strs.length == 0) {
      return false;
    }
    for (String s : strs) {
      if (isEmpty(s)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 转换为字节数组
   * 
   * @param str
   * @return
   */
  public static byte[] getBytes(String str) {
    if (str != null) {
      try {
        return str.getBytes(CHARSET_NAME);
      } catch (UnsupportedEncodingException e) {
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * 转换为字节数组
   * 
   * @param str
   * @return
   */
  public static String toString(byte[] bytes) {
    try {
      return new String(bytes, CHARSET_NAME);
    } catch (UnsupportedEncodingException e) {
      return EMPTY;
    }
  }

  public static String concat(String... strs) {
    if (strs == null) {
      return null;
    }
    StringBuilder sbuilder = new StringBuilder();
    for (String str : strs) {
      if (isNotBlank(str)) {
        sbuilder.append(str);
      }
    }
    if (sbuilder.length() == 0) {
      return null;
    }
    return sbuilder.toString();
  }

  /**
   * 是否包含字符串
   * 
   * @param str 验证字符串
   * @param strs 字符串组
   * @return 包含返回true
   */
  public static boolean inString(String str, String... strs) {
    if (str != null) {
      for (String s : strs) {
        if (str.equals(trim(s))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 替换掉HTML标签方法
   */
  public static String replaceHtml(String html) {
    if (isBlank(html)) {
      return "";
    }
    String regEx = "<.+?>";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(html);
    String s = m.replaceAll("");
    return s;
  }

  /**
   * 替换为手机识别的HTML，去掉样式及属性，保留回车。
   * 
   * @param html
   * @return
   */
  public static String replaceMobileHtml(String html) {
    if (html == null) {
      return "";
    }
    return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
  }

  public static String abbr2(String param, int length) {
    if (param == null) {
      return "";
    }
    StringBuffer result = new StringBuffer();
    int n = 0;
    char temp;
    boolean isCode = false; // 是不是HTML代码
    boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
    for (int i = 0; i < param.length(); i++) {
      temp = param.charAt(i);
      if (temp == '<') {
        isCode = true;
      } else if (temp == '&') {
        isHTML = true;
      } else if (temp == '>' && isCode) {
        n = n - 1;
        isCode = false;
      } else if (temp == ';' && isHTML) {
        isHTML = false;
      }
      try {
        if (!isCode && !isHTML) {
          n += String.valueOf(temp).getBytes("GBK").length;
        }
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      if (n <= length - 3) {
        result.append(temp);
      } else {
        result.append("...");
        break;
      }
    }
    // 取出截取字符串中的HTML标记
    String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
    // 去掉不需要结素标记的HTML标记
    temp_result =
        temp_result
            .replaceAll(
                "</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
                "");
    // 去掉成对的HTML标记
    temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
    // 用正则表达式取出标记
    Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
    Matcher m = p.matcher(temp_result);
    List<String> endHTML = Lists.newArrayList();
    while (m.find()) {
      endHTML.add(m.group(1));
    }
    // 补全不成对的HTML标记
    for (int i = endHTML.size() - 1; i >= 0; i--) {
      result.append("</");
      result.append(endHTML.get(i));
      result.append(">");
    }
    return result.toString();
  }

  /**
   * 转换为Double类型
   */
  public static Double toDouble(Object val) {
    if (val == null) {
      return 0D;
    }
    try {
      return Double.valueOf(trim(val.toString()));
    } catch (Exception e) {
      return 0D;
    }
  }

  /**
   * 转换为Float类型
   */
  public static Float toFloat(Object val) {
    return toDouble(val).floatValue();
  }

  /**
   * 转换为Long类型
   */
  public static Long toLong(Object val) {
    return toDouble(val).longValue();
  }

  /**
   * 转换为Integer类型
   */
  public static Integer toInteger(Object val) {
    return toLong(val).intValue();
  }

  /**
   * 获得用户远程地址
   */
  public static String getRemoteAddr(HttpServletRequest request) {
    String remoteAddr = request.getHeader("X-Real-IP");
    if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("X-Forwarded-For");
    } else if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("Proxy-Client-IP");
    } else if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("WL-Proxy-Client-IP");
    }
    return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
  }

  /**
   * 驼峰命名法工具
   * 
   * @return toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") ==
   *         "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toCamelCase(String s) {
    if (s == null) {
      return null;
    }

    s = s.toLowerCase();

    StringBuilder sb = new StringBuilder(s.length());
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == SEPARATOR) {
        upperCase = true;
      } else if (upperCase) {
        sb.append(Character.toUpperCase(c));
        upperCase = false;
      } else {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  /**
   * 驼峰命名法工具
   * 
   * @return toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") ==
   *         "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toCapitalizeCamelCase(String s) {
    if (s == null) {
      return null;
    }
    s = toCamelCase(s);
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }

  /**
   * 驼峰命名法工具
   * 
   * @return toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") ==
   *         "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
   */
  public static String toUnderScoreCase(String s) {
    if (s == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    boolean upperCase = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      boolean nextUpperCase = true;

      if (i < (s.length() - 1)) {
        nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
      }

      if ((i > 0) && Character.isUpperCase(c)) {
        if (!upperCase || !nextUpperCase) {
          sb.append(SEPARATOR);
        }
        upperCase = true;
      } else {
        upperCase = false;
      }

      sb.append(Character.toLowerCase(c));
    }

    return sb.toString();
  }

  /**
   * 如果不为空，则设置值
   * 
   * @param target
   * @param source
   */
  public static void setValueIfNotBlank(String target, String source) {
    if (isNotBlank(source)) {
      target = source;
    }
  }

  /**
   * 转换为JS获取对象值，生成三目运算返回结果
   * 
   * @param objectString 对象串 例如：row.user.id 返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
   */
  public static String jsGetVal(String objectString) {
    StringBuilder result = new StringBuilder();
    StringBuilder val = new StringBuilder();
    String[] vals = split(objectString, ".");
    for (int i = 0; i < vals.length; i++) {
      val.append("." + vals[i]);
      result.append("!" + (val.substring(1)) + "?'':");
    }
    result.append(val.substring(1));
    return result.toString();
  }

  /** 空字符串。 */
  public static final String EMPTY_STRING = "";

  /**
   * 比较两个字符串（大小写敏感）。
   * 
   * <pre>
   * StringUtil.equals(null, null)   = true
   * StringUtil.equals(null, "abc")  = false
   * StringUtil.equals("abc", null)  = false
   * StringUtil.equals("abc", "abc") = true
   * StringUtil.equals("abc", "ABC") = false
   * </pre>
   *
   * @param str1 要比较的字符串1
   * @param str2 要比较的字符串2
   *
   * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
   */
  public static boolean equals(String str1, String str2) {
    if (str1 == null) {
      return str2 == null;
    }

    return str1.equals(str2);
  }

  /**
   * 比较两个字符串（大小写不敏感）。
   * 
   * <pre>
   * StringUtil.equalsIgnoreCase(null, null)   = true
   * StringUtil.equalsIgnoreCase(null, "abc")  = false
   * StringUtil.equalsIgnoreCase("abc", null)  = false
   * StringUtil.equalsIgnoreCase("abc", "abc") = true
   * StringUtil.equalsIgnoreCase("abc", "ABC") = true
   * </pre>
   *
   * @param str1 要比较的字符串1
   * @param str2 要比较的字符串2
   *
   * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
   */
  public static boolean equalsIgnoreCase(String str1, String str2) {
    if (str1 == null) {
      return str2 == null;
    }

    return str1.equalsIgnoreCase(str2);
  }

  /**
   * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
   * 
   * <pre>
   * StringUtil.isBlank(null)      = true
   * StringUtil.isBlank("")        = true
   * StringUtil.isBlank(" ")       = true
   * StringUtil.isBlank("bob")     = false
   * StringUtil.isBlank("  bob  ") = false
   * </pre>
   *
   * @param str 要检查的字符串
   *
   * @return 如果为空白, 则返回<code>true</code>
   */
  public static boolean isBlank(String str) {
    int length;

    if ((str == null) || ((length = str.length()) == 0)) {
      return true;
    }

    for (int i = 0; i < length; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
   * 
   * <pre>
   * StringUtil.isBlank(null)      = false
   * StringUtil.isBlank("")        = false
   * StringUtil.isBlank(" ")       = false
   * StringUtil.isBlank("bob")     = true
   * StringUtil.isBlank("  bob  ") = true
   * </pre>
   *
   * @param str 要检查的字符串
   *
   * @return 如果为空白, 则返回<code>true</code>
   */
  public static boolean isNotBlank(String str) {
    int length;

    if ((str == null) || ((length = str.length()) == 0)) {
      return false;
    }

    for (int i = 0; i < length; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }

    return false;
  }

  /**
   * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
   * 
   * <pre>
   * StringUtil.isEmpty(null)      = true
   * StringUtil.isEmpty("")        = true
   * StringUtil.isEmpty(" ")       = false
   * StringUtil.isEmpty("bob")     = false
   * StringUtil.isEmpty("  bob  ") = false
   * </pre>
   *
   * @param str 要检查的字符串
   *
   * @return 如果为空, 则返回<code>true</code>
   */
  public static boolean isEmpty(String str) {
    return ((str == null) || (str.length() == 0));
  }

  /**
   * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
   * 
   * <pre>
   * StringUtil.isEmpty(null)      = false
   * StringUtil.isEmpty("")        = false
   * StringUtil.isEmpty(" ")       = true
   * StringUtil.isEmpty("bob")     = true
   * StringUtil.isEmpty("  bob  ") = true
   * </pre>
   *
   * @param str 要检查的字符串
   *
   * @return 如果不为空, 则返回<code>true</code>
   */
  public static boolean isNotEmpty(String str) {
    return ((str != null) && (str.length() > 0));
  }

  /**
   * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
   * 
   * <pre>
   * StringUtil.indexOf(null, *)          = -1
   * StringUtil.indexOf(*, null)          = -1
   * StringUtil.indexOf("", "")           = 0
   * StringUtil.indexOf("aabaabaa", "a")  = 0
   * StringUtil.indexOf("aabaabaa", "b")  = 2
   * StringUtil.indexOf("aabaabaa", "ab") = 1
   * StringUtil.indexOf("aabaabaa", "")   = 0
   * </pre>
   *
   * @param str 要扫描的字符串
   * @param searchStr 要查找的字符串
   *
   * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
   */
  public static int indexOf(String str, String searchStr) {
    if ((str == null) || (searchStr == null)) {
      return -1;
    }

    return str.indexOf(searchStr);
  }

  /**
   * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
   * 
   * <pre>
   * StringUtil.indexOf(null, *, *)          = -1
   * StringUtil.indexOf(*, null, *)          = -1
   * StringUtil.indexOf("", "", 0)           = 0
   * StringUtil.indexOf("aabaabaa", "a", 0)  = 0
   * StringUtil.indexOf("aabaabaa", "b", 0)  = 2
   * StringUtil.indexOf("aabaabaa", "ab", 0) = 1
   * StringUtil.indexOf("aabaabaa", "b", 3)  = 5
   * StringUtil.indexOf("aabaabaa", "b", 9)  = -1
   * StringUtil.indexOf("aabaabaa", "b", -1) = 2
   * StringUtil.indexOf("aabaabaa", "", 2)   = 2
   * StringUtil.indexOf("abc", "", 9)        = 3
   * </pre>
   *
   * @param str 要扫描的字符串
   * @param searchStr 要查找的字符串
   * @param startPos 开始搜索的索引值，如果小于0，则看作0
   *
   * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
   */
  public static int indexOf(String str, String searchStr, int startPos) {
    if ((str == null) || (searchStr == null)) {
      return -1;
    }

    // JDK1.3及以下版本的bug：不能正确处理下面的情况
    if ((searchStr.length() == 0) && (startPos >= str.length())) {
      return str.length();
    }

    return str.indexOf(searchStr, startPos);
  }

  /**
   * 取指定字符串的子串。
   * 
   * <p>
   * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
   * 
   * <pre>
   * StringUtil.substring(null, *, *)    = null
   * StringUtil.substring("", * ,  *)    = "";
   * StringUtil.substring("abc", 0, 2)   = "ab"
   * StringUtil.substring("abc", 2, 0)   = ""
   * StringUtil.substring("abc", 2, 4)   = "c"
   * StringUtil.substring("abc", 4, 6)   = ""
   * StringUtil.substring("abc", 2, 2)   = ""
   * StringUtil.substring("abc", -2, -1) = "b"
   * StringUtil.substring("abc", -4, 2)  = "ab"
   * </pre>
   * 
   * </p>
   *
   * @param str 字符串
   * @param start 起始索引，如果为负数，表示从尾部计算
   * @param end 结束索引（不含），如果为负数，表示从尾部计算
   *
   * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
   */
  public static String substring(String str, int start, int end) {
    if (str == null) {
      return null;
    }

    if (end < 0) {
      end = str.length() + end;
    }

    if (start < 0) {
      start = str.length() + start;
    }

    if (end > str.length()) {
      end = str.length();
    }

    if (start > end) {
      return EMPTY_STRING;
    }

    if (start < 0) {
      start = 0;
    }

    if (end < 0) {
      end = 0;
    }

    return str.substring(start, end);
  }

  /**
   * 检查字符串中是否包含指定的字符串。如果字符串为<code>null</code>，将返回<code>false</code>。
   * 
   * <pre>
   * StringUtil.contains(null, *)     = false
   * StringUtil.contains(*, null)     = false
   * StringUtil.contains("", "")      = true
   * StringUtil.contains("abc", "")   = true
   * StringUtil.contains("abc", "a")  = true
   * StringUtil.contains("abc", "z")  = false
   * </pre>
   *
   * @param str 要扫描的字符串
   * @param searchStr 要查找的字符串
   *
   * @return 如果找到，则返回<code>true</code>
   */
  public static boolean contains(String str, String searchStr) {
    if ((str == null) || (searchStr == null)) {
      return false;
    }

    return str.indexOf(searchStr) >= 0;
  }

  /**
   * <p>
   * Checks if the String contains only unicode digits. A decimal point is not a unicode digit and
   * returns false.
   * </p>
   *
   * <p>
   * <code>null</code> will return <code>false</code>. An empty String ("") will return
   * <code>true</code>.
   * </p>
   *
   * <pre>
   * StringUtils.isNumeric(null)   = false
   * StringUtils.isNumeric("")     = true
   * StringUtils.isNumeric("  ")   = false
   * StringUtils.isNumeric("123")  = true
   * StringUtils.isNumeric("12 3") = false
   * StringUtils.isNumeric("ab2c") = false
   * StringUtils.isNumeric("12-3") = false
   * StringUtils.isNumeric("12.3") = false
   * </pre>
   *
   * @param str the String to check, may be null
   * @return <code>true</code> if only contains digits, and is non-null
   */
  public static boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    int sz = str.length();
    for (int i = 0; i < sz; i++) {
      if (Character.isDigit(str.charAt(i)) == false) {
        return false;
      }
    }
    return true;
  }

  /**
   * 驼峰转下划线
   * 
   * @param param
   * @return
   */
  public static String camelToUnderline(String param) {
    if (param == null || "".equals(param.trim())) {
      return "";
    }
    int len = param.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = param.charAt(i);
      if (Character.isUpperCase(c)) {
        sb.append(UNDERLINE);
        sb.append(Character.toLowerCase(c));
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  /**
   * 下划线转驼峰
   * 
   * @param param
   * @return
   */
  public static String underlineToCamel(String param) {
    if (param == null || "".equals(param.trim())) {
      return "";
    }
    int len = param.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = param.charAt(i);
      if (c == UNDERLINE) {
        if (++i < len) {
          sb.append(Character.toUpperCase(param.charAt(i)));
        }
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }
}
