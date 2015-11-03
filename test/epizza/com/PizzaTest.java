package test.epizza.com;

import org.junit.Assert;
import org.junit.Test;

import com.epizza.EpizzaException;
import com.epizza.item.Pizza;
import com.epizza.item.Pizza.CheeseType;
import com.epizza.item.Pizza.PizzaBase;
import com.epizza.item.Pizza.PizzaSauce;
import com.epizza.item.Pizza.PizzaSize;
import com.epizza.item.Pizza.PizzaToppings;


public class PizzaTest {
	
	@Test
	/**
	 * Test invalid pizza order in the input file
	 */
	public void testInvalidEpizzaOrder(){
		try {
			new Pizza("abc.xyz");
		} catch (EpizzaException e) {
			Assert.assertEquals("Invalid Pizza order Exception", e.getMessage());
		}
	}
	
	
	@Test
	/**
	 * Test validate pizza input string, pizza cost and output string
	 * @throws EPizzaException
	 */
	public void testParseInputAndOutputPizzaDetails1() throws EpizzaException{
		
		String testInputStr = "LARGE|THIN|MEXICANSALSA|ONION,PINEAPPLE|CREAM,DOUBLE";
		Pizza pizza = new Pizza(testInputStr);
		
		Assert.assertEquals(PizzaSize.LARGE, pizza.getSize());
		Assert.assertEquals(PizzaBase.THIN, pizza.getBase());
		Assert.assertEquals(PizzaSauce.MEXICANSALSA, pizza.getSauce());
		Assert.assertEquals(PizzaToppings.ONION, pizza.getPizzaToppings().get(0));
		Assert.assertEquals(PizzaToppings.PINEAPPLE, pizza.getPizzaToppings().get(1));
		Assert.assertEquals(CheeseType.CREAM, pizza.getCheese());
		Assert.assertEquals(true, pizza.isDoubleCheese());
		Assert.assertEquals(testInputStr, pizza.getItemString());
		Assert.assertEquals(new Double(10.5), pizza.getItemBillAmt());
	}
	
	
	/**
	 * Test validate pizza input string, pizza cost and output string
	 * @throws EpizzaException
	 */
	@Test
	public void testParseInputAndOutputPizzaDetails2() throws EpizzaException{
		
		String testInputStr = "MEDIUM|PAN|MARGARITA|OLIVE,CAPSICUM,JALAPENO|MOZZARELLA";
		Pizza pizza = new Pizza(testInputStr);
		
		Assert.assertEquals(PizzaSize.MEDIUM, pizza.getSize());
		Assert.assertEquals(PizzaBase.PAN, pizza.getBase());
		Assert.assertEquals(PizzaSauce.MARGARITA, pizza.getSauce());
		Assert.assertEquals(PizzaToppings.OLIVE, pizza.getPizzaToppings().get(0));
		Assert.assertEquals(PizzaToppings.CAPSICUM, pizza.getPizzaToppings().get(1));
		Assert.assertEquals(PizzaToppings.JALAPENO, pizza.getPizzaToppings().get(2));
		Assert.assertEquals(CheeseType.MOZZARELLA, pizza.getCheese());
		Assert.assertEquals(false, pizza.isDoubleCheese());
		Assert.assertEquals(testInputStr, pizza.getItemString());
		Assert.assertEquals(new Double(9.0), pizza.getItemBillAmt());
	}
	
	
	/**
	 * Test validate pizza input string, pizza cost and output string
	 * @throws EpizzaException
	 */
	@Test
	public void testParseInputAndOutputPizzaDetails3() throws EpizzaException{
		
		String testInputStr = "LARGE|CHEESY|MEXICANSALSA|TOMATO,CAPSICUM,PINEAPPLE|CREAM";
		Pizza pizza = new Pizza(testInputStr);
		
		Assert.assertEquals(PizzaSize.LARGE, pizza.getSize());
		Assert.assertEquals(PizzaBase.CHEESY, pizza.getBase());
		Assert.assertEquals(PizzaSauce.MEXICANSALSA, pizza.getSauce());
		Assert.assertEquals(PizzaToppings.TOMATO, pizza.getPizzaToppings().get(0));
		Assert.assertEquals(PizzaToppings.CAPSICUM, pizza.getPizzaToppings().get(1));
		Assert.assertEquals(PizzaToppings.PINEAPPLE, pizza.getPizzaToppings().get(2));
		Assert.assertEquals(CheeseType.CREAM, pizza.getCheese());
		Assert.assertEquals(false, pizza.isDoubleCheese());
		Assert.assertEquals(testInputStr, pizza.getItemString());
		Assert.assertEquals(new Double(12.5), pizza.getItemBillAmt());
	}
}
