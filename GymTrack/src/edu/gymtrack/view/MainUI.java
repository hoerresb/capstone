package edu.gymtrack.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainUI extends JApplet implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private int privilege;
	
	public MainUI() {

	}
	
	public MainUI(int privilege) {
		this.privilege = privilege;
	}

	@Override
	public void init() {
		setSize(600,400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 126, 0, 0, 0};
		getContentPane().setLayout(gridBagLayout);		
		
		JButton btnMyPlans = new JButton("My Plans");
		btnMyPlans.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnMyPlans.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnMyPlans = new GridBagConstraints();
		gbc_btnMyPlans.insets = new Insets(40, 40, 0, 40);
		gbc_btnMyPlans.gridx = 0;
		gbc_btnMyPlans.gridy = 0;
		this.getContentPane().add(btnMyPlans, gbc_btnMyPlans);
		
		JButton btnAnalyze = new JButton("Analyze Me");
		btnAnalyze.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnAnalyze.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnAnalyze = new GridBagConstraints();
		gbc_btnAnalyze.insets = new Insets(40, 0, 0, 40);
		gbc_btnAnalyze.gridx = 1;
		gbc_btnAnalyze.gridy = 0;
		this.getContentPane().add(btnAnalyze, gbc_btnAnalyze);
		
		JButton btnEditTrainees = new JButton("Edit Trainees");
		btnEditTrainees.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnEditTrainees.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnEditTrainees = new GridBagConstraints();
		gbc_btnEditTrainees.insets = new Insets(40, 0, 0, 40);
		gbc_btnEditTrainees.gridx = 2;
		gbc_btnEditTrainees.gridy = 0;
		this.getContentPane().add(btnEditTrainees, gbc_btnEditTrainees);
		
		JButton btnTrkTrainees = new JButton("Track Trainees");
		btnTrkTrainees.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnTrkTrainees.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnTrkTrainees = new GridBagConstraints();
		gbc_btnTrkTrainees.insets = new Insets(40, 0, 0, 40);
		gbc_btnTrkTrainees.gridx = 3;
		gbc_btnTrkTrainees.gridy = 0;
		this.getContentPane().add(btnTrkTrainees, gbc_btnTrkTrainees);
		
		JButton btnEquipment = new JButton("Equipment");
		btnEquipment.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnEquipment.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnEquipment = new GridBagConstraints();
		gbc_btnEquipment.insets = new Insets(40, 40, 0, 40);
		gbc_btnEquipment.gridx = 0;
		gbc_btnEquipment.gridy = 1;
		this.getContentPane().add(btnEquipment, gbc_btnEquipment);
		
		JButton btnUsers = new JButton("Users");
		btnUsers.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnUsers.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnUsers = new GridBagConstraints();
		gbc_btnUsers.insets = new Insets(40, 0, 0, 40);
		gbc_btnUsers.gridx = 1;
		gbc_btnUsers.gridy = 1;
		this.getContentPane().add(btnUsers, gbc_btnUsers);
		
		JButton btnAnalyzeGym = new JButton("Analyze Gym");
		btnAnalyzeGym.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnAnalyzeGym.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnAnalyzeGym = new GridBagConstraints();
		gbc_btnAnalyzeGym.insets = new Insets(40, 0, 0, 40);
		gbc_btnAnalyzeGym.gridx = 2;
		gbc_btnAnalyzeGym.gridy = 1;
		this.getContentPane().add(btnAnalyzeGym, gbc_btnAnalyzeGym);
	}
	
	public void setLayout() {
		switch (privilege) {
		case 0:
			setMember();
			break;
		case 1:
			setMember();
			setTrainer();
			break;
		case 2:
			setMember();
			setTrainer();
			setOwner();
			break;
		default:
			System.out.println("Invalid privilege level: " + privilege);
		}
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void setMember(){
		JButton btnMyPlans = new JButton("My Plans");
		btnMyPlans.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnMyPlans = new GridBagConstraints();
		gbc_btnMyPlans.gridx = 1;
		gbc_btnMyPlans.gridy = 1;
		this.getContentPane().add(btnMyPlans, gbc_btnMyPlans);
		
		JButton btnAnalyze = new JButton("My Plans");
		btnAnalyze.setPreferredSize(new Dimension(100, 100));
		GridBagConstraints gbc_btnAnalyze = new GridBagConstraints();
		gbc_btnAnalyze.gridx = 2;
		gbc_btnAnalyze.gridy = 1;
		this.getContentPane().add(btnAnalyze, gbc_btnAnalyze);
	}

	private void setTrainer() {
		
	}
	
	private void setOwner() {
		
	}
}
