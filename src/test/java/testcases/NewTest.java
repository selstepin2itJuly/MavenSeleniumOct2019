package testcases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class NewTest {
	WebDriver driver;
	TestBase tb;
	LoginPage lp;
	DashboardPage dp;
  @Test
  public void LoginSuccess() {
	  lp.login(tb.username, tb.password);
	  tb.waitForElementClickable(dp.userLogged);
	  String str = dp.loggedUser();
	  System.out.println(str);
	  assertEquals("John Smith".trim(), str.trim());
  }
  
  @Test(enabled=true)
  public void verifyListOptionOnDashboard() {
	  lp.login(tb.username, tb.password);
	  tb.waitForElementClickable(dp.userLogged);
	  int i=dp.getCountOfSideBarMenu();
	  assertEquals(12,i);
	  
  }
  
  @Test(enabled=true, description="Login Success verification")
  public void LoginunSuccess() {
	  lp.login(tb.username, "werqwer");
	  tb.waitForElementClickable(dp.userLogged);
	  assertTrue(lp.unsuccessfulLogin());
  }
  @BeforeMethod
  public void beforeMethod() throws IOException {
	  
		 tb=new TestBase();
		 driver=tb.getInstance();
		 lp=new LoginPage(driver);
		 dp=new DashboardPage(driver);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
