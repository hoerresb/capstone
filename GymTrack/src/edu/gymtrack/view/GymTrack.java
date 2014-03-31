package edu.gymtrack.view;

import java.awt.event.*;
import javax.swing.*;
import edu.gymtrack.model.User;
import edu.gymtrack.model.UserGroup;
import edu.gymtrack.model.UserGroups;

public class GymTrack extends JApplet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	protected JTextField txtUsername;
	protected JPasswordField txtPassword;
	protected int privilege; 
	
	protected JButton btnSubmit;
	protected JButton btnMyPlans;
	protected JButton btnAnalyzeMe;
	protected JButton btnEditTrainees;
	protected JButton btnTrkTrainees;
	protected JButton btnEquipment;
	protected JButton btnUsers;
	protected JButton btnAnalyzeGym;
	
	protected UserGroups userGroups = new UserGroups();
	protected JList<UserGroup> userGroupList;
	protected JPanel groupToolbar;
	protected JButton newGroupButton;
	protected JButton editGroupButton;
	protected JButton deleteGroupButton;
	protected JTable personTable;
	protected JButton newUserButton;
	protected JButton deleteUserButton;
	protected JTextField nameTextField;
	protected JTextField emailTextField;
	protected JTextField phoneTextField;
	
	public void init() {
		setSize(800,400);
		setName("GymTrack");
        LoginUI.createLoginUI(this);
	}
	public void start(){
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSubmit) {
			btnSubmit();
		}	
		else if (arg0.getSource() == btnMyPlans) {
			MyPlansUI.createMyPlansUI(this);
		}
        else if (arg0.getSource() == btnAnalyzeMe) {
			AnalyzeMeUI.createAnalyzeMeUI(this);
		}
        else if (arg0.getSource() == btnEditTrainees) {
        	EditTraineesUI.createEditTraineesUI(this);
		}
        else if (arg0.getSource() == btnTrkTrainees) {
        	TrkTraineesUI.createTrkTraineesUI(this);
        }
        else if (arg0.getSource() == btnEquipment) {
        	EquipmentUI.createEquipmentUI(this);
        }
        else if (arg0.getSource() == btnUsers) {
        	UsersUI.createUsersUI(this);
        }
        else if (arg0.getSource() == btnAnalyzeGym) {
        	AnalyzeGymUI.createAnalyzeGymUI(this);
        }
        else if (arg0.getSource() == newUserButton){
        	UserGroup group = userGroups.getGroups().get(userGroupList.getSelectedIndex());
    		group.addUser(new User());
    		userGroupList.repaint();
    		personTable.repaint();
        }
        else if (arg0.getSource() == deleteUserButton){
        	UserGroup group = userGroups.getGroups().get(userGroupList.getSelectedIndex());
    		User user = group.getUsers().get(personTable.getSelectedRow());
    		group.removeUser(user);
    		userGroupList.repaint();
    		personTable.repaint();
        }
        else {
        	System.out.println("no action performed implemented for this button" + arg0.getSource().toString());
		}
	}
	
	private void btnSubmit(){
		//TODO validate login information
		
		switch (this.txtUsername.getText()) {
		case "user":
			privilege = 0;
			break;
		case "trainer":
			privilege = 1;
			break;
		case "owner":
			privilege = 2;
			break;
		default:
			break;
		}
		MainUI.createMainUI(this);
	}
}



