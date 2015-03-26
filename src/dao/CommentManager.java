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

import entity.Comment;

public class CommentManager {
	
	DataSource ds;
	
	public CommentManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HomeworkDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void createManager(Comment newComment){
		String sql = "INSERT INTO comment (username, moiveId, comment, date) VALUES(?,?,?,?)";//Assuming auto increment id
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, newComment.getUsername());
			stm.setInt(2, newComment.getMovieId());
			stm.setString(3, newComment.getComment());
			stm.setDate(4, newComment.getDate());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<Comment> readAllComments(){
		String sql = "SELECT * FROM comment";
		List<Comment> comments = new ArrayList<Comment>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();
			while(result.next()){
				String username = result.getString("username");
				int movieId = result.getInt("movieId");
				String comment = result.getString("comment");
				Date date = result.getDate("date");
				Comment cmnt = new Comment(comment, date, username, movieId);
				comments.add(cmnt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	List<Comment> readAllCommentsForUsername(String username){
		String sql = "SELECT * FROM comment WHERE username = ?;";
		List<Comment> comments = new ArrayList<Comment>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			ResultSet result = stm.executeQuery();
			while(result.next()){
				String userName = result.getString("username");
				int movieId = result.getInt("movieId");
				String comment = result.getString("comment");
				Date date = result.getDate("date");
				Comment cmnt = new Comment(comment, date, userName, movieId);
				comments.add(cmnt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	List<Comment> readAllCommentsForMovie(String movieId){
		String sql = "SELECT * FROM comment WHERE movieId = ?;";
		List<Comment> comments = new ArrayList<Comment>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(movieId));
			ResultSet result = stm.executeQuery();
			while(result.next()){
				String username = result.getString("username");
				int movieid = result.getInt("movieId");
				String comment = result.getString("comment");
				Date date = result.getDate("date");
				Comment cmnt = new Comment(comment, date, username, movieid);
				comments.add(cmnt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	Comment readCommentForId(String commentId){
		String sql = "SELECT * FROM comment WHERE commentId = ?;";
		Comment cmnt = new Comment();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(commentId));
			ResultSet result = stm.executeQuery();
			
			result.next();
			int commentid = result.getInt("commentId");
			String username = result.getString("username");
			int movieId = result.getInt("movieId");
			String comment = result.getString("comment");
			Date date = result.getDate("date");
			
			cmnt.setCommentId(commentid);
			cmnt.setUsername(username);
			cmnt.setMovieId(movieId);
			cmnt.setComment(comment);
			cmnt.setDate(date);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cmnt;
	}
	
	void updateComment(String commentId, String newComment){
		String sql = "UPDATE comment SET comment = ? WHERE commentId = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, newComment);
			stm.setInt(2, Integer.parseInt(commentId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void deleteComment(String commentId){
		String sql = "DELETE FROM comment WHERE commentId = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(commentId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
