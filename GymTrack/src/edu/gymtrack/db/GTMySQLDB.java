package edu.gymtrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

import edu.gymtrack.controller.Authentication;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class GTMySQLDB implements GTDB {
	private final String dbUser = "gtuser";
	private final String dbPassword = "gt1234user";
	
	private Stack<Connection> connections = new Stack<Connection>();
	
	private Connection getConnection() throws SQLException {
		Connection c = null;
		while(c == null){
			try{
				c = DriverManager.getConnection(
		                "jdbc:mysql://halenka.com:3308/gymtrack",
		                dbUser,
		                dbPassword);
			}catch(MySQLNonTransientConnectionException e){
				while(!connections.isEmpty()){
					Connection conn = connections.pop();
					if(!conn.isClosed()){
						connections.pop().close();
						System.out.println("Forcibly closed db connection...");
					}
				}
			}
		}
		connections.push(c);
	    return c;
	}
	
//	public void closeConnection() throws SQLException{
//		connections.pop().close();
//	}
	
	private ResultSet getResultSetForQuery(String query) throws SQLException{
		Connection con = getConnection();
		Statement stmt = con.createStatement();
	    return stmt.executeQuery(query);
	}
	/*
	 * I had to add this method because when running a
	 * update query, we need to use executeUpdate
	 * rather than executeQuery.
	 */
	private int runUpdateQuery(String query) throws SQLException{
		Connection con = getConnection();
		Statement stmt = con.createStatement();
	    return stmt.executeUpdate(query);
	}
	
	private int runUpdateQueryGetKey(String query) throws SQLException{
		Connection con = getConnection();
		java.sql.PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		int result = -1;
		if(rs.next()){
			result = rs.getInt(1);
		}
		
		return result;
	}
	
	private void deleteFromDB(String query) throws SQLException{
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
	}

	@Override
	public ResultSet getPlansForUser(String username) throws SQLException {
		String query = new String(
			"SELECT users.username, workout_plans.created, workout_plans.is_user, " +
					"workout_plans.goals, workout_plans.feedback, workout_plans.key " +
					"FROM users LEFT JOIN workout_plans " +
					"ON users.key = workout_plans.user " +
					"WHERE users.username = '" + username + "'"
			);
		return getResultSetForQuery(query);
	}
	
	public ResultSet getWorkoutPlans() throws SQLException{
		String query = new String(
				"SELECT * FROM workout_plans " +
				"INNER JOIN users ON workout_plans.user = users.key"
				);
		return getResultSetForQuery(query);
	}

	@Override
	public Map<String, String> getAuthPairs() throws SQLException {
		String query = new String(
				"SELECT users.username, users.password FROM users"
				);
		ResultSet rs = getResultSetForQuery(query);
		
		Map<String, String> result = new HashMap<String, String>();
		while(rs.next()){
			result.put(rs.getString("username"), rs.getString("password"));
		}
		
		return result;
	}

	@Override
	public ResultSet getUsers() throws SQLException {
		String query = new String("SELECT * FROM users");
		return getResultSetForQuery(query);
	}

	@Override
	public ResultSet getActivities() throws SQLException {
		String query = new String("SELECT * FROM activities");
		return getResultSetForQuery(query);
	}

	@Override
	public ResultSet getEquipment() throws SQLException {
		String query = new String("SELECT * FROM equipment");
		return getResultSetForQuery(query);
	}
	
	@Override
	public ResultSet getEquipment(EquipmentType type) throws SQLException{
		String query = new String("SELECT * FROM equipment WHERE type=" + type.getKey());
		return getResultSetForQuery(query);
	}

	@Override
	public ResultSet getEquipmentTypes() throws SQLException {
		String query = new String("SELECT * FROM equipment_types");
		return getResultSetForQuery(query);
	}

	@Override
	public ResultSet getElementsForPlan(WorkoutPlan plan) throws SQLException {
		String query = new String(
				"SELECT * FROM plan_elements "
				+ "WHERE plan='" + plan.getKey() + '\'');
		return getResultSetForQuery(query);
	}

	@Override
	public ResultSet getWorkoutLogs(int elementKey) throws SQLException {
		String query = new String(
				"SELECT workout_logs.key, workout_logs.element, workout_logs.date, workout_logs.completed, workout_logs.equipment, activities.name "
				+ "FROM workout_logs INNER JOIN plan_elements ON plan_elements.key = workout_logs.element "
				+ "INNER JOIN activities ON plan_elements.activity = activities.key "
				+ "WHERE element='" + elementKey + '\'');
		return getResultSetForQuery(query);
	}
	
	@Override
	public ResultSet getWorkoutLogs(User user) throws SQLException {
		String query = new String(
				"SELECT workout_logs.key, workout_logs.element, workout_logs.date, workout_logs.completed, workout_logs.equipment, activities.name " +
						"FROM users INNER JOIN workout_plans ON users.key = workout_plans.user " +
						"INNER JOIN plan_elements ON plan_elements.plan = workout_plans.key " +
						"INNER JOIN workout_logs ON workout_logs.element = plan_elements.key " +
						"INNER JOIN activities ON plan_elements.activity = activities.key " +
						"WHERE users.username = '" + user.getUsername() + "'");
		
		return getResultSetForQuery(query);
	}
	
	@Override
	public ResultSet getWorkoutLogs(Equipment equipment) throws SQLException{
		String query = new String(
				"SELECT workout_logs.key, workout_logs.element, workout_logs.date, workout_logs.completed, workout_logs.equipment " +
						"FROM plan_elements INNER JOIN workout_logs ON workout_logs.element = plan_elements.key " +
						"WHERE plan_elements.equipment_type = '" + equipment.getKey() + "'");
		
		return getResultSetForQuery(query);
	}

	@Override
	public void updateActivity(Activity a) throws SQLException {
		String query = new String(
				"INSERT INTO `activities` (`key`,`name`,`unit`) "
				+ "VALUES (" + a.getKey() + ",'" + a.getName() + "','" + a.getUnit() + "') "
				+ "ON DUPLICATE KEY UPDATE name='" + a.getName() + "', unit='" + a.getUnit() + "'");
		runUpdateQuery(query);
	}

	@Override
	public void updateEquipment(Equipment e) throws SQLException {
		String query = new String(
				"INSERT INTO `equipment` (`key`,`type`,`name`) "
				+ "VALUES (" + e.getKey() + "," + e.getType().getKey() + ",'" + e.getName() + "') "
				+ "ON DUPLICATE KEY UPDATE type=" + e.getType().getKey() + ", name='" + e.getName() + "'");
		runUpdateQuery(query);
	}

	@Override
	public void updateEquipmentType(EquipmentType e) throws SQLException {
		String query = new String(
				"INSERT INTO `equipment_types` (`key`,`name`) "
				+ "VALUES (" + e.getKey() + ",'" + e.getName() + "') "
				+ "ON DUPLICATE KEY UPDATE name='" + e.getName() + "'");
		runUpdateQuery(query);
	}

	@Override
	public void updatePlanElement(PlanElement e) throws SQLException {
		String query = new String(
				"INSERT INTO `plan_elements` (`key`,`plan`,`activity`,`equipment_type`,`amount_required`) "
				+ "VALUES (" + e.getKey() + "," + e.getPlan().getKey() + "," + e.getActivity().getKey() + "," + e.getEquipmentType().getKey() + "," + e.getNRequired() + ") "
				+ "ON DUPLICATE KEY UPDATE plan=" + e.getPlan().getKey() + ", activity=" + e.getActivity().getKey() + ", equipment_type=" + e.getEquipmentType().getKey() + ", amount_required=" + e.getNRequired());
		runUpdateQuery(query);
	}

	@Override
	public void updateUser(User u, Authentication auth) throws SQLException {
		String query = new String(
				"INSERT INTO `users` (`key`,`username`,`password`,`type`,`trainer`) "
				+ "VALUES (" + u.getID() + ",'" + u.getUsername() + "','" + auth.getHashForUser(u.getUsername()) + "'," + (u.getUserType().ordinal() + 1) + "," + u.getTrainerID() + ") "
				+ "ON DUPLICATE KEY UPDATE username='" + u.getUsername() + "', password='" + auth.getHashForUser(u.getUsername()) + "', type=" + (u.getUserType().ordinal() + 1) + ", trainer=" + u.getTrainerID());
		runUpdateQuery(query);
	}

	@Override
	public void deleteActivity(Activity a) throws SQLException {
		String query = new String("DELETE FROM activities WHERE key = " + a.getKey());
		deleteFromDB(query);
	}

	@Override
	public void deleteEquipment(Equipment e) throws SQLException {
		String query = new String("DELETE FROM equipment WHERE `key` = " + e.getKey());
		deleteFromDB(query);
	}

	@Override
	public void deleteEquipmentType(EquipmentType e) throws SQLException {
		String query = new String("DELETE FROM equipment_types WHERE `key` = " + e.getKey());
		deleteFromDB(query);
		
	}

	@Override
	public void deletePlanElement(PlanElement e) throws SQLException {
		String query = new String("DELETE FROM plan_elements WHERE `key` = " + e.getKey());
		deleteFromDB(query);
	}

	@Override
	public void deleteUser(User u) throws SQLException {
		String query = new String("DELETE FROM users WHERE `key` = " + u.getID());
		deleteFromDB(query);
	}

	@Override
	public void deleteWorkoutLog(WorkoutLog w) throws SQLException {
		String query = new String("DELETE FROM workout_logs WHERE `key` = " + w.getKey());
		deleteFromDB(query);
	}

	@Override
	public void updateWorkoutLog(WorkoutLog w) throws SQLException {
		String query = new String(
				"INSERT INTO `workout_logs` (`key`, `element`, `date`, `completed`, `equipment`) "
				+ "VALUES (" + w.getKey() + "," + w.getElementKey() + ",'" + new java.sql.Timestamp(w.getDate().getTime()) + "'," + w.getNCompleted() + "," + w.getEquipment().getKey() + ") "
				+ "ON DUPLICATE KEY UPDATE element=" + w.getElementKey() + ", date='" + new java.sql.Timestamp(w.getDate().getTime()) + "', completed=" + w.getNCompleted() + ", equipment=" + w.getEquipment().getKey());
		runUpdateQuery(query);
	}

	@Override
	public void deleteWorkoutPlan(WorkoutPlan w) throws SQLException {
		String query = new String("DELETE FROM `workout_plans` WHERE `workout_plans`.`key` = " + w.getKey());
		deleteFromDB(query);
	}

	@Override
	public int updateWorkoutPlan(WorkoutPlan w) throws SQLException {
		String query = new String(
				"INSERT INTO `workout_plans` (`key`,`user`,`created`,`is_user`,`goals`,`feedback`) "
				+ "VALUES (" + w.getKey() + "," + w.getClient().getID() + ",'" + new java.sql.Timestamp(w.getDateCreated().getTime()) + "'," + w.isUserPlan() + ",'" + w.getGoals() + "','" + w.getFeedback() + "') "
				+ "ON DUPLICATE KEY UPDATE user=" + w.getClient().getID() + ", created='" + new java.sql.Timestamp(w.getDateCreated().getTime()) + "', is_user=" + w.isUserPlan() + ", goals='" + w.getGoals() + "', feedback='" + w.getFeedback() + "'" );
		return runUpdateQueryGetKey(query);
	}
}
