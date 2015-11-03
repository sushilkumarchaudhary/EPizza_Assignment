package com.epizza;

public class EpizzaUtill {

	/**
	 * Format bill output 
	 * @param presc
	 * @param amount
	 * @return
	 */
	public static String formatBillOutput(String presc, Double amount){
		if(amount<0)
			return padRight(presc)+ padLeft("-" + Constant.CURRENCY_CODE + roundOff(-amount)) +"\n";
		return padRight(presc)+ padLeft(Constant.CURRENCY_CODE + roundOff(amount)) + "\n";
	}
	
	/**
	 * Right pad the text by length of 60 Char
	 * @param s
	 * @return
	 */
	public static String padRight(String s){
		return String.format("%1$-60" + "s", s);  
	}
	
	/**
	 * Left pad the String by 10 char
	 * @param s
	 * @return
	 */
	public static String padLeft(String s) {
	    return String.format("%1$10" + "s", s);  
	}
	/**
	 * Round off amount to double decimal digit
	 * @param amount
	 * @return
	 */

	public static String roundOff(Double amount){
		return String.format("%.2f",amount);
	}
	
	public static boolean isInputEmail(String line){
		return line.contains(Constant.EMAIL_IDENTIFIER);
	}
}
