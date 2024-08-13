package com.comcast.crm.contacttest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.listenerUtility.ListenerImplementation;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

/**
 * 
 * @author ROHIT M
 * 
 */

@Listeners(ListenerImplementation.class)
public class CreateContactTest extends BaseClass{
	
	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {

		/*Read test script data from excel file*/
		String lastName = elib.getDataFromExcel("contact", 10, 2) + jlib.getRandomNumber();
		
		/*Step 1: Login to application
		 *Check BaseClass*/
		 
		/*Step 2: Navigate to Contacts module*/
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		/*Step 3: Click on "Create Contact" button*/
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/*Step 4: Create new Contact*/
		CreateNewContactPage cnc = new CreateNewContactPage(driver);
		/*Business Logic*/
		cnc.createContact(lastName);

		/*Verify Header message Expected Result*/
		ContactInfoPage cip = new ContactInfoPage(driver);
		String headerMsg = cip.getHeaderMsg().getText();
		boolean status = headerMsg.contains(lastName);
		Assert.assertEquals(status, true);

		/*Verify orgName info Expected Result*/
		String actualLastName = cip.getListedLastName().getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualLastName, lastName);		
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {

		// read test script data from excel file
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// Step 2: Navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Step 3: Click on "Create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		// Step 4: Create new Contact
		CreateNewContactPage cnc = new CreateNewContactPage(driver);

		String startDate = jlib.getSystemDateyyyyMMdd();
		String endDate = jlib.getRequiredDateyyyyMMdd(30);

		// Business Logic
		cnc.createContactWithSupportDate(lastName, startDate, endDate);

		// Verify start date
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualStartDate = cip.getListedSuppStartDate().getText();
		if (actualStartDate.equals(startDate)) {
			System.out.println(startDate + " is created == PASS");
		} else {
			System.out.println(startDate + " is not created == FAIL");
		}

		// Verify end date
		String actualEndDate = cip.getListedSuppEndDate().getText();
		if (actualEndDate.equals(endDate)) {
			System.out.println(endDate + " is created == PASS");
		} else {
			System.out.println(endDate + " is not created == FAIL");
		}
	}
	
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {

		// read test script data from excel file
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// Step 2: Navigate to Organization page
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step 3: Click on "Create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4: Create new Organization
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		// business logic
		cno.createOrg(orgName);

		// Verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerMsg = oip.getHeaderMsg().getText();
		if (headerMsg.contains(orgName)) {
			System.out.println(orgName + " is created == PASS");
		} else {
			System.out.println(orgName + " is not created == FAIL");
		}

		// Step 6: Navigate to Contacts module
		hp.getContactsLink().click();

		// Step 8: Click on "Create Contact" button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		// Step 9: Create new Contact
		CreateNewContactPage cnc = new CreateNewContactPage(driver);

		// Business Logic
		cnc.createContactWithOrg(lastName, wlib, orgName);

		// Verify Header msg Expected Result
		ContactInfoPage cip = new ContactInfoPage(driver);
		headerMsg = cip.getHeaderMsg().getText();
		if (headerMsg.contains(lastName)) {
			System.out.println(lastName + " is created == PASS");
		} else {
			System.out.println(lastName + " is not created == FAIL");
		}

		// Verify lastName info Expected Result
		String actualLastName = cip.getListedLastName().getText();
		if (actualLastName.equals(lastName)) {
			System.out.println(lastName + " is created == PASS");
		} else {
			System.out.println(lastName + " is not created == FAIL");
		}

		// Verify orgName info Expected Result
		String actualOrgName = cip.getListedOrgName().getText();
		if (actualOrgName.trim().equals(orgName)) {
			System.out.println(orgName + " is created == PASS");
		} else {
			System.out.println(orgName + " is not created == FAIL");
		}
	}
}
