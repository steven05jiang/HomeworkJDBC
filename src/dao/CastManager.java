package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.Cast;

public class CastManager {
	
	DataSource ds;
	
	public CastManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HomeworkDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void createCast(Cast newCast){
		String sql = "INSERT INTO cast (characterName, moiveId, actorId) VALUES(?,?,?);";//Assuming auto increment id
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, newCast.getCharacterName());
			stm.setInt(2, newCast.getMovieId());
			stm.setInt(3, newCast.getActorId());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<Cast> readAllCast(){
		String sql = "SELECT * FROM cast";
		List<Cast> casts = new ArrayList<Cast>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();
			while(result.next()){
				String characterName = result.getString("characterName");
				int movieId = result.getInt("movieId");
				int actorId = result.getInt("actorId");
				Cast cast = new Cast(characterName, movieId, actorId);
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}
	
	List<Cast> readAllCastForActor(String actorId){
		String sql = "SELECT * FROM cast WHERE actorId = ?;";
		List<Cast> casts = new ArrayList<Cast>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(actorId));
			ResultSet result = stm.executeQuery();
			while(result.next()){
				String characterName = result.getString("characterName");
				int movieId = result.getInt("movieId");
				int actorid = result.getInt("actorId");
				Cast cast = new Cast(characterName, movieId, actorid);
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}
	
	List<Cast> readAllCastForMovie(String movieId){
		String sql = "SELECT * FROM cast WHERE movieId = ?;";
		List<Cast> casts = new ArrayList<Cast>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(movieId));
			ResultSet result = stm.executeQuery();
			while(result.next()){
				String characterName = result.getString("characterName");
				int movieid = result.getInt("movieId");
				int actorid = result.getInt("actorId");
				Cast cast = new Cast(characterName, movieid, actorid);
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}
	
	Cast readCastForId(String castId){
		String sql = "SELECT * FROM cast WHERE castId = ?;";
		Cast cast = new Cast();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(castId));
			ResultSet result = stm.executeQuery();
			
			result.next();
			int castid = result.getInt("castId");
			String characterName = result.getString("characterName");
			int movieId = result.getInt("movieId");
			int actorId = result.getInt("actorId");
			
			cast.setCastId(castid);
			cast.setCharacterName(characterName);
			cast.setActorId(actorId);
			cast.setMovieId(movieId);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cast;
	}
	
	void updateCast(String castId, Cast newCast){
		String sql = "UPDATE cast SET characterName = ?, actorId = ?, movieId = ? WHERE castId = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, newCast.getCharacterName());
			stm.setInt(1, newCast.getActorId());
			stm.setInt(1, newCast.getMovieId());
			stm.setInt(4, Integer.parseInt(castId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void deleteCast(String castId){
		String sql = "DELETE FROM cast WHERE castId = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(castId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
