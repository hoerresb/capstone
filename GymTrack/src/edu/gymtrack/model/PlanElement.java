package edu.gymtrack.model;

import java.util.ArrayList;

public class PlanElement {
	private Activity activity;
	private Equipment equipment;
	private int nRequired;
	private ArrayList<WorkoutLog> logs = new ArrayList<WorkoutLog>();
	
	public PlanElement(Activity activity, Equipment equipment, int nRequired){
		this.activity = activity;
		this.equipment = equipment;
		this.nRequired = nRequired;
	}
	
	public String getActivityName(){
		return activity.getName();
	}
	
	public int getNRequired(){
		return nRequired;
	}
}
