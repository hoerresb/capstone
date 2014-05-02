package edu.gymtrack.model;

import java.util.Date;

public class WorkoutPlan extends DBMutable{
	private User client;
	private Date created;
	private boolean isUserPlan;
	private String goals;
	private String feedback;
	private int key;
	
	public WorkoutPlan(User client, Date created, boolean isUserPlan, String goals, String feedback, int key, boolean isNew){
		this.client = client;
		this.created = created;
		this.isUserPlan = isUserPlan;
		this.goals = goals;
		this.feedback = feedback;
		this.key = key;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public User getClient() {
		return client;
	}

	public Date getDateCreated() {
		return created;
	}

	public boolean isUserPlan() {
		return isUserPlan;
	}

	public String getGoals() {
		return goals;
	}
	
	public void setGoals(String goals){
		this.goals = goals;
	}
	
	public String getFeedback() {
		return feedback;
	}
	
	public void setFeedback(String feedback){
		this.feedback = feedback;
	}
	
	public int getKey(){
		return key;
	}
	
	@Override
	public String toString(){
		return "Client: " + getClient().getUsername() + ", Created: "
				+ getDateCreated() + ", Personal plan: " + isUserPlan() + ", Goals: " + getGoals() + ", Feedback: "
				+ getFeedback();
	}
}
