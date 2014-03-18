package edu.gymtrack.model;

public class User {
	public enum UserType {OWNER, TRAINER, CLIENT};
	
	private String username;
	private UserType type;
	
	public User(String username, UserType type){
		this.username = username;
		this.type = type;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public UserType getUserType(){
		return this.type;
	}
}
