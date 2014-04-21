package edu.gymtrack.view;
import java.awt.*;

import javax.swing.*;

public class LoginUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	public void createLoginUI(GymTrack gym){
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		GridLayout gridLayout = new GridLayout(2,0);
		gym.getContentPane().setLayout(gridLayout);
		
		JPanel topPanel = new JPanel();
		topPanel.setSize(800, 400);
		gym.getContentPane().add(topPanel);
		
		JLabel lblGymtrack = new JLabel("GymTrack");
		lblGymtrack.setFont(new Font("Calibri", Font.PLAIN, 120));
		topPanel.add(lblGymtrack);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setSize(600, 50);
		gym.getContentPane().add(lowerPanel);
		GridBagLayout gridBadLayout = new GridBagLayout();
		lowerPanel.setLayout(gridBadLayout);
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 30));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 0;
		lowerPanel.add(separator, gbc_separator);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 13));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		lowerPanel.add(lblUsername, gbc_lblUsername);
		
		gym.txtUsername = new JTextField();
		gym.txtUsername.setMinimumSize(new Dimension(15, 20));
		gym.txtUsername.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 1;
		lowerPanel.add(gym.txtUsername, gbc_txtUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		lowerPanel.add(lblPassword, gbc_lblPassword);
		
		gym.txtPassword = new JPasswordField();
		gym.txtPassword.setSize(new Dimension(15, 20));
		gym.txtPassword.setPreferredSize(new Dimension(100, 20));
		gym.txtPassword.setMinimumSize(new Dimension(15, 20));
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 2;
		lowerPanel.add(gym.txtPassword, gbc_txtPassword);
		
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 15));
		gbc_separator = new GridBagConstraints();
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		lowerPanel.add(separator, gbc_separator);
		
		gym.btnSubmit = new JButton();
		gym.btnSubmit.setFont(new Font("Calibri", Font.BOLD, 14));
		gym.btnSubmit.setPreferredSize(new Dimension(90, 30));
		gym.btnSubmit.setText("Sign In");
		gym.btnSubmit.addActionListener(gym);
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 4;
		gbc_btnSubmit.gridwidth = 3;
		lowerPanel.add(gym.btnSubmit, gbc_btnSubmit);
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createLoginUI(gym);
		return this;
	}
}
