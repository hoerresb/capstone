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
		ChartPanel panel = new ChartPanel(chart);
		
		
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		GridLayout gridLayout = new GridLayout(2,0);
		gym.getContentPane().setLayout(gridLayout);
		
		JPanel topPanel = new JPanel();
		topPanel.setSize(800, 400);
		gym.getContentPane().add(panel);
		
		JLabel lblGymtrack = new JLabel("TODO - create AnalyzeGymUI");
		lblGymtrack.setFont(new Font("Calibri", Font.PLAIN, 30));
		topPanel.add(lblGymtrack);
		
		gym.btnBack_AnalyzeGym = new JButton("Back");
		gym.btnBack_AnalyzeGym.addActionListener(gym);
		gym.getContentPane().add(gym.btnBack_AnalyzeGym);
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
