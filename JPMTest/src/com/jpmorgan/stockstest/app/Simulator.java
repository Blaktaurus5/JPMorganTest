package com.jpmorgan.stockstest.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jpmorgan.stockstest.model.*;

/**
*
* @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
* 
*         The main class of the application.
*         It runs a simulator of stock trades and shows the updated
*         information after each one via the standard output.
* 
*/
public class Simulator {
	
	/**
	 * Static main method that creates the initial stocks' data
	 * and runs a trades simulator with them.
	 * @param args the arguments of the main method. They are not used.
	 */
	public static void main(String[] args) {
		
		ArrayList<Stock> stocks;
		Map<Stock,ArrayList<Trade>> trades;
		Stock stock;
		
		stocks = new ArrayList<Stock>();
		trades = new HashMap<Stock,ArrayList<Trade>>();

		try { 
			
			stock = new CommonStock("TEA", 0.0, StockType.COMMON, 100.0, 100.0);
			stocks.add(stock);
			trades.put(stock,new ArrayList<Trade>());
			stock = new CommonStock("POP", 8.0,StockType.COMMON, 100.0, 100.0);
			stocks.add(stock);
			trades.put(stock,new ArrayList<Trade>());
			stock = new CommonStock("ALE", 23.0, StockType.COMMON, 60.0, 60.0);
			stocks.add(stock);
			trades.put(stock,new ArrayList<Trade>());
			stock = new PreferredStock("GIN", 8.0, StockType.PREFERRED, 100.0, 100.0, 2.0);
			stocks.add(stock);
			trades.put(stock,new ArrayList<Trade>());
			stock = new CommonStock("JOE", 13.0, StockType.COMMON, 250.0, 250.0);
			stocks.add(stock);
			trades.put(stock,new ArrayList<Trade>());
			
			new SimulatorTask(stocks, trades).run();
		
		} catch (StockTypeException exc){
			System.err.println(exc.getMessage());
		}

	}

}
