package com.example.jdbcdemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// Create table
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50))";
        jdbcTemplate.execute(sql);
    }
    
    
    public void addUser(User user) {
    	String sql = "INSERT INTO users (id,name,email) values(?,?,?)";
    	jdbcTemplate.update(sql, user.getId(),user.getName(), user.getEmail());
    }
    
    public void deleteUserById(int id) {
    	String sql = "DELETE  FROM users where id = ? ";
    	jdbcTemplate.update(sql, id);
    }
    
    public void updateUserEmail(int id,String email) {
    	String sql = "UPDATE users SET email =? where id=?";
    	jdbcTemplate.update(sql, email,id);
    }
    
    
    public List<User> getAllUsers() {
    	String sql = " SELECT * from users";
    	
    	return jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(rs.getInt("id"),
								rs.getString("name"),
								rs.getString("email"));
			}
    		
    	});
    }
    	 
}
