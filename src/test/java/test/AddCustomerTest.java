package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.AddCustomerPage;
import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;
import util.ExcelReader;

public class AddCustomerTest {
	WebDriver driver;
	ExcelReader excelread = new ExcelReader("data\\\\TF_TestData.xlsx");
	String userName = excelread.getCellData("LoginInfo", "UserName", 2);
	String password = excelread.getCellData("LoginInfo", "Password", 2);
	String fullName = excelread.getCellData("AddContactInfo", "fullName", 2);
	String CompanyName = excelread.getCellData("AddContactInfo", "CompanyName", 2);
	String Email = excelread.getCellData("AddContactInfo", "Email", 2);
	String Phone = excelread.getCellData("AddContactInfo", "Phone", 2);
	String Address = excelread.getCellData("AddContactInfo", "Address", 2);
	String City = excelread.getCellData("AddContactInfo", "City", 2);
	String Country = excelread.getCellData("AddContactInfo", "country", 2);
	String state = excelread.getCellData("AddContactInfo", "state", 2);
	String zip = excelread.getCellData("AddContactInfo", "zip", 2);

	@Test
	public void validUserShouldBeAbleToCreateCustomer() {
		driver = BrowserFactory.init();
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		loginpage.enterUserName(userName);
		loginpage.enterPassWord(password);
		loginpage.clickSigninButton();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.verifyDashboardPage();
		dashboardPage.clickCustomersButton();
		dashboardPage.clickAddCustomersButton();
		AddCustomerPage addCustomerPage = PageFactory.initElements(driver, AddCustomerPage.class);
		addCustomerPage.enterFullName(fullName);
		addCustomerPage.enterCompany(CompanyName);
		addCustomerPage.enterEmail(Email);
		addCustomerPage.enterPhone(Phone);
		addCustomerPage.enterAddress(Address);
		addCustomerPage.enterCity(City);
		addCustomerPage.enterState(state);
		addCustomerPage.enterZip(zip);
		addCustomerPage.enterCountry(Country);

		addCustomerPage.clickSaveButtonOnAddContact();
		addCustomerPage.verifySummaryPage();

		dashboardPage.clickLIstCustomersButton();
		addCustomerPage.verifyEnterName();
		BrowserFactory.tearDown();
	}

}
