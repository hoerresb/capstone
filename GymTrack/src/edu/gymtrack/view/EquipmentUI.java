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
		
		tableData = getTableData();

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);
		
		gym.btnAdd_equipment = new JButton(new ImageIcon("images/add.png", "Add"));
		gym.btnAdd_equipment.setPreferredSize(new Dimension(90, 30));
		gym.btnAdd_equipment.setRolloverIcon(new ImageIcon("images/add_over.png", "Add"));
		gym.btnAdd_equipment.addActionListener(gym);
		topContainer.add(gym.btnAdd_equipment);

		gym.btnEdit_equipment = new JButton(new ImageIcon("images/edit.png", "Edit"));
		gym.btnEdit_equipment.setPreferredSize(new Dimension(90, 30));
		gym.btnEdit_equipment.setRolloverIcon(new ImageIcon("images/edit_over.png", "Edit"));
		gym.btnEdit_equipment.addActionListener(gym);
		topContainer.add(gym.btnEdit_equipment);

		gym.btnDelete_equipment = new JButton(new ImageIcon("images/delete.png", "Delete"));
		gym.btnDelete_equipment.setPreferredSize(new Dimension(90, 30));
		gym.btnDelete_equipment.setRolloverIcon(new ImageIcon("images/delete_over.png", "Delete"));
		gym.btnDelete_equipment.addActionListener(gym);
		topContainer.add(gym.btnDelete_equipment);
		
		JPanel bottomContainer = new JPanel();
		contentPane.add(bottomContainer, BorderLayout.CENTER);
		bottomContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		bottomContainer.add(bottomPanel, BorderLayout.SOUTH);
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(530, 0));
		bottomPanel.add(separator);
		
		gym.btnBack_equipment = new JButton(new ImageIcon("images/back.png", "Back"));
		gym.btnBack_equipment.setPreferredSize(new Dimension(90, 30));
		gym.btnBack_equipment.setRolloverIcon(new ImageIcon("images/back_over.png", "Back"));
		gym.btnBack_equipment.addActionListener(gym);
		bottomPanel.add(gym.btnBack_equipment);
		
		gym.btnLogout = new JButton(new ImageIcon("images/logout.png", "Logout"));
		gym.btnLogout.setPreferredSize(new Dimension(120, 30));
		gym.btnLogout.setRolloverIcon(new ImageIcon("images/logout_over.png", "Logout"));
		gym.btnLogout.addActionListener(gym);
		bottomPanel.add(gym.btnLogout);

		JScrollPane scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);

		gym.traineesTable_EditTrainees = new JTable(tableData, columnNames);
		gym.traineesTable_EditTrainees.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.traineesTable_EditTrainees);
		
		scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);
		
		gym.equipmentTable_equipment = new JTable(tableData, columnNames) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		gym.equipmentTable_equipment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		/*tableData = getTableData();
		gym.equipmentTable_equipment = new JTable(tableData, columnNames);
		gym.equipmentTable_equipment.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.equipmentTable_equipment);*/
		this.reloadPage(gym);
	}
	
	@Override
	public GTUI showUI(GymTrack gym) {
		createEquipmentUI(gym);
		return this;
	}
}
