package edu.gymtrack.view;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import edu.gymtrack.model.User;
import edu.gymtrack.model.User.UserType;
import edu.gymtrack.controller.Authentication;
import edu.gymtrack.db.*;

public class GymTrack extends JApplet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private boolean log = false;
	protected int privilege;
	protected int largetId;
	protected User loggedIn = null;
	GTUI previous;
	/*
	 * Components used by AddTraineeDialog
	 */
	protected JTextField firstName_AddTrainee;
	protected JTextField lastName_AddTrainee;
	protected JTextField email_AddTrainee;
	protected JTextField username_AddTrainee;
	protected JButton okButton_AddTrainee;
	protected JButton cancelButton_AddTrainee;
	
	/*
	 * Components used by AddUserDialog
	 */
	protected JTextField firstName_AddUser;
	protected JTextField lastName_AddUser;
	protected JTextField email_AddUser;
	protected JTextField username_AddUser;
	protected JButton okButton_AddUser;
	protected JButton cancelButton_AddUser;
	protected JRadioButton rdbtnTrainer_addUser;
	protected JRadioButton rdbtnMember_addUser;
	protected AddUserDialog addUserDialog;
	protected DeleteUserDialog deleteUserDialog;
	
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
	protected JButton btnLogout;
	
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
        else if (arg0.getSource() == btnLogout) {
        	loggedIn = null;
        	log = false;
        	mainUI.logOut(this);
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
        else if (arg0.getSource() == btnSeeFeedback_MyPlans){
        	SeeFeedbackDialog dialog = new SeeFeedbackDialog();
        	dialog.setVisible(true);
        }
        else if (arg0.getSource() == btnAdd_EditTrainees){
        	this.addUserDialog = new AddUserDialog(this, this.editTraineesUI);
        	this.addUserDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnAdd_users){
        	this.addUserDialog = new AddUserDialog(this, this.usersUI);
        	this.addUserDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnDelete_users){
        	this.deleteUserDialog = new DeleteUserDialog(this, this.usersUI);
        	this.deleteUserDialog.setVisible(true);
        }
        else if (arg0.getSource() == okButton_AddUser){
        	Factory factory = new Factory();
        	String username = this.username_AddUser.getText();
        	int id = this.largetId + 1;
        	UserType type = UserType.CLIENT;
        	if(this.rdbtnTrainer_addUser.isSelected()){
        		type = UserType.TRAINER;
        	}
        	else{
        		type = UserType.CLIENT;
        	}
        	
        	User user = new User(username, type, id, true);
        	ArrayList<User> newData = new ArrayList<>();
        	newData.add(user);
        	
        	Authentication auth;
        	try {
				auth = factory.createAuthentication();
				auth.addUser(username, "test");
				factory.updateUsers(newData, auth);
			} catch (SQLException e1) {
				// TODO catch
				e1.printStackTrace();
			}
        	if(this.addUserDialog.callingUI == usersUI){
        		usersUI.reloadPage(this);
        		this.addUserDialog.dispose();
        	}
        	else if(this.deleteUserDialog.callingUI == usersUI){
        		usersUI.reloadPage(this);
        		this.deleteUserDialog.dispose();
        	}
        	else if(this.addUserDialog.callingUI == editTraineesUI){
        		editTraineesUI.reloadPage(this);
        		this.addUserDialog.dispose();
        	}
        }
        else if(arg0.getSource() == rdbtnAll_users){
        	((UsersUI)usersUI).filterTableData(null, this);
        }
		else if(arg0.getSource() == rdbtnMembers_users){
			((UsersUI)usersUI).filterTableData(UserType.CLIENT, this);
        }
		else if(arg0.getSource() == rdbtnTrainers_users){
			((UsersUI)usersUI).filterTableData(UserType.TRAINER, this);
		}
        else {
        	System.out.println("no action performed implemented for this button" + arg0.getSource().toString());
		}
	}
	
	private void btnSubmit(){	
		
		Factory f = new Factory();
		try {
			String username = this.txtUsername.getText();
			System.out.println(username);
			Authentication a = Factory.createAuthentication();
			ArrayList<User> u = f.getUsers();
			if(a.authenticateUser(username, this.txtPassword.getText())){
				for(int i = 0; i < u.size(); i++){
					if(username.equals(u.get(i).getUsername())){
						loggedIn = u.get(i);
						if(u.get(i).isClient()){
							privilege = 0;
							log = true;
							break;
						}
						if(u.get(i).isTrainer()){
							privilege = 1;
							log=true;
							break;
						}
						if(u.get(i).isOwner()){
							privilege = 2;
							log=true;
							break;
						}
						}
					if(log == false){
						mainUI.logOut(this);
					}
						
					}
				}
			if(log == false){
				mainUI.logOut(this);
			}
			else{
				mainUI.switchUI(this);
			}
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
/*		switch (this.txtUsername.getText()) {
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
		}*/
	}
}



