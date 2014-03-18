package edu.gymtrack.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class LoginUI extends JApplet implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	public LoginUI() {
		
	}

	public void init() {
		
		setSize(600,300);
		setName("GymTrack");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel topPanel = new JPanel();
		topPanel.setSize(600, 300);
		getContentPane().add(topPanel);
		
		JLabel lblGymtrack = new JLabel("GymTrack");
		lblGymtrack.setFont(new Font("Calibri", Font.PLAIN, 120));
		topPanel.add(lblGymtrack);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setSize(600, 50);
		getContentPane().add(middlePanel);
		GridBagLayout gridBadLayout = new GridBagLayout();
		middlePanel.setLayout(gridBadLayout);
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 30));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 0;
		middlePanel.add(separator, gbc_separator);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 13));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		middlePanel.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setMinimumSize(new Dimension(15, 20));
		txtUsername.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 1;
		middlePanel.add(txtUsername, gbc_txtUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		middlePanel.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setSize(new Dimension(15, 20));
		txtPassword.setPreferredSize(new Dimension(100, 20));
		txtPassword.setMinimumSize(new Dimension(15, 20));
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 2;
		middlePanel.add(txtPassword, gbc_txtPassword);
		
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 15));
		gbc_separator = new GridBagConstraints();
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		middlePanel.add(separator, gbc_separator);
		
		JButton btnSubmit = new JButton();
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 14));
		btnSubmit.setPreferredSize(new Dimension(80, 20));
		btnSubmit.setText("Sign In");
		btnSubmit.addActionListener(this);
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 4;
		gbc_btnSubmit.gridwidth = 3;
		middlePanel.add(btnSubmit, gbc_btnSubmit);
			
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		submit();		
	}
	
	//
	// Return username and password to be authenticated through the database table
	//
	private void submit() {
		System.out.println(this.txtUsername.getText() + " " + String.valueOf(this.txtPassword.getPassword()));
	}

}
