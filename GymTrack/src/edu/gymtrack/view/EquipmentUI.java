package edu.gymtrack.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class EquipmentUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	public void createEquipmentUI(GymTrack gym){
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		JPanel contentPane = new JPanel();
		gym.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//setTitle("Equipment");
		
		String[] columnNames = {"Type", "Count", "Utilization"};
		Object[][] tableData = getTableData();

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);

		gym.btnAdd_equipment = new JButton("Add");
		topContainer.add(gym.btnAdd_equipment);
		gym.btnEdit_equipment = new JButton("Edit");
		topContainer.add(gym.btnEdit_equipment);
		gym.btnDelete_equipment = new JButton("Delete");
		topContainer.add(gym.btnDelete_equipment);
		
		JPanel bottomContainer = new JPanel();
		contentPane.add(bottomContainer, BorderLayout.CENTER);
		bottomContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		bottomContainer.add(bottomPanel, BorderLayout.SOUTH);
		
		gym.btnBack_equipment = new JButton("Back");
		gym.btnBack_equipment.addActionListener(gym);
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addComponent(gym.btnBack_equipment)
					.addContainerGap(546, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_bottomPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(gym.btnBack_equipment))
		);
		bottomPanel.setLayout(gl_bottomPanel);
		
		JScrollPane scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);
		
		gym.equipmentTable_equipment = new JTable(tableData, columnNames);
		gym.equipmentTable_equipment.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.equipmentTable_equipment);
	}

	//TODO implement
	private static Object[][] getTableData(){
		Object[][] data = {
			{"Treadmill", "15", "75%"}, {"Pull-up Bar", "2", "20%"},
			{"Bowflex", "3", "85%"}, {"Free-weights", "6", "45%"},
			{"Leg-press", "2", "25%"}, {"High-squat", "1","25%"}, 
			{"Leg-extension", "3", "20%"}, {"Leg-curl", "3", "60%"}, 
			{"Lat-pull down", "1", "75%"},{"Pec-deck", "1", "75%"}
		};
		return data;
	}
	@Override
	public GTUI showUI(GymTrack gym) {
		createEquipmentUI(gym);
		return this;
	}
}
