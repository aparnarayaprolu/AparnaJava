package testng;

import java.awt.print.PageFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonHome extends BaseDriver{
	
	WebDriver driver;
	@FindBy(how=How.ID,using="twotabsearchtextbox")
	WebElement searchBox;
	@FindBy(how=How.ID,using="nav-link-yourAccount")
	WebElement signInHome;
	@FindBy(how=How.XPATH,using="//div[@id='nav-search']//input[@value='Go']")
	WebElement searchGo;
	//nav-flyout-yourAccount
	@FindBy(how=How.ID,using="nav-cart")
	WebElement viewCart;	
	@FindBy(how=How.ID,using="add-to-cart-button")
	WebElement addToCart;	
	@FindBy(how=How.XPATH,using="/div[@id='rightResultsATF']//div[@id='resultsCol']//ul")
	WebElement itemResultsContainer;
	
	//By scroller = By.xpath("//div[@id='autoscoping-backlink']//span[contains(text(),'Showing results in')]");
	//By itemSelection1 = By.xpath("//li[@id='result_0']//a//h2");
	public AmazonHome(){
		
	}
	
	public AmazonHome(String browserType, String url) {		
		this.driver = getDriver(browserType, url);
		PageFactory.initElements(driver, this);
	}
	
	public AmazonHome(String browserType, String env, String url, String node, String jobName){
		this.driver=getDriverForCrossBrowserExecution(browserType, env, url, node, jobName);
		PageFactory.initElements(driver, this);
	}
	
	public void enterSearchText(String actualValue){
		searchBox.clear();
		searchBox.sendKeys(actualValue);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enterSearchTextWithSuggestions(String actual,String partial){
		enterSearchTextWithSuggestions(searchBox,actual,partial);
	}
	
	private void enterSearchTextWithSuggestions(WebElement element, String actual, String partial){
		//WebElement eleSearch=driver.findElement(element);
		//eleSearch.clear();
		//eleSearch.sendKeys(partial);	
		searchBox.clear();searchBox.sendKeys(partial);
		
		WebElement eleSuggestions=driver.findElement(By.xpath("//div[@id='nav-flyout-searchAjax']"));
		List<WebElement> suggestions=eleSuggestions.findElements(By.tagName("div"));
		for(WebElement elem:suggestions){
			if(elem.getText().equals(actual)){
				elem.click();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	public AmazonSignIn clickSignInToEnter() throws InterruptedException{
		signInHome.click();
		Thread.sleep(3000);
		return PageFactory.initElements(driver,AmazonSignIn.class);
		
	}
	
	public AmazonCart clickViewCart() throws InterruptedException{
		viewCart.click();
		Thread.sleep(5000);
		return PageFactory.initElements(driver,AmazonCart.class);
	}
	
	public void waitForPageLoad() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnsearchGo() throws InterruptedException{
		searchGo.click();
		Thread.sleep(5000);
	}
			
	/*public void scrollToViewDesired(String itemSelection) throws InterruptedException{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",scroller1);
		Thread.sleep(2000);
	}*/
	
	
	public void clickOnItem(String itemName) throws InterruptedException{
		WebDriverWait wdw;
		//wdw = new WebDriverWait(driver, 90);
		//WebElement scroller = wdw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='"+itemName+"']")));
		
		WebElement scroller=	driver.findElement(By.xpath(".//*[text()='"+itemName+"']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",scroller);
		wdw = new WebDriverWait(driver, 90);
		WebElement resultContainer =wdw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='rightResultsATF']//div[@id='resultsCol']//ul")));
		//WebElement i=driver.findElement(By.xpath("//div[@id='rightResultsATF']//div[@id='resultsCol']//ul"));
		List<WebElement> results = resultContainer.findElements(By.tagName("li"));
		
		for(WebElement ele:results){
			WebElement item=ele.findElement(By.tagName("h2"));
			//System.out.println(item.getText());
			if(item.getText().equals(itemName)){
				item.click();
				break;
			}
			}
		Thread.sleep(2000);
	}
	
	public void clickAddToCart() throws InterruptedException{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",addToCart);
		addToCart.click();
		Thread.sleep(2000);
	}
}
