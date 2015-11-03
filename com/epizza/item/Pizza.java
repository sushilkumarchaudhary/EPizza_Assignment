package com.epizza.item;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.epizza.Constant;
import com.epizza.EpizzaException;

public class Pizza implements ItemObject{
	
	public Pizza(String inputStr) throws EpizzaException{
		this.pizzaStr = inputStr;
		parseItemString(inputStr);
	}
	private String pizzaStr;
	
	
	private PizzaSize size;
	private PizzaBase base;	
	private PizzaSauce sauce;
	private CheeseType cheese;
	private boolean isDoubleCheese;
	private List<PizzaToppings> pizzaToppings;
	
	public PizzaSize getSize() {
		return size;
	}

	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public PizzaBase getBase() {
		return base;
	}

	public void setBase(PizzaBase base) {
		this.base = base;
	}

	public PizzaSauce getSauce() {
		return sauce;
	}

	public void setSauce(PizzaSauce sauce) {
		this.sauce = sauce;
	}

	public CheeseType getCheese() {
		return cheese;
	}

	public void setCheese(CheeseType cheese) {
		this.cheese = cheese;
	}

	public boolean isDoubleCheese() {
		return isDoubleCheese;
	}

	public void setDoubleCheese(boolean isDoubleCheese) {
		this.isDoubleCheese = isDoubleCheese;
	}

	public List<PizzaToppings> getPizzaToppings() {
		return pizzaToppings;
	}

	public void setPizzaToppings(List<PizzaToppings> pizzaToppings) {
		this.pizzaToppings = pizzaToppings;
	}

	/**
	 * Calculate and return cost of the Pizza
	 */
	public Double getItemBillAmt() {	
		Double pizzaCost = 0.0;
		pizzaCost += this.getBase().getBaseCost();
		pizzaCost += this.getBase().getBaseCost() * this.getSize().getSizeCostFactor();
		pizzaCost += this.getSize().getToppingCost() * (this.getPizzaToppings().size()-2);
		if(isDoubleCheese()) 
			pizzaCost += this.getSize().getDoubleCheeseCost();		
		return pizzaCost;
	}
	
	/**
	 * Parse the input string and load the pizza attributes from input sring
	 */
	public void parseItemString(String inputStr) throws EpizzaException{
		if(inputStr == null) 
			throw new EpizzaException("Invalid Pizza order Exception");
		String[] in = inputStr.split(Pattern.quote(Constant.PIPE_DELIMITER));
		if(in.length < 5)
			throw new EpizzaException("Invalid Pizza order Exception");
		this.setSize(PizzaSize.valueOf(in[Constant.PIZZA_SIZE_INDX]));
		this.setBase(PizzaBase.valueOf(in[Constant.PIZZA_BASE_INDX]));
		this.setSauce(PizzaSauce.valueOf(in[Constant.PIZZA_SAUCE_INDX]));
		this.setPizzaToppings(PizzaToppings.getToppingList(in[Constant.PIZZA_TOPPING_INDX]));
		String cheese = null, doubleCheesStr = null;
		if(in[Constant.PIZZA_CHEESE_INDX].indexOf(Constant.CAMMA_DELIMITER)>0){
			cheese = in[Constant.PIZZA_CHEESE_INDX].split(Constant.CAMMA_DELIMITER)[0];
			doubleCheesStr = in[4].split(Constant.CAMMA_DELIMITER)[1];
			if("DOUBLE".equals(doubleCheesStr)) setDoubleCheese(true);
		}
		else{ 
			cheese =in[Constant.PIZZA_CHEESE_INDX];
			setDoubleCheese(false);
		}
		this.setCheese(CheeseType.valueOf(cheese));
	}
	
	
	public String getItemString()throws EpizzaException{
		return pizzaStr;
	}
	

	public enum PizzaBase {
		NORMAL( 5.0), 
		PAN(6.0), 
		THIN(6.0),
		CHEESY(7.0) ;
		
		private Double baseCost;
		
		PizzaBase( Double cost){
			this.baseCost =cost;
		}
		
		public Double getBaseCost() {
			return baseCost;
		}
		public void setBaseCost(Double baseCost) {
			this.baseCost = baseCost;
		}
		
	}


	
	public enum PizzaSize {
		
		SMALL(0.00, 1.0, 0.5), 
		MEDIUM(0.25, 1.5, 1.0), 
		LARGE(0.50, 2.0, 1.5);

		PizzaSize(Double baseCostFactor, Double toppingCost, Double doubleCheeseCost) {
			this.sizeCostFactor = baseCostFactor;
			this.toppingCost = toppingCost;
			this.doubleCheeseCost = doubleCheeseCost;
		}
		
		private Double sizeCostFactor;
		private Double toppingCost;
		private Double doubleCheeseCost;
		
		
		public Double getDoubleCheeseCost() {
			return doubleCheeseCost;
		}
		public void setDoubleCheeseCost(Double doubleCheeseCost) {
			this.doubleCheeseCost = doubleCheeseCost;
		}
		public Double getToppingCost() {
			return toppingCost;
		}
		public void setToppingCost(Double toppingCost) {
			this.toppingCost = toppingCost;
		}
		
		public Double getSizeCostFactor() {
			return sizeCostFactor;
		}
		public void setSizeCostFactor(Double costFactor) {
			this.sizeCostFactor = costFactor;
		}		
	}
	
	public enum PizzaSauce {
		MARGARITA, MEXICANSALSA
	}
	
	public enum CheeseType {
		MOZZARELLA, CREAM;
		
	}

	public enum PizzaToppings {
		CAPSICUM, ONION, TOMATO, PINEAPPLE, OLIVE, JALAPENO;

		public static List<PizzaToppings> getToppingList(String toppings) {
			List<PizzaToppings> toppingList = new ArrayList<Pizza.PizzaToppings>();
			for(String topping : toppings.split(Constant.CAMMA_DELIMITER))
				toppingList.add(PizzaToppings.valueOf(topping));
			return toppingList;
		}
	}

}
