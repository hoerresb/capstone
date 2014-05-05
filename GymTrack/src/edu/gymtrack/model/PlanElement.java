package edu.gymtrack.model;

import java.util.ArrayList;

public class PlanElement extends DBMutable{
	private Activity activity;
	private EquipmentType equipmentType;
	private int nRequired;
	private int key;
	private WorkoutPlan plan;
	private ArrayList<WorkoutLog> logs = new ArrayList<WorkoutLog>();
	
	public PlanElement(Activity activity, EquipmentType equipmentType, WorkoutPlan plan, int nRequired, int key, 
			ArrayList<WorkoutLog> logs, boolean isNew){
		this.activity = activity;
		this.equipmentType = equipmentType;
		this.nRequired = nRequired;
		this.logs = logs;
		this.key = key;
		this.plan = plan;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public String getActivityName(){
		return activity.getName();
	}
	
	public Activity getActivity(){
		return activity;
	}
	
	public EquipmentType getEquipmentType(){
		return equipmentType;
	}
	
	public int getNRequired(){
		return nRequired;
	}
	
	public int getKey(){
		return key;
	}
	
	public WorkoutPlan getPlan(){
		return plan;
	}
	
	public String toString(){
		return "Activity: " + activity.getName() + ", Equipment Type: " + equipmentType.getName() + 
				", Requirement: " + nRequired;// + ", Logs: " + logs.toString();
	}
}
