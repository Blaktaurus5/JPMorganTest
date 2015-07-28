package com.jpmorgan.stockstest.model;

/**
 *
 * @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
 * 
 *         Class that models a common stock
 * 
 */
public class CommonStock implements Stock {

	// Symbol of the stock
	private String symbol;
	// Last dividend of the stock
	private double lastDividend;
	// Type of the stock
	private StockType type;
	// Par value of the stock
	private double parValue;
	// Price of the stock
	private double price;

	/**
	 * Default constructor for a common stock.
	 * @param _symbol symbol of the stock.
	 * @param _lastDividend last dividend of the stock.
	 * @param _type type of the stock, has to be StockType.COMMON.
	 * @param _price price of the stock.
	 * @param _parValue par value of the stock.
	 */
	public CommonStock(String _symbol, double _lastDividend, StockType _type, double _parValue, double _price) 
				throws StockTypeException{
		
		if(!_type.equals(StockType.COMMON)) throw new StockTypeException();
		this.symbol = _symbol;
		this.lastDividend = _lastDividend;
		this.type = _type;
		this.parValue = _parValue;
		this.price = _price;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String _symbol) {
		this.symbol = _symbol;
	}

	public double getLastDividend() {
		return this.lastDividend;
	}

	public void setLastDividend(double _lastDividend) {
		this.lastDividend = _lastDividend;
	}

	public StockType getType() {
		return this.type;
	}

	public void setType(StockType _type) {
		this.type = _type;
	}

	public double getParValue() {
		return this.parValue;
	}

	public void setParValue(double _parValue) {
		this.parValue = _parValue;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double _price) {
		this.price = _price;
	}

}
