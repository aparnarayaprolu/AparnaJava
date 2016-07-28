package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import javax.net.ssl.HostnameVerifier;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class AmazonTest {
	String url="http://www.amazon.com";
	AmazonHome homePage;
  @Test(priority=1)
  public void AmazonHomeTestMethod() throws InterruptedException {
	  homePage.enterSearchText("graco stroller");
		homePage.clickOnsearchGo();
		homePage.waitForPageLoad();
  }
  
  @Test(priority=0)
  public void AmazonSigninMethod() throws InterruptedException {
	  AmazonSignIn signPage = homePage.clickSignInToEnter();
		signPage.enterEmailId("aparna407@gmail.com");
		signPage.enterPassword("aparna");
		signPage.clickSignIn();
  }
  @Test(priority=1)
  public void AmazonViewCartMethod() throws InterruptedException {
	  AmazonCart cartPage = homePage.clickViewCart();
  }
  @BeforeMethod
  public void beforeMethod() {
	  
		
  }

  @AfterMethod
  public void afterMethod() {
	  //System.out.println("in after method");
	 
  }

  @BeforeClass
  public void beforeClass() {
	  homePage = new AmazonHome("firefox", url);
	  
  }

  @AfterClass
  public void afterClass() {
	  homePage.driver.quit();
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
