package edu.gymtrack.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class AddEditUserDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	public GTUI callingUI;
	private boolean hideOkBtn = false; 
	ButtonGroup addUser_buttonGroup = new ButtonGroup();

	public AddEditUserDialog(GymTrack gym, GTUI callingUI, boolean isEdit){
		this.callingUI = callingUI;
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		if (isEdit) {
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
		
		JPanel firstName = new JPanel();
		firstName.setLayout(new FlowLayout());
		firstName.setPreferredSize(new Dimension(450, 50));
		JLabel lblFirstName = new JLabel("First name: *");
		gym.firstName_AddEditUser = new JTextField();
		gym.firstName_AddEditUser.setColumns(10);
		firstName.add(lblFirstName);
		firstName.add(gym.firstName_AddEditUser);
		contentPanel.add(firstName);
		
		JPanel lastName = new JPanel();
		lastName.setLayout(new FlowLayout());
		lastName.setPreferredSize(new Dimension(450, 50));
		JLabel lblLastName = new JLabel("Last name: *");
		gym.lastName_AddEditUser = new JTextField();
		gym.lastName_AddEditUser.setColumns(10);
		lastName.add(lblLastName);
		lastName.add(gym.lastName_AddEditUser);
		contentPanel.add(lastName);
		
		JLabel lblEmail = new JLabel("Email:");
		gym.email_AddEditUser = new JTextField();
		gym.email_AddEditUser.setColumns(20);
		contentPanel.add(lblEmail);
		contentPanel.add(gym.email_AddEditUser);
		
		JLabel lblName = new JLabel("Username: *");
		gym.username_AddEditUser = new JTextField();
		gym.username_AddEditUser.setColumns(15);
		contentPanel.add(lblName);
		contentPanel.add(gym.username_AddEditUser);
		
		JLabel lblPass = new JLabel("Password: *");
		gym.password_AddEditUser = new JPasswordField();
		gym.password_AddEditUser.setColumns(15);
		contentPanel.add(lblPass);
		contentPanel.add(gym.firstName_AddEditUser);
		
		JLabel lblRequired = new JLabel("The fields marked with a (*) are required");
		contentPanel.add(lblRequired);
		
		gym.rdbtnTrainer_addUser = new JRadioButton("Trainer");
		gym.rdbtnMember_addUser = new JRadioButton("Member");
		addUser_buttonGroup.add(gym.rdbtnMember_addUser);
		addUser_buttonGroup.add(gym.rdbtnTrainer_addUser);
		gym.rdbtnMember_addUser.setSelected(true);
		
		

		/*GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblFirstName)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(gym.firstName_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(lblLastName)
														.addComponent(lblEmail))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(gym.lastName_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																		.addComponent(gym.email_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(gym.username_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
																		.addGroup(gl_contentPanel.createSequentialGroup()
																				.addComponent(gym.rdbtnMember_addUser)
																				.addPreferredGap(ComponentPlacement.UNRELATED)
																				.addComponent(gym.rdbtnTrainer_addUser)))
																				.addContainerGap(272, Short.MAX_VALUE))
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFirstName)
								.addComponent(gym.firstName_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLastName)
										.addComponent(gym.lastName_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblEmail)
												.addComponent(gym.email_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNewLabel)
														.addComponent(gym.username_AddEditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
																.addComponent(gym.rdbtnMember_addUser)
																.addComponent(gym.rdbtnTrainer_addUser))
																.addGap(22))
				);*/
		
		//contentPanel.setLayout(gl_contentPanel);
		
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
		
		if(callingUI == gym.editTraineesUI){
			gym.rdbtnMember_addUser.setVisible(false);
			gym.rdbtnTrainer_addUser.setVisible(false);
			gym.password_AddEditUser.setVisible(false);
		}

	}
}
