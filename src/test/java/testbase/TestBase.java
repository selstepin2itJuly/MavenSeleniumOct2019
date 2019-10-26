package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.TestUtility;

public class TestBase {

	private WebDriver dr;
	public String cbrowser;
	private final int time=20;
	private String url;
	public String browser;
	public Properties prop;
	public String username;
	public String password;
	/*public WebDriver getInstance(String url) {
		String key="webdriver.chrome.driver";
		String value="./drivers/chromedriver.exe";
		System.setProperty(key, value);
		
		dr=new ChromeDriver();
		
		dr.get(url);
		return dr;
	}*/
	public WebDriver getInstance() throws IOException {
		
		FileInputStream inStream=new FileInputStream(new File(""
				+ "./src/main/java/config/config.properties"));
		prop=new Properties();
		prop.load(inStream);
		browser=prop.getProperty("browser").trim();
		url=prop.getProperty("url").trim();
		username=prop.getProperty("username").trim();
		System.out.println(username);
		password=prop.getProperty("password").trim();
		System.out.println(password);
		cbrowser=browser.toLowerCase();
		String key=null;
		String value=null;
		switch(cbrowser)
		{
		case "chrome":
			ChromeOptions opt=new ChromeOptions();
			opt.addArguments("--disable-notifications ");
			opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			opt.setExperimentalOption("useAutomationExtension", false);
			opt.setCapability("--password-store", false);//not working
			
		    key="webdriver.chrome.driver";
			value="./drivers/chromedriver.exe";
			System.setProperty(key, value);
			dr=new ChromeDriver(opt);
			
			break;
		case "firefox":
			key="webdriver.gecko.driver";
			value="./drivers/geckodriver.exe";
			System.setProperty(key, value);
			dr=new FirefoxDriver();
			break;
		case "ie":
			key="webdriver.ie.driver";
			value="./drivers/IEDriverServer.exe";
			System.setProperty(key, value);
			dr=new InternetExplorerDriver();
			break;
		case "edge":
			/*key="webdriver.ie.driver";
			value="./drivers/IEDriverServer.exe";
			System.setProperty(key, value);*/
			dr=new EdgeDriver();
			break;
		default: Throwable t= new Throwable();
				 t.initCause(null);
				
		}
		//Open URL
		dr.get(url);
		/*Set<Cookie> cook = dr.manage().getCookies();
		System.out.println(cook);
		dr.manage().deleteAllCookies();
		cook = dr.manage().getCookies();
		System.out.println(cook);*/
		dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		/*Dimension d= new Dimension(411,731);
		dr.manage().window().setSize(d);*/
		dr.manage().window().maximize();
		return dr;
	}
	public void scrollToElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		((JavascriptExecutor)dr).executeScript("window.scrollBy(0,-300)", "");
	}
	
	public void CaptureScreen() throws IOException {
		TakesScreenshot ts=(TakesScreenshot) dr;
		 File file = ts.getScreenshotAs(OutputType.FILE);
		 FileHandler.copy(file, new File("./screenShots/"+TestUtility.getTimeStamp()+"_image.png"));
	}
	
	public void waitForElementClickable(WebElement element) {
		
		WebDriverWait wait=new WebDriverWait(dr, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForElementVisible(WebElement element) {
		
		WebDriverWait wait=new WebDriverWait(dr, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void handleAlter() {
		Alert al=dr.switchTo().alert();
		System.out.println(al.getText());
		al.dismiss();
	}
}





