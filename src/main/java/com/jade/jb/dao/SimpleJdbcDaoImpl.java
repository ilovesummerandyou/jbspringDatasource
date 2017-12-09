package com.jade.jb.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport{
	
	public int geCircleCount(){
		String sql = "select count(*) from circle";
		//jdbcTemplate.setDataSource(getDataSource());
		return this.getJdbcTemplate().queryForInt(sql);
	}
}
