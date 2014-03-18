package edu.gymtrack.model;

import java.util.Date;

public class WorkoutLog {
	private Date date;
	private int completed;
	
	public WorkoutLog(Date date, int completed){
		this.date = date;
		this.completed = completed;
	}
	
	public Date getDate(){
		return date;
	}
	
	public int getNCompleted(){
		return completed;
	}
}
