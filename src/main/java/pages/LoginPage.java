package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver dr;
	public LoginPage(WebDriver driver) {
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(xpath="//input[@type='email']")
	public WebElement email;
	
	@FindBy(css="[name='Password']")
	WebElement password;
	
	@FindBy(css="[value='Log in']")
	WebElement loginButton;
	
	@FindBy(css="[class='title'] >strong")
	WebElement welcomeMsg;
	
	@FindBy(xpath="//*[contains(@class,'validation-summary-errors')]")
	WebElement failedValidation;
	
	public void login(String user, String pass) {
		email.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
		
	}
	
	public String getWelcomeMessage() {
		return welcomeMsg.getText();
	}
	
	public boolean checkWelcomeMessage(){
		boolean b=false;
	try {
		b=welcomeMsg.isDisplayed();
	}catch(Exception e) {
		
	}
		return b;
	}
	
	public boolean unsuccessfulLogin() {
		boolean b=false;
		try {
			b=failedValidation.isDisplayed();
		}catch(Exception e) {
			
		}
			return b;
	}
}
