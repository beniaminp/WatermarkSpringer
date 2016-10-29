package com.padana.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateDao {

	@Autowired
	private SessionFactory sessionFactory;

	private DataSource dataSource;

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setDs(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}