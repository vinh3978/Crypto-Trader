package cryptoTrader.gui;
public class TradingBroker {
    private String name;
    private String strategy;
    private String[] coinList;
    
    /**
     * @param newName
     * @param newStrategy
     * @param newCoinList
     */
    public TradingBroker(String newName, String newStrategy, String[] newCoinList){
        this.name = newName;
        this.strategy = newStrategy;
        this.coinList = newCoinList;
    }
    
    /**
     * @param newName
     */
    public void setName(String newName) {
        this.name = newName;
    }
    
    public void setStrategy(String newStrategy) {
        this.strategy = newStrategy;
    }
    
    public void setList(String[] newCoinList) {
        this.coinList = newCoinList;
    }
    
    public String getName() {
        return name;
    }
    
    public String getStrategy() {
        return strategy;
    }
    
    public String[] getCoinList() {
        return coinList;
    }
}