package edu.gymtrack.view;
import java.awt.*;

import javax.swing.*;

public class MainUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	public void createMainUI(GymTrack gym){
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		JPanel contentPane = new JPanel();
		gym.setContentPane(contentPane);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 126, 0, 0, 0};
		contentPane.setLayout(gridBagLayout);		
		
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
		gym.btnMyPlans = new JButton("My Plans");
		gym.btnMyPlans.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnMyPlans.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_btnMyPlans = new GridBagConstraints();
		gym.btnMyPlans.addActionListener(gym);
		gbc_btnMyPlans.insets = new Insets(40, 40, 0, 40);
		gbc_btnMyPlans.gridx = 0;
		gbc_btnMyPlans.gridy = 0;
		gym.getContentPane().add(gym.btnMyPlans, gbc_btnMyPlans);
		
		gym.btnAnalyzeMe = new JButton("Analyze Me");
		gym.btnAnalyzeMe.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnAnalyzeMe.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_btnAnalyze = new GridBagConstraints();
		gym.btnAnalyzeMe.addActionListener(gym);
		gbc_btnAnalyze.insets = new Insets(40, 0, 0, 40);
		gbc_btnAnalyze.gridx = 1;
		gbc_btnAnalyze.gridy = 0;
		gym.getContentPane().add(gym.btnAnalyzeMe, gbc_btnAnalyze);
	}

	private static void setTrainer(GymTrack gym) {
		gym.btnEditTrainees = new JButton("Edit Trainees");
		gym.btnEditTrainees.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnEditTrainees.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_btnEditTrainees = new GridBagConstraints();
		gym.btnEditTrainees.addActionListener(gym);
		gbc_btnEditTrainees.insets = new Insets(40, 0, 0, 40);
		gbc_btnEditTrainees.gridx = 2;
		gbc_btnEditTrainees.gridy = 0;
		gym.getContentPane().add(gym.btnEditTrainees, gbc_btnEditTrainees);
		
		gym.btnTrkTrainees = new JButton("Track Trainees");
		gym.btnTrkTrainees.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnTrkTrainees.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_btnTrkTrainees = new GridBagConstraints();
		gym.btnTrkTrainees.addActionListener(gym);
		gbc_btnTrkTrainees.insets = new Insets(40, 0, 0, 40);
		gbc_btnTrkTrainees.gridx = 3;
		gbc_btnTrkTrainees.gridy = 0;
		gym.getContentPane().add(gym.btnTrkTrainees, gbc_btnTrkTrainees);
	}
	
	private static void setOwner(GymTrack gym) {
		gym.btnEquipment = new JButton("Equipment");
		gym.btnEquipment.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnEquipment.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_btnEquipment = new GridBagConstraints();
		gym.btnEquipment.addActionListener(gym);
		gbc_btnEquipment.insets = new Insets(40, 40, 0, 40);
		gbc_btnEquipment.gridx = 0;
		gbc_btnEquipment.gridy = 1;
		gym.getContentPane().add(gym.btnEquipment, gbc_btnEquipment);
		
		gym.btnUsers = new JButton("Users");
		gym.btnUsers.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnUsers.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_btnUsers = new GridBagConstraints();
		gym.btnUsers.addActionListener(gym);
		gbc_btnUsers.insets = new Insets(40, 0, 0, 40);
		gbc_btnUsers.gridx = 1;
		gbc_btnUsers.gridy = 1;
		gym.getContentPane().add(gym.btnUsers, gbc_btnUsers);
		
		gym.btnAnalyzeGym = new JButton("Analyze Gym");
		gym.btnAnalyzeGym.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnAnalyzeGym.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints gbc_btnAnalyzeGym = new GridBagConstraints();
		gym.btnAnalyzeGym.addActionListener(gym);
		gbc_btnAnalyzeGym.insets = new Insets(40, 0, 0, 40);
		gbc_btnAnalyzeGym.gridx = 2;
		gbc_btnAnalyzeGym.gridy = 1;
		gym.getContentPane().add(gym.btnAnalyzeGym, gbc_btnAnalyzeGym);
	}
	
	@Override
	public GTUI showUI(GymTrack gym) {
		createMainUI(gym);
		return this;
	}
}
