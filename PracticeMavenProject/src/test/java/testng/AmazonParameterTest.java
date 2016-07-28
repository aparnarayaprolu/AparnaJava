package testng;

import org.testng.annotations.Test;

import utilities.ExcelUtility;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class AmazonParameterTest {
	String url="http://www.amazon.com";
	AmazonHome homePage;
	String node = "http://aparna1987:58309926-5770-4585-8bf1-fc701b6100ab@ondemand.saucelabs.com:80/wd/hub";
	String jobName = "job_001";
    
  @DataProvider(name="excelSearchItemData")
  public Object[][] getExcelData(){
	  return ExcelUtility.getWorksheetData("C:\\Users\\Manohar\\Aparna\\Java\\JavaWorkspace\\AmazonBook.xlsx", "SearchItem");
  }
  @BeforeClass
  public void beforeClass() {
	  homePage = new AmazonHome("firefox", url);
  }

  @AfterClass
  public void afterClass() {
	  homePage.driver.quit();
  }
  
  @Test(dataProvider = "excelSearchItemData")
  public void AmazonHomeTestMethod(String searchText) throws InterruptedException {
	  homePage.enterSearchText(searchText);
		homePage.clickOnsearchGo();
		//homePage.waitForPageLoad();
  }
  /*@DataProvider(name="excelSearchItemData")
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "graco stroller" },
      new Object[] { "cradle"},
      new Object[] { "mittens"},
    };
  }*/

}
