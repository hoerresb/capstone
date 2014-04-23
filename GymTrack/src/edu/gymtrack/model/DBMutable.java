package edu.gymtrack.model;

public class DBMutable {
	private boolean newFlag;
	private boolean editFlag;
	private boolean deleteFlag;
	
	public void setEdited(boolean isEdited){
		editFlag = isEdited;
	}
	
	public void setDelete(boolean willDelete){
		deleteFlag = willDelete;
	}
	
	protected void setNew(boolean isNew){
		newFlag = isNew;
	}
	
	public boolean isEdited(){
		return editFlag;
	}
	
	public boolean isNew(){
		return newFlag;
	}
	
	public boolean toBeDeleted(){
		return deleteFlag;
	}
}
