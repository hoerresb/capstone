package edu.gymtrack.model;

import java.util.Date;

public class WorkoutPlan extends DBMutable{
	private User client;
	private User trainer;
	private Date created;
	private boolean isUserPlan;
	private String goals;
	private String feedback;
	
	public WorkoutPlan(User client, User trainer, Date created, boolean isUserPlan, String goals, String feedback, boolean isNew){
		this.client = client;
		this.trainer = trainer;
		this.created = created;
		this.isUserPlan = isUserPlan;
		this.goals = goals;
		this.feedback = feedback;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public User getClient() {
		return client;
	}

	public User getTrainer() {
		return trainer;
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

	public String getFeedback() {
		return feedback;
	}
	
	@Override
	public String toString(){
		return "Client: " + getClient().getUsername() + ", Trainer: " + getTrainer().getUsername() + ", Created: "
				+ getDateCreated() + ", Personal plan: " + isUserPlan() + ", Goals: " + getGoals() + ", Feedback: "
				+ getFeedback();
	}
}
