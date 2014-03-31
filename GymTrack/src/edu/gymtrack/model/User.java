package edu.gymtrack.model;

public class User extends AbstractModelObject{
	private enum UserType {OWNER, TRAINER, CLIENT};
	private UserType type;
	private String m_name;
	private String m_email;
	private String m_phone;
	
	public User(String username, UserType type){
		this.m_name = username;
		this.type = type;
	}
	
	public User() {
		m_name = "enter name";
		m_email = "enter email";
		m_phone = "enter phone #";
	}

	public User(String name, String email, String phone) {
		m_name = name;
		m_email = email;
		m_phone = phone;
	}
	
	public String getName(){
		return this.m_name;
	}
	
	public void setName(String name){
		String oldValue = m_name;
		m_name = name;
		firePropertyChange("name", oldValue, m_name);
	}
	
	public String getEmail(){
		return this.m_email;
	}
	
	public void setEmail(String email) {
		String oldValue = m_email;
		m_email = email;
		firePropertyChange("email", oldValue, m_email);
	}
	
	public String getPhone(){
		return this.m_phone;
	}
	
	public void setPhone(String phone) {
		String oldValue = m_phone;
		m_phone = phone;
		firePropertyChange("phone", oldValue, m_phone);
	}
	
	public UserType getUserType(){
		return this.type;
	}
}
