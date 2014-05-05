package edu.gymtrack.view;
import java.awt.*;

import javax.swing.*;

public class MainUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	static JLabel buttonSpacer1;
	static JLabel buttonSpacer2;
	static JLabel buttonSpacer3;
	static JLabel buttonSpacer4;
	
	public void createMainUI(GymTrack gym){
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		JPanel contentPane = new JPanel();
		gym.setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());		
		
		setLayout(gym);
	}
	
	private static void setLayout(GymTrack gym) {
		switch (gym.privilege) {
		case 0:
			setMember(gym);
			break;
		case 1:
			setMember(gym);
			setTrainer(gym);
			break;
		case 2:
			setMember(gym);
			setTrainer(gym);
			setOwner(gym);
			break;
		default:
			System.out.println("Invalid privilege level: " + gym.privilege);
		}
	}
	
	private static void setMember(GymTrack gym){
		gym.btnMyPlans = new JButton(new ImageIcon("images/myplans.png", "My Plans"));
		gym.btnMyPlans.setPreferredSize(new Dimension(150, 100));
		gym.btnMyPlans.setRolloverIcon(new ImageIcon("images/myplans_over.png", "My Plans"));
		GridBagConstraints gbc_btnMyPlans = new GridBagConstraints();
		gym.btnMyPlans.addActionListener(gym);
		gbc_btnMyPlans.insets = new Insets(0, 40, 0, 40);
		gbc_btnMyPlans.gridx = 0;
		gbc_btnMyPlans.gridy = 0;
		gym.getContentPane().add(gym.btnMyPlans, gbc_btnMyPlans);
		
		gym.btnAnalyzeMe = new JButton(new ImageIcon("images/analyzeme.png", "Analyze Me"));
		gym.btnAnalyzeMe.setPreferredSize(new Dimension(150, 100));
		gym.btnAnalyzeMe.setRolloverIcon(new ImageIcon("images/analyzeme_over.png", "Analyze Me"));
		GridBagConstraints gbc_btnAnalyze = new GridBagConstraints();
		gym.btnAnalyzeMe.addActionListener(gym);
		gbc_btnAnalyze.insets = new Insets(0, 0, 0, 40);
		gbc_btnAnalyze.gridx = 1;
		gbc_btnAnalyze.gridy = 0;
		gym.getContentPane().add(gym.btnAnalyzeMe, gbc_btnAnalyze);
		
		buttonSpacer1 = new JLabel();
		buttonSpacer1.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_spacer = new GridBagConstraints();
		gbc_spacer.insets = new Insets(0, 0, 0, 40);
		gbc_spacer.gridx = 2;
		gbc_spacer.gridy = 0;
		gym.getContentPane().add(buttonSpacer1, gbc_spacer);
		
		buttonSpacer2 = new JLabel();
		buttonSpacer2.setPreferredSize(new Dimension(150, 100));
		gbc_spacer = new GridBagConstraints();
		gbc_spacer.insets = new Insets(0, 0, 0, 40);
		gbc_spacer.gridx = 3;
		gbc_spacer.gridy = 0;
		gym.getContentPane().add(buttonSpacer2, gbc_spacer);
		
		buttonSpacer3 = new JLabel();
		buttonSpacer3.setPreferredSize(new Dimension(150, 100));
		gbc_spacer = new GridBagConstraints();
		gbc_spacer.insets = new Insets(40, 0, 0, 40);
		gbc_spacer.gridx = 0;
		gbc_spacer.gridy = 1;
		gym.getContentPane().add(buttonSpacer3, gbc_spacer);
		
		buttonSpacer4 = new JLabel();
		buttonSpacer4.setPreferredSize(new Dimension(150, 100));
		gbc_spacer = new GridBagConstraints();
		gbc_spacer.insets = new Insets(40, 0, 0, 40);
		gbc_spacer.gridx = 0;
		gbc_spacer.gridy = 2;
		gym.getContentPane().add(buttonSpacer4, gbc_spacer);
		
		gym.btnLogout = new JButton(new ImageIcon("images/logout.png", "Logout"));
		gym.btnLogout.setRolloverIcon(new ImageIcon("images/logout_over.png", ""));
		gym.btnLogout.setPreferredSize(new Dimension(120, 30));
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gym.btnLogout.addActionListener(gym);
		gbc_btnLogout.insets = new Insets(110, 30, 0, 0);
		gbc_btnLogout.gridx = 3;
		gbc_btnLogout.gridy = 2;
		gym.getContentPane().add(gym.btnLogout, gbc_btnLogout);
	}

	private static void setTrainer(GymTrack gym) {
		buttonSpacer1.setVisible(false);
		buttonSpacer2.setVisible(false);
		buttonSpacer3.setVisible(false);
		
		gym.btnEditTrainees = new JButton(new ImageIcon("images/edittrainees.png", "Edit Trainees"));
		gym.btnEditTrainees.setPreferredSize(new Dimension(150, 100));
		gym.btnEditTrainees.setRolloverIcon(new ImageIcon("images/edittrainees_over.png", "Edit Trainees"));
		GridBagConstraints gbc_btnEditTrainees = new GridBagConstraints();
		gym.btnEditTrainees.addActionListener(gym);
		gbc_btnEditTrainees.insets = new Insets(0, 0, 0, 40);
		gbc_btnEditTrainees.gridx = 2;
		gbc_btnEditTrainees.gridy = 0;
		gym.getContentPane().add(gym.btnEditTrainees, gbc_btnEditTrainees);
		
		gym.btnTrkTrainees = new JButton(new ImageIcon("images/tracktrainees.png", "Track Trainees"));
		gym.btnTrkTrainees.setPreferredSize(new Dimension(150, 100));
		gym.btnTrkTrainees.setRolloverIcon(new ImageIcon("images/tracktrainees_over.png", "Track Trainees"));
		GridBagConstraints gbc_btnTrkTrainees = new GridBagConstraints();
		gym.btnTrkTrainees.addActionListener(gym);
		gbc_btnTrkTrainees.insets = new Insets(0, 0, 0, 40);
		gbc_btnTrkTrainees.gridx = 3;
		gbc_btnTrkTrainees.gridy = 0;
		gym.getContentPane().add(gym.btnTrkTrainees, gbc_btnTrkTrainees);
		
		gym.btnAnalyzeTrainees = new JButton(new ImageIcon("images/analyzetrainees.png", "Analyze Trainees"));
		gym.btnAnalyzeTrainees.setPreferredSize(new Dimension(150, 100));
		gym.btnAnalyzeTrainees.setRolloverIcon(new ImageIcon("images/analyzetrainees_over.png", "Analyze Trainees"));
		GridBagConstraints gbc_btnAnalyzeTrainees = new GridBagConstraints();
		gym.btnAnalyzeTrainees.addActionListener(gym);
		gbc_btnAnalyzeTrainees.insets = new Insets(40, 40, 0, 40);
		gbc_btnAnalyzeTrainees.gridx = 0;
		gbc_btnAnalyzeTrainees.gridy = 1;
		gym.getContentPane().add(gym.btnAnalyzeTrainees, gbc_btnAnalyzeTrainees);
	}
	
	private static void setOwner(GymTrack gym) {
		buttonSpacer4.setVisible(false);
		
		gym.btnEquipment = new JButton(new ImageIcon("images/equipment.png", "Equipment"));
		gym.btnEquipment.setPreferredSize(new Dimension(150, 100));
		gym.btnEquipment.setRolloverIcon(new ImageIcon("images/equipment_over.png", "Equipment"));
		GridBagConstraints gbc_btnEquipment = new GridBagConstraints();
		gym.btnEquipment.addActionListener(gym);
		gbc_btnEquipment.insets = new Insets(40, 0, 0, 40);
		gbc_btnEquipment.gridx = 1;
		gbc_btnEquipment.gridy = 1;
		gym.getContentPane().add(gym.btnEquipment, gbc_btnEquipment);
		
		gym.btnUsers = new JButton(new ImageIcon("images/users.png", "Users"));
		gym.btnUsers.setPreferredSize(new Dimension(150, 100));
		gym.btnUsers.setRolloverIcon(new ImageIcon("images/users_over.png", "Users"));
		GridBagConstraints gbc_btnUsers = new GridBagConstraints();
		gym.btnUsers.addActionListener(gym);
		gbc_btnUsers.insets = new Insets(40, 0, 0, 40);
		gbc_btnUsers.gridx = 2;
		gbc_btnUsers.gridy = 1;
		gym.getContentPane().add(gym.btnUsers, gbc_btnUsers);
		
		gym.btnAnalyzeGym = new JButton(new ImageIcon("images/analyzegym.png", "Analyze Gym"));
		gym.btnAnalyzeGym.setPreferredSize(new Dimension(150, 100));
		gym.btnAnalyzeGym.setRolloverIcon(new ImageIcon("images/analyzegym_over.png", "Analyze Gym"));
		GridBagConstraints gbc_btnAnalyzeGym = new GridBagConstraints();
		gym.btnAnalyzeGym.addActionListener(gym);
		gbc_btnAnalyzeGym.insets = new Insets(40, 0, 0, 40);
		gbc_btnAnalyzeGym.gridx = 3;
		gbc_btnAnalyzeGym.gridy = 1;
		gym.getContentPane().add(gym.btnAnalyzeGym, gbc_btnAnalyzeGym);
		
		gym.btnReports = new JButton(new ImageIcon("images/generatereports.png", "Reports"));
		gym.btnReports.setPreferredSize(new Dimension(150, 100));
		gym.btnReports.setRolloverIcon(new ImageIcon("images/generatereports_over.png", "Reports"));
		GridBagConstraints gbc_btnReports = new GridBagConstraints();
		gym.btnReports.addActionListener(gym);
		gbc_btnReports.insets = new Insets(40, 40, 0, 40);
		gbc_btnReports.gridx = 0;
		gbc_btnReports.gridy = 2;
		gym.getContentPane().add(gym.btnReports, gbc_btnReports);
	}
	
	@Override
	public GTUI showUI(GymTrack gym) {
		createMainUI(gym);
		return this;
	}
}
