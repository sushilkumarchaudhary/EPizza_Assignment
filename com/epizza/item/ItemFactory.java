package com.epizza.item;

import com.epizza.EpizzaException;
/*
 * Factory class - Assuming more item like pizza can be sold in future by EPIzza company
 */
public class ItemFactory {

	public static ItemObject getItem(String inputStr) throws EpizzaException{
		//TODO condition wise objection instance creation if  more item like pizza get added.
		return new Pizza(inputStr);
	}
}
