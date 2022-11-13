package cryptoTrader.gui;

import java.util.List;

public class BrokerFileWriter {
	
	/**
	 *  Facade; Creates the file to hold the broker information
	 *  @param brokerList: List of trading brokers
	 */
	
	public void createFile(List<TradingBroker> brokerList) {
		
		/**
		 *  Creates an empty file
		 */
		
		CreateFile.CreateBrokerFile();
		
		/**
		 *  Writes all the brokers' information into the empty file
		 *  @param brokerList: List of trading brokers
		 */
		
		WriteFile.WriteBrokerFile(brokerList);
		
	}
}
