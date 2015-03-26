package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.User;

public class UserManager {
	
	DataSource ds;
	
	public UserManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HomeworkDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void createUser(User newUser){
		String sql = "INSERT INTO user (username,password,firstName,lastName,email,dateOfBirth) VALUES(?,?,?,?,?,?)";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, newUser.getUsername());
			stm.setString(2, newUser.getPassword());
			stm.setString(3, newUser.getFirstName());
			stm.setString(4, newUser.getLastName());
			stm.setString(5, newUser.getEmail());
			stm.setDate(6, newUser.getDateOfBirth());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<User> readAllUsers(){
		String sql = "SELECT * FROM user";
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();
			while(result.next()){
				String username = result.getString("username");
				String password = result.getString("password");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String email = result.getString("email");
				Date dateOfBirth = result.getDate("dateOfBirth");
				User user = new User(username, password, firstName, lastName, email, dateOfBirth);
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	User readUser(String username){
		String sql = "SELECT * FROM user WHERE username = ?";
		User user = new User();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			ResultSet result = stm.executeQuery();
			result.next();
			String password = result.getString("password");
			String firstName = result.getString("firstName");
			String lastName = result.getString("lastName");
			String email = result.getString("email");
			Date dateOfBirth = result.getDate("dateOfBirth");
			
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setDateOfBirth(dateOfBirth);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	void updateUser(String username, User user){
		String sql = "UPDATE user SET username = ?, password = ?, firstName = ?, lastName = ?, email = ?, dateOfBirth = ? WHERE username = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getFirstName());
			stm.setString(4, user.getLastName());
			stm.setString(5, user.getEmail());
			stm.setDate(6, user.getDateOfBirth());
			stm.setString(7, username);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void deleteUser(String username){
		String sql = "DELETE FROM user WHERE username = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
