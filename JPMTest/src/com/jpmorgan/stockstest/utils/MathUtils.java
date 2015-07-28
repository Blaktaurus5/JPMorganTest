package com.jpmorgan.stockstest.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
	
	/**
	 * Function that rounds a double to 2 decimal places,
	 * except in case it is Infinite.
	 * It rounds towards the "nearest neighbor" unless
	 * both neighbors are equidistant, in which case it rounds up.
	 * @param value the number to be rounded
	 * @return the value rounded to 2 decimal places
	 */
	public static double round(double value) {

		if(Double.isInfinite(value))
			return value;
		else{
			BigDecimal bd = new BigDecimal(value);
	    	bd = bd.setScale(2, RoundingMode.HALF_UP);
	    	return bd.doubleValue();
		}
	}
	
	public static double nthRoot(double number, int n){
		return Math.pow(number, (1.0 / n));
	}

}
