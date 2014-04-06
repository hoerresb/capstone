package edu.gymtrack.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import edu.gymtrack.model.WorkoutPlan;

public interface GTDB {
	
	public Map<String, String> getAuthPairs() throws SQLException;
	
	public ResultSet getPlansForUser(String username) throws SQLException;
	
	public ResultSet getUsers() throws SQLException;
	
	public ResultSet getActivities() throws SQLException;
	
	public ResultSet getEquipment() throws SQLException;
	
	public ResultSet getEquipmentTypes() throws SQLException;
	
	public ResultSet getElementsForPlan(WorkoutPlan plan) throws SQLException;
	
	public ResultSet getWorkoutLogs(int elementKey) throws SQLException;
}
