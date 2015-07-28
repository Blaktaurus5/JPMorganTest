package com.jpmorgan.stockstest.app;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.jpmorgan.stockstest.model.Stock;
import com.jpmorgan.stockstest.model.Trade;
import com.jpmorgan.stockstest.model.TradeType;
import com.jpmorgan.stockstest.utils.PrinterUtils;
import com.jpmorgan.stockstest.utils.StocksUtils;

/**
 * 
 * @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
 * 
 *         Class that simulates trades for a given collection
 *         of stocks and shows how their values and properties
 *         change with each trade performed.
 *
 */
public class SimulatorTask extends TimerTask {
	
	// Random time between trades = MINSECONDSTONEXTTRADE + MAXRANDOMSECSTOADD
	
	// The minimum number of seconds that have to pass
	// until a new trade is generated
	private static final int MINSECONDSTONEXTTRADE = 5;
	// The maximum number of seconds to generate randomly
	// that will be added to MINSECONDSTONEXTTRADE
	// in order to generate the real time until the next trade
	private static final int MAXRANDOMSECSTOADD = 5;
	
	private static Timer timer;
	private static ArrayList<Stock> stocks;
	private static Map<Stock, ArrayList<Trade>> trades;

	public SimulatorTask(ArrayList<Stock> _stocks,
			Map<Stock, ArrayList<Trade>> _trades) {
		timer = new Timer();
		stocks = _stocks;
		trades = _trades;
	}

	public SimulatorTask() {
		super();
	}

	@Override
	public void run() {

		if (stocks.size() > 0) {

			Random random;
			int delay;
			double price;
			ArrayList<Trade> stockTrades;
			Stock stock;
			Trade trade;

			// We run the task again simulating a random interval between trades
			random = new Random();
			delay = (MINSECONDSTONEXTTRADE + random.nextInt(MAXRANDOMSECSTOADD)) * 1000;
			timer.schedule(new SimulatorTask(), delay);

			// We select the stock that will be sold or bought randomly
			stock = stocks.get(random.nextInt(stocks.size()));
			stockTrades = (ArrayList<Trade>) trades.get(stock);
			price = stock.getPrice();
			// We generate a random trade for the given stock with a quantity
			// between 1 and MAXQTY, a random type of trade and a random price
			// in the range of the current price +- 15%.
			trade = new Trade(stock, random.nextInt(20) + 1,
					(random.nextBoolean() == true) ? TradeType.BUY
							: TradeType.SELL,
					((random.nextBoolean() == true) ? (price + random
							.nextInt((int) Math.round(price * 0.15)))
							: (price - random.nextInt((int) Math
									.round(price * 0.15)))));
			stockTrades.add(trade);
			stock.setPrice(StocksUtils.getStockPrice(stockTrades));
			trades.put(stock, stockTrades);

			// We print the information of the trade,
			// the updated information of the stocks
			// and the updated GBCE All Share Index
			
			// We print many new lines in order to simulate output clear
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			PrinterUtils.printTradeInfo(trade);
			PrinterUtils.printStocksInfo(stocks);
			PrinterUtils.printAllShareIndex(stocks);
			
		}
	}

}