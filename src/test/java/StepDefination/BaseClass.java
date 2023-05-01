package StepDefination;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;

import org.apache.logging.log4j.*;


/*Parent Class*/
public class BaseClass {
	public static WebDriver driver;
	public LoginPage loginPg;
	public SearchCustomerPage SearchCustPg;
	public AddNewCustomerPage addNewCustPg;
	public static Logger log;
	public ReadConfig readconfig;
	public String generateEmailId()
	{
		return(RandomStringUtils.randomAlphabetic(5));
	}

}