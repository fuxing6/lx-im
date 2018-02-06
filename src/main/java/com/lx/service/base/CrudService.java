package com.lx.service.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lx.dao.base.CrudDao;
import com.lx.domain.DataEntity;
import com.lx.domain.PageInfo;
import com.lx.utils.StringUtils;

public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>>
    extends BaseService {

  protected abstract D dao();

  /**
   * 获取单条数据
   * 
   * @param entity
   * @return
   */
  public T get(T entity) {
    T entityRs = null;
    if (StringUtils.isNotBlank(entity.getId())) {
      entityRs = dao().get(entity);
    }
    if (entityRs == null) {
      return null;
    }
    return entityRs;
  }


  /**
   * 查询列表数据
   * 
   * @param entity
   * @return
   */
  public List<T> findList(T entity) {
    return dao().findList(entity);
  }

  /**
   * 查询分页数据
   * 
   * @param page 分页对象
   * @param entity
   * @return
   */
  public PageInfo<T> findPage(PageInfo<T> page, T entity) {
    PageHelper.startPage(page.getPageNum(), page.getPageSize());
    Page<T> findPage = dao().findPage(entity);
    return new PageInfo<T>(findPage);
  }

  /**
   * 查询分页数据
   * 
   * @param page 分页对象
   * @param entity
   * @return
   */
  public Page<String> findPageIds(PageInfo<T> page, T entity) {
    PageHelper.startPage(page.getPageNum(), page.getPageSize());
    return dao().findPageIds(entity);
  }

  /**
   * 保存数据（插入或更新）
   * 
   * @param entity
   */
  public void save(T entity) {
    if (entity.isNewRecord()) {
      entity.preInsert();
      dao().insert(entity);
    } else {
      entity.preUpdate();
      dao().update(entity);
    }
  }

  /**
   * 删除数据
   * 
   * @param entity
   */
  @Transactional(readOnly = false)
  public void delete(T entity) {
    dao().delete(entity);
  }

  public String inSQL(List<String> ids) {
    if (!CollectionUtils.isEmpty(ids)) {
      StringBuilder sbuilder = new StringBuilder();
      for (String id : ids) {
        sbuilder.append("'" + id + "',");
      }
      String sqlStr = sbuilder.toString();
      if (StringUtils.isBlank(sqlStr)) {
        return null;
      }
      sqlStr = sqlStr.substring(0, sqlStr.lastIndexOf(","));
      return sqlStr;
    }
    return null;
  }
}
