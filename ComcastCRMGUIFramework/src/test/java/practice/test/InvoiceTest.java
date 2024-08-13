package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.listenerUtility.ListenerImplementation;

//@Listeners(com.comcast.crm.listenerUtility.ListenerImplementation.class)
public class InvoiceTest extends BaseClass {

	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

	//@Test
	public void createInvoiceWithContactTest() {
		System.out.println("execute createInvoiceWithContactTest");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImp.class)
	public void activateSim() {
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}

}
