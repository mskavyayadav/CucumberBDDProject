package StepDefination;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef extends BaseClass {

	@Before
	public void setup1() {
		readconfig = new ReadConfig();

		// initialise logger
		log = LogManager.getLogger("StepDef");

		System.out.println("Setup-Sanity method executed..");

		String browser = readconfig.getBrowser();

		// launch browser
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			// driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}

		log.fatal("Setup1 executed...");

	}

	/*
	 * @Before public void setup2() {
	 * 
	 * // initialise logger log = LogManager.getLogger("StepDef");
	 * 
	 * System.out.println("Setup2-Regression method executed..");
	 * WebDriverManager.chromedriver().setup(); ChromeOptions options = new
	 * ChromeOptions(); options.addArguments("--remote-allow-origins=*"); driver =
	 * new ChromeDriver(options); log.info("Setup2 executed..."); }
	 */

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		loginPg = new LoginPage(driver);
		addNewCustPg = new AddNewCustomerPage(driver);
		SearchCustPg = new SearchCustomerPage(driver);
		log.info("chrome browser launched");

	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		log.info("url opened");

	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) {
		loginPg.enterEmail(emailadd);
		loginPg.enterPassword(password);
		log.info("email address and password entered");

	}

	@When("Click on Login")
	public void click_on_login() {
		loginPg.clickOnLoginButton();
		log.info("Clicked on login button");

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle = driver.getTitle();

		if (actualTitle.equals(expectedTitle)) {

			Assert.assertTrue(true);// pass
			log.warn("Test passed: Login feature :Page title matched.");
		} else {
			Assert.assertTrue(false);// fail
			log.warn("Test Failed: Login feature- page title not matched.");

		}

	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginPg.clickOnLogOutButton();
		log.info("user clicked on logout link.");

	}

	/*
	 * @Then("close browser") public void close_browser() { driver.close();
	 * log.info("Browser closed");
	 * 
	 * }
	 */

	// Add new Customer

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";

		if (actualTitle.equals(expectedTitle)) {

			Assert.assertTrue(true);
			log.info("user can view dashboard test passed.");

		} else {
			Assert.assertTrue(false);
			log.warn("user can view dashboard test failed.");

		}
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addNewCustPg.clickOnCustomersMenu();
		log.info("customer menu clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addNewCustPg.clickOnCustomersMenuItem();
		log.info("customer menu item clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustPg.clickOnAddnew();
		log.info("clicked on add new button.");

	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if (actualTitle.equals(expectedTitle)) {

			Assert.assertTrue(true);// pass
			log.info("User can view Add new customer page- passed");
		} else {

			Assert.assertTrue(false);// fail
			log.info("User can view Add new customer page- failed");
		}
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		// addNewCustPg.enterEmail("msk19@gmail.com");
		addNewCustPg.enterEmail(generateEmailId() + "@gmail.com");
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Mohit");
		addNewCustPg.enterLastName("Yadav");
		addNewCustPg.enterGender("Male");
		addNewCustPg.enterDob("5/19/1993");
		addNewCustPg.enterCompanyName("Zento");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");

		log.info("User can view Add new customer page- failed");

	}

	@When("click on Save button")
	public void click_on_save_button() {
		addNewCustPg.clickOnSave();
		log.info("clicked on save button");

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {

		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if (bodyTagText.contains(exptectedConfirmationMsg)) {
			Assert.assertTrue(true);// pass
			log.info("User can view confirmation message - passed");

		} else {

			Assert.assertTrue(false);// fail
			log.warn("User can view confirmation message - failed");

		}
	}

	// Search Customer

	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		SearchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
		log.info("Email address entered");

	}

	@When("Click on search button")
	public void click_on_search_button() {
		SearchCustPg.clickOnSearchButton();
		log.info("Clicked on search button.");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "victoria_victoria@nopCommerce.com";

		// Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail));

		if (SearchCustPg.searchCustomerByEmail(expectedEmail) == true) {
			Assert.assertTrue(true);
			log.info("User should found Email in the Search table - passed");

		} else {

			Assert.assertTrue(false);
			log.info("User should found Email in the Search table - passed");

		}

	}

	/////////////// search customer by name////////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		SearchCustPg.enterFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		SearchCustPg.enterLastName("Terces");

	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName = "Victoria Terces";

		if (SearchCustPg.searchCustomerByName(expectedName) == true) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);

	}

	/*
	 * @After public void teardown2() {
	 * System.out.println("Tear Down method executed.."); driver.quit(); }
	 */

	// @After
	public void teardown(Scenario sc) {
		System.out.println("Tear Down method executed..");
		if (sc.isFailed() == true) {
			// Convert web driver object to TakeScreenshot

			String fileWithPath = "E:\\Cucumber_Autoamtion_Framework\\CucumberFramework\\Screenshot\\failedScreenshot.png";
			TakesScreenshot scrShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			File DestFile = new File(fileWithPath);

			// Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.quit();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {

			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}
	}

}
