package cryptoTrader.gui;

import java.io.File;
import java.io.IOException;

public class CreateFile {
	
	/**
	 *  Creates a broker file to hold the brokers and their information
	 */
	
	public static void CreateBrokerFile() {
		
		/**
		 *  Creates a file called brokerFile.txt
		 */
		
		File brokerFile = new File("brokerFile.txt");
		
		/**
		 *  Try catch to create a new file
		 */
		
		try {
			brokerFile.createNewFile();
			System.out.println("File Created");
		} catch (IOException e2) {
			System.out.println("File could not be created");
			e2.printStackTrace();
		}
	}
	
}
