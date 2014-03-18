package edu.gymtrack.view;

import javax.swing.JApplet;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class MainUI extends JApplet {
	private int privilege;
	
	public MainUI(int privilege) {
		this.privilege = privilege;
	}

	public void init() {
		setSize(600,600);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 3};
		gridBagLayout.rowHeights = new int[]{0, 0, 2};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		setLayout();
	}
	
	public void setLayout() {
		switch (privilege) {
		case 0:
			
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		default:
			System.out.println("Invalid privilege level: " + privilege);
		}
		JButton btnMyPlans = new JButton("My Plans");
		btnMyPlans.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnMyPlans = new GridBagConstraints();
		gbc_btnMyPlans.gridx = 1;
		gbc_btnMyPlans.gridy = 1;
		getContentPane().add(btnMyPlans, gbc_btnMyPlans);
	}

}
