package cryptoTrader.gui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WriteFile {
	
	/**
	 *  Writes broker information into an empty file
	 */
	
	public static void WriteBrokerFile(List<TradingBroker> brokerList) {
		
		/**
		 *  Try catch write information into the file
		 */
		
		try {
			FileWriter brokerWriter = new FileWriter("brokerFile.txt");
			
			/**
			 *  For loop to put in each broker's name, strategy and coinList onto one line
			 */
			
			for(TradingBroker broker : brokerList) {
				
				/**
				 *  For Console testing
				 */
				
				//System.out.println("Name: " + broker.getName());
				
				/**
				 *  Separates the name, strategy and coinList by |
				 */
				
				String brokerInfo = broker.getName() + "|" + broker.getStrategy() + "|"
						+ Arrays.toString(broker.getCoinList()) + "\n";
				
				brokerWriter.write(brokerInfo);
			}
			
			brokerWriter.close();
			
		} catch (IOException e1) {
			System.out.println("File could not be written");
			e1.printStackTrace();
		}
	}
	
}
