/**
 * 
 */
package com.jpmorgan.stockstest.testcases;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stockstest.model.CommonStock;
import com.jpmorgan.stockstest.model.PreferredStock;
import com.jpmorgan.stockstest.model.Stock;
import com.jpmorgan.stockstest.model.StockType;
import com.jpmorgan.stockstest.model.Trade;
import com.jpmorgan.stockstest.model.TradeType;
import com.jpmorgan.stockstest.utils.MathUtils;
import com.jpmorgan.stockstest.utils.StocksUtils;

/**
 * 
 * @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
 * 
 *         Class for testing the class StocksUtils.
 *
 */
public class StocksUtilsTest {
	
	private Stock stock1, stock2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stock1 = new CommonStock("TEA", 0.0, StockType.COMMON, 100.0, 100.0);
		stock2 = new PreferredStock("GIN", 8.0, StockType.PREFERRED, 100.0, 100.0, 2.0);
	}

	/**
	 * Test method for {@link com.jpmorgan.stockstest.utils.StocksUtils#getDividendYield(com.jpmorgan.stockstest.model.Stock, double)}.
	 */
	@Test
	public void testGetDividendYield() {
		
		double dividend1, dividend2;
		
		dividend1 = StocksUtils.getDividendYield(stock1, stock1.getPrice());
		dividend2 = StocksUtils.getDividendYield(stock2, stock2.getPrice());
		Assert.assertEquals(dividend1, (stock1.getLastDividend() / stock1.getPrice()), 0.0);
		Assert.assertEquals(dividend2, ((((PreferredStock)stock2).getFixedDividend() / 100) * stock2.getParValue() / stock2.getPrice()), 0.0);
	}

	/**
	 * Test method for {@link com.jpmorgan.stockstest.utils.StocksUtils#getPERatio(double, double)}.
	 */
	@Test
	public void testGetPERatio() {
		
		double pERatio1, pERatio2;
		
		pERatio1 = StocksUtils.getPERatio(stock1.getPrice(), stock1.getLastDividend());
		pERatio2 = StocksUtils.getPERatio(stock2.getPrice(), stock2.getLastDividend());
		Assert.assertEquals(pERatio1, stock1.getPrice() / stock1.getLastDividend(), 0.0);
		Assert.assertEquals(pERatio2, stock2.getPrice() / stock2.getLastDividend(), 0.0);
		
	}

	/**
	 * Test method for {@link com.jpmorgan.stockstest.utils.StocksUtils#getGeometricMean(java.util.ArrayList)}.
	 */
	@Test
	public void testGetGeometricMean() {
		
		Double geometricMean = new Double(1.0), geometricMeanT;
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		stocks.add(stock1);
		stocks.add(stock2);
		
		geometricMeanT = StocksUtils.getGeometricMean(stocks);
		for (Stock stock : stocks) {
			geometricMean = geometricMean * stock.getPrice();
		}
		geometricMean = MathUtils.nthRoot(geometricMean, stocks.size()); 

		Assert.assertEquals(geometricMean, geometricMeanT, 0.0);
	}

	/**
	 * Test method for {@link com.jpmorgan.stockstest.utils.StocksUtils#getStockPrice(java.util.ArrayList)}.
	 */
	@Test
	public void testGetStockPrice() {

		ArrayList<Trade> trades1 = new ArrayList<Trade>();
		ArrayList<Trade> trades2 = new ArrayList<Trade>();
		double price1, price1T, price2, price2T;
		int quantity, pos, elapsedTime;
		Trade trade;
		
		trades1.add(new Trade(stock1, 10, TradeType.BUY, 50.0));
		trades1.add(new Trade(stock1, 5, TradeType.SELL, 55.0));
		trades1.add(new Trade(stock1, 15, TradeType.BUY, 60.0));
		
		trades2.add(new Trade(stock2, 5, TradeType.SELL, 45.0));
		trades2.add(new Trade(stock2, 25, TradeType.SELL, 50.0));
		trades2.add(new Trade(stock2, 10, TradeType.BUY, 55.0));
		
		price1T = StocksUtils.getStockPrice(trades1);
		price2T = StocksUtils.getStockPrice(trades2);
		
		quantity = 0;
		pos = trades1.size() - 1;
		trade = trades1.get(pos);
		elapsedTime = (int)(System.currentTimeMillis() - trade.getDate().getTime());
		price1 = 0.0;
		
		// We iterate the trades from the most recent one until we get to the last one
		// or until the elapsed time is greater than the max allowed elapsed time
		while(pos > 0 && elapsedTime <= StocksUtils.getMaxelapsedtime()){
			price1 += (trade.getPrice() * trade.getQuantity());
			quantity += trade.getQuantity();
			pos--;
			trade = trades1.get(pos);
			elapsedTime = (int)(System.currentTimeMillis() - trade.getDate().getTime());
		}
		
		price1 += (trade.getPrice() * trade.getQuantity());
		quantity += trade.getQuantity();
		price1 = (price1 / quantity);
		
		Assert.assertEquals(price1, price1T, 0.0);
		
		quantity = 0;
		pos = trades2.size() - 1;
		trade = trades2.get(pos);
		elapsedTime = (int)(System.currentTimeMillis() - trade.getDate().getTime());
		price2 = 0.0;
		
		// We iterate the trades from the most recent one until we get to the last one
		// or until the elapsed time is greater than the max allowed elapsed time
		while(pos > 0 && elapsedTime <= StocksUtils.getMaxelapsedtime()){
			price2 += (trade.getPrice() * trade.getQuantity());
			quantity += trade.getQuantity();
			pos--;
			trade = trades2.get(pos);
			elapsedTime = (int)(System.currentTimeMillis() - trade.getDate().getTime());
		}
		
		price2 += (trade.getPrice() * trade.getQuantity());
		quantity += trade.getQuantity();
		
		price2 = (price2 / quantity);
		
		Assert.assertEquals(price2, price2T, 0.0);	
		
	}

}
