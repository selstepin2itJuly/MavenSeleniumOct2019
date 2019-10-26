package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Functional2 {
  @Test(dataProvider = "dp")
  public void func1(Integer n, String s) {
	  System.out.println("Test 1 - 2");
  }
  @Test
  public void func2() {
	  System.out.println("Test 2 -2 ");
  }
  @Test
  public void func3() {
	  System.out.println("Test 3 - 2");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("B Method - 2");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("A Method - 2");
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
    System.out.println("B class - 2");
  }
  @AfterClass
  public void afterClass() {
	  System.out.println("A Class - 2");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("B Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("A Test");
  }

  

}
