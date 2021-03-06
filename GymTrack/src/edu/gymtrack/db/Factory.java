package edu.gymtrack.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import edu.gymtrack.controller.Authentication;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.User.UserType;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class Factory {
	public static Authentication createAuthentication() throws SQLException{
		GTDB db = new GTMySQLDB();
		Map<String, String> pairs = db.getAuthPairs();
		return new Authentication(pairs);
	}
	
	// methods to pull data from the database
	
	public ArrayList<Activity> getActivities() throws SQLException
	{
		ArrayList<Activity> results = new ArrayList<Activity>();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getActivities();
		while(rs.next())
			results.add(new Activity(rs.getInt("key"), rs.getString("name"), rs.getString("unit"), false));
		
		return results;
	}
	
	public ArrayList<Equipment> getEquipment() throws SQLException
	{
		ArrayList<Equipment> results = new ArrayList<Equipment>();
		
		ArrayList<EquipmentType> types = getEquipmentTypes();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getEquipment();
		while(rs.next()){
			EquipmentType type = null;
			for(EquipmentType t : types){
				if(rs.getInt("type") == t.getKey()){
					type = t;
					break;
				}
			}
			
			results.add(new Equipment(rs.getInt("key"), type, rs.getString("name"), false));
		}
		
		return results;
	}
	
	public ArrayList<Equipment> getEquipment(EquipmentType type) throws SQLException
	{
		ArrayList<Equipment> results = new ArrayList<Equipment>();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getEquipment(type);
		while(rs.next()){
			results.add(new Equipment(rs.getInt("key"), type, rs.getString("name"), false));
		}
		
		return results;
	}
	
	public ArrayList<EquipmentType> getEquipmentTypes() throws SQLException
	{
		ArrayList<EquipmentType> results = new ArrayList<EquipmentType>();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getEquipmentTypes();
		while(rs.next()){
			EquipmentType type = new EquipmentType(rs.getInt("key"), rs.getString("name"), false);
			results.add(type);
		}
		return results;
	}
	
	public ArrayList<PlanElement> getPlanElements(WorkoutPlan plan) throws SQLException
	{
		ArrayList<PlanElement> results = new ArrayList<PlanElement>();
		
		ArrayList<Activity> activities = getActivities();
		ArrayList<EquipmentType> equipmentTypes = getEquipmentTypes();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getElementsForPlan(plan);
		while(rs.next()){
			Activity activity = null;
			for(Activity a : activities){
				if(a.getKey() == rs.getInt("activity")){
					activity = a;
					break;
				}
			}
			
			EquipmentType equipmentType = null;
			for(EquipmentType e : equipmentTypes){
				if(e.getKey() == rs.getInt("equipment_type")){
					equipmentType = e;
					break;
				}
			}
			
			int elementKey = rs.getInt("key");
			ArrayList<WorkoutLog> logs = getWorkoutLogs(elementKey);
			
			results.add(new PlanElement(activity, equipmentType, plan, rs.getInt("amount_required"), elementKey, logs, false));
		}
		
		return results;
	}
	
	public ArrayList<User> getUsers() throws SQLException
	{
		ArrayList<User> results = new ArrayList<User>();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getUsers();
		while(rs.next()){
			User user = new User(rs.getString("username"), User.UserType.values()[rs.getInt("type") - 1], rs.getInt("key"), rs.getInt("trainer"), false);
			results.add(user);
		}
		return results;
	}
	
	public ArrayList<WorkoutLog> getWorkoutLogs(int elementKey) throws SQLException
	{
		ArrayList<WorkoutLog> results = new ArrayList<WorkoutLog>();
		ArrayList<Equipment> equipment = getEquipment();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getWorkoutLogs(elementKey);
		while(rs.next()){
			for(Equipment e : equipment){
				if(e.getKey() == rs.getInt("equipment")){
					WorkoutLog log = new WorkoutLog(rs.getInt("key"), elementKey, rs.getTimestamp("date"), rs.getInt("completed"), rs.getString("name"), e, false);
					results.add(log);
				}
			}
		}
		return results;
	}
	
	public ArrayList<WorkoutLog> getWorkoutLogs(User user) throws SQLException
	{
		ArrayList<WorkoutLog> results = new ArrayList<WorkoutLog>();
		ArrayList<Equipment> equipment = getEquipment();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getWorkoutLogs(user);
		while(rs.next()){
			for(Equipment e : equipment){
				if(e.getKey() == rs.getInt("equipment")){
					WorkoutLog log = new WorkoutLog(rs.getInt("key"), rs.getInt("element"), rs.getDate("date"), rs.getInt("completed"), rs.getString("name"), e, false);
					results.add(log);
				}
			}
		}
		return results;
	}
	
	public ArrayList<WorkoutLog> getWorkoutLogs(Equipment equipment) throws SQLException
	{
		ArrayList<WorkoutLog> results = new ArrayList<WorkoutLog>();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getWorkoutLogs(equipment);
		while(rs.next()){
			WorkoutLog log = new WorkoutLog(rs.getInt("key"), rs.getInt("element"), rs.getDate("date"), rs.getInt("completed"), equipment, false);
			results.add(log);
		}
		return results;
	}
	
	public ArrayList<WorkoutPlan> getWorkoutPlansForUser(User user) throws SQLException
	{
		ArrayList<WorkoutPlan> results = new ArrayList<WorkoutPlan>();
//		ArrayList<User> users = getUsers();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getPlansForUser(user.getUsername());
		while(rs.next()){
//			// get trainer for this plan
//			User trainer = null;
//			int trainerID = rs.getInt("trainer");
//			for(User u : users){
//				if(u.isTrainer() && u.getID() == trainerID){
//					trainer = u;
//					break;
//				}
//			}
//			if(trainer == null)
//				continue;
			
			
			WorkoutPlan plan = new WorkoutPlan(user, rs.getDate("created"), rs.getBoolean("is_user"), 
					rs.getString("goals"), rs.getString("feedback"), rs.getInt("key"), false);
			results.add(plan);
		}
		
		return results;
	}
	
	public ArrayList<WorkoutPlan> getWorkoutPlans() throws SQLException{
		ArrayList<WorkoutPlan> results = new ArrayList<WorkoutPlan>();
//		ArrayList<User> users = getUsers();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getWorkoutPlans();
		while(rs.next()){
			// get trainer for this plan
//			User trainer = null;
//			int trainerID = rs.getInt("trainer");
//			for(User u : users){
//				if(u.isTrainer() && u.getID() == trainerID){
//					trainer = u;
//					break;
//				}
//			}
//			if(trainer == null)
//				continue;
			
			
			WorkoutPlan plan = new WorkoutPlan(
					new User(rs.getString("username"), User.UserType.values()[rs.getInt("type") - 1], rs.getInt("users.key"), rs.getInt("trainer"), false),
					rs.getDate("created"), rs.getBoolean("is_user"), 
					rs.getString("goals"), rs.getString("feedback"), rs.getInt("key"), false);
			results.add(plan);
		}
		
		return results;
	}
	
	ArrayList<WorkoutLog> getLogsForUser(User user) throws SQLException{
		ArrayList<WorkoutLog> results = new ArrayList<WorkoutLog>();
		ArrayList<Equipment> equipment = getEquipment();
		
		GTDB db = new GTMySQLDB();
		ResultSet rs = db.getWorkoutLogs(user);
		while(rs.next()){
			for(Equipment e : equipment){
				if(e.getKey() == rs.getInt("equipment")){
					results.add(new WorkoutLog(rs.getInt("key"), rs.getInt("element"), rs.getDate("date"), rs.getInt("completed"), rs.getString("name"), e, false));
				}
			}
		}
		
		return results;
	}
	
	// methods to update data in the database
	
	public void updateActivities(ArrayList<Activity> newdata) throws SQLException
	{
		GTDB db = new GTMySQLDB();
		for(Activity a : newdata){
			if (a.toBeDeleted() && !a.isNew()){
				db.deleteActivity(a);
			}
			else if(a.isEdited() || a.isNew()){
				db.updateActivity(a);
			}
		}
	}
	
	public void updateEquipment(ArrayList<Equipment> newdata) throws SQLException
	{
		GTDB db = new GTMySQLDB();
		for(Equipment e : newdata){
			if (e.toBeDeleted() && !e.isNew()){
				db.deleteEquipment(e);
			}
			else if(e.isEdited() || e.isNew()){
				db.updateEquipment(e);
			}
		}
	}
	
	public void updateEquipmentType(ArrayList<EquipmentType> newdata) throws SQLException
	{
		GTDB db = new GTMySQLDB();
		for(EquipmentType e : newdata){
			if (e.toBeDeleted() && !e.isNew()){
				db.deleteEquipmentType(e);
			}
			else if(e.isEdited() || e.isNew()){
				db.updateEquipmentType(e);
			}
		}
	}
	
	public void updatePlanElements(ArrayList<PlanElement> newdata) throws SQLException
	{
		GTDB db = new GTMySQLDB();
		for(PlanElement e : newdata){
			if (e.toBeDeleted() && !e.isNew()){
				db.deletePlanElement(e);
			}
			else if(e.isEdited() || e.isNew()){
				db.updatePlanElement(e);
			}
		}
	}
	
	public void updateUsers(ArrayList<User> newdata, Authentication auth) throws SQLException
	{
		GTDB db = new GTMySQLDB();
		for(User u : newdata){
			if (u.toBeDeleted() && !u.isNew()){
				db.deleteUser(u);
			}
			else if(u.isEdited() || u.isNew()){
				db.updateUser(u, auth);
			}
		}
	}
	
	public void updateWorkoutLogs(ArrayList<WorkoutLog> newdata) throws SQLException
	{
		GTDB db = new GTMySQLDB();
		for(WorkoutLog w : newdata){
			if (w.toBeDeleted() && !w.isNew()){
				db.deleteWorkoutLog(w);
			}
			else if(w.isEdited() || w.isNew()){
				db.updateWorkoutLog(w);
			}
		}
	}
	
	public ArrayList<Integer> updateWorkoutPlans(ArrayList<WorkoutPlan> newdata) throws SQLException
	{
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		GTDB db = new GTMySQLDB();
		for(WorkoutPlan w : newdata){
			if (w.toBeDeleted() && !w.isNew()){
				db.deleteWorkoutPlan(w);
			}
			else if(w.isEdited() || w.isNew()){
				results.add(db.updateWorkoutPlan(w));
			}
		}
		
		return results;
	}
	
}
