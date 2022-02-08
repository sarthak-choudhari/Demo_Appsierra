package listeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener{

	private static final Logger logger = LogManager.getLogger(Listener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		logger.info(("Running test method ---> " + result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Successfully Executed ---> " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.error("Test execution ---> " + result.getMethod().getMethodName() + " failed...");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("Test ---> " + result.getMethod().getMethodName() + " skipped...");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("Test failed but within percentage % ---> " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Suite ---> " + context.getName() + " Started ***");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info(("Test Suite ---> " + context.getName() + " Ending ***"));
	}

}
