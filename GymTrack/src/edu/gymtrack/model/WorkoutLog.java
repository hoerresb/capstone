package edu.gymtrack.model;

import java.util.Date;

public class WorkoutLog extends DBMutable{
	private int key;
	private int elementKey;
	private Date date;
	private int completed;
	private String exerciseName;
	
	public WorkoutLog(int key, int elementKey, Date date, int completed, String exerciseName, boolean isNew){
		this.key = key;
		this.elementKey = elementKey;
		this.date = date;
		this.completed = completed;
		this.exerciseName = exerciseName;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public WorkoutLog(int key, int elementKey, Date date, int completed, boolean isNew){
		this(key, elementKey, date, completed, "", isNew);
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
	
	public String toString(){
		return "Date: " + date + ", Completed: " + completed + ", Exercise: " + exerciseName;
	}
}
