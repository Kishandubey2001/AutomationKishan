package newchild;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.utility.RandomString;

public class selenium {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
	WebDriver driver=	new ChromeDriver();
	driver.get("http://qa-sms.geduservices.com/login");
	driver.manage().window().maximize();
	Thread.sleep(3000);
  WebElement email=	   driver.findElement(By.id("userName"));
  email.sendKeys("kkdubey@geduservices.com");
   WebElement password=    driver.findElement(By.id("password"));
   password.sendKeys("Gedu@12345");
   driver.findElement(By.xpath("//button[text()='Login']")).click();
	Thread.sleep(3000);
   driver.findElement(By.xpath("//div[text()='K']")).click();
   Thread.sleep(3000);
  WebElement systemsetting= driver.findElement(By.xpath("//p[text()='System Settings']"));
  JavascriptExecutor  js= (JavascriptExecutor)driver;
  js.executeScript("arguments[0].click()", systemsetting);
  Thread.sleep(3000);
   driver.findElement(By.xpath("//span[text()='Workflow Builder']")).click();
   driver.findElement(By.xpath("//button[text()='Create']")).click();
 String randomString=  RandomString.make(10);
   driver.findElement(By.xpath("//input[@name='workflowName']")).sendKeys(randomString);
   driver.findElement(By.xpath("//button[text()='Submit']")).click();
   Thread.sleep(3000);
    WebElement dragarea=  driver.findElement(By.xpath("//p[text()='Trigger']"));
  WebElement droparea= driver.findElement(By.xpath("//button[@title='zoom in']"));
   Thread.sleep(3000);
  Actions acobj= new Actions(driver);
  acobj.dragAndDrop(dragarea, droparea).build().perform();
   Thread.sleep(5000); 
 
   
  
	
	
		
	}

}
