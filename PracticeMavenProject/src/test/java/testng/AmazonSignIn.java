package testng;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AmazonSignIn extends AmazonHome{
	
	

	//By emailId = By.id("ap_email");
		//By password = By.id("ap_password");
		//By signIn = By.id("signInSubmit");
	
	@FindBy(how=How.ID,using="ap_email")
	WebElement emailId;

	@FindBy(how=How.ID,using="ap_password")
	WebElement password;
	
	@FindBy(how=How.ID,using="signInSubmit")
	WebElement signIn;
	
	public void enterEmailId(String actual){	
	emailId.clear();
	emailId.sendKeys(actual);
	}

public void enterPassword(String actual){
	password.clear();
	password.sendKeys(actual);
}

public void clickSignIn() throws InterruptedException{
	signIn.click();
	Thread.sleep(2000);
}
	
	
}
