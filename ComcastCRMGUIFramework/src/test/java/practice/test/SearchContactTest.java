package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * Test class for Contact module
 * @author ROHIT M
 * 
 */

public class SearchContactTest extends BaseClass {
	/**
	 * Scenario: login()==> navigateContact==> createContact==verify
	 */
	@Test
	public void searchContactTest() {
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
