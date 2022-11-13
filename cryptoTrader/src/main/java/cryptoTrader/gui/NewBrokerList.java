package cryptoTrader.gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class NewBrokerList {
	
	/**
	 * Singleton; Saves each broker and their information in a list
	 * @param dtm: Table used for the main UI
	 * @param cryptoList: List of cryptocurrencies
	 * @param brokerList: List of brokers
	 */
	
	public static List<TradingBroker> setBrokers(DefaultTableModel dtm, String[] cryptoList, List<TradingBroker> brokerList) {
		
		/**
		 *  For loop to loop through the table dtm
		 */
		
		for (int rowIndex = 0; rowIndex < dtm.getRowCount(); rowIndex++) {
			
			/**
			 *  Trader gets the broker's name
			 */
			
			Object traderObject = dtm.getValueAt(rowIndex, 0);
			if (traderObject == null) {
				return brokerList;
			}
			
			String traderName = traderObject.toString();
			
			
			/**
			 *  Trader gets the list of coins for the broker
			 */
			
			Object coinObject = dtm.getValueAt(rowIndex, 1);
			if (coinObject == null) {
				return brokerList;
			}
			String[] coinNames = coinObject.toString().replace(" ", "").replace("[", "").replace("]", "").split(",");
			for(int j = 0; j < coinNames.length; j++) {
				boolean test = false;
				for (int i = 0; i < cryptoList.length; i++) {
					if(coinNames[j].equals((cryptoList[i].toLowerCase()))) {
						
						test = true;
					}
				}
				
				if(test == false) {
					return brokerList;
				}
			}
			
			/**
			 *  Trader gets the strategy for the broker
			 */
			
			Object strategyObject = dtm.getValueAt(rowIndex, 2);
			if (strategyObject == null) {
				return brokerList;
			}
			
			String strategyName = strategyObject.toString();
			
			
			/**
			 *  Makes sure that the broker is able to be added to the brokerList
			 */
			
		    Object nameColumn = dtm.getValueAt(rowIndex, 0);
		    if (nameColumn == null) {
		        continue;
		    }    
		    boolean contains = false;
		    for (TradingBroker broker : brokerList) {
		        if (broker.getName().equalsIgnoreCase(traderName)) {
		            if(broker.getStrategy().equalsIgnoreCase(strategyName)) {
		            	if(broker.getCoinList().equals(coinNames)) {
		            		contains = true;
		            	}
		            }
		        }
		    }    
		    if (!contains) {
		        /**
		         *  Gets the other info to create a new `TradingBroker` object
		         */
		        TradingBroker newBroker = new TradingBroker(traderName, strategyName, coinNames);
		        brokerList.add(newBroker);
		    }
		    
		}
		return brokerList;
	}
}
