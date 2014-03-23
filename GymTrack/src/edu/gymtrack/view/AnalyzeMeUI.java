package edu.gymtrack.view;
import java.awt.*;
import javax.swing.*;

public class AnalyzeMeUI extends GymTrack {
	private static final long serialVersionUID = 1L;
	
	public static void createAnalyzeMeUI(GymTrack gym){
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
	}
}
