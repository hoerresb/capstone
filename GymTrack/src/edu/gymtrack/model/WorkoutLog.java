package edu.gymtrack.model;

import java.util.Date;

public class WorkoutLog extends DBMutable{
	private Date date;
	private int completed;
	
	public WorkoutLog(Date date, int completed, boolean isNew){
		this.date = date;
		this.completed = completed;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public Date getDate(){
		return date;
	}
	
	public int getNCompleted(){
		return completed;
	}
	
	public String toString(){
		return "Date: " + date + ", Completed: " + completed;
	}
}
