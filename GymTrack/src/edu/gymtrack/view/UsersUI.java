package edu.gymtrack.view;

import java.awt.*;
import java.util.List;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import edu.gymtrack.model.User;
import edu.gymtrack.model.UserGroup;
import edu.gymtrack.model.UserGroups;

public class UsersUI extends GymTrack {
	private static final long serialVersionUID = 1L;

	public static void createUsersUI(GymTrack gym){
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(1000,400);
		setInitialValues(gym);
		gym.setName("Add/Edit users");
		JSplitPane contentPane = new JSplitPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gym.setContentPane(contentPane);

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		leftPanel.setLayout(gridBagLayout);
		contentPane.setLeftComponent(leftPanel);

		gym.groupToolbar = new JPanel();
		FlowLayout flowLayout = (FlowLayout) gym.groupToolbar.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		leftPanel.add(gym.groupToolbar, gbc);

		//Not implemented buttons, not sure if we will use these or not, right now they don't do anything
		gym.newGroupButton = new JButton("New...");
		gym.groupToolbar.add(gym.newGroupButton);
		gym.editGroupButton = new JButton("Edit");
		gym.groupToolbar.add(gym.editGroupButton);
		gym.deleteGroupButton = new JButton("Delete");
		gym.groupToolbar.add(gym.deleteGroupButton);

		gym.userGroupList = new JList<UserGroup>();
		gym.userGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		leftPanel.add(gym.userGroupList, gbc2);

		JSplitPane rightPanel = new JSplitPane();
		rightPanel.setBorder(null);
		rightPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.setRightComponent(rightPanel);

		JPanel topPanel = new JPanel();
		GridBagLayout gridBagLayout2 = new GridBagLayout();
		gridBagLayout2.columnWidths = new int[] { 0, 0 };
		gridBagLayout2.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout2.columnWeights = new double[] { 1.0, 1.0E-4 };
		gridBagLayout2.rowWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		topPanel.setLayout(gridBagLayout2);
		rightPanel.setLeftComponent(topPanel);

		JPanel personToolbar = new JPanel();
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.anchor = GridBagConstraints.NORTHWEST;
		gbc3.insets = new Insets(0, 0, 5, 0);
		gbc3.gridx = 0;
		gbc3.gridy = 0;
		topPanel.add(personToolbar, gbc3);

		gym.newUserButton = new JButton("New...");
		gym.newUserButton.addActionListener(gym);
		personToolbar.add(gym.newUserButton);
		gym.deleteUserButton = new JButton("Delete");
		gym.deleteUserButton.addActionListener(gym);
		personToolbar.add(gym.deleteUserButton);

		gym.personTable = new JTable();
		gym.personTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(gym.personTable);
		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.fill = GridBagConstraints.BOTH;
		gbc4.gridx = 0;
		gbc4.gridy = 1;
		topPanel.add(scrollPane, gbc4);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new MatteBorder(10, 10, 10, 10,
				(Color) null));
		GridBagLayout gridBagLayout3 = new GridBagLayout();
		gridBagLayout3.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout3.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout3.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		gridBagLayout3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0E-4 };
		bottomPanel.setLayout(gridBagLayout3);
		rightPanel.setRightComponent(bottomPanel);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc5 = new GridBagConstraints();
		gbc5.insets = new Insets(0, 0, 5, 5);
		gbc5.anchor = GridBagConstraints.WEST;
		gbc5.gridx = 0;
		gbc5.gridy = 0;
		bottomPanel.add(lblName, gbc5);

		gym.nameTextField = new JTextField();
		GridBagConstraints gbc6 = new GridBagConstraints();
		gbc6.insets = new Insets(0, 0, 5, 0);
		gbc6.fill = GridBagConstraints.HORIZONTAL;
		gbc6.gridx = 1;
		gbc6.gridy = 0;
		bottomPanel.add(gym.nameTextField, gbc6);
		gym.nameTextField.setColumns(10);

		JLabel lblEmail = new JLabel("E-Mail:");
		GridBagConstraints gbc7 = new GridBagConstraints();
		gbc7.anchor = GridBagConstraints.WEST;
		gbc7.insets = new Insets(0, 0, 5, 5);
		gbc7.gridx = 0;
		gbc7.gridy = 1;
		bottomPanel.add(lblEmail, gbc7);

		gym.emailTextField = new JTextField();
		GridBagConstraints gbc8 = new GridBagConstraints();
		gbc8.insets = new Insets(0, 0, 5, 0);
		gbc8.fill = GridBagConstraints.HORIZONTAL;
		gbc8.gridx = 1;
		gbc8.gridy = 1;
		bottomPanel.add(gym.emailTextField, gbc8);
		gym.emailTextField.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		GridBagConstraints gbc9 = new GridBagConstraints();
		gbc9.anchor = GridBagConstraints.WEST;
		gbc9.insets = new Insets(0, 0, 5, 5);
		gbc9.gridx = 0;
		gbc9.gridy = 2;
		bottomPanel.add(lblPhone, gbc9);

		gym.phoneTextField = new JTextField();
		GridBagConstraints gbc10 = new GridBagConstraints();
		gbc10.insets = new Insets(0, 0, 5, 0);
		gbc10.fill = GridBagConstraints.HORIZONTAL;
		gbc10.gridx = 1;
		gbc10.gridy = 2;
		bottomPanel.add(gym.phoneTextField, gbc10);
		gym.phoneTextField.setColumns(10);

		initDataBindings(gym);
	}

	private static void setInitialValues(GymTrack gym) {
		// TODO: get the actual data from database

		UserGroup group1 = new UserGroup("Trainers");
		group1.addUser(new User("trainer one", "trainerOne@email.com",
				"1234567890"));
		group1.addUser(new User("trainer two", "trainerTwo@email.com",
				"1234567890"));
		group1.addUser(new User("trainer three", "trainerThree@email.com",
				"1234567890"));
		gym.userGroups.addGroup(group1);
		//
		UserGroup group2 = new UserGroup("Members");
		group2.addUser(new User("member one", "memberOne@email.com",
				"1234567890"));
		group2.addUser(new User("member two", "memberTwo@email.com",
				"1234567890"));
		group2.addUser(new User("member three", "memberThree@email.com",
				"1234567890"));
		group2.addUser(new User("member four", "memberFour@email.com",
				"1234567890"));
		gym.userGroups.addGroup(group2);
	}

	private static void initDataBindings(GymTrack gym) {
		BeanProperty<UserGroups, List<UserGroup>> phoneGroupsBeanProperty = BeanProperty.create("groups");
		JListBinding<UserGroup, UserGroups, JList> jListBinding = SwingBindings.createJListBinding(AutoBinding.UpdateStrategy.READ, gym.userGroups, phoneGroupsBeanProperty, gym.userGroupList);
		//
		ELProperty<UserGroup, Object> phoneGroupEvalutionProperty = ELProperty.create("${name} (${userCount})");
		jListBinding.setDetailBinding(phoneGroupEvalutionProperty);
		//
		jListBinding.bind();
		//
		BeanProperty<JList, List<User>> jListPersonsBeanProperty = BeanProperty.create("selectedElement.users");
		JTableBinding<User, JList, JTable> jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ, gym.userGroupList, jListPersonsBeanProperty, gym.personTable);
		//
		BeanProperty<User, String> personBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(personBeanProperty).setColumnName("Name");
		//
		BeanProperty<User, String> personBeanProperty_1 = BeanProperty.create("email");
		jTableBinding.addColumnBinding(personBeanProperty_1).setColumnName("E-mail");
		//
		BeanProperty<User, String> personBeanProperty_2 = BeanProperty.create("phone");
		jTableBinding.addColumnBinding(personBeanProperty_2).setColumnName("Phone");
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty = BeanProperty.create("selectedElement.name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, gym.personTable, jTableBeanProperty, gym.nameTextField, jTextFieldBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_1 = BeanProperty.create("selectedElement.email");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, gym.personTable, jTableBeanProperty_1, gym.emailTextField, jTextFieldBeanProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_2 = BeanProperty.create("selectedElement.phone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_3 = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, gym.personTable, jTableBeanProperty_2, gym.phoneTextField, jTextFieldBeanProperty_2);
		autoBinding_3.bind();
		//
		ELProperty<JTable, Object> jTableEvalutionProperty = ELProperty.create("${ selectedElement != null }");
		BeanProperty<JButton, Boolean> jButtonBeanProperty_2 = BeanProperty.create("enabled");
		AutoBinding<JTable, Object, JButton, Boolean> autoBinding_7 = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, gym.personTable, jTableEvalutionProperty, gym.deleteUserButton, jButtonBeanProperty_2);
		autoBinding_7.bind();
	}
}

