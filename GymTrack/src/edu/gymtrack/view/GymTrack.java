package edu.gymtrack.view;

import java.awt.Checkbox;
import java.awt.Event;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.gymtrack.model.User.UserType;
import edu.gymtrack.model.*;
import edu.gymtrack.controller.Authentication;
import edu.gymtrack.db.*;

public class GymTrack extends JApplet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private boolean log = false;
	protected int privilege;
	protected int largetId;
	protected User loggedIn = null;
	protected int row = 0;
	protected int col = 0;
	GTUI previous;
	
	/*
	 * Universal Components
	 */
	protected JButton btnLogout;
	protected ConnectionErrorDialog connectionError;
	protected AddEditUserDialog addEditUserDialog;
	protected DeleteUserDialog deleteUserDialog;
	protected InvalidSelectionDialog invalidSelectionDialog;
	protected CreatePlan createPlan;
	
	/*
	 * components used by CreatePlan
	 */
	protected JTextField textField_CreatePlan;
	protected JTextField goalTextField_CreatePlan;
	protected JComboBox comboBox_CreatePlan;
	protected JTable table_CreatePlan;
	protected DefaultTableModel model;
	protected JButton okButton_CreatePlan;
	protected JButton cancelButton_CreatePlan;
	protected JComboBox<String> equipmentComboBox_CreatePlan;
	
	/*
	 * components used by ConnectionErrorDialog
	 */
	protected JButton btnOk_connectionerr;
	
	/*
	 * components used by InvalidSelectionDialog
	 */
	protected JButton btnOk_invalidSelection;
	
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
	 * Components used by AddEditUserDialog
	 */
	protected JTextField firstName_AddEditUser;
	protected JTextField lastName_AddEditUser;
	protected JTextField email_AddEditUser;
	protected JTextField username_AddEditUser;
	protected JPasswordField password_AddEditUser;
	protected JButton okButton_AddEditUser;
	protected JButton updateButton_EditUser;
	protected JButton cancelButton_AddEditUser;
	protected JRadioButton rdbtnTrainer_addUser;
	protected JRadioButton rdbtnMember_addUser;
	protected JRadioButton rdbtnOwner_addUser;
	protected String firstName_DeleteUser;
	protected String lastName_DeleteUser;
	protected Checkbox isTraineeCB_AddEditUser;
	protected JComboBox<String> trainer_AddEditUser;
	
	/*
	 * Components used by DeleteUserDialog
	 */
	protected JButton okButton_DeleteUser;
	protected JButton cancelButton_DeleteUser;
	
	/*
	 * Components used by LogWorkDialog
	 */
	protected JTextField amountTextField_LogWork;
	protected JComboBox<String> plansComboBox_LogWork;
	protected JComboBox<String> exerciseComboBox_LogWork;
	protected JComboBox<String> equipmentComboBox_LogWork;
	protected JButton okButton_LogWork;
	protected JButton cancelButton_LogWork;
	protected LogWorkDialog logWorkDialog;
	
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
	protected LoginErrorDialog loginError;
	protected JButton btnSubmit;
	protected JTextField txtUsername;
	protected JPasswordField txtPassword;
	
	/*
	 * components used by LoginErrorDialog
	 */
	protected JButton btnOk_loginerr;
	
	/*
	 * components used by MainUI
	 */
	protected JButton btnMyPlans;
	protected JButton btnAnalyzeMe;
	protected JButton btnEditTrainees;
	protected JButton btnTrkTrainees;
	protected JButton btnAnalyzeTrainees;
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
	 * Components used by AddEditEquipmentDialog
	 */
	protected JTextField nameTextField_EditEquip;
	protected JComboBox typesComboBox_EditEquip;
	protected JButton okButton_EditEquip;
	protected JButton updateButton_EditEquip;
	protected JButton cancelButton_EditEquip;
	protected AddEditEquipmentDialog addEditEquipmentDialog;
	
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
	protected JButton btnAddExercises_TrkTrainees;
//	protected ArrayList<User> userList;
	/*
	 * Components used by AnalyzeMeUI
	 */
	protected JButton btnBack_AnalyzeMe;
	
	
	/*
	 * Components used by AnalyzeGymUI
	 */
	protected JButton btnBack_AnalyzeGym;
	
	/*
	 * Components used by SeeFeedbackDialog
	 */
	SeeFeedbackDialog feedbackDialog;
	protected JButton btnCancel_Feedback;
	protected JButton btnUpdate_Feedback;
	
	/*
	 * Components used by provideFeedbackDialog
	 */
	
	ProvideFeedbackDialog provideFeedbackDialog;
	protected JButton btnCancel_PFeedback;
	protected JButton btnUpdate_PFeedback;
	
	// View objects -- I didn't know how to get the back button to work without making them unstatic
	GTUI loginUI = new LoginUI();
	GTUI myPlansUI = new MyPlansUI();
	GTUI analyzeMeUI = new AnalyzeMeUI();
	GTUI analyzeGymUI = new AnalyzeGymUI();
	GTUI editTraineesUI = new EditTraineesUI();
	GTUI trkTraineesUI = new TrkTraineesUI();
	GTUI analyzeTraineesUI = new AnalyzeTraineesUI();
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
        else if (arg0.getSource() == btnAnalyzeTrainees) {
        	analyzeTraineesUI.switchUI(this);
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
			logWorkDialog = new LogWorkDialog(this, (MyPlansUI)myPlansUI);
			logWorkDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnSeeFeedback_MyPlans){
        	feedbackDialog = new SeeFeedbackDialog(this, loggedIn.getUserType());
        	feedbackDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnAdd_EditTrainees){
        	addEditUserDialog = new AddEditUserDialog(this, null, editTraineesUI);
        	addEditUserDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnEdit_EditTrainees) {
        	if(traineesTable_EditTrainees.getSelectedRow() < 0)
        		return;
        	
        	addEditUserDialog = new AddEditUserDialog(this, ((EditTraineesUI)editTraineesUI).getSelectedTrainee(this), editTraineesUI);
        	addEditUserDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnDeleteSelectedPlan_TrkTrainees){
        	//TODO delete the plan selected in planTable_TrkTrainees
        	int row = this.planTable_TrkTrainees.getSelectedRow();
        	String planNumber = (String)this.planTable_TrkTrainees.getModel().getValueAt(row, 1);
        	String member = (String)this.traineesList_TrkTrainees.getSelectedValue();
        	//TODO delete from database 'planNumber' for 'member'
        	trkTraineesUI.reloadPage(this);
        }
        else if (arg0.getSource() == btnCreatNewPlan_TrkTrainees){
        	createPlan = new CreatePlan(this, null);
        	createPlan.setVisible(true);
        }
        else if (arg0.getSource() == okButton_CreatePlan) {
        	Factory factory = new Factory();
        	int numRows = table_CreatePlan.getModel().getRowCount();
        	String[] exercises = new String[numRows];
        	String[] reps = new String[numRows];

        	for(int i = 0; i<numRows; ++i){
        		exercises[i] = (String)table_CreatePlan.getModel().getValueAt(i, 0);
        	}

        	for(int i = 0; i<numRows; ++i){
        		reps[i] = (String)table_CreatePlan.getModel().getValueAt(i, 1).toString();
        	}
        	
        	User member = ((TrkTraineesUI)trkTraineesUI).getSelectedUser();
        	ArrayList<Integer> planKey= new ArrayList<>();
        	
        	
        	WorkoutPlan newPlan = createPlan.getNewPlan(member);
//        	if(newPlan.isNew()){
				ArrayList<WorkoutPlan> Planlist = new ArrayList<>();
				Planlist.add(newPlan);
				try {//add the plan
					planKey = factory.updateWorkoutPlans(Planlist);
				} catch (SQLException e) {
					e.printStackTrace();
				}
//        	}
//        	else{
//        		planKey.clear();
//        		planKey.add(newPlan.getKey());
//        	}
			
			try {//add the plan elements to the plan added above
				for(WorkoutPlan plan : factory.getWorkoutPlansForUser(member)){
					if(plan.getKey() == planKey.get(0)){
						newPlan = plan;
					}
				}
				
				PlanElement planElement;
				ArrayList<PlanElement> elementList = new ArrayList<>();
				ArrayList<WorkoutLog> logList;
				
				ArrayList<Activity> addedActivities = createPlan.getAddedActivities();
				for(int i=0; i<addedActivities.size(); ++i) {//make the plan elements
					logList = new ArrayList<WorkoutLog>();
					planElement = new PlanElement(addedActivities.get(i), createPlan.getSelectedEquipmentType(), newPlan, Integer.parseInt(reps[i]), 0, logList, true);
					elementList.add(planElement);
				}
				factory.updatePlanElements(elementList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        	this.createPlan.dispose();
        	trkTraineesUI.reloadPage(this);
        }
        else if (arg0.getSource() == cancelButton_CreatePlan){
        	this.createPlan.dispose();
        }
        else if (arg0.getSource() == btnDeleteSelectedPlan_TrkTrainees){
        	//TODO delete the plan selected in planTable_TrkTrainees
        	int row = this.planTable_TrkTrainees.getSelectedRow();
        	String planNumber = (String)this.planTable_TrkTrainees.getModel().getValueAt(row, 1);
        	String member = (String)this.traineesList_TrkTrainees.getSelectedValue();
        	//TODO delete from database 'planNumber' for 'member'
        	trkTraineesUI.reloadPage(this);
        }
        else if (arg0.getSource() == btnDelete_EditTrainees) {
        	User toDelete = ((EditTraineesUI)editTraineesUI).getSelectedTrainee(this);
        	if(toDelete == null)
        		return;
        	
        	toDelete.setDelete(true);
        	ArrayList<User> users = new ArrayList<User>();
        	users.add(toDelete);
        	
        	Factory f = new Factory();
        	try {
				f.updateUsers(users, Factory.createAuthentication());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	((EditTraineesUI)editTraineesUI).refreshTable(this);
        }
        else if (arg0.getSource() == btnAdd_users){
        	addEditUserDialog = new AddEditUserDialog(this, null, usersUI);
        	addEditUserDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnEdit_users){
        	addEditUserDialog = new AddEditUserDialog(this, ((UsersUI)usersUI).getSelectedUser(this), usersUI);
        	addEditUserDialog.setVisible(true);
        }
        else if (arg0.getSource() == btnDelete_users){
        	User user = ((UsersUI)usersUI).getSelectedUser(this);
        	if (user == null) {
        		invalidSelectionDialog = new InvalidSelectionDialog(this);
    			invalidSelectionDialog.setVisible(true);
        	}
        	else {
        		deleteUserDialog = new DeleteUserDialog(this, this.usersUI, ((UsersUI)usersUI).getSelectedUser(this));
            	deleteUserDialog.setVisible(true);
        	}
        }
        else if (arg0.getSource() == updateButton_EditUser){
            User toEdit = addEditUserDialog.getUserToEdit(); // null if new
            String username = this.username_AddEditUser.getText();
            int trainerID = addEditUserDialog.getSelectedTrainer(this).getID();
            User.UserType type;
            if(rdbtnOwner_addUser.isSelected())
            	type = User.UserType.OWNER;
            else if(rdbtnTrainer_addUser.isSelected())
            	type = User.UserType.TRAINER;
            else
            	type = User.UserType.CLIENT;
            
            boolean editing = toEdit != null;
            User edited;
            if(editing){
            	edited = new User(username, type, toEdit.getID(), trainerID, false);
            	edited.setEdited(true);
            }
            else{
            	edited = new User(username, type, 0, trainerID, true);
            }
            
        	ArrayList<User> newData = new ArrayList<>();
        	newData.add(edited);
        	
        	Factory factory = new Factory();
        	Authentication auth;
        	try {
				auth = factory.createAuthentication();
				if(auth.getHashForUser(username) == null)
					auth.addUser(edited.getUsername(), password_AddEditUser.getText());
				else if(!password_AddEditUser.getText().isEmpty())
					auth.updatePassword(username, null, password_AddEditUser.getText());
					
				factory.updateUsers(newData, auth);
			} catch (SQLException e1) {
				e1.printStackTrace();
				connectionError = new ConnectionErrorDialog(this);
				connectionError.setVisible(true);
			}
        		
//        	}
       
        	if(addEditUserDialog.callingUI == usersUI){
        		usersUI.reloadPage(this);
        		addEditUserDialog.dispose();
        	}
        	else if(addEditUserDialog.callingUI == editTraineesUI){
        		editTraineesUI.reloadPage(this);
        		addEditUserDialog.dispose();
        	}
        }
        else if(arg0.getSource() == cancelButton_AddEditUser) {
        	addEditUserDialog.dispose();
        }
        else if (arg0.getSource() == okButton_AddEditUser){
        	Factory factory = new Factory();
        	String username = this.username_AddEditUser.getText();
        	int id = this.largetId + 1;
        	UserType type = UserType.CLIENT;
        	if(this.rdbtnTrainer_addUser.isSelected()){
        		type = UserType.TRAINER;
        	}
        	else if(this.rdbtnMember_addUser.isSelected()){
        		type = UserType.CLIENT;
        	}
        	else {
        		type = UserType.OWNER;
        	}
        	
        	User user = new User(username, type, id, addEditUserDialog.getSelectedTrainer(this).getID(), true);
        	ArrayList<User> newData = new ArrayList<>();
        	newData.add(user);
        	
        	Authentication auth;
        	try {
				auth = factory.createAuthentication();
				auth.addUser(username, String.valueOf(this.password_AddEditUser.getPassword()));
				factory.updateUsers(newData, auth);
			} catch (SQLException e1) {
				e1.printStackTrace();
				connectionError = new ConnectionErrorDialog(this);
				connectionError.setVisible(true);
			}
        	
        	if(addEditUserDialog.callingUI == usersUI){
        		usersUI.reloadPage(this);
        		addEditUserDialog.dispose();
        	}
        	else if(addEditUserDialog.callingUI == editTraineesUI){
        		editTraineesUI.reloadPage(this);
        		addEditUserDialog.dispose();
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
		else if (arg0.getSource() == btnCancel_Feedback){
			feedbackDialog.dispose();
		}
		else if (arg0.getSource() == okButton_LogWork){
			WorkoutLog newLog = logWorkDialog.getNewEntry();
			if(newLog != null){
				ArrayList<WorkoutLog> arLog = new ArrayList<WorkoutLog>();
				arLog.add(newLog);
				try {
					(new Factory()).updateWorkoutLogs(arLog);
					logWorkDialog.dispose();
					((MyPlansUI)myPlansUI).updateLogDetailsTable();
				} catch (SQLException e) {
					e.printStackTrace();
					connectionError = new ConnectionErrorDialog(this);
					connectionError.setVisible(true);
				}
			}
		}
		else if (arg0.getSource() == cancelButton_LogWork){
			logWorkDialog.dispose();
		}
		else if (arg0.getSource() == btnUpdate_PFeedback){
			ArrayList<WorkoutPlan> updatedPlans = provideFeedbackDialog.getUpdatedPlans();
			try {
				(new Factory()).updateWorkoutPlans(updatedPlans);
				provideFeedbackDialog.dispose();
			} catch (SQLException e) {
				e.printStackTrace();
				connectionError = new ConnectionErrorDialog(this);
				connectionError.setVisible(true);
			}
			trkTraineesUI.reloadPage(this);
		}
		else if (arg0.getSource() == btnUpdate_Feedback){
			ArrayList<WorkoutPlan> updatedPlans = feedbackDialog.getUpdatedPlans();
			try {
				(new Factory()).updateWorkoutPlans(updatedPlans);
				feedbackDialog.dispose();
			} catch (SQLException e) {
				e.printStackTrace();
				connectionError = new ConnectionErrorDialog(this);
				connectionError.setVisible(true);
			}
		}
		else if (arg0.getSource() == btnCancel_PFeedback){
			provideFeedbackDialog.dispose();
			}
		else if (arg0.getSource() == btnDelete_equipment){
			Equipment toDelete = ((EquipmentUI)equipmentUI).getSelectedEquipment(this);
			if(toDelete != null){
				ArrayList<Equipment> alToDelete = new ArrayList<Equipment>();
				toDelete.setDelete(true);
				alToDelete.add(toDelete);
				try {
					(new Factory()).updateEquipment(alToDelete);
				} catch (SQLException e) {
					e.printStackTrace();
					connectionError = new ConnectionErrorDialog(this);
					connectionError.setVisible(true);
				}
				
				((EquipmentUI)equipmentUI).refreshDisplay(this);
			}
		}
		else if (arg0.getSource() == btnAdd_equipment){
			addEditEquipmentDialog = new AddEditEquipmentDialog(this, null, false);
			addEditEquipmentDialog.setVisible(true);
		}
		else if(arg0.getSource() == btnEdit_equipment){
			if(((EquipmentUI)equipmentUI).getSelectedEquipment(this) == null)
				return;
			
			Equipment toEdit = ((EquipmentUI)equipmentUI).getSelectedEquipment(this);
			addEditEquipmentDialog = new AddEditEquipmentDialog(this, toEdit, true);
			addEditEquipmentDialog.setVisible(true);
		}
		else if(arg0.getSource() == okButton_EditEquip || arg0.getSource() == updateButton_EditEquip){
			Equipment updated = addEditEquipmentDialog.getUpdatedEntry();
			if(updated != null){
				ArrayList<Equipment> alToEdit = new ArrayList<Equipment>();
				alToEdit.add(updated);
				try {
					(new Factory()).updateEquipment(alToEdit);
				} catch (SQLException e) {
					e.printStackTrace();
					connectionError = new ConnectionErrorDialog(this);
					connectionError.setVisible(true);
				}
				addEditEquipmentDialog.dispose();
				((EquipmentUI)equipmentUI).refreshDisplay(this);
			}
		}
		else if(arg0.getSource() == cancelButton_EditEquip){
			addEditEquipmentDialog.dispose();
		}
		else if(arg0.getSource() == okButton_AddTrainee) {
			// TODO
			addEditUserDialog.dispose();
		}
		else if(arg0.getSource() == cancelButton_AddTrainee) {
			addEditUserDialog.dispose();
		}
		else if(arg0.getSource() == btnOk_loginerr) {
			loginError.dispose();
		}
		else if(arg0.getSource() == btnOk_connectionerr) {
			connectionError.dispose();
		}
		else if(arg0.getSource() == btnOk_invalidSelection) {
			invalidSelectionDialog.dispose();
		}
		else if(arg0.getSource() == btnProvideFeedback_TrkTrainees) {
			provideFeedbackDialog = new ProvideFeedbackDialog(this, loggedIn.getUserType());
			provideFeedbackDialog.setVisible(true);
		}
		else if(arg0.getSource() == btnCreatNewPlan_TrkTrainees) {
			// TODO
		}
		else if(arg0.getSource() == btnDeleteSelectedPlan_TrkTrainees) {
			// TODO
		}
		else if(arg0.getSource() == okButton_DeleteUser) {
			if(deleteUserDialog.callingUI == usersUI){
				User toDelete = ((UsersUI)usersUI).getSelectedUser(this);
				Factory factory = new Factory();
				if(toDelete != null){
					ArrayList<User> alToDelete = new ArrayList<User>();
					toDelete.setDelete(true);
					alToDelete.add(toDelete);
					try {
						(new Factory()).updateUsers(alToDelete, factory.createAuthentication());
					} catch (SQLException e) {
						e.printStackTrace();
						connectionError = new ConnectionErrorDialog(this);
						connectionError.setVisible(true);
					}
					
					((UsersUI)usersUI).reloadPage(this);
				}
        		deleteUserDialog.dispose();
        	}
        	else if(deleteUserDialog.callingUI == editTraineesUI){
        		editTraineesUI.reloadPage(this);
        		deleteUserDialog.dispose();
        	}
			
			//this.row = usersTable_users.getSelectedRow();
        	
        	/*String deleteUser = "";
        	int deleteId = 0;
        	UserType deleteType = null;
        	String delete = "";
        	
        	
        	System.out.println(deleteType);
        	if(this.row != -1){
        		
        		for(int i = 0 ; i < 3; i++){
        		 deleteUser =	(String) usersTable_users.getModel().getValueAt(this.row, 0);
        		 delete = (String) usersTable_users.getModel().getValueAt(this.row,1);
        		 deleteId = (int) usersTable_users.getModel().getValueAt(this.row,2);
        		}
        		
        		if(deleteUser.equals(loggedIn.getUsername())){
        			return;
        		}
        		
        		if(delete.equalsIgnoreCase("TRAINER")){
        			deleteType = UserType.TRAINER;
        		}
        		else if(delete.equalsIgnoreCase("Client")){
        			deleteType = UserType.CLIENT;
        		}
        		
        		
        		
        		User user = new User(deleteUser, deleteType, deleteId, true);
            	ArrayList<User> newData = new ArrayList<>();
            	newData.add(user);
            	user.setDelete(true);
            	user.setNew(false);
  
            	
            	Authentication auth;
            	try {
    				auth = factory.createAuthentication();
    				auth.addUser(deleteUser, "test");
    				factory.updateUsers(newData, auth);
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    				connectionError = new ConnectionErrorDialog(this);
					connectionError.setVisible(true);
    			}
        		
        	}*/
  
			/*if(deleteUserDialog.callingUI == usersUI){
        		usersUI.reloadPage(this);
        		deleteUserDialog.dispose();
        	}
        	else if(deleteUserDialog.callingUI == editTraineesUI){
        		editTraineesUI.reloadPage(this);
        		deleteUserDialog.dispose();
        	}*/
		}
		else if(arg0.getSource() == cancelButton_DeleteUser) {
			deleteUserDialog.dispose();
		}
		else if(arg0.getSource() == btnAddExercises_TrkTrainees){
			if(((TrkTraineesUI)trkTraineesUI).getSelectedWorkoutPlan() != null){
				createPlan = new CreatePlan(this, ((TrkTraineesUI)trkTraineesUI).getSelectedWorkoutPlan());
	        	createPlan.setVisible(true);
			}
		}
		else {
			System.out.println("no action performed implemented for this button" + arg0.getSource().toString());
		}
	}
	
	private void btnSubmit() {	
		
		Factory f = new Factory();
		try {
			String username = this.txtUsername.getText();
			Authentication a = Factory.createAuthentication();
			ArrayList<User> u = f.getUsers();
			System.out.println(username);
			//System.out.println(this.txtPassword.getPassword());
			if(a.authenticateUser(username, String.valueOf(this.txtPassword.getPassword()))){
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
				loginError = new LoginErrorDialog(this);
				loginError.setVisible(true);
			}
			else{
				mainUI.switchUI(this);
			}
			}
			
		 catch (SQLException e) {
			e.printStackTrace();
			connectionError = new ConnectionErrorDialog(this);
			connectionError.setVisible(true);
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
	
//	private Activity convertToActivityObject(String exercise, Factory factory) throws SQLException{
//		ArrayList<Activity> activities = factory.getActivities();
//		
//		if(exercise.equalsIgnoreCase("Curls")){
//			for(Activity act : activities){
//				if(act.getName().equalsIgnoreCase("Curls")){
//					return act;
//				}
//			}
//		}
//		else if(exercise.equalsIgnoreCase("Walk")){
//			for(Activity act : activities){
//				if(act.getName().equalsIgnoreCase("Walk")){
//					return act;
//				}
//			}
//		}
//		else{
//			return activities.get(0); //default
//		}
//		return null;//not sure why this is needed but it wouldn't compile for me without it
//	}
	

}



