package edu.gymtrack.model;

import java.util.ArrayList;

public class PlanElement extends DBMutable{
	private Activity activity;
	private Equipment equipment;
	private int nRequired;
	private int key;
	private ArrayList<WorkoutLog> logs = new ArrayList<WorkoutLog>();
	
	public PlanElement(Activity activity, Equipment equipment, int nRequired, int key, 
			ArrayList<WorkoutLog> logs, boolean isNew){
		this.activity = activity;
		this.equipment = equipment;
		this.nRequired = nRequired;
		this.logs = logs;
		this.key = key;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public String getActivityName(){
		return activity.getName();
	}
	
	public int getNRequired(){
		return nRequired;
	}
	
	public int getKey(){
		return key;
	}
	
	public String toString(){
		return "Activity: " + activity.getName() + ", Equipment: " + equipment.getName() + 
				", Requirement: " + nRequired + ", Logs: " + logs.toString();
	}
}
