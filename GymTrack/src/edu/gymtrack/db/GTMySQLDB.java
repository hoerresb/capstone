package edu.gymtrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
	
	private Connection getConnection() throws SQLException {
	    return DriverManager.getConnection(
	                         "jdbc:mysql://halenka.com:3308/gymtrack",
	                         dbUser,
	                         dbPassword);
	}
	
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
	
	private void deleteFromDB(String query) throws SQLException{
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.executeQuery(query);
	}

	@Override
	public ResultSet getPlansForUser(String username) throws SQLException {
		String query = new String(
			"SELECT users.username, workout_plans.created, workout_plans.is_user, " +
					"workout_plans.goals, workout_plans.feedback, workout_plans.key,"
					+ "workout_plans.trainer " +
					"FROM users LEFT JOIN workout_plans " +
					"ON users.key = workout_plans.user " +
					"WHERE users.username = '" + username + "'"
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
				"SELECT workout_logs.key, workout_logs.element, workout_logs.date, workout_logs.completed, activities.name "
				+ "FROM workout_logs INNER JOIN plan_elements ON plan_elements.key = workout_logs.element "
				+ "INNER JOIN activities ON plan_elements.activity = activities.key "
				+ "WHERE element='" + elementKey + '\'');
		return getResultSetForQuery(query);
	}
	
	@Override
	public ResultSet getWorkoutLogs(User user) throws SQLException {
		String query = new String(
				"SELECT workout_logs.key, workout_logs.element, workout_logs.date, workout_logs.completed, activities.name " +
						"FROM users INNER JOIN workout_plans ON users.key = workout_plans.user " +
						"INNER JOIN plan_elements ON plan_elements.plan = workout_plans.key " +
						"INNER JOIN workout_logs ON workout_logs.element = plan_elements.key " +
						"INNER JOIN activities ON plan_elements.activity = activities.key " +
						"WHERE users.username = '" + user.getUsername() + "'");
		
		return getResultSetForQuery(query);
	}

	@Override
	public void updateActivity(Activity a) throws SQLException {
		String query = new String(
				"INSERT INTO `activities` (`key`,`name`,`unit`) "
				+ "VALUES (" + a.getKey() + ",'" + a.getName() + "','" + a.getUnit() + "') "
				+ "ON DUPLICATE KEY UPDATE name='" + a.getName() + "', unit='" + a.getUnit() + "'");
		getResultSetForQuery(query);
	}

	@Override
	public void updateEquipment(Equipment e) throws SQLException {
		String query = new String(
				"INSERT INTO `equipment` (`key`,`type`,`name`) "
				+ "VALUES (" + e.getKey() + "," + e.getType() + ",'" + e.getName() + "') "
				+ "ON DUPLICATE KEY UPDATE type=" + e.getType() + ", name='" + e.getName() + "'");
		getResultSetForQuery(query);
	}

	@Override
	public void updateEquipmentType(EquipmentType e) throws SQLException {
		String query = new String(
				"INSERT INTO `equipment_types` (`key`,`name`) "
				+ "VALUES (" + e.getKey() + ",'" + e.getName() + "') "
				+ "ON DUPLICATE KEY UPDATE name='" + e.getName() + "'");
		getResultSetForQuery(query);
	}

	@Override
	public void updatePlanElement(PlanElement e) throws SQLException {
		String query = new String(
				"INSERT INTO `plan_elements` (`key`,`plan`,`activity`,`equipment`,`amount_required`) "
				+ "VALUES (" + e.getKey() + "," + e.getPlan().getKey() + "," + e.getActivity().getKey() + "," + e.getEquipment().getKey() + "," + e.getNRequired() + ") "
				+ "ON DUPLICATE KEY UPDATE plan=" + e.getPlan().getKey() + ", activity=" + e.getActivity().getKey() + ", equipment=" + e.getEquipment().getKey() + ", amount_required=" + e.getNRequired());
		getResultSetForQuery(query);
	}

	@Override
	public void updateUser(User u, Authentication auth) throws SQLException {
		String query = new String(
				"INSERT INTO `users` (`key`,`username`,`password`,`type`) "
				+ "VALUES (" + u.getID() + ",'" + u.getUsername() + "','" + auth.getHashForUser(u.getUsername()) + "'," + (u.getUserType().ordinal() + 1) + ") "
				+ "ON DUPLICATE KEY UPDATE username='" + u.getUsername() + "', password='" + auth.getHashForUser(u.getUsername()) + "', type=" + (u.getUserType().ordinal() + 1));
		runUpdateQuery(query);
	}

	@Override
	public void deleteActivity(Activity a) throws SQLException {
		String query = new String("DELETE FROM activities WHERE key = " + a.getKey());
		deleteFromDB(query);
	}

	@Override
	public void deleteEquipment(Equipment e) throws SQLException {
		String query = new String("DELETE FROM equipment WHERE key = " + e.getKey());
		deleteFromDB(query);
	}

	@Override
	public void deleteEquipmentType(EquipmentType e) throws SQLException {
		String query = new String("DELETE FROM equipment_types WHERE key = " + e.getKey());
		deleteFromDB(query);
		
	}

	@Override
	public void deletePlanElement(PlanElement e) throws SQLException {
		String query = new String("DELETE FROM plan_elements WHERE key = " + e.getKey());
		deleteFromDB(query);
	}

	@Override
	public void deleteUser(User u) throws SQLException {
		String query = new String("DELETE FROM users WHERE key = " + u.getID());
		deleteFromDB(query);
	}

	@Override
	public void deleteWorkoutLog(WorkoutLog w) throws SQLException {
		String query = new String("DELETE FROM workout_logs WHERE key = " + w.getKey());
		deleteFromDB(query);
	}

	@Override
	public void updateWorkoutLog(WorkoutLog w) throws SQLException {
		String query = new String(
				"INSERT INTO `workout_logs` (`key`, `element`, `date`, `completed`) "
				+ "VALUES (" + w.getKey() + "," + w.getElementKey() + "," + w.getDate() + "," + w.getNCompleted() + ")"
				+ "ON DUPLICATE KEY UPDATE element=" + w.getElementKey() + ", date=" + w.getDate() + ", completed=" + w.getNCompleted() + ")");
		getResultSetForQuery(query);
	}

	@Override
	public void deleteWorkoutPlan(WorkoutPlan w) throws SQLException {
		String query = new String("DELETE FROM workout_plans WHERE key = " + w.getKey());
		deleteFromDB(query);
	}

	@Override
	public void updateWorkoutPlan(WorkoutPlan w) throws SQLException {
		String query = new String(
				"INSERT INTO `workout_plans` (`key`,`trainer`,`user`,`created`,`is_user`,`goals`,`feedback`) "
				+ "VALUES (" + w.getKey() + "," + w.getTrainer() + "," + w.getClient() + "," + w.getDateCreated() + "," + w.isUserPlan() + ",'" + w.getGoals() + "','" + w.getFeedback() + "') "
				+ "ON DUPLICATE KEY UPDATE trainer=" + w.getTrainer() + ", client=" + w.getClient() + ", created=" + w.getDateCreated() + ", is_user=" + w.isUserPlan() + ", goals='" + w.getGoals() + "', feedback='" + w.getFeedback() + "'" );
		getResultSetForQuery(query);
	}
}
