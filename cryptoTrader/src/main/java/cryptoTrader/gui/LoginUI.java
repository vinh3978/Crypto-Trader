package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;	
/**
 * 
 * @author Tim
 * LoginUI class used to generate Login window.
 * If username and password are valid, Login window is closed and MainUI window is open
 * 
 */
public class LoginUI extends JFrame implements ActionListener {

			private JButton loginButton;
			private JPanel loginPanel;
			private JLabel userIDLabel, passwordLabel;
			private final JTextField userIDField, passwordField;
			private boolean AuthenticationApproval;
			private boolean done = false;
			
			/**
			 * LoginUI constructor with Username textfield, Password textfield, and a Login button
			 */
			public LoginUI() {
				
				userIDLabel = new JLabel();
				userIDLabel.setText("User ID: ");
				
				userIDField = new JTextField(10);
				
				passwordLabel = new JLabel();
				passwordLabel.setText("Password: ");
				
				passwordField= new JTextField(10);
				
				loginButton = new JButton("Login Now");
				
				loginPanel = new JPanel(new GridLayout(12,10));
				loginPanel.add(userIDLabel);
				loginPanel.add(userIDField);
				loginPanel.add(passwordLabel);
				loginPanel.add(passwordField);
				loginPanel.add(loginButton);
				
				add(loginPanel, BorderLayout.CENTER);
				
				loginButton.addActionListener(this);
				setTitle("Login Panel");
				
				
			}
			

				
			
			/**
			 * Action performed after Login button is pressed
			 * check validity of username and password
			 * if valid then close login window and open MainUI
			 * if not then close login window and open WrongUI to notify user the username/password is wrong
			 */
			public void actionPerformed(ActionEvent ae) {
				
				String userIDText = userIDField.getText();
				String passwordText = passwordField.getText();
				String command = ae.getActionCommand();
				
				
				if (userIDText.equals("admin") && passwordText.equals("12345")) {
					AuthenticationApproval = true;
				} else {
					AuthenticationApproval = false;
				}
				
				if (this.getApproval()==true) {
					JFrame frame = MainUI.getInstance();
					frame.setSize(900, 600);
					frame.pack();
					frame.setVisible(true);
					MainUI.frameL.setVisible(false);
				} else if (this.getApproval()==false) {
					JFrame frameW = new WrongUI();
					frameW.setSize(200,200);
					frameW.setVisible(true);
					MainUI.frameL.setVisible(false);
				}
				
				
			}
			
			/**
			 * This method is to return the value true if 
			 * the combination of username and password is valid and vice versa
			 * @return
			 */
			public boolean getApproval() {
				return AuthenticationApproval;
			}
		
		
}