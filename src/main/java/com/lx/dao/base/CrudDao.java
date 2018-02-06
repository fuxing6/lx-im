package com.lx.dao.base;

import java.util.List;

import com.github.pagehelper.Page;

public interface CrudDao<T> extends BaseDao {

  /**
   * 获取单条数据
   * 
   * @param entity
   * @return
   */
  public T get(T entity);

  /**
   * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
   * 
   * @param entity
   * @return
   */
  public List<T> findList(T entity);

  /**
   * 分页查询列表
   * 
   * @param entity
   * @return
   */
  public Page<T> findPage(T entity);

  /**
   * 分页先查询id
   * 
   * @param entity
   * @return
   */
  public Page<String> findPageIds(T entity);

  /**
   * 查询所有数据列表
   * 
   * @param entity
   * @return
   */
  public List<T> findAllList(T entity);

  /**
   * 插入数据
   * 
   * @param entity
   * @return
   */
  public int insert(T entity);

  /**
   * 更新数据
   * 
   * @param entity
   * 
   * @return
   */
  public int update(T entity);

  /**
   * 删除数据（一般为逻辑删除，更新del_flag字段为1）
   * 
   * @param entity
   * @return
   */
  public int delete(T entity);
}
