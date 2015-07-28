package com.jpmorgan.stockstest.utils;

import java.util.ArrayList;

import com.jpmorgan.stockstest.model.PreferredStock;
import com.jpmorgan.stockstest.model.Stock;
import com.jpmorgan.stockstest.model.StockType;
import com.jpmorgan.stockstest.model.Trade;

/**
*
* @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
* 
*         Util class for calculating stocks and trades related values
* 
*/
public class StocksUtils {
	
	// The maximum number of milliseconds that may have passed since 
	// a trade was performed in order to take it into account when
	// calculating the stock prices. 15 minutes by default.
	private static final int MAXELAPSEDTIME = 900000;
	
	/**
	 * static function that returns the dividend yield of a given stock
	 * @param stock the stock we want to calculate its dividend yield
	 * @param tickerPrice the ticker price of the stock
	 * @return the dividend yield of the given stock
	 */
	public static double getDividendYield(Stock stock, double tickerPrice){
		
		if(stock.getType().equals(StockType.COMMON))
			return stock.getLastDividend() / tickerPrice;
		else{
			PreferredStock pStock = (PreferredStock)stock;
			return ((pStock.getFixedDividend() / 100) * pStock.getParValue()) / tickerPrice;
		}
	}

	/**
	 * Static function that returns the P/E ratio of a given share.
	 * @param tickerPrice the ticker price of the given share.
	 * @param dividend the dividend of the share whose P/E ratio we want to calculate.
	 * @return the P/E ratio of the given share.
	 */
	public static double getPERatio(double tickerPrice, double dividend) {

		return tickerPrice / dividend;
	}

	/**
	 * Static function that returns the geometric mean price of the given stocks.
	 * @param stocks the stocks whose geometric mean price we want to calculate.
	 * @return the geometric mean price of the given stocks.
	 */
	public static double getGeometricMean(ArrayList<Stock> stocks) {

		double geometricMean = 1.0;

		for (Stock stock : stocks) {
			geometricMean = geometricMean * stock.getPrice();
		}
		
		return MathUtils.nthRoot(geometricMean, stocks.size());

	}

	/**
	 * Static function that returns the current stock price based on
	 * the trades performed in the last given period of time
	 * (declared in MAXELAPSEDTIME; 15 minutes by default).
	 * @param trades the trades of the stock whose price we want to calculate.
	 * @return the price of the trades' stock.
	 */
	public static double getStockPrice(ArrayList<Trade> trades) {
		
		double stockPrice = 0.0; 
		int quantity = 0, pos = trades.size() - 1, elapsedTime;
		Trade trade = trades.get(pos);
		// The elapsed time between the current trade and the actual moment (in miliseconds)
		elapsedTime = (int)(System.currentTimeMillis() - trade.getDate().getTime());
		
		// We iterate the trades from the most recent one until we get to the last one
		// or until the elapsed time is greater than the max allowed elapsed time
		while(pos > 0 && elapsedTime <= MAXELAPSEDTIME){
			stockPrice += (trade.getPrice() * trade.getQuantity());
			quantity += trade.getQuantity();
			pos--;
			trade = trades.get(pos);
			elapsedTime = (int)(System.currentTimeMillis() - trade.getDate().getTime());
		}
		
		stockPrice += (trade.getPrice() * trade.getQuantity());
		quantity += trade.getQuantity();

		return stockPrice / quantity;
	}

	/**
	 * Static function that returns the value of MAXELAPSEDTIME.
	 * That is the maximum number of milliseconds that may have passed
	 * since a trade was performed in order to take it into account
	 * when calculating a stocks price based on its most recent trades.
	 * @return the value (in milliseconds) of MAXELAPSEDTIME.
	 */
	public static int getMaxelapsedtime() {
		return MAXELAPSEDTIME;
	}

}
