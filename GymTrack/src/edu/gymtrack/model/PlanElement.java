package edu.gymtrack.model;

import java.util.ArrayList;

public class PlanElement {
	private String activity;
	private Equipment equipment;
	private int nRequired;
	private ArrayList<WorkoutLog> logs = new ArrayList<WorkoutLog>();
	
	public PlanElement(String activity, Equipment equipment, int nRequired){
		this.activity = activity;
		this.equipment = equipment;
		this.nRequired = nRequired;
	}
	
	public String getActivityName(){
		return activity;
	}
	
	public int getNRequired(){
		return nRequired;
	}
}
