package edu.gymtrack.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginErrorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public LoginErrorDialog(GymTrack gym) {
 
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setTitle("Log-in Error");
		this.setPreferredSize(new Dimension(400,200));
		this.setBounds(100, 100, 400, 200);
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		contentPane.setPreferredSize(new Dimension(400,120));
		this.getContentPane().add(contentPane);
		
		JLabel dialogText1 = new JLabel("Invalid username and/or password. Please try again.");
		contentPane.add(dialogText1);
		
		JLabel separator = new JLabel();
		separator.setPreferredSize(new Dimension(400, 60));
		contentPane.add(separator);
		
		JLabel dialogText2 = new JLabel("If you forgot your log-in information please contact your trainer.");
		contentPane.add(dialogText2);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		this.getContentPane().add(buttonPane);
		
		gym.btnOk_loginerr = new JButton(new ImageIcon("images/dialog_ok.png", "Ok"));
		gym.btnOk_loginerr.setPreferredSize(new Dimension(70,25));
		gym.btnOk_loginerr.setRolloverIcon(new ImageIcon("images/dialog_ok_over.png", "Ok"));
		gym.btnOk_loginerr.addActionListener(gym);
		buttonPane.add(gym.btnOk_loginerr);
	}
	
}
