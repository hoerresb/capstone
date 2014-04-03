package edu.gymtrack.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import edu.gymtrack.controller.Authentication;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class Factory {
	public static Authentication createAuthentication() throws SQLException{
		GTDB db = new GTMySQLDB();
		Map<String, String> pairs = db.getAuthPairs();
		return new Authentication(pairs);
	}
	
	// methods to pull data from the database
	
	public ArrayList<Activity> getActivities()
	{
	
	}
	
	public ArrayList<Equipment> getEquipment()
	{
	
	}
	
	public ArrayList<EquipmentType> getEquipmentTypes()
	{
	
	}
	
	public ArrayList<PlanElement> getPlanElements(WorkoutPlan plan)
	{
	
	}
	
	public ArrayList<User> getUsers()
	{
	
	}
	
	public ArrayList<WorkoutLog> getWorkoutLogs(User user)
	{
		
	}
	
	public ArrayList<WorkoutPlan> getWorkoutPlansForUser(User user)
	{
		
	}
	
	public ArrayList<WorkoutPlan> getWorkoutPlans()
	{
		
	}
	
	// methods to update data in the database
	
	public void updateActivities(ArrayList<Activity> newdata)
	{
		
	}
	
	public void updateEquipment(ArrayList<Equipment> newdata)
	{
		
	}
	
	public void updateEquipmentType(ArrayList<EquipmentType> newdata)
	{
		
	}
	
	public void updatePlanElements(ArrayList<PlanElement> newdata)
	{
		
	}
	
	public void updateUsers(ArrayList<User> newdata)
	{
		
	}
	
	public void updateWorkoutLogs(ArrayList<WorkoutLog> newdata)
	{
		
	}
	
	public void updateWorkoutPlans(ArrayList<WorkoutPlan> newdata)
	{
		
	}
	
}
