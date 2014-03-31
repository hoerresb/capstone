package edu.gymtrack.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a collection of user groups
 */
public class UserGroups extends AbstractModelObject{
	private List<UserGroup> m_groups = new ArrayList<UserGroup>();

	public void addGroup(UserGroup group) {
		List<UserGroup> oldValue = m_groups;
		m_groups = new ArrayList<UserGroup>(m_groups);
		m_groups.add(group);
		firePropertyChange("groups", oldValue, m_groups);
	}

	public void removeGroup(UserGroup group) {
		List<UserGroup> oldValue = m_groups;
		m_groups = new ArrayList<UserGroup>(m_groups);
		m_groups.remove(group);
		firePropertyChange("groups", oldValue, m_groups);
	}

	public List<UserGroup> getGroups() {
		return m_groups;
	}
}