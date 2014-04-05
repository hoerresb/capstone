package edu.gymtrack.view;

import java.awt.event.*;
import javax.swing.*;

public class GymTrack extends JApplet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	protected int privilege;
	
	/*
	 * components used by LoginUI
	 */
	protected JButton btnSubmit;
	protected JTextField txtUsername;
	protected JPasswordField txtPassword;
	
	/*
	 * components used by MainUI
	 */
	protected JButton btnMyPlans;
	protected JButton btnAnalyzeMe;
	protected JButton btnEditTrainees;
	protected JButton btnTrkTrainees;
	protected JButton btnEquipment;
	protected JButton btnUsers;
	protected JButton btnAnalyzeGym;
	
	/*
	 * components used by UserUI
	 */
	protected JRadioButton rdbtnTrainers;
	protected JRadioButton rdbtnMembers;
	protected JRadioButton rdbtnAll;
	protected JButton btnAddUser;
	protected JButton btnEditUser;
	protected JButton btnDeleteUser;
	protected JTable ownerTable;
	
	/*
	 * components use by TrkTraineesUI
	 */
	JList memberList;
	JTable trainerTable;
	
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



