package cryptoTrader.gui;

/**
 *  List of coin prices
 */

public class CryptocoinList {
    private double[] prices;
    
    public void setPrices(double[] prices) {
        this.prices = prices;
    }
    
    public double[] getPrices() {
        return prices;
    }
}
