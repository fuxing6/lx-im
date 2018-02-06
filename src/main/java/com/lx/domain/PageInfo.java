package com.lx.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import com.lx.utils.CookieUtil;
import com.lx.utils.StringUtils;

public class PageInfo<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  // 当前页
  private int pageNum;
  // 每页的数量
  private int pageSize;
  // 总记录数
  private long total;
  // 总页数
  private int pages;
  // 结果集
  private List<T> list;
  // 是否为第一页
  private boolean isFirstPage = false;
  // 是否为最后一页
  private boolean isLastPage = false;

  private String orderTableAlias;

  private String orderColumn;

  private String orderBy;

  public PageInfo() {}

  public PageInfo(HttpServletRequest request, HttpServletResponse response) {
    this(request, response, -2);
  }

  /**
   * 构造方法
   * 
   * @param request 传递 repage 参数，来记住页码
   * @param response 用于设置 Cookie，记住页码
   * @param defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
   */
  public PageInfo(HttpServletRequest request, HttpServletResponse response, int defaultPageSize) {
    // 设置页码参数（传递repage参数，来记住页码）
    String no = request.getParameter("pageNo");
    if (StringUtils.isNumeric(no)) {
      CookieUtil.setCookie(response, "pageNo", no);
      this.setPageNum(Integer.parseInt(no));
    } else if (request.getParameter("repage") != null) {
      no = CookieUtil.getCookie(request, "pageNo");
      if (StringUtils.isNumeric(no)) {
        this.setPageNum(Integer.parseInt(no));
      }
    }
    // 设置页面大小参数（传递repage参数，来记住页码大小）
    String size = request.getParameter("pageSize");
    if (StringUtils.isNumeric(size)) {
      CookieUtil.setCookie(response, "pageSize", size);
      this.setPageSize(Integer.parseInt(size));
    } else if (request.getParameter("repage") != null) {
      no = CookieUtil.getCookie(request, "pageSize");
      if (StringUtils.isNumeric(size)) {
        this.setPageSize(Integer.parseInt(size));
      }
    } else if (defaultPageSize != -2) {
      this.pageSize = defaultPageSize;
    }
  }

  public void setPage(Page<?> page) {
    this.pageNum = page.getPageNum();
    this.pageSize = page.getPageSize();
    this.pages = page.getPages();
    this.total = page.getTotal();
  }

  /**
   * 包装Page对象
   *
   * @param list
   */
  public PageInfo(List<T> list) {
    if (list instanceof Page) {
      Page<T> page = (Page<T>) list;
      this.pageNum = page.getPageNum();
      this.pageSize = page.getPageSize();

      this.pages = page.getPages();
      this.list = page;
      this.total = page.getTotal();
    } else if (list instanceof Collection) {
      this.pageNum = 1;
      this.pageSize = list.size();

      this.pages = 1;
      this.list = list;
      this.total = list.size();
    }
    if (list instanceof Collection) {
      // 判断页面边界
      judgePageBoudary();
    }
  }

  /**
   * 判定页面边界
   */
  private void judgePageBoudary() {
    isFirstPage = pageNum == 1;
    isLastPage = pageNum == pages;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public boolean isIsFirstPage() {
    return isFirstPage;
  }

  public void setIsFirstPage(boolean isFirstPage) {
    this.isFirstPage = isFirstPage;
  }

  public boolean isIsLastPage() {
    return isLastPage;
  }

  public void setIsLastPage(boolean isLastPage) {
    this.isLastPage = isLastPage;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("PageInfo{");
    sb.append("pageNum=").append(pageNum);
    sb.append(", pageSize=").append(pageSize);
    sb.append(", total=").append(total);
    sb.append(", pages=").append(pages);
    sb.append(", list=").append(list);
    sb.append(", isFirstPage=").append(isFirstPage);
    sb.append(", isLastPage=").append(isLastPage);
    sb.append(", navigatepageNums=");
    sb.append('}');
    return sb.toString();
  }

  public String getOrderColumn() {
    return orderColumn;
  }

  public void setOrderColumn(String orderColumn) {
    this.orderColumn = orderColumn;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getOrderTableAlias() {
    return orderTableAlias;
  }

  public void setOrderTableAlias(String orderTableAlias) {
    this.orderTableAlias = orderTableAlias;
  }

}
