package edu.gymtrack.model;

public class EquipmentType extends DBMutable{
	private int key;
	private String name;
	
	public EquipmentType(int key, String name, boolean isNew){
		this.key = key;
		this.name = name;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public String getName(){
		return name;
	}
	
	public int getKey(){
		return key;
	}
	
	public String toString(){
		return "Name: " + name + ", Key: " + key;
	}
}
