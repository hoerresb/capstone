package edu.gymtrack.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;
import edu.gymtrack.model.PlanElement;

public class LogWorkDialog extends JDialog implements ActionListener{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private GymTrack gym;
	private MyPlansUI plansUI;
	private ArrayList<PlanElement> elements;
	private ArrayList<Equipment> equipment;
	
	public LogWorkDialog(GymTrack gym, MyPlansUI plansUI) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.gym = gym;
		this.plansUI = plansUI;
		setModal(true);
		setTitle("Log Work");
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setContentPane(contentPanel);
		
		String[] columnNames = {"Date", "Exercise", "Amount", "% of plan complete"};
		Object[][] tableDataObjects = getWorklogTableData(plansUI);
		
		
		JPanel topContainer = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		topContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(topContainer, BorderLayout.CENTER);
		
		JPanel tableTitleContainer = new JPanel();
		JScrollPane scrollableTableContainer = new JScrollPane();
		JPanel dropDownTitleContainer = new JPanel();
		JPanel dropDownContainer = new JPanel();
		
		GroupLayout gl_topContainer = new GroupLayout(topContainer);
		gl_topContainer.setHorizontalGroup(
			gl_topContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(tableTitleContainer, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(scrollableTableContainer, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(dropDownContainer, GroupLayout.PREFERRED_SIZE, 424, Short.MAX_VALUE)
				.addComponent(dropDownTitleContainer, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_topContainer.setVerticalGroup(
			gl_topContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topContainer.createSequentialGroup()
					.addComponent(tableTitleContainer, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollableTableContainer, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dropDownTitleContainer, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dropDownContainer, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
		);
		
		gym.plansComboBox_LogWork = new JComboBox<String>();
		gym.plansComboBox_LogWork.addActionListener(this);
		gym.plansComboBox_LogWork.setModel(new DefaultComboBoxModel<String>(getPlansDropDownMenuOptions(plansUI)));
		
		gym.exerciseComboBox_LogWork = new JComboBox<String>();
		gym.exerciseComboBox_LogWork.addActionListener(this);
		gym.exerciseComboBox_LogWork.setModel(
			new DefaultComboBoxModel<String>(getActivitiesDropDownMenuOptions(
				plansUI, 
				plansUI.plans.get(gym.plansComboBox_LogWork.getSelectedIndex()))
			)
		);
		
		gym.equipmentComboBox_LogWork = new JComboBox<String>();
		gym.equipmentComboBox_LogWork.setModel(new DefaultComboBoxModel<String>(getEquipmentDropDownMenuOptions(elements.get(gym.exerciseComboBox_LogWork.getSelectedIndex()))));
		
		gym.amountTextField_LogWork = new JTextField();
		gym.amountTextField_LogWork.setColumns(10);
		GroupLayout gl_dropDownContainer = new GroupLayout(dropDownContainer);
		gl_dropDownContainer.setHorizontalGroup(
			gl_dropDownContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownContainer.createSequentialGroup()
//					.addGap(35)
					.addComponent(gym.plansComboBox_LogWork, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(gym.exerciseComboBox_LogWork, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(gym.equipmentComboBox_LogWork, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(gym.amountTextField_LogWork, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_dropDownContainer.setVerticalGroup(
			gl_dropDownContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_dropDownContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(gym.plansComboBox_LogWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gym.exerciseComboBox_LogWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gym.equipmentComboBox_LogWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gym.amountTextField_LogWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		dropDownContainer.setLayout(gl_dropDownContainer);
		
		JTable recentlyLoggedWorkTable = new JTable(tableDataObjects, columnNames);
		scrollableTableContainer.setViewportView(recentlyLoggedWorkTable);
        recentlyLoggedWorkTable.setFillsViewportHeight(true);
		
		
		JLabel lblLogNewWork = new JLabel("Log new work");
		GroupLayout gl_dropDownTitleContainer = new GroupLayout(dropDownTitleContainer);
		gl_dropDownTitleContainer.setHorizontalGroup(
			gl_dropDownTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownTitleContainer.createSequentialGroup()
					.addComponent(lblLogNewWork)
					.addContainerGap(358, Short.MAX_VALUE))
		);
		gl_dropDownTitleContainer.setVerticalGroup(
			gl_dropDownTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownTitleContainer.createSequentialGroup()
					.addComponent(lblLogNewWork)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		dropDownTitleContainer.setLayout(gl_dropDownTitleContainer);
		
		JLabel lblRecentlyLoggedWork = new JLabel("Recently logged work");
		GroupLayout gl_tableTitleContainer = new GroupLayout(tableTitleContainer);
		gl_tableTitleContainer.setHorizontalGroup(
			gl_tableTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tableTitleContainer.createSequentialGroup()
					.addComponent(lblRecentlyLoggedWork)
					.addContainerGap(378, Short.MAX_VALUE))
		);
		gl_tableTitleContainer.setVerticalGroup(
			gl_tableTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tableTitleContainer.createSequentialGroup()
					.addComponent(lblRecentlyLoggedWork)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		tableTitleContainer.setLayout(gl_tableTitleContainer);
		topContainer.setLayout(gl_topContainer);
		
		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonContainer, BorderLayout.SOUTH);

		gym.okButton_LogWork = new JButton(new ImageIcon("images/dialog_ok.png", "Ok"));
		gym.okButton_LogWork.setPreferredSize(new Dimension(70,25));
		gym.okButton_LogWork.setRolloverIcon(new ImageIcon("images/dialog_ok_over.png", "Ok"));
		gym.okButton_LogWork.addActionListener(gym);
		buttonContainer.add(gym.okButton_LogWork);
		getRootPane().setDefaultButton(gym.okButton_LogWork);

		gym.cancelButton_LogWork = new JButton(new ImageIcon("images/dialog_cancel.png", "Cancel"));
		gym.cancelButton_LogWork.setPreferredSize(new Dimension(70,25));
		gym.cancelButton_LogWork.setRolloverIcon(new ImageIcon("images/dialog_cancel_over.png", "Cancel"));
		gym.cancelButton_LogWork.addActionListener(gym);
		buttonContainer.add(gym.cancelButton_LogWork);

	}
	
	private Object[][] getWorklogTableData(MyPlansUI plans){
		Object[][] data = plans.getWorklogTableData();
		return data;
	}
	
	private String[] getPlansDropDownMenuOptions(MyPlansUI plansUI){
		ArrayList<String> strPlans = new ArrayList<String>();
		for(WorkoutPlan plan : plansUI.plans){
			strPlans.add(plan.getDateCreated().toString());
		}
		String[] result = new String[strPlans.size()];
		strPlans.toArray(result);
		return result;
	}
	
	private String[] getActivitiesDropDownMenuOptions(MyPlansUI plansUI, WorkoutPlan plan){
		ArrayList<String> strActivities = new ArrayList<String>();
		elements = new ArrayList<PlanElement>();
		
		for(PlanElement element : plansUI.elements.values()){
			if(element.getPlan().getKey() == plan.getKey()){
				strActivities.add(element.getActivityName());
				elements.add(element);
			}
		}
		String[] result = new String[strActivities.size()];
		strActivities.toArray(result);
		return result;
	}
	
	private String[] getEquipmentDropDownMenuOptions(PlanElement selectedElement){
		ArrayList<String> strEquipment = new ArrayList<String>();
		
		Factory f = new Factory();
		try {
			equipment = f.getEquipment(selectedElement.getEquipmentType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Equipment e : equipment){
			strEquipment.add(e.getName());
		}
		
		String[] result = new String[strEquipment.size()];
		strEquipment.toArray(result);
		return result;
	}
	
	public WorkoutLog getNewEntry(){
		if(elements.isEmpty() || equipment.isEmpty())
			return null;
		
		String strAmount = gym.amountTextField_LogWork.getText();
		int amount = 0;
		try{
			amount = Integer.parseInt(strAmount);
		} catch(NumberFormatException e){
			return null;
		}
		return new WorkoutLog(0, elements.get(gym.exerciseComboBox_LogWork.getSelectedIndex()).getKey(), new Date(), amount, equipment.get(gym.equipmentComboBox_LogWork.getSelectedIndex()), true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == gym.plansComboBox_LogWork){
			gym.exerciseComboBox_LogWork.setModel(
				new DefaultComboBoxModel<String>(getActivitiesDropDownMenuOptions(
					plansUI, 
					plansUI.plans.get(gym.plansComboBox_LogWork.getSelectedIndex())))
			);
			
			gym.equipmentComboBox_LogWork.setModel(
				(elements.size() > 0 ?
				new DefaultComboBoxModel<String>(getEquipmentDropDownMenuOptions(
					elements.get(gym.exerciseComboBox_LogWork.getSelectedIndex()))) :
				new DefaultComboBoxModel<String>(new String[0])
				)
			);
		}
		else if(arg0.getSource() == gym.exerciseComboBox_LogWork){
			gym.equipmentComboBox_LogWork.setModel(
				new DefaultComboBoxModel<String>(getEquipmentDropDownMenuOptions(
					elements.get(gym.exerciseComboBox_LogWork.getSelectedIndex())))
			);
		}
		
	}
}
