package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	WebDriver dr;
	public DashboardPage(WebDriver driver) {
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(css="[class='account-info']")
	public WebElement userLogged;
	
	@FindBy(css="[class='sidebar-menu tree'] >li >a>span")
	List<WebElement> sideBarMenu;
	
	@FindBy(xpath="//*[@class='account-info']/following::a[text()='Logout']")
	WebElement logout;
	
	public String loggedUser() {
		return userLogged.getText().trim();
	}
	
	public int getCountOfSideBarMenu() {
		return sideBarMenu.size();
	}
	
	public List<String> getTextOfSideBarMenu() {
		List<String> list=new ArrayList<String>();
		for(WebElement e:sideBarMenu) {
			list.add(e.getText());
		}
		return list;
	}
	
	public void logOut() {
		logout.click();
	}
}
