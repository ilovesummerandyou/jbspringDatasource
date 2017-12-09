package com.jade.jb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jade.jb.dao.HibernateDaoImpl;
import com.jade.jb.dao.JdbcDaoImpl;
import com.jade.jb.dao.SimpleJdbcDaoImpl;
import com.jade.jb.model.Circle;


public class JdbcDemo {
	
	public static void main(String[] args){
		 //Circle circle = new JdbcDaoImpl().getCircle(1);
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		 JdbcDaoImpl jdbcDaoImpl = (JdbcDaoImpl) ctx.getBean("jdbcDaoImpl");
		 //System.out.println(jdbcDaoImpl.getCircle(1).getName());
		 
		 //System.out.println(jdbcDaoImpl.geCircleCount());
		 
		 //System.out.println(jdbcDaoImpl.getCircleForId(1).getName());
		 //List<Circle> l = jdbcDaoImpl.getAllCircleList();
		 //System.out.println(l.size());
		 
//		 SimpleJdbcDaoImpl dao = (SimpleJdbcDaoImpl) ctx.getBean("simeJdbcDaoImpl");
//		 System.out.println(dao.geCircleCount());
		 
		 HibernateDaoImpl dao = (HibernateDaoImpl) ctx.getBean("hibernateDaoImpl");
		 System.out.println(dao.geCircleCount());
		 
		 
	}
	
}
