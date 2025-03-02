package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    private WebDriver driver; 

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());

        
        Object testClass = result.getInstance();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getDeclaredField("driver").get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            takeScreenshot(result.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Execution Started");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Execution Finished");
    }

    
    private void takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshots/" + testName + ".png");

        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot taken: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
