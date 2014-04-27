package edu.gymtrack.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;
import edu.gymtrack.model.PlanElement;

public class AddEditEquipmentDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private GymTrack gym;
	private ArrayList<Integer> equipmentKeys;
	ArrayList<EquipmentType> types = null;
	Equipment editing;
	
	public AddEditEquipmentDialog(GymTrack gym, Equipment equipment) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.gym = gym;
		editing = equipment;
		setModal(true);
		setTitle("Add/Edit Equipment");
		setBounds(100, 100, 450, 200);
		setContentPane(contentPanel);
		
		JPanel topContainer = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		topContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(topContainer, BorderLayout.CENTER);
		
		JPanel tableTitleContainer = new JPanel();
		JPanel dropDownContainer = new JPanel();
		
		GroupLayout gl_topContainer = new GroupLayout(topContainer);
		gl_topContainer.setHorizontalGroup(
			gl_topContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(tableTitleContainer, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(dropDownContainer, GroupLayout.PREFERRED_SIZE, 424, Short.MAX_VALUE)
		);
		gl_topContainer.setVerticalGroup(
			gl_topContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topContainer.createSequentialGroup()
					.addComponent(tableTitleContainer, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dropDownContainer, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
		);
		
		gym.typesComboBox_EditEquip = new JComboBox();
		gym.typesComboBox_EditEquip.setModel(new DefaultComboBoxModel(getTypesDropDownMenuOptions()));
		
		gym.nameTextField_EditEquip = new JTextField();
		gym.nameTextField_EditEquip.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name");
		GroupLayout gl_dropDownContainer = new GroupLayout(dropDownContainer);
		gl_dropDownContainer.setHorizontalGroup(
			gl_dropDownContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownContainer.createSequentialGroup()
					.addComponent(gym.typesComboBox_EditEquip, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(80)
					.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(gym.nameTextField_EditEquip, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_dropDownContainer.setVerticalGroup(
			gl_dropDownContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_dropDownContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(gym.typesComboBox_EditEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gym.nameTextField_EditEquip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		dropDownContainer.setLayout(gl_dropDownContainer);

		topContainer.setLayout(gl_topContainer);
		{
			JPanel buttonContainer = new JPanel();
			buttonContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonContainer, BorderLayout.SOUTH);
			{
				gym.okButton_EditEquip = new JButton("OK");
				gym.okButton_EditEquip.addActionListener(gym);
				gym.okButton_EditEquip.setActionCommand("OK");//find out what this does
				buttonContainer.add(gym.okButton_EditEquip);
				getRootPane().setDefaultButton(gym.okButton_EditEquip);
			}
			{
				gym.cancelButton_EditEquip = new JButton("Cancel");
				gym.cancelButton_EditEquip.addActionListener(gym);
				gym.cancelButton_EditEquip.setActionCommand("Cancel");//find out what this does
				buttonContainer.add(gym.cancelButton_EditEquip);
			}
		}
		
		if(editing != null){
			gym.nameTextField_EditEquip.setText(equipment.getName());
			for(int i = 0; i < types.size(); ++i){
				if(types.get(i).getName() == equipment.getName()){
					gym.typesComboBox_EditEquip.setSelectedIndex(i);
					break;
				}
			}
		}
	}
	
	private String[] getTypesDropDownMenuOptions(){
		ArrayList<String> strTypes = new ArrayList<String>();
		equipmentKeys = new ArrayList<Integer>();
		
		try {
			types = (new Factory()).getEquipmentTypes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(types == null)
			return new String[0];
		
		for(EquipmentType type : types){
			strTypes.add(type.getName());
			equipmentKeys.add(type.getKey());
		}
		String[] result = new String[strTypes.size()];
		strTypes.toArray(result);
		return result;
	}
	
	public Equipment getUpdatedEntry(){
		if(equipmentKeys.isEmpty() || gym.nameTextField_EditEquip.getText().trim() == "")
			return null;
		
		Equipment result = new Equipment(
			editing == null ? 0 : editing.getKey(),
			types.get(gym.typesComboBox_EditEquip.getSelectedIndex()),
			gym.nameTextField_EditEquip.getText().trim(),
			editing == null
		);
		
		if(editing != null)
			result.setEdited(true);
		
		return result;
	}
}
