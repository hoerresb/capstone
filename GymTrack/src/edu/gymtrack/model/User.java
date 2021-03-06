package edu.gymtrack.model;

public class User extends DBMutable{
	public enum UserType {OWNER, TRAINER, CLIENT};
	
	private String username;
	private String name;
	private UserType type;
	private int id;
	private int trainerID;
	
	public User(String username, UserType type, int id, int trainerID, boolean isNew){
		this.username = username;
		this.type = type;
		this.id = id;
		this.trainerID = trainerID;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UserType getUserType(){
		return this.type;
	}
	
	public int getID(){
		return id;
	}
	
	public int getTrainerID(){
		return trainerID;
	}
	
	public boolean isTrainer(){
		return type == UserType.TRAINER;
	}
	
	public boolean isClient(){
		return type == UserType.CLIENT;
	}
	
	@Override
	public String toString(){
		return "Username: " + username + ", Type: " + type.toString() + ", ID: " + id;
	}

	public boolean isOwner() {
		return type == UserType.OWNER;
	}
}
