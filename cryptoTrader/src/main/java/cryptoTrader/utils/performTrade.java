package cryptoTrader.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cryptoTrader.gui.TradingBroker;

/**
 * 
 * 
 * executes the different trading strategies
 * 
 * 
 */

public class performTrade {
	
 static int strategyA=0;
 static int strategyB=0;
 static int strategyC=0;
 static int strategyD=0;
 static int rowNum=0;
	




public static int getStrategyA() {
	return strategyA;
}
/**
 * @param strategyA
 */
public static void setStrategyA(int strategyA) {
	performTrade.strategyA = strategyA;
}
public static int getStrategyB() {
	return strategyB;
}
/**
 * @param strategyB
 */
public static void setStrategyB(int strategyB) {
	performTrade.strategyB = strategyB;
}
public static int getStrategyC() {
	return strategyC;
}
/**
 * @param strategyC
 */
public static void setStrategyC(int strategyC) {
	performTrade.strategyC = strategyC;
}
public static int getStrategyD() {
	return strategyD;
}
/**
 * @param strategyD
 */
public static void setStrategyD(int strategyD) {
	performTrade.strategyD = strategyD;
}
public static int getRowNum() {
	return rowNum;
}
public static void setRowNum(int rowNum) {
	performTrade.rowNum = rowNum;
}
/**
 * @param data
 * @param name
 * @param coinName
 * @param priceForCoin
 * @param priceYesterday
 * @param date
 * @return data
 * Executes strategy A which sells if todays price is higher than yesterdays
 */
public Object[][] strategyA(Object[][] data, String name,  String coinName,double priceForCoin,double priceYesterday, String date) {
	if(priceForCoin >= priceYesterday) {
	data[rowNum][0] = name;
	data[rowNum][1] = "Strategy-A";
	data[rowNum][2] = coinName;
	data[rowNum][3] = "Sell";
	data[rowNum][4] = "10";
	data[rowNum][5] = String.format("%.2f",priceForCoin);
	data[rowNum][6] = date;
	rowNum++;
    strategyA++;
	}
	
    return data;

}

/**
 * @param data
 * @param name
 * @param coinName
 * @param priceForCoin
 * @param priceYesterday
 * @param date
 * @return data
 * Executes strategy B which buys if todays price is lower than yesterdays
 */
public Object[][] strategyB(Object[][] data, String name,  String coinName,double priceForCoin,double priceYesterday,String date) {
	
	if(priceForCoin < priceYesterday) {
	data[rowNum][0] = name;
	data[rowNum][1] = "Strategy-B";
	data[rowNum][2] = coinName;
	data[rowNum][3] = "Buy";
	data[rowNum][4] = "10";
	data[rowNum][5] = String.format("%.2f",priceForCoin);
	data[rowNum][6] = date;
    strategyB++;
    rowNum++;
	}
	
    return data;

}

/**
 * @param data
 * @param name
 * @param coinName
 * @param priceForCoin
 * @param todayVolume
 * @param yesterdayVolume
 * @param date
 * @return data
 * Executes strategy C which sells if todays volume is higher than yesterdays
 */
public Object[][] strategyC(Object[][] data, String name,  String coinName,double priceForCoin,double todayVolume, double yesterdayVolume, String date) {
	if(todayVolume >= yesterdayVolume) {
	data[rowNum][0] = name;
	data[rowNum][1] = "Strategy-C";
	data[rowNum][2] = coinName;
	data[rowNum][3] = "Buy";
	data[rowNum][4] = "15";
	data[rowNum][5] = String.format("%.2f", priceForCoin);
	data[rowNum][6] = date;
    strategyC++;
    rowNum++;
	
	}
return data;
	
}

/**
 * @param data
 * @param name
 * @param coinName
 * @param priceForCoin
 * @param todayVolume
 * @param yesterdayVolume
 * @param date
 * @return data
 * Executes strategy A which buys if todays volume is higher than yesterdays
 */
public Object[][] strategyD(Object[][] data, String name,  String coinName,double priceForCoin,double todayVolume, double yesterdayVolume, String date) {
	if(todayVolume < yesterdayVolume) {
	data[rowNum][0] = name;
	data[rowNum][1] = "Strategy-D";
	data[rowNum][2] = coinName;
	data[rowNum][3] = "Sell";
	data[rowNum][4] = "15";
	data[rowNum][5] = String.format("%.2f",priceForCoin);
	data[rowNum][6] = date;
    strategyD++;
    rowNum++;
	
	}
return data;
}
	
	
	
}
