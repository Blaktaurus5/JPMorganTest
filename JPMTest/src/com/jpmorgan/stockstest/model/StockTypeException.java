package com.jpmorgan.stockstest.model;

/**
*
* @author Cristian Poncela Perez - cristian.poncela.perez@gmail.com
* 
*         Custom exception for the case of creating a stock whose
*         type does not correspond the one indicated in the StockType argument
* 
*/
public class StockTypeException extends Exception {

	/**
	 * Auto-generated serialVersionUID
	 */
	private static final long serialVersionUID = -1594390422261540137L;
	
	// We customize the message of the exception
	@Override
	public String getMessage() {
		
		StackTraceElement stelement = Thread.currentThread().getStackTrace()[2];
		
		return "StockTypeException: the StockType does not correspond to the Stock object's being created.\nUse StockType.COMMON for CommonStock and StockType.PREFERRED for PreferredStock." +
				"\n\tat " + stelement.getClassName() + "." + stelement.getMethodName() + "(" + stelement.getClassName() + ":" + stelement.getLineNumber() + ")";
	}
	

}
