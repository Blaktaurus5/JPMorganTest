package com.jpmorgan.stockstest.model;

/**
*
* @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
* 
*         Interface that models a generic stock
* 
*/
public interface Stock {

		public String getSymbol();

		public void setSymbol(String _symbol);

		public double getLastDividend();

		public void setLastDividend(double _lastDividend);

		public StockType getType();
		
		public void setType(StockType _type);

		public double getParValue();

		public void setParValue(double _parValue);

		public double getPrice();

		public void setPrice(double _price);

}
