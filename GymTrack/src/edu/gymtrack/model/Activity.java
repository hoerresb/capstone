package edu.gymtrack.model;

public class Activity extends DBMutable{
	private int key;
	private String name;
	
	
	public Activity(int key, String name, boolean newEntry){
		this.key = key;
		this.name = name;
		setNew(newEntry);
		setDelete(false);
		setEdited(false);
	}
	
	// getters
	
	public int getKey(){
		return this.key;
	}
	
	public String getName(){
		return this.name;
	}
	
	// setters
	
	// the database manages the keys
//	public void setKey(int key){
//		this.key = key;
//	    this.editFlag = true;
//	}
	
	public void setName(String name){
		this.name = name;
		setEdited(true);
	}
	
	public void delete(){
		setDelete(true);
	}
	
	@Override
	public String toString(){
		return "Name: " + name + ", Key: " + key + ", New: " + isNew() + ", Edited: " + isEdited()
				+ ", S/B Deleted: " + toBeDeleted();
	}
}
