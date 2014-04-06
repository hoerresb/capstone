package edu.gymtrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
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
				"SELECT users.username, users.password_hash"
				+ "FROM users"
				);
		ResultSet rs = getResultSetForQuery(query);
		
		Map<String, String> result = new HashMap<String, String>();
		while(rs.next()){
			result.put(rs.getString("username"), rs.getString("password_hash"));
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
				"SELECT * FROM workout_logs "
				+ "WHERE element='" + elementKey + '\'');
		return getResultSetForQuery(query);
	}
}
