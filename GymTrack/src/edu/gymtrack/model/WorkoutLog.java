package edu.gymtrack.model;

import java.util.Date;

public class WorkoutLog extends DBMutable{
	private int key;
	private int elementKey;
	private Date date;
	private int completed;
	private String exerciseName;
	private Equipment equipment;
	
	public WorkoutLog(int key, int elementKey, Date date, int completed, String exerciseName, Equipment equipment, boolean isNew){
		this.key = key;
		this.elementKey = elementKey;
		this.date = date;
		this.completed = completed;
		this.exerciseName = exerciseName;
		this.equipment = equipment;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public WorkoutLog(int key, int elementKey, Date date, int completed, Equipment equipment, boolean isNew){
		this(key, elementKey, date, completed, "", equipment, isNew);
	}

	public int getKey(){
		return key;
	}
	
	public int getElementKey(){
		return elementKey;
	}
	
	public Date getDate(){
		return date;
	}
	
	public int getNCompleted(){
		return completed;
	}
	
	public String getExerciseName(){
		return exerciseName;
	}
	
	public Equipment getEquipment(){
		return equipment;
	}
	
	public String toString(){
		return "Date: " + date + ", Completed: " + completed + ", Exercise: " + exerciseName;
	}
}
