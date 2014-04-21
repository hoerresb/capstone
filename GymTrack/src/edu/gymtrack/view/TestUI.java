package edu.gymtrack.view;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class TestUI extends JApplet {

	/**
	 * Create the applet.
	 */
	public TestUI() {
		
	}
	
	public void init() {
		this.setSize(800,400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.getContentPane().setLayout(gridBagLayout);
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(530, 0));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		this.getContentPane().add(separator);
		
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		ImageIcon backIcon = new ImageIcon("images/back.png", "");
		JButton btnBack_EditTrainees = new JButton("", backIcon);
		btnBack_EditTrainees.setPreferredSize(new Dimension(90, 30));
		gbc_btnBack.insets = new Insets(10, 10, 10, 10);
		//gym.btnBack_EditTrainees.setMinimumSize(new Dimension(90, 30));
		//btnBack_EditTrainees.addActionListener(gym);
		
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		ImageIcon logoutIcon = new ImageIcon("images/logout.png", "");
		JButton btnLogout = new JButton("", logoutIcon);
		btnLogout.setPreferredSize(new Dimension(120, 30));
		gbc_btnLogout.insets = new Insets(10, 10, 10, 10);
		
		this.getContentPane().add(btnBack_EditTrainees, gbc_btnBack);
		this.getContentPane().add(btnLogout, gbc_btnLogout);
	}

}
