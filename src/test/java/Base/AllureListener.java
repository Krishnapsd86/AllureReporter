package Base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListener implements ITestListener  {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String msg) {
		return msg;
	}

	public void onStart(ITestContext iTestContext) {
		System.out.println("OnStart Method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", BaseClass.getDriver());
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("onFinish :" + iTestContext.getName());
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("OnTestStart " + getTestMethodName(iTestResult) + "start");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("OnTestSuccess " + getTestMethodName(iTestResult) + "success");
	}

	public void onTestFailure(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseClass.getDriver();
		// Allure ScreenShot and saving Testlog
		if (driver instanceof WebDriver) {
			System.out.println("ScreenShot capture " + getTestMethodName(iTestResult));
			saveFailureScreenShot(driver);
		}
		saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot");
	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("On Test Skip :" + getTestMethodName(iTestResult));
	}

	public void onTestFailedWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test Failed but success ratio :" + getTestMethodName(iTestResult));
	}
}
