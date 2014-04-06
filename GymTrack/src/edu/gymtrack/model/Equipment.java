package edu.gymtrack.model;

public class Equipment extends DBMutable{
	private int key;
	private EquipmentType type;
	private String name;
	
	public Equipment(int key, EquipmentType type, String name, boolean isNew){
		this.key = key;
		this.type = type;
		this.name = name;
		setNew(isNew);
		setDelete(false);
		setEdited(false);
	}
	
	public EquipmentType getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public int getKey(){
		return key;
	}
	
	public String toString(){
		return "Name: " + name + ", Type: " + type.getName() + ", Key: " + key;
	}
}
