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
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getActivities(): Not yet implemented");
	}
	
	public ArrayList<Equipment> getEquipment()
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getEquipment(): Not yet implemented");
	}
	
	public ArrayList<EquipmentType> getEquipmentTypes()
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getEquipmentTypes(): Not yet implemented");
	}
	
	public ArrayList<PlanElement> getPlanElements(WorkoutPlan plan)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getPlanElements(WorkoutPlan plan): Not yet implemented");
	}
	
	public ArrayList<User> getUsers()
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getUsers(): Not yet implemented");
	}
	
	public ArrayList<WorkoutLog> getWorkoutLogs(User user)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getWorkoutLogs(User user): Not yet implemented");
	}
	
	public ArrayList<WorkoutPlan> getWorkoutPlansForUser(User user)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getWorkoutPlansForUser(User user): Not yet implemented");
	}
	
	public ArrayList<WorkoutPlan> getWorkoutPlans()
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.getWorkoutPlans(): Not yet implemented");
	}
	
	// methods to update data in the database
	
	public void updateActivities(ArrayList<Activity> newdata)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.updateActivities(ArrayList<Activity> newdata): Not yet implemented");
	}
	
	public void updateEquipment(ArrayList<Equipment> newdata)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.updateEquipment(ArrayList<Equipment> newdata): Not yet implemented");
	}
	
	public void updateEquipmentType(ArrayList<EquipmentType> newdata)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.updateEquipmentType(ArrayList<EquipmentType> newdata): Not yet implemented");
	}
	
	public void updatePlanElements(ArrayList<PlanElement> newdata)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.updatePlanElements(ArrayList<PlanElement> newdata): Not yet implemented");
	}
	
	public void updateUsers(ArrayList<User> newdata)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.updateUsers(ArrayList<User> newdata): Not yet implemented");
	}
	
	public void updateWorkoutLogs(ArrayList<WorkoutLog> newdata)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.updateWorkoutLogs(ArrayList<WorkoutLog> newdata): Not yet implemented");
	}
	
	public void updateWorkoutPlans(ArrayList<WorkoutPlan> newdata)
	{
		//TODO implement this function
		throw new UnsupportedOperationException("Factory.updateWorkoutPlans(ArrayList<WorkoutPlan> newdata): Not yet implemented");
	}
	
}
