package edu.gymtrack.model;

import java.util.ArrayList;

public class PlanElement extends DBMutable{
	private Activity activity;
	private Equipment equipment;
	private int nRequired;
	private ArrayList<WorkoutLog> logs = new ArrayList<WorkoutLog>();
	
	public PlanElement(Activity activity, Equipment equipment, int nRequired, boolean isNew){
		this.activity = activity;
		this.equipment = equipment;
		this.nRequired = nRequired;
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
	
	public String toString(){
		return "Activity: " + activity.getName() + ", Equipment: " + equipment.getName() + 
				", Requirement: " + nRequired + ", Logs: " + logs.toString();
	}
}
