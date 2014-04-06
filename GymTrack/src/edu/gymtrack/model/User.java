package edu.gymtrack.model;

public class User extends DBMutable{
	public enum UserType {OWNER, TRAINER, CLIENT};
	
	private String username;
	private UserType type;
	private int id;
	
	public User(String username, UserType type, int id, boolean isNew){
		this.username = username;
		this.type = type;
		this.id = id;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public UserType getUserType(){
		return this.type;
	}
	
	public int getID(){
		return id;
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
}
