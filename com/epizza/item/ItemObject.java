package com.epizza.item;

import com.epizza.EpizzaException;

/**
 * Interface to represent all the ITEM which can be order from ePiZZA shop
 * @author SUSHIL
 *
 */
public interface ItemObject {
	
	/**
	 * method to parse Item details from input
	 * @return 
	 * @throws EpizzaException 
	 */
	void parseItemString(String inputStr) throws EpizzaException;
	
	/**
	 * Method to get the item details as String 
	 * @throws EpizzaException 
	 */
	public String getItemString() throws EpizzaException;
	
	/**
	 * Method to calculate & get bill amount of the Item
	 * @return
	 */
	public Double getItemBillAmt();

}
