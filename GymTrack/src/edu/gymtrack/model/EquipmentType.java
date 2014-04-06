package edu.gymtrack.model;

public class EquipmentType {
	private int key;
	private String name;
	
	public EquipmentType(int key, String name){
		this.key = key;
		this.name = name;
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
