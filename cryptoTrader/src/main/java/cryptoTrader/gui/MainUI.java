package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import cryptoTrader.utils.DataFetcher;
import cryptoTrader.utils.DataVisualizationCreator;
import cryptoTrader.utils.performTrade;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.gui.WrongUI;
import cryptoTrader.gui.LoginUI;


//Own Code
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * 
 * This implements the MainUI class which draws the panel, and handles the user input - trading information, "Add row" button, "Remove row" button, "Perform trade" .
 * The Main function is also call with in this class
 * 
 */

public class MainUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats, chartPanel, tablePanel;

	
	private List<String> selectedList;

	private JTextArea selectedTickerList;

	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;
	
    private List<TradingBroker> brokerList = new ArrayList();
   
    List<ArrayList<String>> trades = new ArrayList<ArrayList<String>>();
    private Object[][] data  = new Object[100][7];
    public static LoginUI frameL;
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	
	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar


		JPanel north = new JPanel();


		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);



		JPanel south = new JPanel();
		
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(700, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);

						
		/**
		 *  Reads the file to add the all of the brokers' information to the table
		 */
		dtm = BrokerFileReader.readFile(dtm);
		
    }

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	public static void main(String[] args) {
		
		/**
		 * calling LoginUI to input and validate user, and password 
		 */
		frameL = new LoginUI();
		frameL.setSize(450,300);
		frameL.setVisible(true);
	}

	/**
	 * Using the utility provided to populate a list of available Crypto coins  
	 */
	AvailableCryptoList instant = AvailableCryptoList.getInstance();
	String[] cryptoList = instant.getAvailableCryptos();
	/**
	 *
	 */
	@Override
	/**
	 *  Handle the button click actions
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("refresh".equals(command)) {
			/**
			 * "Perform Trade" button is pressed
			 */
			int counter = dtm.getRowCount();
			for (int count = 0; count < counter; count++){
				// iterate through the rows in the table to make sure all the fields are filled properly
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					
					/** 
					 *  For loop to make sure the trader names are different
					 */
					
					for (int i = 0; i < counter; i++) {
						Object tmpTraderObject = dtm.getValueAt(i, 0);
                        if(tmpTraderObject.toString().equals(traderObject.toString()) && i != count) {
                            JOptionPane.showMessageDialog(this, "please fill in another Trader name on line " + (count + 1) );
                            return;
                        }
                        
                    }
					
					
					// Make sure cypto coin name is provided
					
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					// If more than one crpto types are provided, treated as comma separated
					String[] coinNames = coinObject.toString().split(",");
					
					// retrieve strategy name
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					//System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
					
					for(int j = 0; j <coinNames.length; j++) {
						int test = -1;
						//Validate the input crypto types against the available crypto list
						for (int i = 0; i < cryptoList.length; i++) {
							if(coinNames[j].equals((cryptoList[i].toLowerCase()))) {
								
								test = 0;
							}
						}
						
						if(test == -1) {
							// in validation failed, ask for correction
							JOptionPane.showMessageDialog(this, "please fill in a  proper cryptocoin list on line " + (count + 1) );
							return;
						}
					}
	        }
			stats.removeAll();
					} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
		
			
		
		/**
	 	 *  Clear the brokerList
	 	 */
		brokerList.clear();
				
		/**
		 *  Updates the brokerList
		 */
		brokerList = NewBrokerList.setBrokers(dtm, cryptoList, brokerList);
				
		/**
		 *  Console: Makes sure the broker information is saved properly
		 */
				
				
				
		/**
		 *  Creates a file that will hold the broker information
		 */
				
		BrokerFileWriter fileWriter = new BrokerFileWriter();
		fileWriter.createFile(brokerList);
				
		// get today's date  and yesterday's date  to retrieve the trading information (price, volume, and CAP) 
        // also dates are used the trading strategies
        LocalDate today = LocalDate.now();
		LocalDate yesterDay = today.minusDays(1);
				     
		String yesterDayStr = yesterDay.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String todayStr = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
		//instantiate the object to retrieve trading data
		DataFetcher fetch = new DataFetcher();
		
		//instantiate the object to perform the trade
	    performTrade strategy = new performTrade();
	    if( "refresh".equals(command)) {  
	        for (int i = 0; i < brokerList.size(); i++) {
	        	//iterates through the broker list to see if it satisfies the strategy selected
				
	        	if(((String)brokerList.get(i).getStrategy()).equals("Strategy-A")) {
	        		// Strategy-A - when today's price is greater than yesterday's , sell 10 shares
	        		int numOfCoin = (brokerList.get(i).getCoinList()).length;
	        	    
	        		for(int j = 0; j < numOfCoin; j++) {
	        		   double todayPrice = fetch.getPriceForCoin(brokerList.get(i).getCoinList()[j], todayStr);
	        		   double yesterdayPrice = fetch.getPriceForCoin(brokerList.get(i).getCoinList()[j], yesterDayStr);
	        	       data = strategy.strategyA(data,brokerList.get(i).getName(),brokerList.get(i).getCoinList()[j] , todayPrice,yesterdayPrice,todayStr);
	        	    }
	        	}
	        	
	        	if(((String)brokerList.get(i).getStrategy()).equals("Strategy-B")) {
	        		// Strategy-B - when today's price is less than yesterday's , buy 10 shares
	        		int numOfCoin = (brokerList.get(i).getCoinList()).length;
	        	
	        	    for(int j = 0; j < numOfCoin; j++) {
	        		    double todayPrice = fetch.getPriceForCoin(brokerList.get(i).getCoinList()[j], todayStr);
	        		    double yesterdayPrice = fetch.getPriceForCoin(brokerList.get(i).getCoinList()[j], yesterDayStr);
	        		
	        		    data = strategy.strategyB(data,brokerList.get(i).getName(),brokerList.get(i).getCoinList()[j] ,todayPrice,yesterdayPrice,todayStr);
	        	
	        	     }
	        	
	        	}
	        	
	        	if(((String)brokerList.get(i).getStrategy()).equals("Strategy-C")) {
	        		// Strategy-c - when today's volume is greater than yesterday's , buy 15 shares
	        		int numOfCoin = (brokerList.get(i).getCoinList()).length;
	        	    for(int j = 0; j < numOfCoin; j++) {
	        	    	double todayPrice = fetch.getPriceForCoin(brokerList.get(i).getCoinList()[j], todayStr);
	        		    double todayVolume = fetch.getVolumeForCoin(brokerList.get(i).getCoinList()[j], todayStr);
	        		    double yesterdayVolume = fetch.getVolumeForCoin(brokerList.get(i).getCoinList()[j], yesterDayStr);
	        		
	        	        data = strategy.strategyC(data,brokerList.get(i).getName(),brokerList.get(i).getCoinList()[j] , todayPrice,todayVolume,yesterdayVolume,todayStr);
	        	    }
	        	
	        	}
	        	
	        	if(((String)brokerList.get(i).getStrategy()).equals("Strategy-D")) {
	        		// Strategy-c - when today's volume is less than yesterday's , sell 15 shares
	        		int numOfCoin = (brokerList.get(i).getCoinList()).length;
	        	    for(int j = 0; j < numOfCoin; j++) {
	        		    double todayPrice = fetch.getPriceForCoin(brokerList.get(i).getCoinList()[j], todayStr);
	        		    double todayVolume = fetch.getVolumeForCoin(brokerList.get(i).getCoinList()[j], todayStr);
	        		    double yesterdayVolume = fetch.getVolumeForCoin(brokerList.get(i).getCoinList()[j], yesterDayStr);	
	        	        data = strategy.strategyD(data,brokerList.get(i).getName(),brokerList.get(i).getCoinList()[j] ,todayPrice,todayVolume,yesterdayVolume, todayStr);
	        	    }
	        	
	        	}
	        	
	        }
        
	        // display the trading list and set up the bar according to the validated information
            DataVisualizationCreator creator = new DataVisualizationCreator();
  	        creator.createCharts();
        }
        
     	
	 
         // initialize the data structures
		 data  = new Object[100][7];
		 strategy.setStrategyA(0);
		 strategy.setStrategyB(0);
		 strategy.setStrategyC(0);
		 strategy.setStrategyD(0);
		 strategy.setRowNum(0);
		 
		

	}
	
	
	public ArrayList <TradingBroker> getBrokerList(){
		return (ArrayList<TradingBroker>) brokerList;
		   
	   }
	
	public  List<ArrayList<String>> getTrades(){
		return trades;
	}
	public void setTrades(List<ArrayList<String>> trade) {
		trades = trade;
	}
	public  Object[][] getData(){
		return data;
	}
	public void setData(Object[][] data) {
		data = this.data;
	}

}
