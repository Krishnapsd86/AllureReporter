package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.AllureListener;
import Base.BaseClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({AllureListener.class})
public class DemoTest extends BaseClass {

	@BeforeMethod
	public void setUp() {
		
		BaseClass bs = new BaseClass();	
		bs.setUp_driver();
		//bs.getDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	// Allure Annotation
	@Description("Verify the logo of page")
	@Epic("EP001")
	@Feature("Feautue1: logo")
	@Story("Story : logo Presence")
	@Step("Verify the logo")
	@Severity(SeverityLevel.MINOR)
	public void Logo() {
		boolean logoDisplayed = driver.findElement(By.cssSelector("img[alt='nopCommerce demo store']")).isDisplayed();
		Assert.assertEquals(logoDisplayed, true);
	}

	@Test(priority = 2)
	// Allure Annotation
	@Description("Verify the login of page")
	@Epic("EP001")
	@Feature("Feautue2: login")
	@Story("Story : Valid login")
	@Step("Verify the login")
	@Severity(SeverityLevel.BLOCKER)
	public void Login() {
		driver.findElement(By.cssSelector(".ico-login")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("Test@yahoo.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Test@123");
		driver.findElement(By.cssSelector("button[class='button-1 login-button']")).click();
		String Msgs = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']")).getText();
		Assert.assertEquals(Msgs, "invalid msgs");
	}

	@Test(priority = 3,expectedExceptions = {SkipException.class})
	// Allure Annotation
	@Description("Verify the register of page")
	@Epic("EP001")
	@Feature("Feautue3: register")
	@Story("Story : User Register")
	@Step("Verify the logo")
	@Severity(SeverityLevel.MINOR)
	public void Register() {
		//throw new SkipException("Skipped");
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
