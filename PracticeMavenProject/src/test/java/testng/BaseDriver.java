package testng;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseDriver {
	public static WebDriver getDriver(String browserType, String url){
		WebDriver driver = null;
		switch(browserType){
		case "firefox": 
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Manohar\\Aparna\\Java\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case "ie":
			System.setProperty("webdriver.ie.driver", "C:\\Users\\Manohar\\Aparna\\Java\\chromedriver_win32\\chromedriver.exe");
			driver = new InternetExplorerDriver();
			break;
		default:
			driver = new FirefoxDriver();
			break;
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public static WebDriver getDriverForCrossBrowserExecution(String browserType, String env, String url, String node, String jobName){
		DesiredCapabilities cap=null; 
		WebDriver driver=null;
		  
		  if(browserType.equals("firefox")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
			}
			else if(browserType.equals("chrome")){
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
			}
			
			if(env.equals("mac"))
			cap.setPlatform(Platform.MAC);
			else
			cap.setPlatform(Platform.WINDOWS);
			
			Random r = new Random(1000);
			jobName = jobName + r.nextLong();
			cap.setCapability("name", jobName);
			try {
				driver = new RemoteWebDriver(new URL(node), cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.get(url);
			driver.manage().window().maximize();
			return driver;
	}
}
