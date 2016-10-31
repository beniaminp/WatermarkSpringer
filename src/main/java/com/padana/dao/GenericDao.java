package com.padana.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {
	<T> T get(final Class<T> clazz, Long id);
	
	List<T> getAll(final Class<T> clazz);

	void update(T obj);
	
	void delete(final Class<T> clazz, Long obj);

	Serializable save(final Class<T> clazz, T obj);
	
	void saveOrUpdate(final Class<T> clazz, T obj);
	
	<T> T getFiltered(final Class<T> clazz, T ... obj);
	
	<T> List<T> getListFiltered(final Class<T> clazz, T ... obj);
	
	<T> List<T> getNEListFiltered(Class<T> clazz, T... obj);
}