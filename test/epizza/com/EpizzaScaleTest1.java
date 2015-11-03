package test.epizza.com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epizza.Constant;
import com.epizza.EpizzaException;
import com.epizza.EpizzaScale;
import com.epizza.EpizzaUtill;

public class EpizzaScaleTest1 {
	
	@Before
	public void runBeforeTest() throws IOException{	
		File emailBase = new File(Constant.EMAIL_FILE_BASE);
		if(emailBase.exists()) {
			emailBase.delete();
		}
		FileWriter writer = new FileWriter(new File("testInput1.txt"));
		writer.write("SMALL|NORMAL|MARGARITA|CAPSICUM,ONION,OLIVE|MOZZARELLA\nLARGE|PAN|MARGARITA|ONION,PINEAPPLE|CREAM,DOUBLE\nEMAIL:sushilkumarchaudhary@gmail.com\n");
		writer.close();
	}
	
	
	@Test
	/**
	 * Test total Bill amount for first and next order.(include and exclude new customer discount)
	 * @throws EPizzaException
	 */
	public void testTotalBillAmout() throws EpizzaException{
		EpizzaScale ePizzaScale = new EpizzaScale("testInput1.txt", "testOutput1.txt");
		Assert.assertEquals("17.82", EpizzaUtill.roundOff(ePizzaScale.calculateAndPrintTotalBill()));
		Assert.assertEquals("19.80", EpizzaUtill.roundOff(ePizzaScale.calculateAndPrintTotalBill()));
		Assert.assertTrue(new File("testOutput1.txt").exists());
	}
	
	
	
	@After
	public void runAfterTest(){
		new File("testInput1.txt").delete();
		new File("testOutput1.txt").delete();
	}
}
