package edu.gymtrack.view;
import java.awt.*;

import javax.swing.*;

public class AnalyzeMeUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	public void createAnalyzeMeUI(GymTrack gym){
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		GridLayout gridLayout = new GridLayout(2,0);
		gym.getContentPane().setLayout(gridLayout);
		
		JPanel topPanel = new JPanel();
		topPanel.setSize(800, 400);
		gym.getContentPane().add(topPanel);
		 
		JLabel lblGymtrack = new JLabel("TODO - create AnalyzeMeUI");
		lblGymtrack.setFont(new Font("Calibri", Font.PLAIN, 30));
		topPanel.add(lblGymtrack);
		
		gym.btnBack_AnalyzeMe = new JButton("Back");
		gym.btnBack_AnalyzeMe.setPreferredSize(new Dimension(90, 30));
		gym.btnBack_AnalyzeMe.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnBack_AnalyzeMe.addActionListener(gym);
		topPanel.add(gym.btnBack_AnalyzeMe);
		gym.btnLogout.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnLogout.setPreferredSize(new Dimension(90, 30));
		gym.btnLogout.addActionListener(gym);
		topPanel.add(gym.btnLogout);
	}

	public GTUI showUI(GymTrack gym) {
		createAnalyzeMeUI(gym);
		return this;
	}
}
