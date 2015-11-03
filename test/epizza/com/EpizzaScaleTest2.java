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

public class EpizzaScaleTest2 {
	
	@Before
	public void runBeforeTest() throws IOException{	
		File emailBase = new File(Constant.EMAIL_FILE_BASE);
		if(emailBase.exists()) {
			emailBase.delete();
		}
		FileWriter writer = new FileWriter(new File("testInput2.txt"));
		writer.write("SMALL|NORMAL|MARGARITA|CAPSICUM,ONION,OLIVE|MOZZARELLA\nLARGE|PAN|MARGARITA|ONION,PINEAPPLE|CREAM,DOUBLE\n");
		writer.close();
	}
	
	/**
	 * total bill amount for the customer not given the email details.
	 * @throws EpizzaException
	 */
	@Test
	public void testTotalBillAmout() throws EpizzaException{
		EpizzaScale ePizzaScale = new EpizzaScale("testInput2.txt", "testOutput2.txt");
		Assert.assertEquals("19.80", EpizzaUtill.roundOff(ePizzaScale.calculateAndPrintTotalBill()));
		Assert.assertTrue(new File("testOutput2.txt").exists());
	}
	
	
	
	@After
	public void runAfterTest(){
		new File("testInput2.txt").delete();
		new File("testOutput2.txt").delete();
	}
}
