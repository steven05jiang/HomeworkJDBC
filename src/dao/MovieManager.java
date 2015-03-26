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

import entity.Movie;

public class MovieManager {
	
	DataSource ds;
	
	public MovieManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HomeworkDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void createMovie(Movie newMovie){
		String sql = "INSERT INTO movie (id,title,posterImage,releaseDate) VALUES(?,?,?,?);";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, newMovie.getId());
			stm.setString(2, newMovie.getTitle());
			stm.setString(3, newMovie.getPosterImage());
			stm.setDate(4, newMovie.getReleaseDate());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	List<Movie> readAllMovies(){
		String sql = "SELECT * FROM movie";
		List<Movie> movies = new ArrayList<Movie>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();
			while(result.next()){
				int id = result.getInt("id");
				String title = result.getString("title");
				String posterImage = result.getString("posterImage");
				Date releaseDate = result.getDate("releaseDate");
				Movie movie = new Movie(id, title, posterImage, releaseDate);
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	Movie readMovie(String movieId){
		String sql = "SELECT * FROM movie WHERE id = ?";
		Movie movie = new Movie();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(movieId));
			ResultSet result = stm.executeQuery();
			result.next();
			int id = result.getInt("id");
			String title = result.getString("title");
			String posterImage = result.getString("posterImage");
			Date releaseDate = result.getDate("releaseDate");
			
			movie.setId(id);
			movie.setTitle(title);
			movie.setPosterImage(posterImage);
			movie.setReleaseDate(releaseDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	void updateMovie(String movieId, Movie movie){
		String sql = "UPDATE movie SET id = ?, title = ?, posterImage = ?, releaseDate = ? WHERE id = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, movie.getId());
			stm.setString(2, movie.getTitle());
			stm.setString(3, movie.getPosterImage());
			stm.setDate(4, movie.getReleaseDate());
			stm.setInt(5, Integer.parseInt(movieId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void deleteMovie(String movieId){
		String sql = "DELETE FROM movie WHERE id = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(movieId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
