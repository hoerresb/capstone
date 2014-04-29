package edu.gymtrack.view;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class AnalyzeMeUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	
	
	public void createAnalyzeMeUI(GymTrack gym){
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		gym.getContentPane().setLayout(new FlowLayout());
		
		final Factory factory = new Factory();
		
		final XYDataset dataset = createDataset(factory, gym); 
		final JFreeChart chart = ChartFactory.createTimeSeriesChart(
				gym.loggedIn.getUsername() + "'s Workout History", 
				"Date",
				"Work",
				dataset);
		
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600,350));
		chartPanel.setMinimumSize(new Dimension(400,350));

		gym.getContentPane().add(chartPanel);
		/* 
		JLabel lblGymtrack = new JLabel("TODO - create AnalyzeMeUI");
		lblGymtrack.setFont(new Font("Calibri", Font.PLAIN, 30));
		topPanel.add(lblGymtrack);
		*/
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(800,50));
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(530, 0));
		bottomPanel.add(separator);
		
		gym.btnBack_AnalyzeMe = new JButton("", new ImageIcon("images/back.png", "Back"));
		gym.btnBack_AnalyzeMe.setPreferredSize(new Dimension(90, 30));
		gym.btnBack_AnalyzeMe.setRolloverIcon(new ImageIcon("images/back_over.png", "Back"));
		gym.btnBack_AnalyzeMe.addActionListener(gym);
		bottomPanel.add(gym.btnBack_AnalyzeMe);
		
		gym.btnLogout = new JButton("", new ImageIcon("images/logout.png", "Logout"));
		gym.btnLogout.setPreferredSize(new Dimension(120, 30));
		gym.btnLogout.setRolloverIcon(new ImageIcon("images/logout_over.png", "Logout"));
		gym.btnLogout.addActionListener(gym);
		bottomPanel.add(gym.btnLogout);
		
		gym.getContentPane().add(bottomPanel);
	}
	
	private XYDataset createDataset(Factory factory, GymTrack gym){
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		try {
			ArrayList<WorkoutPlan> plans = factory.getWorkoutPlansForUser(gym.loggedIn);
			
		for (WorkoutPlan currentPlan: plans)
		{
			ArrayList<PlanElement> elements = factory.getPlanElements(currentPlan);
			for (PlanElement currentElement: elements)
			{
				ArrayList<WorkoutLog> logs = factory.getWorkoutLogs(currentElement.getKey());
				if (!logs.isEmpty()){
					final TimeSeries series = new TimeSeries(currentElement.getActivityName());
					for (WorkoutLog currentLog: logs)
					{
						series.add(new Second(currentLog.getDate()), currentLog.getNCompleted());
					}
					dataset.addSeries(series);
				}
			}
		}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataset;
	}
	
//    private JFreeChart createChart(final XYDataset dataset, GymTrack gym) {
//    	
//    	// shamelessly ripped from java2s.com
//        
//        // create the chart...
//        final JFreeChart chart = ChartFactory.createXYLineChart(
//            (gym.loggedIn.getUsername() + "'s Workout History"),      // chart title
//            "Date",    
//            "Work",    
//            dataset,   
//            PlotOrientation.VERTICAL,
//            true,      
//            true,      
//            false
//        );
//
//
//        chart.setBackgroundPaint(Color.white);
//
//        final XYPlot plot = chart.getXYPlot();
//        plot.setBackgroundPaint(Color.lightGray);
//    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
//        plot.setDomainGridlinePaint(Color.white);
//        plot.setRangeGridlinePaint(Color.white);
//        
//        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesLinesVisible(0, false);
//        renderer.setSeriesShapesVisible(1, false);
//        plot.setRenderer(renderer);
//
//        // change the auto tick unit selection to integer units only...
////        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
////        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
////        rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
//        // OPTIONAL CUSTOMISATION COMPLETED.
//                
//        return chart;
//        
//    }

	public GTUI showUI(GymTrack gym) {
		createAnalyzeMeUI(gym);
		return this;
	}
}
