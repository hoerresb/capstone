package edu.gymtrack.view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.User;

public class AddEditUserDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private boolean hideOkBtn = false; 
	ButtonGroup addUser_buttonGroup = new ButtonGroup();
	User toEdit;
	private ArrayList<User> trainers;
	GTUI callingUI;

	public AddEditUserDialog(GymTrack gym, User toEdit, GTUI callingUI){
		this.toEdit = toEdit;
		this.callingUI = callingUI;
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		if (toEdit != null) {
			this.setTitle("Edit user");
			this.hideOkBtn = true;
		}
		else {
			this.setTitle("Add a user");
		}
		this.setBounds(100, 100, 450, 300);
		this.setPreferredSize(new Dimension(450,300));
		this.setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		gym.rdbtnTrainer_addUser = new JRadioButton("Trainer");
		gym.rdbtnMember_addUser = new JRadioButton("Member");
		gym.rdbtnOwner_addUser = new JRadioButton("Owner");
		addUser_buttonGroup.add(gym.rdbtnMember_addUser);
		addUser_buttonGroup.add(gym.rdbtnTrainer_addUser);
		addUser_buttonGroup.add(gym.rdbtnOwner_addUser);
		gym.rdbtnMember_addUser.setSelected(true);
		contentPanel.add(gym.rdbtnMember_addUser);
		contentPanel.add(gym.rdbtnTrainer_addUser);
		contentPanel.add(gym.rdbtnOwner_addUser);
		
//		JPanel firstName = new JPanel();
//		firstName.setLayout(new FlowLayout());
//		firstName.setPreferredSize(new Dimension(440, 25));
//		JLabel lblFirstName = new JLabel("First name: *");
//		gym.firstName_AddEditUser = new JTextField();
//		gym.firstName_AddEditUser.setColumns(10);
//		firstName.add(lblFirstName);
//		firstName.add(gym.firstName_AddEditUser);
//		contentPanel.add(firstName);
//		
//		JPanel lastName = new JPanel();
//		lastName.setLayout(new FlowLayout());
//		lastName.setPreferredSize(new Dimension(440, 25));
//		JLabel lblLastName = new JLabel("Last name: *");
//		gym.lastName_AddEditUser = new JTextField();
//		gym.lastName_AddEditUser.setColumns(10);
//		lastName.add(lblLastName);
//		lastName.add(gym.lastName_AddEditUser);
//		contentPanel.add(lastName);
//		
//		JPanel email = new JPanel();
//		email.setLayout(new FlowLayout());
//		email.setPreferredSize(new Dimension(440, 25));
//		JLabel lblEmail = new JLabel("Email:");
//		gym.email_AddEditUser = new JTextField();
//		gym.email_AddEditUser.setColumns(20);
//		email.add(lblEmail);
//		email.add(gym.email_AddEditUser);
//		contentPanel.add(email);
		
		JPanel trainerPanel = new JPanel();
		trainerPanel.setLayout(new FlowLayout());
		trainerPanel.setPreferredSize(new Dimension(440, 25));
		JLabel lblTrainer = new JLabel("Trainer: *");
		gym.trainer_AddEditUser = new JComboBox<String>(new DefaultComboBoxModel<String>(getAvailableTrainers(gym)));
		for(int i = 0; i < trainers.size() && toEdit != null; ++i)
			if(toEdit.getTrainerID() == trainers.get(i).getID())
				gym.trainer_AddEditUser.setSelectedIndex(i);
		trainerPanel.add(lblTrainer);
		trainerPanel.add(gym.trainer_AddEditUser);
		contentPanel.add(trainerPanel);
		
		JPanel userName = new JPanel();
		userName.setLayout(new FlowLayout());
		userName.setPreferredSize(new Dimension(440, 25));
		JLabel lblName = new JLabel("Username: *");
		gym.username_AddEditUser = new JTextField(toEdit == null ? "" : toEdit.getUsername());
		gym.username_AddEditUser.setColumns(15);
		userName.add(lblName);
		userName.add(gym.username_AddEditUser);
		contentPanel.add(userName);
		
		JPanel passWord = new JPanel();
		passWord.setLayout(new FlowLayout());
		passWord.setPreferredSize(new Dimension(440, 25));
		JLabel lblPass = new JLabel("Password: *");
		gym.password_AddEditUser = new JPasswordField();
		gym.password_AddEditUser.setColumns(8);
		passWord.add(lblPass);
		passWord.add(gym.password_AddEditUser);
		contentPanel.add(passWord);

		JPanel fromUsersSpacer = new JPanel();
		fromUsersSpacer.setPreferredSize(new Dimension(440, 30));
		contentPanel.add(fromUsersSpacer);
		
		JPanel fromEditTraineeSpacer = new JPanel();
		fromEditTraineeSpacer.setPreferredSize(new Dimension(440, 90));
		contentPanel.add(fromEditTraineeSpacer);
		
		if(gym.loggedIn.getUserType() == User.UserType.TRAINER){
			boolean isMine;
			if(toEdit == null)
				isMine = true;
			else
				isMine = toEdit.getTrainerID() == gym.loggedIn.getID();
			
			JPanel mineSpacer = new JPanel();
			mineSpacer.setPreferredSize(new Dimension(440, 90));
			contentPanel.add(mineSpacer);
			gym.isTraineeCB_AddEditUser = new Checkbox("My Trainee", isMine);
		}
		
		JLabel lblRequired = new JLabel("The fields marked with a (*) are required");
		contentPanel.add(lblRequired);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		if (this.hideOkBtn) {
			gym.updateButton_EditUser = new JButton(new ImageIcon("images/dialog_update.png", "Update"));
			gym.updateButton_EditUser.setPreferredSize(new Dimension(70,25));
			gym.updateButton_EditUser.setRolloverIcon(new ImageIcon("images/dialog_update_over.png", "Update"));
			gym.updateButton_EditUser.addActionListener(gym);
			buttonPane.add(gym.updateButton_EditUser);
		}
		else {
			gym.okButton_AddEditUser = new JButton(new ImageIcon("images/dialog_ok.png", "Ok"));
			gym.okButton_AddEditUser.setPreferredSize(new Dimension(70,25));
			gym.okButton_AddEditUser.setRolloverIcon(new ImageIcon("images/dialog_ok_over.png", "Ok"));
			gym.okButton_AddEditUser.addActionListener(gym);
			buttonPane.add(gym.okButton_AddEditUser);
		}

		gym.cancelButton_AddEditUser = new JButton(new ImageIcon("images/dialog_cancel.png", "Cancel"));
		gym.cancelButton_AddEditUser.setPreferredSize(new Dimension(70,25));
		gym.cancelButton_AddEditUser.setRolloverIcon(new ImageIcon("images/dialog_cancel_over.png", "Cancel"));
		gym.cancelButton_AddEditUser.addActionListener(gym);
		buttonPane.add(gym.cancelButton_AddEditUser);
		
		if(gym.loggedIn.getUserType() == User.UserType.TRAINER){
			fromEditTraineeSpacer.setVisible(true);
			fromUsersSpacer.setVisible(false);
			gym.rdbtnOwner_addUser.setVisible(false);
			gym.rdbtnMember_addUser.setVisible(false);
			gym.rdbtnTrainer_addUser.setVisible(false);
			passWord.setVisible(false);
		}
		else {
			fromEditTraineeSpacer.setVisible(false);
			fromUsersSpacer.setVisible(true);
		}

	}
	
	private String[] getAvailableTrainers(GymTrack gym){
		ArrayList<User> users = new ArrayList<User>();
		try {
			users = (new Factory()).getUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		trainers = new ArrayList<User>();
		ArrayList<String> trainerNames = new ArrayList<String>();
		for(User u : users){
			if(u.getUserType() == User.UserType.TRAINER){
				trainers.add(u);
				trainerNames.add(u.getUsername());
			}
		}
		
		String[] result = new String[trainerNames.size()];
		trainerNames.toArray(result);
		return result;
	}
	
	public User getSelectedTrainer(GymTrack gym){
		return trainers.get(gym.trainer_AddEditUser.getSelectedIndex());
	}
	
	public User getUserToEdit(){
		return toEdit;
	}
}
