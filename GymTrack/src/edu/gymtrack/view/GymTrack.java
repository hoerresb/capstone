package edu.gymtrack.view;

import java.awt.event.*;

import javax.swing.*;

public class GymTrack extends JApplet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	protected int privilege;
	GTUI previous;
	/*
	 * Components used by LogWorkDialog
	 */
	protected JTextField amountTextField_LogWork;
	protected JComboBox exerciseComboBox_LogWork;
	protected JButton okButton_LogWork;
	protected JButton cancelButton_LogWork;
	
	/*
	 * Components used by EditTrainees
	 */
	protected JTable traineesTable_EditTrainees;
	protected JButton btnAdd_EditTrainees;
	protected JButton btnEdit_EditTrainees;
	protected JButton btnDelete_EditTrainees;
	protected JButton btnBack_EditTrainees;
	
	/*
	 * Components used by MyPlansUI
	 */
	protected JButton btnBack_MyPlans;
	protected JButton btnLogWork_MyPlans;
	protected JButton btnSeeFeedback_MyPlans;
	protected JTable planDetailsTable_MyPlans;
	protected JTable loggedWorkTable_MyPlans;
	
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
	 * Component used by EquipmentUI
	 */
	protected JTable equipmentTable_equipment;
	protected JButton btnAdd_equipment;
	protected JButton btnEdit_equipment;
	protected JButton btnDelete_equipment;
	protected JButton btnBack_equipment;
	
	/*
	 * components used by UserUI
	 */
	protected JRadioButton rdbtnTrainers_users;
	protected JRadioButton rdbtnMembers_users;
	protected JRadioButton rdbtnAll_users;
	protected JButton btnAdd_users;
	protected JButton btnEdit_users;
	protected JButton btnDelete_users;
	protected JButton btnBack_users;
	protected JTable usersTable_users;
	
	/*
	 * components use by TrkTraineesUI
	 */
	protected JList traineesList_TrkTrainees;
	protected JTable planTable_TrkTrainees;
	protected JTable loggedProgressTable_TrkTrainees;
	protected JButton btnBack_TrkTrainees;
	protected JButton btnProvideFeedback_TrkTrainees;
	protected JButton btnCreatNewPlan_TrkTrainees;
	protected JButton btnDeleteSelectedPlan_TrkTrainees;
	
	/*
	 * Components used by AnalyzeMeUI
	 */
	protected JButton btnBack_AnalyzeMe;
	
	
	/*
	 * Components used by AnalyzeGymUI
	 */
	protected JButton btnBack_AnalyzeGym;
	
	
	// View objects -- I didn't know how to get the back button to work without making them unstatic
	GTUI loginUI = new LoginUI();
	GTUI myPlansUI = new MyPlansUI();
	GTUI analyzeMeUI = new AnalyzeMeUI();
	GTUI analyzeGymUI = new AnalyzeGymUI();
	GTUI editTraineesUI = new EditTraineesUI();
	GTUI trkTraineesUI = new TrkTraineesUI();
	GTUI equipmentUI = new EquipmentUI();
	GTUI mainUI = new MainUI();
	GTUI usersUI = new UsersUI();
	
	public void init() {
		setSize(800,400);
		setName("GymTrack");
        loginUI.switchUI(this);
	}
	public void start(){
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSubmit) {
			btnSubmit();
		}	
		else if (arg0.getSource() == btnMyPlans) {
			myPlansUI.switchUI(this);
		}
        else if (arg0.getSource() == btnAnalyzeMe) {
			analyzeMeUI.switchUI(this);
		}
        else if (arg0.getSource() == btnEditTrainees) {
        	editTraineesUI.switchUI(this);
		}
        else if (arg0.getSource() == btnTrkTrainees) {
        	trkTraineesUI.switchUI(this);
        }
        else if (arg0.getSource() == btnEquipment) {
        	equipmentUI.switchUI(this);
        }
        else if (arg0.getSource() == btnUsers) {
        	usersUI.switchUI(this);
        }
        else if (arg0.getSource() == btnAnalyzeGym) {
        	analyzeGymUI.switchUI(this);
        }
        else if (arg0.getSource() == btnBack_MyPlans){
        	myPlansUI.goBack(this);
        }
        else if (arg0.getSource() == btnBack_EditTrainees){
        	editTraineesUI.goBack(this);
        }
        else if (arg0.getSource() == btnBack_equipment){
        	equipmentUI.goBack(this);
        }
        else if (arg0.getSource() == btnBack_TrkTrainees){
        	trkTraineesUI.goBack(this);
        }
        else if (arg0.getSource() == btnBack_users){
        	usersUI.goBack(this);
        }
        else if (arg0.getSource() == btnBack_AnalyzeMe){
        	analyzeMeUI.goBack(this);
        }
        else if (arg0.getSource() == btnBack_AnalyzeGym){
        	analyzeGymUI.goBack(this);
        }
        else if (arg0.getSource() == btnLogWork_MyPlans){
				LogWorkDialog dialog = new LogWorkDialog(this);
				dialog.setVisible(true);
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
		mainUI.switchUI(this);
	}
}



