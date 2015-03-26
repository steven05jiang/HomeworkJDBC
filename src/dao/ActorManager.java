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

import entity.Actor;

public class ActorManager {

	DataSource ds;

	public ActorManager() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HomeworkDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void createActor(Actor newActor) {
		String sql = "INSERT INTO actor (id,firstName,lastName,dateOfBirth) VALUES(?,?,?,?)";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, newActor.getId());
			stm.setString(2, newActor.getFirstName());
			stm.setString(3, newActor.getLastName());
			stm.setDate(4, newActor.getDateOfBirth());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<Actor> readAllActors() {
		String sql = "SELECT * FROM actor";
		List<Actor> actors = new ArrayList<Actor>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet result = stm.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				Date dateOfBirth = result.getDate("dateOfBirth");
				Actor actor = new Actor(id, firstName, lastName, dateOfBirth);
				actors.add(actor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}

	Actor readActor(String actorId) {
		String sql = "SELECT * FROM actor WHERE id = ?";
		Actor actor = new Actor();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(actorId));
			ResultSet result = stm.executeQuery();
			result.next();
			int id = result.getInt("id");
			String firstName = result.getString("firstName");
			String lastName = result.getString("lastName");
			Date dateOfBirth = result.getDate("dateOfBirth");

			actor.setId(id);
			actor.setFirstName(firstName);
			actor.setLastName(lastName);
			actor.setDateOfBirth(dateOfBirth);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}

	void updateActor(String actorId, Actor actor) {
		String sql = "UPDATE movie SET id = ?, firstName = ?, lastName = ?, dateOfBirth = ? WHERE id = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, actor.getId());
			stm.setString(2, actor.getFirstName());
			stm.setString(3, actor.getLastName());
			stm.setDate(4, actor.getDateOfBirth());
			stm.setInt(5, Integer.parseInt(actorId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void deleteActor(String actorId) {
		String sql = "DELETE FROM actor WHERE id = ?;";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Integer.parseInt(actorId));
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

