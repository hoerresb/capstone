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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class DeleteUserDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	public GTUI callingUI;
	ButtonGroup addUser_buttonGroup = new ButtonGroup();

	public DeleteUserDialog(GymTrack gym, GTUI callingUI){
		this.callingUI = callingUI;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Delete a User");
		setBounds(100, 100, 450, 300);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblFirstName = new JLabel("User Name");
		gym.firstName_DeleteUser = new JTextField();
		gym.firstName_DeleteUser.setColumns(10);
		/*JLabel lblLastName = new JLabel("Last name");
		gym.lastName_AddUser = new JTextField();
		gym.lastName_AddUser.setColumns(10);
		JLabel lblEmail = new JLabel("Email");
		gym.email_AddUser = new JTextField();
		gym.email_AddUser.setColumns(10);
		JLabel lblNewLabel = new JLabel("Username");
		gym.username_AddUser = new JTextField();
		gym.username_AddUser.setColumns(10);*/

		//gym.rdbtnTrainer_addUser = new JRadioButton("Trainer");
		//gym.rdbtnMember_addUser = new JRadioButton("Member");
		//addUser_buttonGroup.add(gym.rdbtnMember_addUser);
		//addUser_buttonGroup.add(gym.rdbtnTrainer_addUser);
		//gym.rdbtnMember_addUser.setSelected(true);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblFirstName)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(gym.firstName_DeleteUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
				
				))));
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFirstName)
								.addComponent(gym.firstName_DeleteUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										
				)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				gym.okButton_AddEditUser = new JButton("OK");
				gym.okButton_AddEditUser.addActionListener(gym);
				gym.okButton_AddEditUser.setActionCommand("OK");
				buttonPane.add(gym.okButton_AddEditUser);
				getRootPane().setDefaultButton(gym.okButton_AddEditUser);
			}
			{
				gym.cancelButton_AddEditUser = new JButton("Cancel");
				gym.cancelButton_AddEditUser.setActionCommand("Cancel");
				buttonPane.add(gym.cancelButton_AddEditUser);
			}
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		gym.okButton_DeleteUser = new JButton(new ImageIcon("images/dialog_ok.png", "Ok"));
		gym.okButton_DeleteUser.setPreferredSize(new Dimension(70,25));
		gym.okButton_DeleteUser.setRolloverIcon(new ImageIcon("images/dialog_ok_over.png", "Ok"));
		gym.okButton_DeleteUser.addActionListener(gym);
		buttonPane.add(gym.okButton_DeleteUser);
		
		gym.cancelButton_DeleteUser = new JButton(new ImageIcon("images/dialog_cancel.png", "Cancel"));
		gym.cancelButton_DeleteUser.setPreferredSize(new Dimension(70,25));
		gym.cancelButton_DeleteUser.setRolloverIcon(new ImageIcon("images/dialog_cancel_over.png", "Cancel"));
		gym.cancelButton_DeleteUser.addActionListener(gym);
		buttonPane.add(gym.cancelButton_DeleteUser);
		
		if(callingUI == gym.editTraineesUI){
			gym.rdbtnMember_addUser.setVisible(false);
			gym.rdbtnTrainer_addUser.setVisible(false);
		}

	}
}
