package edu.gymtrack.view;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.WorkoutLog;

public class EquipmentUI extends GTUI {
	private static final long serialVersionUID = 1L;
	private final String[] columnNames = {"Name", "Type", "Utilization"};
	
	private Object[][] tableData;
	private ArrayList<Equipment> equipments = null;
	private JScrollPane scrollablePane;
	
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
		
		tableData = getTableData();

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);

		gym.btnAdd_equipment = new JButton("Add");
		gym.btnAdd_equipment.addActionListener(gym);
		topContainer.add(gym.btnAdd_equipment);
		
		gym.btnEdit_equipment = new JButton("Edit");
		gym.btnEdit_equipment.addActionListener(gym);
		topContainer.add(gym.btnEdit_equipment);
		
		gym.btnDelete_equipment = new JButton("Delete");
		gym.btnDelete_equipment.addActionListener(gym);
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
		
		scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);
		
		gym.equipmentTable_equipment = new JTable(tableData, columnNames);
		gym.equipmentTable_equipment.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.equipmentTable_equipment);
	}
	
	private void updateEquipmentFromDB(){
		Factory f = new Factory();
		try {
			equipments = f.getEquipment();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Object[][] getTableData(){
		Factory f = new Factory();
		updateEquipmentFromDB();
		
		if(equipments == null)
			return null;
		
		Object[][] data = new Object[equipments.size()][3];
		for(int i = 0; i < equipments.size(); ++i){
			Equipment equipment = equipments.get(i);
			data[i][0] = equipment.getName();
			data[i][1] = equipment.getType().getName();
			
			int util = 0;
			try {
				ArrayList<WorkoutLog> logs = f.getWorkoutLogs(equipment);
				for(WorkoutLog log : logs)
					util += log.getNCompleted();
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data[i][2] = util;
		}
		return data;
	}
	
	public Equipment getSelectedEquipment(GymTrack gym){
		int selected = gym.equipmentTable_equipment.getSelectedRow();
		
		if(selected < 0)
			return null;
		
		return equipments.get(selected);
	}
	
	public void refreshDisplay(GymTrack gym){
		tableData = getTableData();
		gym.equipmentTable_equipment = new JTable(tableData, columnNames);
		gym.equipmentTable_equipment.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.equipmentTable_equipment);
	}
	
	@Override
	public GTUI showUI(GymTrack gym) {
		createEquipmentUI(gym);
		return this;
	}
}
