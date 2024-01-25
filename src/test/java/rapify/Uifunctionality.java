package rapify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Uifunctionality {
	
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeTest
	public void startreports() {
		htmlReporter = new ExtentSparkReporter("ExtentReportDemo.html");
		reports = new ExtentReports(); 
		reports.attachReporter(htmlReporter);
		
		
		reports.setSystemInfo("Machine", "testpc1"); 
		reports.setSystemInfo("OS", "windows 11"); 
		reports.setSystemInfo("user", "Sahil"); 
		reports.setSystemInfo("Browser", "chrome");
		
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	

@Test
public void test() throws InterruptedException { 

	test= reports.createTest("ui funcationality");
	 
	WebDriver driver=new ChromeDriver();
	driver.get("https://shabnamrapify.devajdevtech.xyz/index.php/login");
	driver.manage().window().maximize();
	
	WebElement username=driver.findElement(By.xpath("//*[@id=\"icon_prefix\"]"));
	username.click();
	username.sendKeys("ajdevtech2022@gmail.com");
	Thread.sleep(2000);
	
	WebElement password=driver.findElement(By.xpath("//*[@id=\"icon_telephone\"]"));
	password.click();
	password.sendKeys("admin");
	Thread.sleep(2000);
	WebElement loginbutton=driver.findElement(By.xpath("//button[@type='submit']"));
	loginbutton.click();
	Thread.sleep(2000);
	
	WebElement aisitebutton=driver.findElement(By.xpath("//*[@id=\"ai_sites\"]/button"));
	aisitebutton.click();
	
 
}

@AfterMethod
public void getTestResult(ITestResult result) 
{
	if(result.getStatus()==ITestResult.FAILURE) 
	{
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "FAILED", ExtentColor.RED ));
	}
	else if(result.getStatus()==ITestResult.SUCCESS) 
	{
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASS ", ExtentColor.GREEN));
	}
	else if(result.getStatus()==ITestResult.SKIP) 
	{
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "SKIP", ExtentColor.YELLOW ));
	}
}
    @AfterTest
    public void tearDown()  
    {
	reports.flush();
    }
}



