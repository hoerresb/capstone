package edu.gymtrack.model;

public class Equipment {
	private int key;
	private EquipmentType type;
	private String name;
	
	public Equipment(int key, EquipmentType type, String name){
		this.key = key;
		this.type = type;
		this.name = name;
	}
	
	public EquipmentType getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "Name: " + name + ", Type: " + type.getName() + ", Key: " + key;
	}
}
