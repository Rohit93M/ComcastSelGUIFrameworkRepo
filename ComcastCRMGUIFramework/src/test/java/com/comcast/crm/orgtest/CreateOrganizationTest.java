package com.comcast.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseclass.BaseClass;
import com.comcast.crm.generic.webdriverUtility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners( com.comcast.crm.listenerUtility.ListenerImplementation.class)
public class CreateOrganizationTest extends BaseClass {
	
	/**
	 * @author ROHIT M
	 * @throws Throwable
	 * This is a smoke scenario. We want to check whether critical functionalities
	 * like Create Organization and Organization module is working or not
	 */

	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO,"Read data from excel");
		/*This object is getting initialized in ListenerImplentation class*/

		/*Read test script data from excel file*/
		String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();

		/*Step 2: Navigate to Organization page*/
		
		//ListenerImplementation.test.log(Status.INFO,orgName+"Navigate to Organization page");
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Organization page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		/*getter method provides single element action whereas business method providesmultiple element actions*/

		/*Step 3: Click on "Create Organization" button*/
		OrganizationsPage op = new OrganizationsPage(driver);
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Create Organization page");
		op.getCreateNewOrgBtn().click();

		/*Step 4: Enter all the details and Create a new Organization*/
		UtilityClassObject.getTest().log(Status.INFO,"Create a new Org");
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);

		/*business logic*/
		cno.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+"New Org is created");

		/*Verify Header message Expected Result*/
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerMsg = oip.getHeaderMsg().getText();
		Boolean status= headerMsg.contains(orgName);
		Assert.assertEquals(status, true);

		/*Verify orgName info Expected Result*/
		String actOrgName = oip.getListedOrgName().getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actOrgName, orgName);

		// hp.navigateToCampaignPage();
	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustriesTest()
			throws EncryptedDocumentException, IOException, InterruptedException {

		// read test script data from excel file
		String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 7, 3);
		String type = elib.getDataFromExcel("org", 7, 4);

		// Step 2: Navigate to Organization page
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step 3: Click on "Create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4: Create new Organization
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);

		// Business logic
		cno.createOrg(orgName, wlib, industry, type);

		// Verify the industries info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualIndustry = oip.getListedIndustryName().getText();
		if (actualIndustry.equals(industry)) {
			System.out.println(industry + " information is verified == PASS");
		} else {
			System.out.println(industry + " information is not verified == FAIL");
		}

		// Verify the type info
		String actualType = oip.getListedType().getText();
		if (actualType.equals(type)) {
			System.out.println(type + " information is verified == PASS");
		} else {
			System.out.println(type + " information is not verified == FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithPhoneNoTest()
			throws EncryptedDocumentException, IOException, InterruptedException {

		// read test script data from excel file

		String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String phoneNo = elib.getDataFromExcel("org", 4, 3);

		// Step 2: Navigate to Organization page
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step 3: Click on "Create Organization" button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4: Enter all the details and Create new Organization
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);

		// Business logic
		cno.createOrg(orgName, phoneNo);

//		Verify the phone no info expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualPhoneNo = oip.getListedPhoneNo().getText();
		if (actualPhoneNo.equals(phoneNo)) {
			System.out.println(phoneNo + " information is verified == PASS");
		} else {
			System.out.println(phoneNo + " information is not verified == FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void deleteOrganizationTest() throws Throwable {

		// Read test script data from excel file
		String orgName = elib.getDataFromExcel("org", 10, 2) + jlib.getRandomNumber();

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

		// Verify Header message Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " org name is verified===PASS");
		} else {
			System.out.println(orgName + " org name is not verified===FAIL");
		}

		// Verify orgName info Expected Result
		actOrgName = oip.getListedOrgName().getText();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + " is created == PASS");
		} else {
			System.out.println(orgName + " is not created == FAIL");
		}

		// Step-5: Go back to Organizations Page
		hp.getOrgLink().click();

		// Step-6: Search for Organization
		op.getSearchTF().sendKeys(orgName);
		wlib.handleDropdown(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();

		// Step-7: In dynamic Web table Select and delete Org
		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../../td[last()]/a[text()='del']")).click();

		// Step-8: Handle the alert
		wlib.switchToAlertAndAccept(driver);
	}
}
