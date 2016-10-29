package com.padana.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface GenericService<T> {
	<T> T get(final Class<T> clazz, Long obj);

	List<T> getAll(final Class<T> clazz);

	<T> void update(T obj);

	void delete(final Class<T> clazz, Long obj);

	Serializable save(final Class<T> clazz, T obj);

	void saveOrUpdate(final Class<T> clazz, T obj);

	<T> T getFiltered(final Class<T> clazz, T obj);

	<T> List<T> getListFiltered(final Class<T> clazz, T obj);
}