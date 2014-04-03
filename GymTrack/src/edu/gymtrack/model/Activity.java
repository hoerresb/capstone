package edu.gymtrack.model;

public class Activity {

	private int key;
	private String name;
	private boolean newFlag;
	private boolean editFlag;
	private boolean deleteFlag;
	
	
	public Activity(int key, String name, boolean newEntry){
		this.key = key;
		this.name = name;
		if (newEntry)
			newFlag = true;
	}
	
	// getters
	
	public int getKey(){
		return this.key;
	}
	
	public String getName(){
		return this.name;
	}
	
	// setters
	
	public void setKey(int key){
		this.key = key;
	    this.editFlag = true;
	}
	
	public void setName(String name){
		this.name = name;
		this.editFlag = true;
	}
	
	public void delete(){
		this.deleteFlag = true;
	}
	
}
