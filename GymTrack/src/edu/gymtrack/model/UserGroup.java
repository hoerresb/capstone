package edu.gymtrack.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a collection of users
 */
public class UserGroup extends AbstractModelObject{
	private List<User> m_users = new ArrayList<User>();
	private String m_name;

	public UserGroup() {
	}

	public UserGroup(String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		String oldValue = m_name;
		m_name = name;
		firePropertyChange("name", oldValue, m_name);
	}

	public void addUser(User user) {
		List<User> oldValue = m_users;
		m_users = new ArrayList<User>(m_users);
		m_users.add(user);
		firePropertyChange("users", oldValue, m_users);
		firePropertyChange("userCount", oldValue.size(), m_users.size());
	}

	public void removeUser(User user) {
		List<User> oldValue = m_users;
		m_users = new ArrayList<User>(m_users);
		m_users.remove(user);
		firePropertyChange("users", oldValue, m_users);
		firePropertyChange("userCount", oldValue.size(), m_users.size());
	}

	public List<User> getUsers() {
		return m_users;
	}
	
	public int getUserCount() {
		return m_users.size();
	}
}