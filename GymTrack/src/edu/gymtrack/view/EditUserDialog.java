package edu.gymtrack.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class EditUserDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	public GTUI callingUI;
	ButtonGroup addUser_buttonGroup = new ButtonGroup();

	public EditUserDialog(GymTrack gym, GTUI callingUI){
		this.callingUI = callingUI;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Edit a User");
		setBounds(100, 100, 450, 300);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblFirstName = new JLabel("First name");
		gym.firstName_EditUser = new JTextField();
		gym.firstName_EditUser.setColumns(10);
		JLabel lblLastName = new JLabel("Last name");
		gym.lastName_EditUser = new JTextField();
		gym.lastName_EditUser.setColumns(10);
		JLabel lblEmail = new JLabel("Email");
		gym.email_EditUser = new JTextField();
		gym.email_EditUser.setColumns(10);
		JLabel lblNewLabel = new JLabel("Username");
		gym.username_EditUser = new JTextField();
		gym.username_EditUser.setColumns(10);
		
		gym.rdbtnTrainer_addUser = new JRadioButton("Trainer");
		gym.rdbtnMember_addUser = new JRadioButton("Member");
		addUser_buttonGroup.add(gym.rdbtnMember_addUser);
		addUser_buttonGroup.add(gym.rdbtnTrainer_addUser);
		gym.rdbtnMember_addUser.setSelected(true);
		


		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblFirstName)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(gym.firstName_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(lblLastName)
														.addComponent(lblEmail))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(gym.lastName_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																		.addComponent(gym.email_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(gym.username_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
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
								.addComponent(gym.firstName_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLastName)
										.addComponent(gym.lastName_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblEmail)
												.addComponent(gym.email_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNewLabel)
														.addComponent(gym.username_EditUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
																.addComponent(gym.rdbtnMember_addUser)
																.addComponent(gym.rdbtnTrainer_addUser))
																.addGap(22))
				);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				gym.okButton_EditUser = new JButton("OK");
				gym.okButton_EditUser.addActionListener(gym);
				gym.okButton_EditUser.setActionCommand("OK");
				buttonPane.add(gym.okButton_EditUser);
				getRootPane().setDefaultButton(gym.okButton_AddUser);
			}
			{
				gym.cancelButton_AddUser = new JButton("Cancel");
				gym.cancelButton_AddUser.setActionCommand("Cancel");
				buttonPane.add(gym.cancelButton_AddUser);
			}
		}
		
		if(callingUI == gym.editTraineesUI){
			gym.rdbtnMember_addUser.hide();
			gym.rdbtnTrainer_addUser.hide();
		}

	}
}
