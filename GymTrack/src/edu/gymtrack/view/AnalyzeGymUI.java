package edu.gymtrack.view;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.WorkoutLog;

import javax.swing.*;

public class AnalyzeGymUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	
	public void createAnalyzeGymUI(GymTrack gym){
		
		final Factory factory = new Factory();
		
		final PieDataset dataset = createDataset(factory);
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800,350));		
		
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		gym.getContentPane().setLayout(new FlowLayout());
		
		gym.getContentPane().add(chartPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(800,50));
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(530, 0));
		bottomPanel.add(separator);
		
		gym.btnBack_AnalyzeGym = new JButton("", new ImageIcon("images/back.png", "Back"));
		gym.btnBack_AnalyzeGym.setPreferredSize(new Dimension(90, 30));
		gym.btnBack_AnalyzeGym.setRolloverIcon(new ImageIcon("images/back_over.png", "Back"));
		gym.btnBack_AnalyzeGym.addActionListener(gym);
		bottomPanel.add(gym.btnBack_AnalyzeGym);
		
		gym.btnLogout = new JButton("", new ImageIcon("images/logout.png", "Logout"));
		gym.btnLogout.setPreferredSize(new Dimension(120, 30));
		gym.btnLogout.setRolloverIcon(new ImageIcon("images/logout_over.png", "Logout"));
		gym.btnLogout.addActionListener(gym);
		bottomPanel.add(gym.btnLogout);
		
		gym.getContentPane().add(bottomPanel);
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createAnalyzeGymUI(gym);
		return this;
	}
	
	private PieDataset createDataset(Factory factory)
	{
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		try {
			ArrayList<Equipment> equipment = factory.getEquipment();
			for (Equipment current: equipment)
			{
				ArrayList<WorkoutLog> logs = factory.getWorkoutLogs(current);
				dataset.setValue(current.getName(), logs.size());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return dataset;
	}
	
	private JFreeChart createChart(PieDataset dataset)
	{
		JFreeChart chart = ChartFactory.createPieChart(
	            "Equipment Usage",  // chart title
	            dataset,             // data
	            true,               // include legend
	            true,
	            false
	        );
		
		return chart;
	}
}
