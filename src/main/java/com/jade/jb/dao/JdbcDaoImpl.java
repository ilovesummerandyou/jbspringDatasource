package com.jade.jb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.jade.jb.model.Circle;


@Component
public class JdbcDaoImpl {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Circle getCircle(int id) {
		Connection conn = null;
		
		try{
//			String driver = "com.mysql.jdbc.Driver";			
//			Class.forName(driver).newInstance();
//			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/springdemo?" +
//                                   "user=root&password=root1234");
			conn = (Connection) dataSource.getConnection();
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM circle WHERE id = ?");
			ps.setInt(1, id);
			
			Circle circle = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				circle = new Circle(id, rs.getString("name"));
			}
			rs.close();
			ps.close();
			
			return circle;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
//	public int geCircleCount(){
//		String sql = "select count(*) from circle";
//		//jdbcTemplate.setDataSource(getDataSource());
//		return jdbcTemplate.queryForInt(sql);
//	}
//	
	public String getCircleName(int id) {
		String sql = "select name from circle where id = ?";
		return (String) jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
	}
	
	public Circle getCircleForId(int id) {
		String sql = "select * from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new CircleMapper());
		
	}
	
	private static final class CircleMapper implements RowMapper<Circle>{
		public Circle mapRow (ResultSet resultSet, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("id"));
			circle.setName(resultSet.getString("name"));
			return circle;
		}
	}
	
	public List<Circle> getAllCircleList(){
		String sql = "select * from circle";
		return  jdbcTemplate.query(sql,new CircleMapper());

	}
}
