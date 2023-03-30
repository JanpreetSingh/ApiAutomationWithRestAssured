package com.employeeApi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersTestNG extends TestListenerAdapter{
	
	ExtentHtmlReporter extentReporter;
	ExtentReports report;
	ExtentTest test;
	
	
	 @Override
	  public void onStart(ITestContext testContext) {

		extentReporter = new ExtentHtmlReporter("./reports/apiAutomationWithRestAssured/employeeApiReport.html");
		
		extentReporter.config().setDocumentTitle("apiAutomationWithRestAssured Project");
		extentReporter.config().setReportName("Employee API Report");
		extentReporter.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(extentReporter);
		
		report.setSystemInfo("Host Name", "localhost");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("user", "JSJ");
		
	  }
	 
	 
	 @Override
	  public void onTestSuccess(ITestResult result) {
	    
//		 test = report.createTest(result.getClass().getName());
//		 test.createNode(result.getName());
		 test = report.createTest(result.getName()); // Create new entry in the report
		 
		 test.log(Status.PASS, "Testcase PASSED is ----> " + result.getName());
		 
	  }
	 
	 
	  @Override
	  public void onTestFailure(ITestResult result) {
		  
		  test = report.createTest(result.getName()); // Create new entry in the report
			 
		  test.log(Status.FAIL, "Testcase FAILED is ----> " + result.getName()); // to add name in the report
		  test.log(Status.FAIL, "Testcase FAILED is ----> " + result.getThrowable()); // to add error/exception in the report
		  
	  }
	  
	  
	  @Override
	  public void onTestSkipped(ITestResult result) {
		  
		  test = report.createTest(result.getName()); // Create new entry in the report
			 
		  test.log(Status.FAIL, "Testcase SKIPPED is ----> " + result.getName()); // to add name in the report
		  
	  }
	  

	  @Override
	  public void onFinish(ITestContext testContext) {
		  
		  report.flush();
		  
	  }
	  

}























