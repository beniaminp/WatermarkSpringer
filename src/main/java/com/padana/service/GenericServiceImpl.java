package com.padana.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.padana.dao.GenericDao;

@Service("genericService")
@Transactional
public class GenericServiceImpl<T> implements GenericService<T> {

	@Autowired
	private GenericDao genericDao;

	public <T> T get(final Class<T> clazz, final Long obj) {
		return (T) genericDao.get(clazz, obj);
	}
	
	public List<T> getAll(final Class<T> clazz) {
		return genericDao.getAll(clazz);
	}
	
	public <T> void update(final T obj) {
		genericDao.update(obj);
	}
	
	public void delete(final Class<T> clazz, final Long obj) {
		genericDao.delete(clazz, obj);

	}
	
	public Serializable save(final Class<T> clazz, final T obj) {
		return genericDao.save(clazz, obj);
	}

	public void saveOrUpdate(Class<T> clazz, T obj) {
		genericDao.saveOrUpdate(clazz, obj);
	}
	
	public <T> T getFiltered(Class<T> clazz, T ...obj ) {
		return (T) genericDao.getFiltered(clazz, obj);
	}
	
	public <T> List<T> getListFiltered(Class<T> clazz, T ... obj) {
		return (List<T>) genericDao.getListFiltered(clazz, obj);
	}
	
	public <T> List<T> getNEListFiltered(Class<T> clazz, T... obj){
		return (List<T>) genericDao.getNEListFiltered(clazz, obj);
	}
}