package cryptoTrader.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.TradingBroker;

/**
 * for creating graphs to show the information
 *
 */
public class DataVisualizationCreator {
	 MainUI instance = MainUI.getInstance();
	
	public void createCharts() {
//		createTextualOutput();
		createTableOutput();
//		createTimeSeries();
//		createScatter();
		createBar();
	}

	private void createTextualOutput() {
//		DefaultTableModel dtm = new  DefaultTableModel(new Object[] {"Broker Name", "Ticker List", "Strategy Name"}, 1);
//		JTable table = new JTable(dtm);
//		//table.setPreferredSize(new Dimension(600, 300));
//		dtm.e
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
//                "Broker Actions",
//                TitledBorder.CENTER,
//                TitledBorder.TOP));
//		
//	
//		
//		scrollPane.setPreferredSize(new Dimension(800, 300));
//		table.setFillsViewportHeight(true);;
		
//		MainUI.getInstance().updateStats(scrollPane);
	}
	
	private void createTableOutput() {
		
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		
		
		
		
		
Object[][] data = instance.getData();
for (int i = 0; i < data.length; i++) {
	if(data[i][0] != null) {
	System.out.println(data[i][0]);
	}
}
		
		
		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
	}

	private void createTimeSeries() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);
		
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		
		
		JFreeChart chart = new JFreeChart("Daily Price Line Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		MainUI.getInstance().updateStats(chartPanel);
	}
	
	private void createScatter() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);
		
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		
		
		JFreeChart scatterChart = new JFreeChart("Daily Price Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}
	

/**
 * This is to create the bar graph
 */
private void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		 Object[][] list = instance.getData();
		
		for (int i = 0; i < list.length; i++) {
			if(list[i][1] != null) {
			if(list[i][1].equals("Strategy-A")) {
			
			dataset.setValue(performTrade.strategyA,(String)list[i][0],(String)list[i][1]);
			
			}
			if(list[i][1].equals("Strategy-B")) {
				dataset.setValue(performTrade.strategyB,(String)list[i][0],(String)list[i][1]);
				}
			if(list[i][1].equals("Strategy-C")) {
				dataset.setValue(performTrade.strategyC,(String)list[i][0],(String)list[i][1]);
				}
			if(list[i][1].equals("Strategy-D")) {
				dataset.setValue(performTrade.strategyD,(String)list[i][0],(String)list[i][1]);
				}
			}
		}
			
			
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.1, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}


}
