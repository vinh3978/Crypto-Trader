package cryptoTrader.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class BrokerFileReader {
	
	/**
	 *  Reads the file and adds the information to the table
	 *  @param dtm: Table used for the main UI
	 */
	
	public static DefaultTableModel readFile(DefaultTableModel dtm){
		
		/**
		 *  Removes the initial row
		 */
		
		dtm.removeRow(0);
		
		/**
		 *  Creates a scanner to read the brokerFile
		 */
		
        Scanner brokerReader;
        try {
            brokerReader = new Scanner(new File("brokerFile.txt"));
            
            /**
             *  Try catch to read the file
             */
            
            try {
            	
            	/**
            	 *  While loop to check if the file has a next line
            	 */
            	
		        while(brokerReader.hasNextLine()) {
		            
		        	/**
		        	 *  Splits create a string for the broker and split it by |
		        	 */
		        	
		            String readString = brokerReader.nextLine();
		            
		            String[] brokerTextInfo = readString.split("\\|");
		            
		            /**
		             *  For console testing
		             */
		            
		            //System.out.println(brokerTextInfo[0] + " " + brokerTextInfo[1] + " " + brokerTextInfo[2]);
		            
		            /**
		             *  Creates the coin list for the table
		             */
		            
		            String[] coinSplit = brokerTextInfo[2].replace("[", "").replace("]", "").replace(" ", "").split(",");
		            
		            /**
		             *  For console testing
		             */
		            
		            /**
		             *  System.out.println(Arrays.toString(coinSplit));
		             */
		            
		            String coinsString = String.join(",", coinSplit);
		            
		            /**
		             *  For console testing
		             */
		            
		            //System.out.println(coinsString);
		            
		            /**
		             *  Adds a row with a broker and its information
		             */
		            
		            Object[] brokerFinal = new Object[] {brokerTextInfo[0], coinsString, brokerTextInfo[1]};
		            
		            dtm.addRow(brokerFinal);
		        }
            } catch (ArrayIndexOutOfBoundsException a1) {
            	System.out.println("No File Contents");
            }
        } catch (FileNotFoundException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        return dtm;
	}
	
}
