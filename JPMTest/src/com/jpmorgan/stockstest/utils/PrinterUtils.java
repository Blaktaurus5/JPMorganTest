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
*         Util class for printing information via standard output
* 
*/
public class PrinterUtils {
	
	/**
	 * Static method that prints via standard output the information of a given trade
	 * @param trade the trade whose information we want to print
	 */
	public static void printTradeInfo(Trade trade){

		System.out.println("New trade: [" + trade.getType() + "] "
				+ trade.getStock().getSymbol() + " - Qty. "
				+ trade.getQuantity() + " - Price " + MathUtils.round(trade.getPrice())
				+ " - " + trade.getDate());

	}
	
	/**
	 * Static method that prints via standard output the information of a given number of stocks
	 * @param stocks the stocks whose information we want to print
	 */
	public static void printStocksInfo(ArrayList<Stock> stocks){
		
		double aux;
		
		// We print the header
		System.out.println("\n| Stock\t|    Type  \t| Last Dividend\t| Fixed Dividend | Dividend Yield |  P/E ratio\t| Par Value |  Price   |\n");
		
		// We print the information of each stock in a new line
		for (Stock stck : stocks) {
			System.out.print("  " + stck.getSymbol() + "\t    "
					+ stck.getType());
			aux = MathUtils.round(StocksUtils.getDividendYield(stck, stck.getPrice()));
			System.out.print("\t\t"
					+ MathUtils.round(stck.getLastDividend()) + "\t"
					+ (stck.getType().equals(StockType.COMMON) ? "\t -" : "\t" 
					+ MathUtils.round(((PreferredStock) stck).getFixedDividend())) + "\t\t"	+ aux);
			aux = MathUtils.round(StocksUtils.getPERatio(stck.getPrice(), aux));
			System.out.print("\t     "
					+ (Double.isInfinite(aux) ? "\t-" : aux) + "\t    "
					+ MathUtils.round(stck.getParValue()) + "\t" + MathUtils.round(stck.getPrice()) + "\n");
		}
	}
	
	/**
	 * Static method that prints via standard output the GBCE All Share Index of a given number of stocks
	 * @param stocks the stocks whose GBCE All Share Index we want to print
	 */
	public static void printAllShareIndex(ArrayList<Stock> stocks){
		
		System.out.println("\nGBCE All Share Index: " + MathUtils.round(StocksUtils.getGeometricMean(stocks)) + "\n");
	}

}
