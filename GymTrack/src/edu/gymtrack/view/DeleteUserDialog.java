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

import edu.gymtrack.model.User;

public class DeleteUserDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	public GTUI callingUI;
	ButtonGroup addUser_buttonGroup = new ButtonGroup();

	public DeleteUserDialog(GymTrack gym, GTUI callingUI, User user){
		this.callingUI = callingUI;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Delete a User");
		setBounds(100, 100, 450, 120);
		this.setPreferredSize(new Dimension(450,120));
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new FlowLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel question = new JLabel("Are you sure you want to delete \"" + user.getUsername() + "\"?");
		contentPanel.add(question);
		
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
	}
}
