package test.epizza.com;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epizza.Constant;
import com.epizza.EpizzaException;
import com.epizza.EpizzaScale;

public class EpizzaNewCustTest {

	@Before
	public void runBeforeTest() throws IOException{
		File emailBase = new File(Constant.EMAIL_FILE_BASE);
		if(emailBase.exists()) {
			emailBase.delete();
		}		
	}
	
	
	@Test
	/**
	 * In database system test case can be defined to rollback to original state
	 * @throws EPizzaException
	 */
	public void testEpizzaNewCustomer() throws EpizzaException{
		
		EpizzaScale ePizzaScale = new EpizzaScale("testInput.txt", "testOutput.txt");
		Assert.assertTrue(ePizzaScale.isNewCustomer("EpizzaTest@abc.com"));
		Assert.assertFalse(ePizzaScale.isNewCustomer("EpizzaTest@abc.com"));
	}
	
	@After
	public void runAfterTest(){
		File emailBase = new File(Constant.EMAIL_FILE_BASE);
		if(emailBase.exists()) {
			emailBase.delete();
		}
	}

}
