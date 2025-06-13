package com.testend.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testend.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentTest test;
	
	ExtentReports extent = ExtentReportNG.getReportObject();

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public  void onTestStart(ITestResult result) {
		// not implemented

		 test =extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	}

   
	@Override
	public void onTestSuccess(ITestResult result) {
		// not implemented
		
		extentTest.get().log(Status.PASS, "Test Passed");
	}


	@Override
	public void onTestFailure(ITestResult result) {
		// not implemented
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String filepath = null;
		
		extentTest.get().fail(result.getThrowable());
		
		try {
			 filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromBase64String(filepath, result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
	    // not implemented
		
		extent.flush();
	  }



}
