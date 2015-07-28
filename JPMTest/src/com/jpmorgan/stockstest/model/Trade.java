package com.jpmorgan.stockstest.model;
import java.util.Date;

/**
*
* @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
* 
*         Class that models a stock's trade
* 
*/
public class Trade {
    
	// The trade's stock
	private Stock stock;
	// The date when the trade is performed
    private Date date;
    // The quantity of the trade
    private int quantity;
    // The type of the trade
    private TradeType type;
    // The price of the trade
    private double price;

    /**
     * Basic constructor that takes the current date of the system
     * as the date the trade was performed at.
     * @param _stock the trade's stock.
     * @param _quantity the quantity of the trade.
     * @param _type the type of the trade.
     * @param _price the price of the trade.
     */
    public Trade(Stock _stock, int _quantity, TradeType _type, double _price){
    	this.stock = _stock;
        this.date = new Date(System.currentTimeMillis());
        this.quantity = _quantity;
        this.type = _type;
        this.price = _price;
    }
    
    /**
     * Advanced constructor for creating a trade and defining its
     * date of execution.
     * @param _stock the trade's stock.
     * @param _date the trade's date of execution.
     * @param _quantity the quantity of the trade.
     * @param _type the type of the trade.
     * @param _price the price of the trade.
     */
    public Trade(Stock _stock, Date _date, int _quantity, TradeType _type, double _price){
    	this.stock = _stock;
        this.date = _date;
        this.quantity = _quantity;
        this.type = _type;
        this.price = _price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TradeType getType() {
        return type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }
    
    public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
