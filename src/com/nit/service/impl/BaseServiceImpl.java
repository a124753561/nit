/**
 *====================================================
 * 文件名称: BaseServiceImpl.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.nit.Filter;
import com.nit.Order;
import com.nit.Page;
import com.nit.Pageable;
import com.nit.dao.BaseDao;
import com.nit.entity.BaseEntity;
import com.nit.service.BaseService;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @ClassName: BaseServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午3:23:28
 */
@Transactional(readOnly = true)
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	/** 更新忽略属性 */
	private static final String[] UPDATE_IGNORE_PROPERTIES = new String[] { BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME,
			BaseEntity.MODIFY_DATE_PROPERTY_NAME };

	/** baseDao */
	private BaseDao<T, ID> baseDao;

	public void setBaseDao(BaseDao<T, ID> baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#find(java.io.Serializable)
	 */
	public T find(ID id) {
		return baseDao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#findAll()
	 */
	@Override
	public List<T> findAll() {
		return findList(null, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#findList(java.io.Serializable[])
	 */
	@Override
	public List<T> findList(ID... ids) {
		List<T> result = new ArrayList<T>();
		if (ids != null) {
			for (ID id : ids) {
				T entity = find(id);
				if (entity != null) {
					result.add(entity);
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#findFirst()
	 */
	@Override
	public T findFirst() {
		List<T> all = this.findAll();
		if (all != null && all.size() > 0) {
			return all.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#findList(java.lang.Integer, java.util.List, java.util.List)
	 */
	@Override
	public List<T> findList(Integer count, List<Filter> filters, List<Order> orders) {
		return findList(null, count, filters, orders);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#findList(java.lang.Integer, java.lang.Integer, java.util.List, java.util.List)
	 */
	@Override
	public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
		return baseDao.findList(first, count, filters, orders);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#findPage(com.nit.Pageable)
	 */
	@Override
	public Page<T> findPage(Pageable pageable) {
		return baseDao.findPage(pageable);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#count()
	 */
	@Override
	public long count() {
		return count(new Filter[] {});
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#count(com.nit.Filter[])
	 */
	@Override
	public long count(Filter... filters) {
		return baseDao.count(filters);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#exists(java.io.Serializable)
	 */
	@Override
	public boolean exists(ID id) {
		return baseDao.find(id) != null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#exists(com.nit.Filter[])
	 */
	@Override
	public boolean exists(Filter... filters) {
		return baseDao.count(filters) > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#save(java.lang.Object)
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		baseDao.persist(entity);

	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#update(java.lang.Object)
	 */
	@Transactional(readOnly = false)
	public T update(T entity) {
		return baseDao.merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#update(java.lang.Object, java.lang.String[])
	 */
	@Transactional(readOnly = false)
	public T update(T entity, String... ignoreProperties) {
		Assert.notNull(entity);
		if (baseDao.isManaged(entity)) {
			throw new IllegalArgumentException("Entity must not be managed");
		}
		T persistant = baseDao.find(baseDao.getIdentifier(entity));
		if (persistant != null) {
			copyProperties(entity, persistant, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
			return update(persistant);
		} else {
			return update(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#delete(java.io.Serializable)
	 */
	@Transactional(readOnly = false)
	public void delete(ID id) {
		delete(baseDao.find(id));

	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#delete(java.io.Serializable[])
	 */
	@Transactional(readOnly = false)
	public void delete(ID... ids) {
		if (ids != null) {
			for (ID id : ids) {
				delete(baseDao.find(id));
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.BaseService#delete(java.lang.Object)
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		baseDao.remove(entity);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object sourceValue = readMethod.invoke(source);
						Object targetValue = readMethod.invoke(target);
						if (sourceValue != null && targetValue != null && targetValue instanceof Collection) {
							Collection collection = (Collection) targetValue;
							collection.clear();
							collection.addAll((Collection) sourceValue);
						} else {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, sourceValue);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}
}
