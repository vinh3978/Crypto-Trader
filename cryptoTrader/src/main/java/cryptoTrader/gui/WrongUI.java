package cryptoTrader.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;	
/**
 * 
 * @author Timothy Nguyen
 * WrongUI class used to display notification panel in case of invalid username/password
 * 
 */
public class WrongUI extends JFrame implements ActionListener {
	private JLabel wrongLabel;
	private JPanel wrongPanel;
	
	/**
	 * WrongUI constructor
	 * @wrongLabel and @wrongPanel are the window with error message 
	 */
	public WrongUI() {
		wrongLabel = new JLabel();
		wrongLabel.setText("Wrong Username or Password");
		wrongPanel = new JPanel(new GridLayout(1,1));
		wrongPanel.add(wrongLabel);
		
		add(wrongPanel, BorderLayout.CENTER);
		setTitle("Error");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
