package testng;

import org.testng.annotations.Test;

import utilities.ExcelUtility;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;

public class AmazonCrossBrowserTest {
	
	String url="http://www.amazon.com";
	AmazonHome homePage;
	String node = "http://aparna1987:58309926-5770-4585-8bf1-fc701b6100ab@ondemand.saucelabs.com:80/wd/hub";	
	WebDriver driver;
  
	@Parameters({"browserType","env"})
  @BeforeClass
  public void beforeClass(String browserType, String env) {
	  String jobName="001";
	  homePage = new AmazonHome(browserType, env, url, node,jobName);
  }

  @AfterClass
  public void afterClass() {
	  homePage.driver.quit();
  }
  
  @Test(dataProvider = "excelSearchItemData")
  public void AmazonHomeAddToCartTestMethod(String searchText, String itemSelection) throws InterruptedException {
	  homePage.enterSearchText(searchText);  
	  homePage.clickOnsearchGo();
	  homePage.clickOnItem(itemSelection);
	  homePage.clickAddToCart();	
  }

  @DataProvider(name="excelSearchItemData")
  public Object[][] getExcelData(){
	  return ExcelUtility.getWorksheetData("C:\\Users\\Manohar\\Aparna\\Java\\JavaWorkspace\\AmazonBook.xlsx", "SearchItem");
  }

}
