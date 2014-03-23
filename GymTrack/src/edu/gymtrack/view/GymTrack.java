package edu.gymtrack.view;

import java.awt.event.*;
import javax.swing.*;

public class GymTrack extends JApplet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	protected JTextField txtUsername;
	protected JPasswordField txtPassword;
	protected int privilege; 
	protected JButton btnSubmit;
	protected JButton btnMyPlans;
	protected JButton btnAnalyze;
	protected JButton btnEditTrainees;
	protected JButton btnTrkTrainees;
	protected JButton btnEquipment;
	protected JButton btnUsers;
	protected JButton btnAnalyzeGym;
	
	public void init() {
		setSize(800,400);
		setName("GymTrack");
        LoginUI.createLoginUI(this);
        
	}
	public void start(){
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSubmit) {
			btnSubmit();
		}	
		else if (arg0.getSource() == btnMyPlans) {
			//TODO
		}
        else if (arg0.getSource() == btnAnalyze) {
			//TODO
		}
        else if (arg0.getSource() == btnEditTrainees) {
        	//TODO
		}
        else if (arg0.getSource() == btnTrkTrainees) {
        	//TODO
        }
        else if (arg0.getSource() == btnEquipment) {
        	//TODO
        }
        else if (arg0.getSource() == btnUsers) {
        	//TODO
        }
        else if (arg0.getSource() == btnAnalyzeGym) {
        	//TODO
        }
        else {
        	System.out.println("no action performed implemented for this button" + arg0.getSource().toString());
		}
	}
	
	private void btnSubmit(){
		//TODO validate login information
		
		switch (this.txtUsername.getText()) {
		case "user":
			privilege = 0;
			break;
		case "trainer":
			privilege = 1;
			break;
		case "owner":
			privilege = 2;
			break;
		default:
			break;
		}
		MainUI.createMainUI(this);
	}
}



