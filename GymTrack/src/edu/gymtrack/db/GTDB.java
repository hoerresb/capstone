package edu.gymtrack.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import edu.gymtrack.controller.Authentication;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
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
	
	public void updateActivity(Activity a) throws SQLException;
	
	public void updateEquipment(Equipment e) throws SQLException;
	
	public void updateEquipmentType(EquipmentType e) throws SQLException;
	
	public void updatePlanElement(PlanElement e) throws SQLException;
	
	public void updateUser(User u, Authentication auth) throws SQLException;
}
