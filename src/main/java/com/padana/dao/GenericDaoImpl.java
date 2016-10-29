package com.padana.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("GenericDao")
public class GenericDaoImpl<T> extends HibernateDao implements GenericDao<T> {

	private Class<T> type;
	private T filter;

	public GenericDaoImpl() {

	}

	public GenericDaoImpl(Class<T> type, T filter) {
		this.type = type;
		this.filter = filter;
	}

	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	public <T> T get(final Class<T> clazz, Long obj) {
		return (T) getSession().get(clazz, (Serializable) obj);
	}

	public List<T> getAll(final Class<T> clazz) {
		Criteria criteria = getSession().createCriteria(clazz);
		return (List<T>) criteria.list();
	}

	public void update(T obj) {
		getSession().update(obj);
	}

	public void delete(final Class<T> clazz, Long obj) {
		getSession().delete(get(clazz, obj));

	}

	public Serializable save(final Class<T> clazz, T obj) {
		return getSession().save(obj);
	}

	public void saveOrUpdate(Class<T> clazz, T obj) {
		getSession().saveOrUpdate(obj);
		
	}

	public <T> List<T> getListFiltered(Class<T> clazz, T obj) {
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.equals(obj);
		return (List<T>) criteria.list();
	}

	public <T> T getFiltered(Class<T> clazz, T obj) {
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.equals(obj);
		return (T) criteria.uniqueResult();
	}

}