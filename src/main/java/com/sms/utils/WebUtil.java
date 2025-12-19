package com.sms.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput.ScrollOrigin;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.epam.healenium.SelfHealingDriver;
//import com.google.auto.common.BasicAnnotationProcessor.Step;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebUtil {

	private WebDriver delegate; // null
	private SelfHealingDriver driver;
	private Properties properties;
	public WebDriver getDriver()
	{
		return driver;
	}

	//    This is singleton class singleton means we can create object Once only
	private WebUtil() {

	}
	private static WebUtil Ksw;

	public static WebUtil getobject() {
		if (Ksw == null) {
			Ksw = new WebUtil();
		}
		return Ksw;
	}
	//	 this is Extent report configure

	private ExtentTest extTest;
	public ExtentTest getExtentTestObject() {
		return this.extTest;
	}

	public void setExtentTestObject(ExtentTest extTest) {
		this.extTest = extTest;
	}

	/// This is cross browser test method 



	public void launchbrowser(String browsername) {
		if (browsername.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.OFF); // Suppress browser logs
			logPrefs.enable(LogType.DRIVER, Level.OFF);  // Su
			ChromeOptions options = new ChromeOptions();
			options.setCapability("goog:loggingPrefs", logPrefs);
			// Add Chrome options
			options.addArguments("--disable-notifications");
			options.addArguments("--remote-allow-origins=*");
			//options.addArguments("download.default_directory=" + downloadFolderPath);
			//			options.addArguments("--headless");

			System.out.println("\n" + "****EXECUTING WEBSITE ON CHROME BROWSER****");
			Path resourceDirectory = Paths.get("src", "test", "resources", "exefiles", "chromedriver.exe");
			String absolutePath = resourceDirectory.toFile().getAbsolutePath();

			HashMap<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", "C:\\Users\\KishanKumarDubey\\Downloads");

			//  ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);


			//			Chrome driver instance
			//System.setProperty("webdriver.chrome.driver", absolutePath);
			WebDriverManager.chromedriver().setup();
			delegate = new ChromeDriver(options);
			 driver = SelfHealingDriver.create(delegate);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120)); // Waits for the page to load
			driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(120));

		} else if (browsername.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.gicko.driver", "driver//chromedriver.exe");
			driver = (SelfHealingDriver) new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		}
		else if (browsername.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.gicko.driver", "driver//chromedriver.exe");
			driver = (SelfHealingDriver) new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		}


	}


	public void zoomInZoomOut(int zoomPercent) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='" + zoomPercent + "%';");
	}




	public void findBrokenLink(String websiteURl)
	{
		try {
			driver.get(websiteURl);
			List<WebElement> links = driver.findElements(By.tagName("a"));
			//  System.out.println("Total links found: " + links.size());
			extTest.log(Status.INFO, "Total links found: " + links.size());

			for (WebElement link : links) {
				String url = link.getAttribute("href");
				if (url == null || url.isEmpty()) {
					continue; // Skip empty links
				}

				// Check the link response
				try {
					HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
					httpURLConnection.setRequestMethod("HEAD"); // Use HEAD request for efficiency
					httpURLConnection.connect();

					int responseCode = httpURLConnection.getResponseCode();
					if (responseCode >= 400) { // Only print broken links
						// System.out.println("❌ Broken Link: " + url + " → Response Code: " + responseCode);
						extTest.log(Status.INFO, "❌ Broken Link: " + url + " → Response Code: " + responseCode);
					}
				} catch (IOException e) {
					// System.out.println("⚠️ Error checking URL: " + url + " → " + e.getMessage());
					extTest.log(Status.INFO,"⚠️ Error checking URL: " + url + " → " + e.getMessage());
				}
			}
		} finally {
			// driver.quit(); // Ensure browser closes
		}
	}


	public  void uploadmultipleFiles(WebElement uploadElement, List<String> filePaths) {
		try {
			// Prepare the file paths as a comma-separated string
			StringBuilder files = new StringBuilder();
			for (String filePath : filePaths) {
				files.append(filePath).append(",");
			}

			// Remove the last comma from the file paths string
			String finalFilePaths = files.toString().replaceAll(",$", "");

			// Upload the files by sending the paths to the file input element
			uploadElement.sendKeys(finalFilePaths);
			// System.out.println("Files uploaded successfully: " + finalFilePaths);
			extTest.log(Status.INFO, "Files uploaded successfully: " + finalFilePaths);
		} catch (Exception e) {
			// System.err.println("Error during file upload: " + e.getMessage());
			extTest.log(Status.INFO,"Error during file upload: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void fileuploadusingrobotclass(String filePath) throws InterruptedException, AWTException
	{
		Robot robot = new Robot();

		// Type the path of the file to upload (replace with your file path)
		// String filePath = "C:\\path\\to\\your\\file.txt";

		// Simulate typing the file path
		StringSelection stringSelection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		// Press Ctrl + V to paste the file path into the file dialog
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		// Press Enter to confirm the file selection
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Give time for the file to upload
		Thread.sleep(3000);

	}


	////  GetEditboxValue

	public String GetEditboxValue(WebElement we, String logicalname)
	{
		String textValue = null;

		try {
			textValue = we.getAttribute("value");
			extTest.log(Status.INFO, "Passed ! Get Attributevalue  take successfully of " + logicalname + "");

			// If value attribute is empty, fallback to getText()
			if (textValue == null || textValue.trim().isEmpty()) {
				textValue = we.getText();
			}
		}catch(NoSuchElementException e)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			textValue = (String) js.executeScript("return arguments[0].textContent;", we);	

		} catch (StaleElementReferenceException e) {
			textValue = we.getText();
			extTest.log(Status.INFO, "Passed ! Get Attributevalue take successfully of " + logicalname + "");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !Get Attributevalue is not take successfully of " + logicalname + "");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;

		}
		return textValue;

	}





	public WebDriverWait explicitWait() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(null));
		return wait;
	}


	public void clickbytab()
	{
		Actions ac=	new Actions(driver);
		ac.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
		extTest.log(Status.PASS, "Click by tab perform successfully");
	}

	public void tab()
	{
		Actions ac=	new Actions(driver);
		ac.sendKeys(Keys.TAB).build().perform();
		extTest.log(Status.PASS, " tab perform for next element successfully");

	}

	public void Enter()
	{
		Actions ac=	new Actions(driver);
		ac.sendKeys(Keys.ENTER).build().perform();
		extTest.log(Status.PASS, "Enter Button perform successfully");


	}



	public static void sendEmailWithReport(String recipient, String subject, String body, String filePath) {
		final String senderEmail = "codingkishan2001@gmail.com";
		final String senderPassword = "Kishan@2001"; // Use App Password here

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);

			// Email Body
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);

			// Email Attachment
			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName(filePath);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Email sent successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void writeDataInExcel(List<String> headerNames, String filename, String tablename, String sheetname) throws IOException, InterruptedException {
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet(sheetname);

		// Create header row
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headerNames.size(); i++) {
			headerRow.createCell(i).setCellValue(headerNames.get(i));
		}

		int rowCount = 1;

		while (true) {
			List<WebElement> rows;
			try {
				rows = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row MuiDataGrid')]"));
			} catch (StaleElementReferenceException e) {
				Thread.sleep(2000);
				rows = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row MuiDataGrid')]"));
			}

			for (int i = 0; i < rows.size(); i++) {
				WebElement row;
				try {
					// Re-fetch row element again to avoid stale element
					rows = driver.findElements(By.xpath("//div[contains(@class,'MuiDataGrid-row MuiDataGrid')]"));
					row = rows.get(i);
				} catch (StaleElementReferenceException | IndexOutOfBoundsException e) {
					Thread.sleep(1000);
					continue;
				}

				List<WebElement> cells;
				try {
					cells = row.findElements(By.xpath(".//div[contains(@class,'MuiDataGrid-cell MuiDataGrid-cell--textStart')]"));
				} catch (StaleElementReferenceException e) {
					Thread.sleep(500);
					continue;
				}

				Row excelRow = sheet.createRow(rowCount++);
				int maxColumns = Math.min(headerNames.size(), cells.size());

				for (int j = 0; j < maxColumns; j++) {
					try {
						String cellData = cells.get(j).getText();
						excelRow.createCell(j).setCellValue(cellData);
					} catch (StaleElementReferenceException e) {
						continue;
					}
				}
			}

			// Handle "Next" button
			try {
				WebElement nextButton = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-bokx31']"));
				if (!nextButton.isEnabled()) break;

				nextButton.click();
				Thread.sleep(3000); // Allow time for next page to load
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				System.out.println("❗Next button not found or stale: " + e.getMessage());
				break;
			}
		}

		// Save the Excel file
		try (FileOutputStream fileOut = new FileOutputStream(filename)) {
			workbook.write(fileOut);
			extTest.log(Status.INFO, "✅ Data written to Excel for " + tablename);
		}

		workbook.close();
	}





	public void elementtobeclickable(WebElement we)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(we));
		loginButton.click();
		extTest.log(Status.INFO, "Click with explicitwait successfully");
	}

	/////   Calander best =========


	public void calendarHandleBest(WebElement clickDatepicker,
			WebElement allelement,
			WebElement monthYearTitle,
			WebElement clickNextButton,
			WebElement clickPreviousButton,
			String targetMonth,
			String targetYear,
			WebElement clickOnDate) {

		try {
			clickDatepicker.click();
			extTest.log(Status.INFO, "Click is performed on datepicker successfully");
		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", clickDatepicker);
			extTest.log(Status.INFO, "Click is performed on datepicker using JavaScript");
		}

		waitvisibilityofAllelements(allelement);

		String monthYear = monthYearTitle.getText();
		String currentMonth = monthYear.split(" ")[0].trim();
		String currentYear = monthYear.split(" ")[1].trim();

		// Convert months to numeric values for comparison
		Map<String, Integer> monthMap = new HashMap<>();
		monthMap.put("January", 1);
		monthMap.put("February", 2);
		monthMap.put("March", 3);
		monthMap.put("April", 4);
		monthMap.put("May", 5);
		monthMap.put("June", 6);
		monthMap.put("July", 7);
		monthMap.put("August", 8);
		monthMap.put("September", 9);
		monthMap.put("October", 10);
		monthMap.put("November", 11);
		monthMap.put("December", 12);

		int targetMonthNum = monthMap.get(targetMonth);
		int targetYearNum = Integer.parseInt(targetYear);

		while (!(currentMonth.equals(targetMonth) && currentYear.equals(targetYear))) {
			int currentMonthNum = monthMap.get(currentMonth);
			int currentYearNum = Integer.parseInt(currentYear);

			try {
				if (currentYearNum < targetYearNum || 
						(currentYearNum == targetYearNum && currentMonthNum < targetMonthNum)) {
					clickNextButton.click();
				} else {
					clickPreviousButton.click();
				}
			} catch (StaleElementReferenceException | ElementClickInterceptedException e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				if (currentYearNum < targetYearNum ||
						(currentYearNum == targetYearNum && currentMonthNum < targetMonthNum)) {
					js.executeScript("arguments[0].click()", clickNextButton);
				} else {
					js.executeScript("arguments[0].click()", clickPreviousButton);
				}
			}

			monthYear = monthYearTitle.getText();
			currentMonth = monthYear.split(" ")[0].trim();
			currentYear = monthYear.split(" ")[1].trim();
		}

		try {
			clickOnDate.click();
			extTest.log(Status.INFO, "Date selected successfully");
		} catch (ElementNotInteractableException | StaleElementReferenceException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", clickOnDate);
			extTest.log(Status.INFO, "Date selected using JavaScript");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void calanderhanldesecondway(WebElement element,int xOffset,  int yOffset, CharSequence key, int times) throws AWTException
	{
		//WebElement  element=driver.findElement(By.xpath("//input[@name='programStartDate']"));



		// Get location of the element
		org.openqa.selenium.Point location = element.getLocation();
		int elementX = location.getX();
		int elementY = location.getY();

		// Add browser window/frame offsets to calculate screen coordinates
		//	     int xOffset = 525;  // Adjust as needed
		//	     int yOffset = 145; // Typical top offset for Chrome/Windows

		int screenX = elementX + xOffset;
		int screenY = elementY + yOffset;

		// Use Robot to click on Y location
		Robot robot = new Robot();
		robot.mouseMove(screenX, screenY);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Actions actions = new Actions(driver);
		for (int i = 0; i < times; i++) {
			actions.sendKeys(key).perform();
		}
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	// By this method we can delete all cookies 

	public void deleteAllcookies()
	{
		try
		{
			driver.manage().deleteAllCookies();	
			//	extTest.log(Status.INFO, "All cookies deleted successfully");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void zoominout(String screenzoom)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='"+screenzoom+"'");
	}

	// This is flunent wait

	public static WebElement fluentWaitForElement(WebDriver driver, final By locator, int timeoutSeconds) {
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeoutSeconds))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);

		return wait.until(driver1 -> driver1.findElement(locator));

	}

	// Example method for creating a Fluent Wait with custom conditions
	public static WebElement fluentWaitForElementWithCustomCondition(WebDriver driver, int timeoutSeconds, int pollingIntervalMilliseconds, Function<WebDriver, WebElement> condition) {
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeoutSeconds))
				.pollingEvery(Duration.ofMillis(pollingIntervalMilliseconds))
				.ignoring(NoSuchElementException.class);

		return wait.until(condition);
	}

	public String getpagesource() {
		String getpagesource = driver.getPageSource();
		return getpagesource;
	}

	//	  This is implicitlyWait it use with driver where need to time it will manage 
	// however element is not finding in the page then it will throw nosuch element 
	// it will work where ever need time
	public void myimplicitlyWait(int timeouts) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeouts));
		//extTest.log(Status.INFO, "implicit");


	}
	//     This is maximize window method by this we can maximize the window
	public void maximizeWindow() {
		driver.manage().window().maximize();
		//extTest.log(Status.INFO, "Passed ! Maximize window successfully");

	}
	//    This is get method by this we can hit the url of the application
	public void hitUrl(String hiturl) {
		driver.get(hiturl);
		 waitForPageLoad(driver);
			// printmethod("Page loaded completely!");
			 System.out.println("Page loaded completely!");
	}
	
	

	public static void waitForPageLoad(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    wait.until(webDriver -> ((JavascriptExecutor) webDriver)
	            .executeScript("return document.readyState").equals("complete"));
	}
	
	
	
	//     This is send keys method by this way we can send the value in input box
	public void mySendkeys(WebElement we, String inputvalue,String logicalname) {
		//String elementname = we.getAccessibleName();

		try {
			//	if(we.getText()!=" ")
			we.clear();
			we.sendKeys(inputvalue);
			extTest.log(Status.INFO,
					"Passed ! " + inputvalue + " is input successfully in " + logicalname + " text box");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='" + inputvalue + "'", we);
			extTest.log(Status.INFO,
					"Passed !" + inputvalue + " is input successfully in " + logicalname + " text box");

		} catch (StaleElementReferenceException e) {
			we.sendKeys(inputvalue);
			extTest.log(Status.INFO,
					"Passed ! " + inputvalue + " is input successfully in " + logicalname + " text box");


		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.FAIL,
					"Failed ! " + inputvalue + " is not input successfully in " + logicalname + " text box");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

		}
	}

	public void ownclear(WebElement we)
	{
		we.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		we.sendKeys(Keys.BACK_SPACE); // or Keys.DELETE
		extTest.log(Status.INFO, "field is cleared successfully");

	}


	public void clickonDatePicker(int numberoftabs)
	{
		Actions acobj=  new Actions(driver);

		for(int i=1; i<=numberoftabs; i++)
		{
			acobj.sendKeys(Keys.TAB).build().perform();
		}
		acobj.sendKeys(Keys.ENTER).build().perform();

	}



	//       This is myclick method by this way we can click on the element
	public void myclick(WebElement we,String elementname) {
		//String elementname = we.getAccessibleName();
		try {
			we.click();
			extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname + " ");
		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", we);
			extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname + " using java script");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", we);
			extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname + " using javascript");
		} catch (StaleElementReferenceException e) {
			we.click();
			extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname + " ");
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.FAIL, "Failed ! Click is not perform on " + elementname + " ");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );



			throw e;

		}
	}

	public void hardassertwithstring(String actualvalue,String expectedvalue)
	{
		//		 /String actualAllProductVisible = "ALL PRODUCTS"; // This should come from your actual test logic
		//	        String expectedAllProductVisible = "ALL PRODUCTS";

		try {
			// Assert the value
			Assert.assertEquals(actualvalue, expectedvalue);
			extTest.pass("Assertion Passed: Actual '" + actualvalue + " value matches expected value " + expectedvalue + "'.");

			//extTest.pass("Assertion Passed: Actual " +actualvalue+ "value matches expected value.");
		} catch (AssertionError e) {
			// Log failure
			extTest.fail("Assertion Failed: Expected '" + actualvalue + "' but found '" + expectedvalue + "'.");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			//                    MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
			//			extTest.log(Status.FAIL, e);

			throw e; // Rethrow the exception if you want the test to fail

		}
	}
	
	//  This method is print the element in extend report

	public void printmethod(String putstring)
	{
		extTest.log(Status.INFO, putstring);	
	}
	
	

	public void myclick1(WebElement we,String elementName ) {
		//String elementName = we.getAccessibleName();
		int maxRetries = 3; // Maximum number of retries for stale element
		int attempts = 0;

		while (attempts < maxRetries) {
			try {
				we.click();
				extTest.log(Status.INFO, "Passed! Click is performed on " + elementName + " ");
				break;
			} catch (ElementClickInterceptedException e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", we);
				extTest.log(Status.INFO, "Passed! Click is performed on " + elementName + " using JavaScript");
				break;
			} catch (ElementNotInteractableException e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", we);
				extTest.log(Status.INFO, "Passed! Click is performed on " + elementName + " using JavaScript");
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
				if (attempts >= maxRetries) {
					extTest.log(Status.FAIL, "Failed! Click could not be performed on " + elementName + " due to stale element");
					throw e;
				}
				// Re-acquire the element and try again
				extTest.log(Status.INFO, "Retrying click on " + elementName + " due to stale element (Attempt " + attempts + ")");
				we = driver.findElement(By.xpath("//xpath-of-element")); // Replace with appropriate locator for your element
			} catch (Exception e) {
				e.printStackTrace();
				extTest.log(Status.FAIL, "Failed! Click is not performed on " + elementName);
				String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
				extTest.fail("Exception occurred: " +exceptionMessage );


				throw e;
			}
		}
	}

	// Explicit wait for visibility of element located

	public void waitvisibilityofAllelements(WebElement allelement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfAllElements(allelement)); // 	
	}




	//   This is listmyclick by this way we can click one time on the listed element	
	public void myclicklist(List<WebElement> list,String elementname) {

		for (int i = 0; i < list.size(); i++) {
			WebElement welist = list.get(i);
			//String elementname = welist.getAccessibleName();
			try {
				welist.click();

				extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname );

			}

			catch (ElementClickInterceptedException e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", welist);
				extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname + " using java script");

			} catch (ElementNotInteractableException e) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", welist);
				extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname + " using javascript");

			} catch (StaleElementReferenceException e) {
				((WebElement) welist).click();
				extTest.log(Status.INFO, "Passed ! Click is perform on " + elementname + " ");

			} catch (Exception e) {
				e.printStackTrace();
				extTest.log(Status.INFO, "Failed ! Click is not perform on " + elementname + " ");
				String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
				extTest.fail("Exception occurred: " +exceptionMessage );

				throw e;

			}
		}

	}
	//         This is verify string method by this method we can verify expected and actual value
	public void verifyString(String actualtext, String expected) {
		if (actualtext.contains(expected)) {
			extTest.log(Status.PASS, "Passed " + actualtext + " and " + expected + " are matched of ");

		} else {
			extTest.log(Status.FAIL, "Failed " + actualtext + " and " + expected + " are not matched of ");


		}
	}
	//   This is mygetText method by this method we can get InnterText of the element
	public String mygetText(WebElement we,String elementname ) {
		//String elementname = we.getAccessibleName();
		String gettext = null;
		try {
			gettext = we.getText();
			extTest.log(Status.INFO, "Passed ! Get text take successfully of  " +  elementname + "");

		}catch(NoSuchElementException e)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			gettext = (String) js.executeScript("return arguments[0].textContent;", we);	

		} catch (StaleElementReferenceException e) {
			gettext = we.getText();
			extTest.log(Status.INFO, "Passed ! Get text take successfully of " + elementname + "");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! Get text is not take successfully of " + elementname + "");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}
		return gettext;
	}

	public String myAttribute(WebElement we,String elementname ) {
		//String elementname = we.getAccessibleName();
		String gettext = null;
		try {
			gettext = we.getAttribute("value");
			extTest.log(Status.INFO, "Passed ! Get Attributevalue  take successfully of " + elementname + "");

		}catch(NoSuchElementException e)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			gettext = (String) js.executeScript("return arguments[0].textContent;", we);	

		} catch (StaleElementReferenceException e) {
			gettext = we.getText();
			extTest.log(Status.INFO, "Passed ! Get Attributevalue take successfully of " + elementname + "");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !Get Attributevalue is not take successfully of " + elementname + "");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}
		return gettext;
	}





	public List<String> myGetTextFromElements(List<WebElement> elements) {
		String elementname = ((WebElement) elements).getAccessibleName();

		List<String> textList = new ArrayList<>();

		for (WebElement element : elements) {
			String elementText = null;

			try {
				elementText = element.getText();
				extTest.log(Status.INFO, "Passed! Text taken successfully for element: "+elementname );
			} catch (NoSuchElementException e) {
				// In case the text is not directly available
				JavascriptExecutor js = (JavascriptExecutor) driver;
				elementText = (String) js.executeScript("return arguments[0].textContent;"+elementname);
			} catch (StaleElementReferenceException e) {
				// In case the element becomes stale
				elementText = element.getText();
				extTest.log(Status.INFO, "Passed! Text taken successfully for element: "+elementname);
			} catch (Exception e) {
				// Catch any other exceptions and log failure
				e.printStackTrace();
				extTest.log(Status.INFO, "Failed! Text could not be retrieved for element: " +elementname);
				String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
				extTest.fail("Exception occurred: " + exceptionMessage);
				throw e;
			}

			textList.add(elementText);
		}

		return textList;
	}




	//  This is getTitle method by this method we can get GetTitle of the window 

	public String getTitle() {
		String gettitle = null;
		try {
			gettitle = driver.getTitle();
			extTest.log(Status.INFO, "Passed ! GetTitle got successfully of UI page");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.FAIL, "Failed !GetTitle did not get successfully of UI page");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}
		return gettitle;

	}
	//     This is mouseover method by this method we can hower mouse on the element
	public void mymouseover(WebElement we,String elementname) {
		//String elementname = we.getAccessibleName();
		Actions acobj = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			acobj.moveToElement(we).build().perform();
			extTest.log(Status.INFO, "Passed !Mause over has been successfully of " + elementname + "");

		} catch (StaleElementReferenceException e) {
			acobj.moveToElement(we).build().perform();
			extTest.log(Status.INFO, "Passed ! Mause over has been successfully of " + elementname + "");

		} catch (ElementNotInteractableException e) {
			if (driver instanceof JavascriptExecutor) {
				String script = "var evObj = document.createEvent('MouseEvents');"
						+ "evObj.initMouseEvent('mouseover',true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
						+ "arguments[0].dispatchEvent(evObj);";
				js.executeScript(script, we);
				extTest.log(Status.INFO, "Passed ! JavaScript Executor is  supported.");

			} else {
				extTest.log(Status.FAIL, "Failed !JavaScript Executor is not supported.");
				extTest.fail("Exception occurred: " + e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! Mause over has not worked successfully of " + elementname + "");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}

	}

	//  This is actions click by this method we can click on the element if normal click is not working then

	public void actionclick(WebElement we,String elementName) {
		//String elementName = we.getAccessibleName();
		Actions acobj = new Actions(driver);
		try {
			acobj.click(we).build().perform();
			extTest.log(Status.INFO,
					"Passed !click is performed on " + elementName + " using the actions click(webelement) method");

		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", we);
			extTest.log(Status.INFO, "Passed ! click is performed on " + elementName + " using the javasript click()");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", we);
			extTest.log(Status.INFO, "Passed ! click is performed on " + elementName + " using the javasript click()");
		} catch (StaleElementReferenceException e) {
			acobj.click(we).build().perform();
			extTest.log(Status.INFO,
					"Passed !click is performed on " + elementName + " using the actions click(webelement) method");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !click is not performed on " + elementName + " using the actions click()");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}

	}
	//    This is double click method if we wanna double click then we will have to use double click of the Actions class
	public void actionDoubleclick(WebElement we,String elementname) {
		//String elementname = we.getAccessibleName();
		Actions acobj = new Actions(driver);
		try {
			acobj.doubleClick(we).build().perform();
			extTest.log(Status.INFO, "Passed ! Double click performed on " + elementname
					+ " successfully using action doubleclick method");

		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String jsDoubleClick = "var target = arguments[0];                                 "
					+ "var offsetX = arguments[1];                                "
					+ "var offsetY = arguments[2];                                "
					+ "var rect = target.getBoundingClientRect();                 "
					+ "var cx = rect.left + (offsetX || (rect.width / 2));        "
					+ "var cy = rect.top + (offsetY || (rect.height / 2));        "
					+ "                                                           "
					+ "emit('mousedown', {clientX: cx, clientY: cy, buttons: 1}); "
					+ "emit('mouseup',   {clientX: cx, clientY: cy});             "
					+ "emit('mousedown', {clientX: cx, clientY: cy, buttons: 1}); "
					+ "emit('mouseup',   {clientX: cx, clientY: cy});             "
					+ "emit('click',     {clientX: cx, clientY: cy, detail: 2});  "
					+ "                                                           "
					+ "function emit(name, init) {                                "
					+ "target.dispatchEvent(new MouseEvent(name, init));        "
					+ "}                                                          ";
			js.executeScript(jsDoubleClick, we);
			extTest.log(Status.INFO,
					"Passed ! doubleclick is performed on " + elementname + " using the javascript double click");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			String jsDoubleClick = "var target = arguments[0];                                 "
					+ "var offsetX = arguments[1];                                "
					+ "var offsetY = arguments[2];                                "
					+ "var rect = target.getBoundingClientRect();                 "
					+ "var cx = rect.left + (offsetX || (rect.width / 2));        "
					+ "var cy = rect.top + (offsetY || (rect.height / 2));        "
					+ "                                                           "
					+ "emit('mousedown', {clientX: cx, clientY: cy, buttons: 1}); "
					+ "emit('mouseup',   {clientX: cx, clientY: cy});             "
					+ "emit('mousedown', {clientX: cx, clientY: cy, buttons: 1}); "
					+ "emit('mouseup',   {clientX: cx, clientY: cy});             "
					+ "emit('click',     {clientX: cx, clientY: cy, detail: 2});  "
					+ "                                                           "
					+ "function emit(name, init) {                                "
					+ "target.dispatchEvent(new MouseEvent(name, init));        "
					+ "}                                                          ";
			js.executeScript(jsDoubleClick, we);
			extTest.log(Status.INFO,
					"Passed ! doubleclick is performed on " + elementname + " using the javascript double click");

		} catch (StaleElementReferenceException e) {
			acobj.doubleClick(we);
			extTest.log(Status.INFO, "Passed ! doubleclick is performed on " + elementname + " double click method");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! doubleclick is not performed on " + elementname + " ");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;

		}

	}
	//       This is context click method of the Actions class by this method we can right click on the element
	public void actionRightClick(WebElement we,String elementNames ) {
		String elementName = we.getAccessibleName();
		Actions acobj = new Actions(driver);
		try {
			acobj.contextClick(we).build().perform();

			extTest.log(Status.INFO, "Passed ! Right click is performed on " + elementName + " ");

		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String script = "var event = new MouseEvent('contextmenu', {" + "  bubbles: true," + "  cancelable: false,"
					+ "  view: window" + "});" + "arguments[0].dispatchEvent(event);";
			js.executeScript(script, we);
			extTest.log(Status.INFO, "Passed ! Right click is performed on " + elementName + " using the javasript");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String script = "var event = new MouseEvent('contextmenu', {" + "  bubbles: true," + "  cancelable: false,"
					+ "  view: window" + "});" + "arguments[0].dispatchEvent(event);";
			js.executeScript(script, we);
			extTest.log(Status.INFO, "Passed ! Right click is performed on " + elementName + " using the javasript");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! Right click is not performed on " + elementName + " ");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;

		}

	}
	//      This is drag and drop method of the Actions class by this way we can drag the element to drop the onther place
	public void actiondragAnddrop(WebElement wedrag, WebElement wedrop,String dragelement,String DropElement) throws InterruptedException {
		//String dragelement = wedrag.getAccessibleName();
		//String DropElement = wedrop.getAccessibleName();

		Actions acobj = new Actions(driver);
		try {
			acobj.dragAndDrop(wedrag, wedrop).build().perform();
			extTest.log(Status.INFO, "Passed !" + dragelement + " is drag at the place " + DropElement + "");

		} catch (StaleElementReferenceException e) {
			acobj.dragAndDrop(wedrag, wedrop).build().perform();
		} catch (ElementNotInteractableException e) {
			String xto = Integer.toString(wedrag.getLocation().x);
			String yto = Integer.toString(wedrop.getLocation().y);
			((JavascriptExecutor) driver).executeScript(
					"function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; "
							+ "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
							wedrag, xto, yto);
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !drag and drog didn't work");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}
	// This is second way to drag and drop 

	public void dragdropbyJavascript(By ByFrom, By ByTo) {
		WebElement LocatorFrom = driver.findElement(ByFrom);
		WebElement LocatorTo = driver.findElement(ByTo);
		String xto = Integer.toString(LocatorTo.getLocation().x);
		String yto = Integer.toString(LocatorTo.getLocation().y);
		((JavascriptExecutor) driver).executeScript(
				"function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; "
						+ "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
						LocatorFrom, xto, yto);
	}
	//       This is dropdownindex by this method we can handle the dropdown with indexed based

	public void mydropdownindex(WebElement we, int indexnumber,String elementname) {
		//	String elementname = we.getAccessibleName();

		Select dropdown = new Select(we);
		try {
			dropdown.selectByIndex(indexnumber);
			extTest.log(Status.INFO,
					"Passed ! Index number " + indexnumber + "th element is selected successfully " + elementname + "");

		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "arguments[0].selectedIndex = " + indexnumber + ";";
			jsExecutor.executeScript(script, we);
			extTest.log(Status.INFO,
					"Passed ! Index number " + indexnumber + "th element is selected successfully " + elementname + "");

		} catch (StaleElementReferenceException e) {
			Select dropdown1 = new Select(we);
			dropdown1.selectByIndex(indexnumber);
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed!Index number " + indexnumber + "th element is not selected successfully "
					+ elementname + "");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}
	//    This is handledrop down with visible text by this method we can handle the drop drop down basis of the visible text
	public void mydropdownByVisibleText(WebElement we, String visibleTextToSelect,String elementName ) {
		//String elementName = we.getAccessibleName();
		Select dropdown = new Select(we);
		try {
			dropdown.selectByVisibleText(visibleTextToSelect);
			extTest.log(Status.INFO,
					"Passed !" + visibleTextToSelect + " is selected in " + elementName + " successfully");

		} catch (ElementNotInteractableException e) {

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "var dropdown = arguments[0];" + "var visi" + "bleText = arguments[1];"
					+ "for (var i = 0; i < dropdown.options.length; i++) {"
					+ "  if (dropdown.options[i].textContent === visibleText) {" + "    dropdown.selectedIndex = i;"
					+ "    break;" + "  }" + "}";
			jsExecutor.executeScript(script, we, visibleTextToSelect);
			extTest.log(Status.INFO,
					"Passed !" + visibleTextToSelect + " is selected in " + elementName + " successfully");
		} catch (StaleElementReferenceException e) {
			Select dropdown1 = new Select(we);
			dropdown1.selectByVisibleText(visibleTextToSelect);
			extTest.log(Status.INFO,
					"Passed !" + visibleTextToSelect + " is selected in " + elementName + " successfully");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO,
					"Failed !" + visibleTextToSelect + " is not selected in " + elementName + " successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );



			throw e;
		}
	}
	//    This is dropdownselected Byvalue by this we can handle the dropdown with Value 
	public void mydropdownSelectedValue(WebElement we, String selectedValue,String elementname) {
		//String elementname = we.getAccessibleName();
		Select dropdownSelectedValue = new Select(we);
		try {
			dropdownSelectedValue.selectByValue(selectedValue);
			extTest.log(Status.INFO,
					"Passed ! " + selectedValue + " is selected in " + elementname + " successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String script = "var dropdown = arguments[0];" + "var value = arguments[1];"
					+ "for (var i = 0; i < dropdown.options.length; i++) {"
					+ "  if (dropdown.options[i].value === value) {" + "    dropdown.selectedIndex = i;" + "    break;"
					+ "  }" + "}";
			jsExecutor.executeScript(script, we, selectedValue);
			extTest.log(Status.INFO,
					"Passed ! " + selectedValue + " is selected in " + elementname + " successfully");

		} catch (StaleElementReferenceException e) {
			dropdownSelectedValue = new Select(we);
			dropdownSelectedValue.selectByValue(selectedValue);
			extTest.log(Status.INFO,
					"Passed ! " + selectedValue + " is selected in " + elementname + " successfully");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO,
					"Failed ! " + selectedValue + " is not selected in " + elementname + " successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );



			throw e;
		}

	}
	//       This is get first selected option by this we can get which drop down is selected already 
	public String getFirstSelectedOptions(WebElement we, String insertvalue) {
		String elementName = we.getAccessibleName();
		Select obj = new Select(we);
		obj.selectByValue(insertvalue);
		WebElement weselectoption = obj.getFirstSelectedOption();
		String actualtext = weselectoption.getAttribute("value");
		return actualtext;

	}


	public  void printDropdownValues(WebElement dropdownButton, WebElement dropdownContainer) {
		try {
			// Click dropdown to open options
			dropdownButton.click();

			// Get all dropdown options inside the container
			List<WebElement> options = dropdownContainer.findElements(By.tagName("div")); // Adjust if needed

			// Print all values
			printmethod("Dropdown values:");
			for (WebElement option : options) {
				printmethod(option.getText());
			}

		} catch (NoSuchElementException e) {
			System.out.println("Dropdown or options not found: " + e.getMessage());
		}
	}



	//      This is getAll options method by this way we can all option of the drop down


	public static List<String> getAllOptions(WebElement we) {
		List<String> optionsList = new ArrayList<>();
		try {
			// Ensure the WebElement is a <select> dropdown
			if (!we.getTagName().equalsIgnoreCase("select")) {
				throw new UnexpectedTagNameException("select", we.getTagName());
			}

			// Create a Select object
			Select select = new Select(we);
			List<WebElement> allOptions = select.getOptions();

			// Loop through options and store their text
			for (WebElement option : allOptions) {
				optionsList.add(option.getText());
			}

		} catch (NoSuchElementException e) {
			System.out.println("Dropdown element not found: " + e.getMessage());
		} catch (UnexpectedTagNameException e) {
			System.out.println("Provided element is not a <select> tag: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
		return optionsList; // Returns empty list if exception occurs
	}






	//    This is drop dwon size method by this method we can get how much options is presend in the drop down


	public int getsizeofdropdown(WebElement we, String elementName) {
		try {
			Select objS = new Select(we);
			List<WebElement> listofgetsize = objS.getOptions();
			return listofgetsize.size();
		} catch (Exception e) {
			System.out.println("Exception occurred while getting the size of dropdown: " + elementName);
			e.printStackTrace();
			return -1; // Returning -1 to indicate an error
		}
	}




	//  This is getCurrent URL by this way we can know that we url we are using right now
	public String getCurrentURl() {
		try {
			String getcurrenturl = driver.getCurrentUrl();
			extTest.log(Status.INFO, getcurrenturl+" is currect url");
			return getcurrenturl;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	//   This is verify the int method by this method we can verify actual int value and expected int value
	public void verifyInt(int actualsize, int expectedsize) {
		if (actualsize == expectedsize) {
			extTest.log(Status.PASS, "Passed " + actualsize + " and " + expectedsize + " are matched");

		} else {
			extTest.log(Status.INFO, "Failed " + actualsize + " and " + expectedsize + " are mismatched");


		}
	}
	//     This is myscroll method of the Actions class by this method we can scroll the page up to down
	public void myscroll(int x, int y) {
		try {
			Actions acobj = new Actions(driver);
			acobj.scrollByAmount(x, y).build().perform();
			extTest.log(Status.INFO, "Passed ! Scroll is done successfully using actions ");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	//   this scroll by javaScript by this method we can scroll the page up to down 
	public void scrollbyJavasript() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 1000);");
			extTest.log(Status.INFO, "Passed ! Scroll is done successfully using javascript");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !Scroll is not done successfully using javascript");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}
	}
	//     This is scrolltoelement with help of the  javaScript method we can scroll the page till the element
	public void scrolltoElement(WebElement we,String elementName) {
		//String elementName = we.getAccessibleName();
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", we);
			System.out.println("Passed ! Scroll is performed till+ "+elementName+"  successfully using javascript");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !Scroll is not performed till + "+ elementName+"  successfully using javascript");
			extTest.fail("Exception occurred: " + e.getMessage());
			//			extTest.fail("Exception occurred: " + e.getMessage(), 
			//                    MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
			//			extTest.log(Status.FAIL, e);

			throw e;
		}

	}
	//   This is isdisplayed method of the webElemet by this method we can verify that element is displayed on the page or not
	public void isDisplayed(WebElement we,String elementname) {

		try {
			boolean actual = we.isDisplayed();
			if (actual == true) {
				extTest.log(Status.INFO, "Passed ! this " + elementname + " is Visible on UI");

			} else {
				extTest.log(Status.FAIL, "Failed ! this " + elementname + " is not Visible on UI ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.FAIL, "Failed This " + elementname + " is not Visible on UI");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void isEnable(WebElement we, String elementname, String typeofPerform) {
		try {
			boolean actual = we.isEnabled();
			if (actual == true) {
				extTest.log(Status.INFO, "Pased ! this " + we + " is Enabled On UI");

			} else {
				extTest.log(Status.INFO, "Failed ! " + we + " is Disabled On UI");

			}

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! " + we + " is Disabled On UI");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}
	}


	public void isSelected(WebElement we,String elementName)
	{
		try {
			boolean actual = we.isSelected();
			if (actual == false) {
				extTest.log(Status.INFO, "Pased ! this " + elementName + " is Selected");

			} else {
				extTest.log(Status.INFO, "Failed ! " + elementName + " is not selected");

			}

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! " + elementName + " is not Selected");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}

	}

	public void ActionsScroll(WebElement we,String elementName) {
		try
		{
			Actions ac = new Actions(driver);
			ac.scrollToElement(we).build().perform();
			extTest.log(Status.INFO, "Actions scroll working here till " + elementName +"");

		}catch(Exception e)
		{
			e.printStackTrace();
			extTest.log(Status.FAIL, "Actions scroll working here till "+elementName+" ");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

		}


	}

	public void ActionsScroll2(int x, int y) {
		Actions ac = new Actions(driver);
		ac.scrollByAmount(x, y).build().perform();
		extTest.log(Status.INFO, "Actions scroll working here");

	}

	public void ActionsScroll3(ScrollOrigin we) {
		Actions ac = new Actions(driver);
		ac.scrollFromOrigin(we, 0, 0).build().perform();
		extTest.log(Status.INFO, "Actions scroll working here");

	}

	public boolean verifyboolean(boolean actual, String elementName, String typeofPreform) {
		if (actual == true) {
			extTest.log(Status.INFO, "Passed ! " + elementName + " is " + typeofPreform + " on UI");

		} else if (actual == false) {
			extTest.log(Status.INFO, "Passed ! " + elementName + " is " + typeofPreform + " on UI");

		}
		return actual;

	}

	public void verifyisSelected(WebElement we, String elementName) {
		try {
			boolean actualselected = we.isSelected();
			if (actualselected == false) {
				extTest.log(Status.INFO, "Failed !  " + elementName+ " is not selected");

			} else {
				extTest.log(Status.INFO, "Passed ! "+elementName+" is selected");

			}
		} catch (Exception e) {
			e.printStackTrace();
			//extTest.log(Status.FAIL, null);
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}

	}

	public void waitUntilElementIsvisible(By objB, int timeouts) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.visibilityOfElementLocated(objB));
		extTest.log(Status.INFO, "Passed ! waitUntilElementIsvisible worked properly");
	}

	public void waitUntilinVisibleElementOnUI(By objB, int timeouts) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.invisibilityOfElementLocated(objB));
		System.out.println("Passed ! waitUntilinVisibleElementOnUI worked properly");
	}

	public void waitUntilElementIsvisible(WebElement we, String elmentName, int timeouts) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.visibilityOf(we));
		extTest.log(Status.INFO, "Passed ! waitUntilElementIsvisible worked properly");

	}

	public void waitUntilElementIsEnable(WebElement we, String elmentName, int timeouts) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.elementToBeClickable(we));
		extTest.log(Status.INFO, "Passed ! waitUntilElementIsEnable worked properly");

	}

	public void waitUntilElementIsEnable(By objb, int timeouts) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.elementToBeClickable(objb));
		extTest.log(Status.INFO, "Passed !waitUntilElementIsEnable worked properly");
	}

	public void waitUntilElementIsDisable(By objb, int timeouts) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(objb)));
		extTest.log(Status.INFO, "Passed ! waitUntilElementIsDisable worked properly");

	}

	public void waitUntilElementIsDisable(WebElement we, String elementname, int timeouts) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(we)));
		extTest.log(Status.INFO, "Passed ! waitUntilElementIsDisable worked properly");

	}

	public void waitUntilElementTextChanged(By objb, String text, int timeouts) {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wt.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(objb, text)));
		extTest.log(Status.INFO, "Passed ! waitUntilElementTextChanged worked properly");

	}

	public void myclose() {
		try {
			driver.close();
			extTest.log(Status.INFO, "Passed !close worked successfully");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! close didn't worked successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void myquite() {
		try {
			driver.quit();
			extTest.log(Status.INFO, "Passed ! myquite worked successfully");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed  myquite didn't worked successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}

	}

	public void myclear(WebElement we, String elementname) {
		try {
			we.clear();
			extTest.log(Status.INFO, "Passed ! " + elementname + "'s element is clear successfully");

		} catch (StaleElementReferenceException e) {
			we.clear();
			System.out.println("Passed ! " + elementname + "'s element is cleared successfully");
		} catch (ElementNotInteractableException e) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].value = '';", we);
			extTest.log(Status.INFO, "Passed ! " + elementname + "'s element is cleared successfully");
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! " + elementname + "'s element is not cleared successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void switchtowindowTitle(String expectedTitle) {
		try {
			Set<String> handlevalue = driver.getWindowHandles();
			for (String eachvalue : handlevalue) {
				driver.switchTo().window(eachvalue);
				String gettite = getTitle();
				if (gettite.equalsIgnoreCase(expectedTitle)) {
					break;
				}
			}
			extTest.log(Status.INFO, "Passed ! It has been switched on window successfully through the getTitle");
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !It has not been switched on window successfully through the getTitle");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void switchtowindowContainsTitle(String expectedTitle) {
		try {
			Set<String> handlevalue = driver.getWindowHandles();
			for (String eachvalue : handlevalue) {
				driver.switchTo().window(eachvalue);
				String gettite = getTitle();
				if (gettite.contains(expectedTitle) == true) {
					break;
				}
			}
			extTest.log(Status.INFO,
					"Passed !It has been switched on window successfully through the getTitleContains");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO,
					"Failed ! It has not been switched on window successfully through the getTitleContains");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );



			throw e;
		}

	}

	public void switchtowidowUrl(String expectedurl) {
		try {
			Set<String> handlevalue = driver.getWindowHandles();
			for (String eachvalue : handlevalue) {
				driver.switchTo().window(eachvalue);
				String geturl = getCurrentURl();
				if (geturl.equalsIgnoreCase(expectedurl)) {
					break;
				}
			}
			extTest.log(Status.INFO, "Passed ! It has been switched on window successfully through the url");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! It has not been switched on window successfully through the url");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void switchtowidowContainsUrl(String expectedurl) {
		try {
			Set<String> handlevalue = driver.getWindowHandles();
			for (String eachvalue : handlevalue) {
				driver.switchTo().window(eachvalue);
				String geturl = getCurrentURl();
				if (geturl.contains(expectedurl)) {
					break;
				}
			}
			extTest.log(Status.INFO, "Passed !It has been switched on window successfully through the Containsurl");
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !It has not been switched on window successfully through the Containsurl");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void switchtoframeIndex(int index) {
		try {
			driver.switchTo().frame(index);
			extTest.log(Status.INFO,
					"Passed ! it has been switched into frame through the indexing successfully from frame");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO,
					"Failed !it has not been switched into frame through the indexing successfully from frame");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}
	}

	public void switchtoframeIdorName(String idorname) {
		try {
			driver.switchTo().frame((idorname));
			extTest.log(Status.INFO,
					"Passed ! it has been switched into frame through the webelement successfully from frame");

		} catch (Exception e) {
			e.printStackTrace();
			driver.switchTo().frame((idorname));
			extTest.log(Status.INFO,
					"Failed !it has not been switched into frame through IDorName successfully from frame");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );



			throw e;
		}
	}

	public void swicthtoframeWebElement(WebElement we, String elementName) {
		try {
			driver.switchTo().frame(we);
			extTest.log(Status.INFO,
					"Passed ! it has been switched into frame through the webelement successfully from frame");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO,
					"Failed it has not been switched into frame through the webelement successfully from frame");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}
	}

	public void switchoutFromeFrame() {
		try {
			driver.switchTo().defaultContent();
			extTest.log(Status.INFO, "Passed ! it has been got out successfully from frame");
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! it has been got out successfully from frame");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}
	}

	public void switchToParentFrame() {
		try {
			driver.switchTo().parentFrame();
			extTest.log(Status.INFO, "Passed !parentFrame method has worked properly");
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !parentFrame method has worked properly");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


		}

	}

	public void takeScreenshotofFullpage(String folderlocation) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		try {
			File soucrce = ts.getScreenshotAs(OutputType.FILE);
			Files.copy(soucrce, new File(folderlocation));
			extTest.log(Status.INFO, "Passed ! Screenshot has been taken of fullpage successfully");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed !Screenshot has been taken of full page successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );



			throw e;
		}

	}

	/**
	 * this method is used for TakeScreenshot of hole page
	 * 
	 * @param xpath
	 * @param elementName
	 * @param folderlocation
	 * @throws IOException
	 */
	public void takesScreenshotofparticularElement(WebElement we, String elementName, String folderlocation)
			throws IOException {
		try {
			File soucrce = we.getScreenshotAs(OutputType.FILE);
			Files.copy(soucrce, new File(folderlocation+".png"));
			extTest.log(Status.INFO, "Screenshot has been taken of " + elementName + " successfully");

		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Screenshot has been taken of " + elementName + " successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void popupaccept() {
		try {
			driver.switchTo().alert().accept();
			extTest.log(Status.INFO, "Passed !Popup has been accepted successfully");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! Popup has not been accepted successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public void popupdismissed() {
		try {

			driver.switchTo().alert().dismiss();
			extTest.log(Status.INFO, "Passed !Popup has been accepted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.INFO, "Failed ! Popup has not been accepted successfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );


			throw e;
		}

	}

	public String popupgetmessage() {
		String gettext=null;
		try {
			gettext = driver.switchTo().alert().getText();
			extTest.log(Status.PASS, "Get text is taken proper of popup");


		} catch (Exception e) {
			e.printStackTrace();
			extTest.log(Status.FAIL, "Get text is taken proper of popup");
			extTest.fail("Exception occurred: " + e.getMessage());


			throw e;
		}
		return gettext;

	}

	public void sendkeyswithEnter(String input)
	{
		Actions acobj=  new Actions(driver);
		acobj.sendKeys(input).sendKeys(Keys.ENTER).build().perform();
		extTest.log(Status.INFO, "Sendkeys with enter perform successfully");
	}

	public void verifypopupmessage(String expectedpopupmessage) {
		String actualpopupmessage = popupgetmessage();
		if (actualpopupmessage.equals(expectedpopupmessage)) {
			extTest.log(Status.INFO, "Passed! popup message is showing and right");
		} else {
			extTest.log(Status.INFO, "Failed! popup message is not showing and not right");

		}

	}



	public void checkAllChecksboxes(List<WebElement> listwe, String collectioElementName) {
		// List<WebElement> checkallboxes= myfindElements(xpath,collectioElementName);
		for (int i = 0; i < listwe.size(); i++) {
			WebElement webcheck = listwe.get(i);
			if (webcheck.isSelected() == false) {
				try {
					webcheck.click();
				} catch (ElementNotInteractableException e) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", webcheck);
				} catch (Exception e) {
					e.printStackTrace();
					String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
					extTest.fail("Exception occurred: " +exceptionMessage );


					throw e;
				}

			} else {
				extTest.log(Status.INFO, " it is already checked");
			}
		}
	}

	public void UncheckAllChecksboxes(List<WebElement> listwe, String collectioElementName) {
		// List<WebElement> checkallboxes= myfindElements(xpath,collectioElementName);
		for (int i = 0; i < listwe.size(); i++) {
			WebElement webcheck = listwe.get(i);
			if (webcheck.isSelected() == true) {
				try {
					webcheck.click();
				} catch (ElementNotInteractableException e) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click()", webcheck);
				} catch (Exception e) {
					e.printStackTrace();
					String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
					extTest.fail("Exception occurred: " +exceptionMessage );

					throw e;
				}

			} else {
				extTest.log(Status.INFO, " it is already unchecked");
			}
		}
	}

	public int getTableColumnCount(List<WebElement> tablelistwe, String elementName) {
		int tablecolumnCount = tablelistwe.size();
		extTest.log(Status.INFO, "getTableColumnCount");
		return tablecolumnCount;

	}

	public int getTableRowCount(List<WebElement> tablelistwe, String elementName) {

		int rowCount = tablelistwe.size() - 1;
		extTest.log(Status.INFO, "getTableRowCount");
		return rowCount;
	}

	public int getTableColumnHeaderCount(List<WebElement> tablelistwe, String elementName) {
		int columnCount = tablelistwe.size();
		extTest.log(Status.INFO, "getTableColumnHeaderCount");
		return columnCount;
	}

	/* in this method we want all the column header names in a list<String> */
	public List<String> getTableColumnNamesList(List<WebElement> tablewelist, String elementName) {
		List<WebElement> weListColumns = driver.findElements(By.xpath(tablewelist + "//tr[1]//td"));
		List<String> listColumnNames = new ArrayList<String>();
		int columnCount = weListColumns.size();
		for (int i = 0; i <= columnCount - 1; i++) {
			WebElement weColumnHeader = weListColumns.get(i);
			String columnName = weColumnHeader.getText();
			listColumnNames.add(columnName);
		}
		extTest.log(Status.INFO, "getTableColumnNamesList");
		return listColumnNames;
	}

	/* this method returns column number on the basis of column name */
	public int getColumnNumberByColumnName(List<WebElement> tablelistwe, String tableName, String columnName) {
		int columnNumber = -1;
		int columnCount = tablelistwe.size();
		for (int i = 0; i <= columnCount - 1; i++) {
			WebElement weTableColumn = tablelistwe.get(i);
			String tablColumnName = weTableColumn.getText();
			if (tablColumnName.equalsIgnoreCase(columnName) == true) {
				columnNumber = i;
				break;
			}
		}
		extTest.log(Status.INFO, "getColumnNumberByColumnName");

		return columnNumber;

	}










	/* this method returns row data in list on the basis of row number */
	public List<String> getRowDataListByRowNumber(List<WebElement> tablelistwe, String tableName, int rowNumber) {
		List<String> rowDataList = new ArrayList<String>();
		for (int i = 0; i <= tablelistwe.size() - 1; i++) {
			WebElement weRowData = tablelistwe.get(i);
			String data = weRowData.getText();
			rowDataList.add(data);
		}
		extTest.log(Status.INFO, "getRowDataListByRowNumber");
		return rowDataList;
	}

	public List<String> getColumnDataListByColumnNumber(List<WebElement> tablelistwe, String tableName,
			int columnNumber) {
		List<String> columnNameList = new ArrayList<>();
		for (int i = 1; i < tablelistwe.size(); i++) {
			String columnName = tablelistwe.get(i).getText();
			columnNameList.add(columnName);
		}
		extTest.log(Status.INFO, "getColumnDataListByColumnNumber");
		return columnNameList;
	}

	public void verifyinnertext(WebElement we, String expectedValue) {
		String actualvalue = we.getText();

		System.out.println(actualvalue);
		if (actualvalue.equals(expectedValue)) {
			extTest.log(Status.PASS,
					"Passed! actual " + actualvalue + " && expectedvalue" + expectedValue + " are matched");
		} else {
			extTest.log(Status.FAIL,
					"Failed! actual " + actualvalue + " && expectedvalue" + expectedValue + " are not matched");
			System.out.println();
		}
		Assert.assertEquals(actualvalue, expectedValue);
	}

	public void verifyatrributevalue(WebElement we, String expectedValue, String acttributevalue) {
		String actualvalue = we.getAttribute(acttributevalue);
		if (actualvalue.equals(expectedValue)) {
			extTest.log(Status.PASS,
					"Passed! actual " + actualvalue + " && expectedvalue" + expectedValue + " are matched");
		} else {
			extTest.log(Status.INFO,
					"Failed! actual " + actualvalue + " && expectedvalue" + expectedValue + " are not matched");
		}
		Assert.assertEquals(actualvalue, expectedValue);

	}

	public void verifygetTitle(String expectedTitle) {
		String actualtitle = driver.getTitle();
		if (actualtitle.equals(expectedTitle)) {
			extTest.log(Status.PASS,
					"Passed! actual " + actualtitle + " expectedtitle" + expectedTitle + " are matched");
		} else {
			extTest.log(Status.FAIL,
					"Failed! actual " + actualtitle + " expectedtitle" + expectedTitle + " are not matched");
		}
		Assert.assertEquals(actualtitle, expectedTitle);

	}

	public void verifyIsDisplayed(WebElement we) {
		boolean actual = we.isDisplayed();
		if (actual == true) {
			extTest.log(Status.PASS, "Passed ! " + actual + " is displayed on UI");
		} else {
			extTest.log(Status.FAIL, "Failed ! " + actual + " is not displayed on UI");
		}
		Assert.assertEquals(actual, true);

	}

	public void verifyIsSelected(WebElement we) {
		boolean actual = we.isSelected();
		if (actual == true) {
			extTest.log(Status.PASS, "Passed ! " + actual + " is selected on UI");
		} else {
			extTest.log(Status.FAIL, "Failed ! " + actual + " is not selected on UI");
		}
		Assert.assertEquals(actual, true);

	}

	public void verifyIsEnabled(WebElement we) {
		boolean actual = we.isEnabled();
		if (actual == true) {
			extTest.log(Status.PASS, "Passed ! " + actual + " is selected on UI");
		} else {
			extTest.log(Status.FAIL, "Failed ! " + actual + " is not selected on UI");
		}
		Assert.assertEquals(actual, true);

	}



	public String takeSnapshot(String testcaseName) {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File snapshotSourceFileObj = tss.getScreenshotAs(OutputType.FILE);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh_mm_ss a");
		String timeStamp = df.format(new Date());

		// Define the destination file path
		File snapshotDestinationFileObj = new File("FailScreenshot/" + testcaseName + timeStamp + ".png");

		// Check if an old screenshot for this test case exists and delete it
		File directory = new File("FailScreenshot/");
		File[] files = directory.listFiles((dir, name) -> name.startsWith(testcaseName) && name.endsWith(".png"));
		if (files != null) {
			for (File file : files) {
				file.delete(); // Delete each old screenshot file with the same testcaseName prefix
			}
		}

		try {
			Files.copy(snapshotSourceFileObj, snapshotDestinationFileObj);
		} catch (IOException e) {
			e.printStackTrace();
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

		}
		return snapshotDestinationFileObj.getAbsolutePath();
	}


	public void selectDropdownOptionsMultipleClick(By dropdownLocator, String[] options) {
		try {
			WebElement dropdown = driver.findElement(dropdownLocator);
			dropdown.click();											 // Open the dropdown

			Robot robot = new Robot();

			for (String option : options) {
				// Navigate to the dropdown menu
				robot.keyPress(KeyEvent.VK_DOWN); 					// Move down in the list
				robot.keyRelease(KeyEvent.VK_DOWN);

				// You may need to add a delay if the dropdown takes time to populate options
				//   Thread.sleep(500);
				myimplicitlyWait(10);
				// Type the option to select it (some dropdowns support typing)
				for (char c : option.toCharArray()) {
					robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
					robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
				}

				// Press Enter to select the option
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

				// Optional: Add a delay to allow for selection to complete
				//  Thread.sleep(1000);
				myimplicitlyWait(10);
			}
		} catch (Exception e) {
			System.out.println("Error selecting dropdown options: " + e.getMessage());
		}
	}

	public void fileupload(WebElement we, String filepath)
	{
		String elemenname=	  we.getAccessibleName();
		try
		{
			we.sendKeys(filepath);
			extTest.log(Status.INFO, "file uploaded success of "+ elemenname);
		}
		catch(ElementNotInteractableException e)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String script = "var target = arguments[0], offsetX = arguments[1], offsetY = arguments[2], document = target.ownerDocument || document, window = document.defaultView || window;" +
					"var input = document.createElement('INPUT');" +
					"input.type = 'file';" +
					"input.style.display = 'none';" +
					"input.onchange = function () {" +
					"var rect = target.getBoundingClientRect(), x = rect.left + (offsetX || (rect.width >> 1)), y = rect.top + (offsetY || (rect.height >> 1)), dataTransfer = { files: this.files };" +
					"['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
					"var evt = document.createEvent('MouseEvent');" +
					"evt.initMouseEvent(name, true, true, window, 0, 0, 0, x, y, false, false, false, false, 0, null);" +
					"evt.dataTransfer = dataTransfer;" +
					"target.dispatchEvent(evt);" +
					"});" +
					"};" +
					"document.body.appendChild(input);" +
					"return input;";

			WebElement input = (WebElement) js.executeScript(script, we, 0, 0);

			input.sendKeys(Paths.get(filepath).toAbsolutePath().toString());
			extTest.log(Status.INFO, "file uploaded successfully using javascript of "+ elemenname);

		}catch(Exception e)
		{
			e.printStackTrace();
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

		}

	}



	public  void goToBackPage() {
		try {
			driver.navigate().back();
			System.out.println("the Page has been backed sucessfully");
			//("the Page has been backed sucessfully");
		}catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code to go back
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.history.back();");
			System.out.println("the Page has been backed sucessfully");

			//printMessage("the Page has been backed sucessfully");
		}catch(WebDriverException e) {

			//WebDriverException:-----WebDriverException can be thrown for various reasons, such as invalid URL, 
			//                        network issues, browser crashes, or any other unexpected 
			//	                      errors during the navigation.......
			e.printStackTrace();
			System.out.println("the Page hasn't been backed sucessfully");
			extTest.fail("Exception occurred: " + e.getMessage());
			//			extTest.fail("Exception occurred: " + e.getMessage(), 
			//                    MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
			//			extTest.log(Status.FAIL, e);

			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("the Page hasn't been backed sucessfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}
	}

	public  void goToForwardPage() {
		try {
			driver.navigate().forward();
			//printMessage("the Page has been forwarded sucessfully");
		}catch (ElementNotInteractableException e) {
			// Use JavascriptExecutor to execute JavaScript code to go forward
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.history.forward();");
			//printMessage("the Page has been forwarded sucessfully");
		}catch(WebDriverException e) {

			//WebDriverException:-----WebDriverException can be thrown for various reasons, such as invalid URL, 
			//                        network issues, browser crashes, or any other unexpected 
			//	                      errors during the navigation.......
			e.printStackTrace();
			//printMessage("the Page hasn't been forwarded sucessfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			//printMessage("the Page hasn't been forwarded sucessfully");
			String exceptionMessage = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
			extTest.fail("Exception occurred: " +exceptionMessage );

			throw e;
		}
	}

	public void handlesuggesstionDropdown(WebElement we,String input,String gettext) throws InterruptedException{
		String elementname=	we.getAccessibleName();

		// Focus on the input or dropdown
		try
		{
			we.click(); 	
			extTest.log(Status.INFO, "click is perform "+ elementname);
		}catch(ElementClickInterceptedException e)
		{
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);	
			extTest.log(Status.INFO, "click is perform using javascript of "+ elementname);


		}catch(ElementNotInteractableException e)
		{
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);
			extTest.log(Status.INFO, "click is perform using javascript of "+ elementname);


		}catch(StaleElementReferenceException e)
		{
			we.click(); 
			extTest.log(Status.INFO, "click is perform "+ elementname);
			e.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();	
		}

		try
		{
			we.sendKeys(input);
			Thread.sleep(3000);
			extTest.log(Status.INFO, "value send successfully in  "+ elementname);

		}catch (ElementClickInterceptedException e) {
			Thread.sleep(3000);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value=\"" + input + "\"", we);
			extTest.log(Status.INFO, "value send successfully in  "+ elementname+ " using javascript");

		}catch(ElementNotInteractableException e)
		{
			Thread.sleep(3000);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value=\"" + input + "\"", we); 
			extTest.log(Status.INFO, "value send successfully in  "+ elementname+ " using javascript");

		}catch(Exception e)
		{
			e.printStackTrace();  
		}

		Thread.sleep(5000);  
		WebElement dropdownOptions = driver.findElement(By.xpath("//ul[@role='listbox']"));  
		java.util.List<WebElement> options = dropdownOptions.findElements(By.tagName("li"));  
		for (WebElement option : options) {
			if (option.getText().equals(gettext)) {
				JavascriptExecutor js11 = (JavascriptExecutor) driver;
				js11.executeScript("arguments[0].click();", option);  
				extTest.log(Status.INFO, "suggession dropdown handled successfully");
				break;
			}
		}
	}







	public  void calenderhandle(WebElement clickdatepicker,WebElement allelement,WebElement monthyeartitle,WebElement clicknextbutton,String Month,String Year,WebElement clickondate)
	{

		try
		{
			clickdatepicker.click();
			extTest.log(Status.INFO," click is performed on datepicker successfully" );

		}catch(ElementClickInterceptedException e)
		{
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", clickdatepicker);
			extTest.log(Status.INFO," click is performed on datepicker successfully using javascript" );

		}
		waitvisibilityofAllelements(allelement);
		String monthyear=	monthyeartitle.getText();
		String month= monthyear.split(" ")[0].trim();
		String year=monthyear.split(" ")[1].trim();
		while(!(month.equals(Month) && year.equals(Year)))
		{
			try
			{
				clicknextbutton.click();
				//extTest.log(Status.INFO, "click is performed on next button");
			}catch(StaleElementReferenceException e)
			{
				clicknextbutton.click();
				//extTest.log(Status.INFO, "click is performed on next button");


			}catch(ElementClickInterceptedException e)
			{
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click()", clicknextbutton);
				//extTest.log(Status.INFO, "click is performed on next button");

			}

			monthyear=	monthyeartitle.getText();
			month= monthyear.split(" ")[0].trim();
			year=monthyear.split(" ")[1].trim();
		}
		try
		{
			clickondate.click();
			extTest.log(Status.INFO, "Date selected successfully ");
		}catch(ElementNotInteractableException e)
		{
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", clickondate);
			extTest.log(Status.INFO, "Date selected successfully using javascript ");
		}catch(StaleElementReferenceException e)
		{
			clickondate.click();
			extTest.log(Status.INFO, "Date selected successfully ");
		}catch(Exception e)
		{
			e.printStackTrace();

		}


	}



	public void selectDate(
			String targetDateStr,            // Format: dd-MM-yyyy
			WebElement nextBtn,
			WebElement prevBtn,
			WebElement monthYearLabel,
			List<WebElement> dayCells) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate targetDate = LocalDate.parse(targetDateStr, formatter);

		// Open the date picker


		// Navigate to the correct month and year
		while (true) {
			String displayedMonthYear = monthYearLabel.getText();
			DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
			YearMonth displayed = YearMonth.parse(displayedMonthYear, monthYearFormatter);
			YearMonth target = YearMonth.from(targetDate);

			if (displayed.isBefore(target)) {
				nextBtn.click();
			} else if (displayed.isAfter(target)) {
				prevBtn.click();
			} else {
				break;
			}

			// Re-fetch the label and buttons if DOM changes
			monthYearLabel = driver.findElement(By.cssSelector(".ui-datepicker-title"));
			nextBtn = driver.findElement(By.cssSelector(".ui-datepicker-next"));
			prevBtn = driver.findElement(By.cssSelector(".ui-datepicker-prev"));
		}

		// Re-fetch day cells after month navigation
		dayCells = driver.findElements(By.cssSelector("td[data-handler='selectDay'] a"));
		for (WebElement day : dayCells) {
			if (day.getText().equals(String.valueOf(targetDate.getDayOfMonth())) && day.isEnabled()) {
				day.click();
				break;
			}
		}
	}









	public  boolean downloadFile(String downloadFilepath,String expectedFileName,String elementname) {
		try {

			//myclick(we, elementname);
			// Wait for file to download
			Thread.sleep(5000); // You can replace this with a more sophisticated wait (e.g., WebDriverWait)
			// Check if file is downloaded
			return isFileDownloaded(downloadFilepath, expectedFileName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

	// Method to check if file is downloaded
	public  boolean isFileDownloaded(String downloadFilepath, String expectedFileName) {
		File dir = new File(downloadFilepath);
		File[] dirContents = dir.listFiles();

		if (dirContents != null) {
			for (File file : dirContents) {
				if (file.getName().contains(expectedFileName)) {

					return true;
				}
			}
		}
		return false;
	}





	public String generateMobileNumber() {
		Random random = new Random();

		// Ensure the first digit is not zero
		int firstDigit = random.nextInt(9) + 1;

		// Generate the remaining 9 digits
		StringBuilder mobileNumber = new StringBuilder();
		mobileNumber.append(firstDigit);
		for (int i = 0; i < 9; i++) {
			mobileNumber.append(random.nextInt(10)); // Random digit between 0 and 9
		}

		return mobileNumber.toString();
	}

	public  String generateIndianMobileNumber() {
		Random random = new Random();

		// Start with a valid first digit (6, 7, 8, or 9)
		int firstDigit = 6 + random.nextInt(4); // Generates a number between 6 and 9

		// Generate the remaining 9 digits
		StringBuilder mobileNumber = new StringBuilder();
		mobileNumber.append(firstDigit);
		for (int i = 0; i < 9; i++) {
			mobileNumber.append(random.nextInt(10)); // Random digit between 0 and 9
		}

		return mobileNumber.toString();
	}


	public int getTableRowCount(String tableXpath, String elementName) {
		int countRow = -1;
		try {
			List<WebElement> webList = myFindElements(tableXpath + "//tr");

			if (!webList.isEmpty()) {
				countRow = webList.size();
				printmethod("Total row count of table '" + elementName + "' is = " + countRow);
			} else {
				printmethod("List is empty, no elements found in the table: " + elementName);
			}
		} catch (Exception e) {
			printmethod("Error while counting rows in table: " + elementName);
			e.printStackTrace();
		}
		return countRow;
	}


	public List<WebElement> myFindElements(String string) {
		List<WebElement> listofrow=	driver.findElements(By.xpath(string));
		return listofrow;
	}


	//	////////////getTableColumnCount  \\\\\\\\\\
	//	/**
	//	* this method is used to obtain column count of the table as a int.
	//	* @param tableXpath
	//	*        with the help table xpath we can find those element that comes under the table
	//	*        as multiple column list name*/

	public  int getTableColumnHeaderCount(String tableXpath,String elementName) {

		List<WebElement> webListColumns=myFindElements(tableXpath+"//tr[1]//td");
		int countColumn=-1;
		if (webListColumns.isEmpty()==false) {
			try {
				countColumn=webListColumns.size();
				printmethod("total column count of "+elementName+"  table is ="+countColumn);
			} catch (Exception e) {
				e.printStackTrace();
				printmethod("total column count of "+elementName+"  table is ="+countColumn);
				throw e;
			}

		} else {
			printmethod("List is empty there is no any element");
		}
		return  countColumn;
	}


	//	////////////getTableColumnNamesList  \\\\\\\\\\
	//
	//	/**
	//	* this method is used to obtain column name of table as a list
	//	* 
	//	* @param tableXpath
	//	*        with the help table xpath we can find those element that comes under the table
	//	*        as multiple column list name 
	//	* 
	//	* @param elementName
	//	*        this shows in which element we are working and print a proper 
	//	*        message in the console. 
	//	* 
	//	* @return it will return list of webElements
	//	*/
	public   List<String> getTableColumnNamesList(String tableXpath, String elementName) {

		List<WebElement> weListColumns=myFindElements(tableXpath);
		String columnName="";
		List<String> listColumnNames =new ArrayList<>();
		if (weListColumns.isEmpty()==false) {
			printmethod("List is not empty there are available multiple elements");

			try {
				for (int i = 0; i < weListColumns.size(); i++) {
					WebElement weColumnHeader	=weListColumns.get(i);
					columnName=weColumnHeader.getText();
					listColumnNames.add(columnName);
				}
				printmethod("the list of column name is found from the table successfully");

			} catch (Exception e) {
				e.printStackTrace();
				printmethod("the list of column name is not found from the table successfully");
				throw e;
			}

		} else {
			printmethod("List is empty there is no any element");
		}
		return listColumnNames;
	}
	//////////////	     getColumnHeaderNumberByColumnName  \\\\\\\\\\
	//
	//	/**
	//	* @param tableXpath* @param tableName* @param columnName* @return */
	public  int getColumnHeaderNumberByColumnName(String tableXpath,String tableName,String columnName) {
		List<WebElement> columnNamesList=myFindElements(tableXpath+"//tr[1]//td");
		int columnNumber=-1;
		if (columnNamesList.isEmpty()==false) {
			printmethod("List is not empty there are available multiple elements");
			try {
				for (int i = 0; i < columnNamesList.size(); i++) {
					WebElement webColumnNames=columnNamesList.get(i);
					String actcolumnName =webColumnNames.getText();
					if (actcolumnName.equalsIgnoreCase(columnName) ) {
						columnNumber=i+1;
						break;
					} 
				}
				printmethod("column header number of the list is found by column name so column header number="+columnNumber);
			} catch (StaleElementReferenceException e) {
				columnNamesList=myFindElements(tableXpath+"//tr[1]//td");
				for (int i = 0; i < columnNamesList.size(); i++) {
					WebElement webColumnNames=columnNamesList.get(i);
					String actcolumnName =webColumnNames.getText();
					if (actcolumnName.equalsIgnoreCase(columnName) ) {
						columnNumber=i+1;
						break;
					} 
				}
				printmethod("column header number of the list is found by column name so column header number="+columnNumber);
				return columnNumber;
			}catch (ElementNotInteractableException e) {
				columnNamesList=myFindElements(tableXpath+"//tr[1]//td");
				// Use JavascriptExecutor to execute JavaScript code and get the inner text
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				for (int i = 0; i < columnNamesList.size(); i++) {
					WebElement webColumnNames=columnNamesList.get(i);
					String script="return arguments[0].innerText;";
					String actcolumnName = (String) jsExecutor.executeScript(script, webColumnNames);
					if (actcolumnName.equalsIgnoreCase(columnName) ) {
						columnNumber=i+1;
						break;
					}
				}
				printmethod("column header number of the list is found by column name so column header number ="+columnNumber);
				return columnNumber;
			}catch (Exception e) {
				e.printStackTrace();
				printmethod("column header number of the list is not found by column name successfully");
			}
		} else {
			printmethod("List is empty there are not available multiple elements");
		}
		return columnNumber;
	}
	//
	//
	//	////////////   myInnerText  \\\\\\\\\\
	//	/**
	//	 * The myInnerText() method is commonly used to find the inner text
	//	 * of any element.
	//	 * @param xpath        it is used to find element which we have to perform operation.        
	//	 * @param elementName       this shows in which element we are working and print a proper 
	//	 *        message in the console.  */
	public  String myInnerText(WebElement webObj) {
		String innerText=null;
		if(webObj.isDisplayed() &&webObj.isEnabled()) {
			printmethod("Element is displayed and enabled");
			try {
				innerText=webObj.getText();
				printmethod("Inner Text :-"+innerText +" is Found Successfully");
			}catch (ElementNotInteractableException e) {
				// Use JavascriptExecutor to execute JavaScript code and get the inner text
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				String script = "return arguments[0].innerText;";
				innerText = (String) jsExecutor.executeScript(script, webObj);
				printmethod("Inner Text :-"+innerText +" is Found Successfully");
				return innerText;
			}catch(StaleElementReferenceException e) {

				innerText=webObj.getText();
				printmethod("Inner Text :-"+innerText +" is Found Successfully");
			}
			catch(Exception e) {
				e.printStackTrace();
				printmethod("Inner Text :-"+innerText +" is not Found Successfully");
				throw e;
			}
		}else {
			printmethod("Element is displayed and enabled");
		}
		return innerText;
	}
	//
	//						////////////getXAndYOffSet  \\\\\\\\\\
	//	/** The getXAndYOffSet() method is commonly used to find the location
	//	* of any element that means x offset and y offset.
	//	* @param xpath
	//	*        it is used to find element which we have to perform operation.
	//	* @param elementName
	//	*        this shows in which element we are working and print a proper message in the console. 
	//	* @return it returns the object of Point class.     */
	public  Point getXAndYOffSet(WebElement webObj) {

		Point location=null;
		if(webObj.isDisplayed() &&webObj.isEnabled()) {
			printmethod("Element is displayed and enabled");
			try {
				location=webObj.getLocation();
				printmethod("the location of the element is found successfully");
			}catch(StaleElementReferenceException e) {

				location=webObj.getLocation();
				printmethod("the location of the element is found successfully");
			}
			catch(Exception e) {
				e.printStackTrace();
				printmethod("the location of the element is not found successfully");
				throw e;
			}
		}else {
			printmethod("Element is displayed and enabled");
		}
		return location;
	}
	//	////////////getHeightAndWidth  \\\\\\\\\\
	//	/*** The getHeightAndWidth() method is commonly used to find height and width  of any element.
	//	 * @param xpath *        it is used to find element which we have to perform operation.
	//	 * @param elementName      this shows in which element we are working and print a proper message in the console. 
	//	 * @return        it returns the object of Dimension class. */
	public  Dimension getHeightAndWidth(WebElement webObj) {

		Dimension size=null;
		if(webObj.isDisplayed() &&webObj.isEnabled()) {
			printmethod("Element is displayed and enabled");
			try {
				size=webObj.getSize();
				printmethod("the size of the element is found successfully");
			}catch(StaleElementReferenceException e) {

				size=webObj.getSize();
				printmethod("the size of the element is found successfully");
			}
			catch(Exception e) {
				e.printStackTrace();
				printmethod("the size of the element is not found successfully");
				throw e;
			}
		}else {
			printmethod("Element is displayed and enabled");
		}
		return size;
	}

	///      ===========    Verification generic method    ==============





	public void verifyElementIsDisplayed(WebElement we, String elementName) {
		try {
			if (we != null && we.isDisplayed()) {
				extTest.log(Status.INFO, elementName + " is displayed on UI");
			} else {
				extTest.log(Status.FAIL, elementName + " is not displayed on UI");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking display status of " + elementName + ": " + e.getMessage());
			e.printStackTrace();
		}
	}


	public void verifyElementisEnabled(WebElement we, String elementName)
	{
		try {
			if (we != null && we.isEnabled()) {
				extTest.log(Status.INFO, elementName + " is enabled on UI");
			} else {
				extTest.log(Status.FAIL, elementName + " is not enabled on UI");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking enable status of " + elementName + ": " + e.getMessage());
			e.printStackTrace();
		}	
	}

	public void verifyCheckboxIsSelected(WebElement checkbox, String elementName) {
		try {
			if (checkbox != null && checkbox.isSelected()) {
				extTest.log(Status.PASS, elementName + " checkbox is selected.");
			} else {
				extTest.log(Status.FAIL, elementName + " checkbox is NOT selected.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking if checkbox is selected: " + e.getMessage());
		}
	}

	public void verifyCheckboxIsNotSelected(WebElement checkbox, String elementName) {
		try {
			if (checkbox != null && !checkbox.isSelected()) {
				extTest.log(Status.PASS, elementName + " checkbox is not selected.");
			} else {
				extTest.log(Status.FAIL, elementName + " checkbox is selected.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking if checkbox is not selected: " + e.getMessage());
		}
	}

	public void verifyCheckboxIsEnabled(WebElement checkbox, String elementName) {
		try {
			if (checkbox != null && checkbox.isEnabled()) {
				extTest.log(Status.PASS, elementName + " checkbox is enabled.");
			} else {
				extTest.log(Status.FAIL, elementName + " checkbox is disabled.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking if checkbox is enabled: " + e.getMessage());
		}
	}

	public void verifyCheckboxIsDisabled(WebElement checkbox, String elementName) {
		try {
			if (checkbox != null && !checkbox.isEnabled()) {
				extTest.log(Status.PASS, elementName + " checkbox is disabled.");
			} else {
				extTest.log(Status.FAIL, elementName + " checkbox is enabled.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking if checkbox is disabled: " + e.getMessage());
		}
	}


	public void verifyToggleButtonIsOff(WebElement toggle, String elementName) {
		try {
			if (toggle != null && !toggle.isSelected()) {
				extTest.log(Status.PASS, elementName + " toggle button is OFF.");
			} else {
				extTest.log(Status.FAIL, elementName + " toggle button is ON.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking toggle OFF: " + e.getMessage());
		}
	}

	public void verifyToggleButtonIsOn(WebElement toggle, String elementName) {
		try {
			if (toggle != null && toggle.isSelected()) {
				extTest.log(Status.PASS, elementName + " toggle button is ON.");
			} else {
				extTest.log(Status.FAIL, elementName + " toggle button is OFF.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking toggle ON: " + e.getMessage());
		}
	}

	public void verifyToggleButtonIsDisplayed(WebElement toggle, String elementName) {
		try {
			if (toggle != null && toggle.isDisplayed()) {
				extTest.log(Status.PASS, elementName + " toggle button is displayed.");
			} else {
				extTest.log(Status.FAIL, elementName + " toggle button is NOT displayed.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking toggle display: " + e.getMessage());
		}
	}

	public void verifyToggleButtonIsNotDisplayed(WebElement toggle, String elementName) {
		try {
			if (toggle == null || !toggle.isDisplayed()) {
				extTest.log(Status.PASS, elementName + " toggle button is NOT displayed.");
			} else {
				extTest.log(Status.FAIL, elementName + " toggle button is displayed.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking toggle not displayed: " + e.getMessage());
		}
	}

	public void verifyToggleButtonIsEnabled(WebElement toggle, String elementName) {
		try {
			if (toggle != null && toggle.isEnabled()) {
				extTest.log(Status.PASS, elementName + " toggle button is enabled.");
			} else {
				extTest.log(Status.FAIL, elementName + " toggle button is disabled.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking toggle enabled: " + e.getMessage());
		}
	}

	public void verifyToggleButtonIsDisabled(WebElement toggle, String elementName) {
		try {
			if (toggle != null && !toggle.isEnabled()) {
				extTest.log(Status.PASS, elementName + " toggle button is disabled.");
			} else {
				extTest.log(Status.FAIL, elementName + " toggle button is enabled.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking toggle disabled: " + e.getMessage());
		}
	}


	public void verifyRadioButtonIsDisplayed(WebElement radio, String elementName) {
		try {
			if (radio != null && radio.isDisplayed()) {
				extTest.log(Status.PASS, elementName + " radio button is displayed.");
			} else {
				extTest.log(Status.FAIL, elementName + " radio button is NOT displayed.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking radio display: " + e.getMessage());
		}
	}

	public void verifyRadioButtonIsSelected(WebElement radio, String elementName) {
		try {
			if (radio != null && radio.isSelected()) {
				extTest.log(Status.PASS, elementName + " radio button is selected.");
			} else {
				extTest.log(Status.FAIL, elementName + " radio button is NOT selected.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking radio selected: " + e.getMessage());
		}
	}

	public void verifyRadioButtonIsNotSelected(WebElement radio, String elementName) {
		try {
			if (radio != null && !radio.isSelected()) {
				extTest.log(Status.PASS, elementName + " radio button is NOT selected.");
			} else {
				extTest.log(Status.FAIL, elementName + " radio button is selected.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking radio not selected: " + e.getMessage());
		}
	}

	public void verifyRadioButtonIsEnabled(WebElement radio, String elementName) {
		try {
			if (radio != null && radio.isEnabled()) {
				extTest.log(Status.PASS, elementName + " radio button is enabled.");
			} else {
				extTest.log(Status.FAIL, elementName + " radio button is disabled.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking radio enabled: " + e.getMessage());
		}
	}

	public void verifyRadioButtonIsDisabled(WebElement radio, String elementName) {
		try {
			if (radio != null && !radio.isEnabled()) {
				extTest.log(Status.PASS, elementName + " radio button is disabled.");
			} else {
				extTest.log(Status.FAIL, elementName + " radio button is enabled.");
			}
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking radio disabled: " + e.getMessage());
		}
	}

	public void verifyElementIsNotDisplayed(WebElement element, String elementName) {
		try {
			if (element == null || !element.isDisplayed()) {
				extTest.log(Status.PASS, elementName + " is NOT displayed on UI.");
			} else {
				extTest.log(Status.FAIL, elementName + " is unexpectedly displayed on UI.");
			}
		} catch (NoSuchElementException e) {
			extTest.log(Status.PASS, elementName + " is NOT displayed (NoSuchElementException caught).");
		} catch (Exception e) {
			extTest.log(Status.FAIL, "Exception while checking not displayed status of " + elementName + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void verifydefaultValueeditbox(WebElement we,String textfieldvalue,String elementname)
	{
		String actualvalue=	GetEditboxValue(we, elementname);
		if(actualvalue.contains(textfieldvalue))
		{
			extTest.log(Status.INFO, textfieldvalue +" is present in  "+elementname);
		}else
		{
			extTest.log(Status.FAIL, textfieldvalue +" is not present in  "+elementname);

		}
	}


	public void getCurrentDate() {
		Date date = new Date();
		String currentDate = date.toString().replace(" ", "_").replace(":", "_");
		System.out.println(currentDate);
	}


	public String getCurrentDateAndTime() {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy_mm_dd_hh_mm_ss");
		Date Objdate = new Date();
		String date = simpleDateformat.format(Objdate);
		return date;

	}

	public void dragAndDrop2(WebElement source, WebElement destination) {
		Actions actions = new Actions(driver);
		if (source.isDisplayed() && source.isEnabled()) {
			actions.clickAndHold(source).moveToElement(source).release(destination).build().perform();
		}

	}


	public String captureScreenshotBase64() {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String base64 = screenshot.getScreenshotAs(OutputType.BASE64);
		System.out.println("Screenshot shot saved succesfully");

		return base64;

	}

	public void takeScreenshotUsingRobotClassRectanleArea(int x, int y, int width, int Height) {
		String date = getCurrentDateAndTime();
		String imgName = "robot+date";

		try {
			Robot robot = new Robot();
			Rectangle reatangle = new Rectangle(x, y, width, Height);
			BufferedImage srcImage = robot.createScreenCapture(reatangle);
			ImageIO.write(srcImage, "PNG", new File("screenShot\\" + imgName + ".png"));
		} catch (AWTException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void takeScreenshotRobotFullPage() {
		String date = getCurrentDateAndTime();
		String imgName = "robot+date";

		try {
			Robot robot = new Robot();

			java.awt.Dimension d=	Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rectangle = new Rectangle(d);
			BufferedImage srcFile = robot.createScreenCapture(rectangle);
			ImageIO.write(srcFile, "PNG", new File("screenShot\\" + imgName + ".png"));

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void ScrollingUsingMouseWheel(int number) {
		try {
			Robot robot = new Robot();
			robot.mouseWheel(number);
			// Neeche Aane ke liye positive Number Upar Jaane ke liye negative Number
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public void dropDownWithoutSelectClassTag(WebElement we, String selectvalue) {
		while (true) {
			try {
				Robot rb = new Robot();
				rb.delay(2);
				rb.keyPress(KeyEvent.VK_DOWN);
				rb.keyRelease(KeyEvent.VK_DOWN);
				rb.delay(2);
				String value = we.getText();
				if (value.contains(selectvalue)) {
					rb.keyPress(KeyEvent.VK_ENTER);
					rb.keyRelease(KeyEvent.VK_ENTER);
					rb.delay(2);
					break;
				}

			} catch (AWTException e) {
				e.printStackTrace();
			}

		}
		//testLogger.log(Status.PASS, "value is selected from the drop down  " + selectvalue);

	}



	//////////////Java Script Executor////////////////
	public void javaScriptClick(WebElement we) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", we);
		System.out.println(" clicked by java script");
	}

	public void javaScriptSendKeys(WebElement we, String inputValue) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		we.clear();
		jse.executeAsyncScript("arguments[0].value='" + inputValue + "'", we);
		System.out.println("value is Inputed successfully");
		// isase date agar value me diya hoto send kar sakte hai

	}

	public String javaScriptPageTitle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String titleOfPage = js.executeScript("return document.title").toString();
		return titleOfPage;
	}

	public String javaScriptGetURL() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String urlOfPage = js.executeScript("return document.URL").toString();
		return urlOfPage;
	}

	public void jsSendKeyWithAttributeValue(WebElement we, String text) {

	}

	public void javaScriptRefresh() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("hisotry.go(0)");
	}

	public void scrollPageUntilElementIsFound(WebElement we) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", we);

	}

	public void scrollPageByNumbers(int x, int y) {

	}

	public void scrollPageTillPageEnd() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public String javaScriptGetTextOnPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String textOnPage = js.executeScript("return document.documentElement.innerText").toString();
		return textOnPage;
		//This will print the text of whole Page
	}

	// flashing elements Using Java Script///
	public void javaScriptFlash(WebElement we) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i <= 5; i++) {
			js.executeScript("arguments[0].style.background='green';", we);

			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			// js.executeScript("arguments[0].style.background='"+defaultColor+"';",we);

		}

	}

	
    public boolean verifyVisibleText(String text) {
        try {
            String script = "return Array.from(document.querySelectorAll('body, body *'))"
                          + ".filter(el => el.innerText && el.innerText.includes(arguments[0]))"
                          + ".length > 0;";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (Boolean) js.executeScript(script, text);
        } catch (Exception e) {
            System.out.println("Error while verifying visible text: " + e.getMessage());
            return false;
        }
    }
    
    public void AssertTextPresent(String text)
    {
    boolean actualtext=	verifyVisibleText(text);
    if(actualtext==true)
    {
    	printmethod(text+" is present on UI");
    }else
    {
    	printmethod(text+" is not present on UI");
	
    }

    }
    
    
    














	//
	//
	//	public  String getRandomName(int i) {
	//		RandomString rs=new RandomString();
	//		String str=rs.nextString();
	//		return str;
	//	}







	//	public  List<String> getColumnDataListByColumnName( List<WebElement> tablelistwe1,String tableName,String columnName) {
	//		//		int columnNumber=-1;
	//		//		List<WebElement> listColumnNames=driver.findElements(By.xpath(tableXpath+"//tr[1]//td"));
	//		//		int columnCount=listColumnNames.size();
	//		//		for(int i=0; i<=columnCount-1;i++) {
	//		//			WebElement weTableColumn=listColumnNames.get(i);
	//		//			String tablColumnName=weTableColumn.getText();
	//		//			if(tablColumnName.equalsIgnoreCase(columnName)==true) {
	//		//                 columnNumber=i;
	//		//                 break;
	//		//			}
	//		//		}
	//		//		
	//		//		List<WebElement> columnsList=driver.findElements(By.xpath( tableXpath+"//tr//td["+columnNumber+"]"));
	//		//		List<String> columnNameList =new ArrayList<>();
	//		//		for (int i = 1; i < columnsList.size(); i++) {
	//		//			String tableColumnName=columnsList.get(i ).getText();
	//		//			columnNameList.add(columnName);
	//		//		}
	//		int columnNumber=getColumnNumberByColumnName(tablelistwe1, tableName, columnName);
	//		//List<String>columnNameList=getColumnDataListByColumnNumber(tablelistwe, tableName, columnNumber);
	//
	//		return columnNameList;



	//	public  int getRowNumberByUniqueColumnRowID(String tableXpath, String tableName, String uniqueData, String uniqueColumnName) {
	//		int rowNumber=-1;
	//		List<String> columnDataList=getColumnDataListByColumnName(tableXpath, tableName,uniqueColumnName);
	//		for(int i=0; i<=columnDataList.size()-1;i++) {
	//			String uniqueColumnData=columnDataList.get(i);
	//			if(uniqueColumnData.equalsIgnoreCase(uniqueData)) {
	//				rowNumber=i;
	//				break;
	//			}
	//		}
	//		return rowNumber;
	//
	//
	//
	//	}
	//
	//	public  List<String> getRowDataListByRowID(String tableXpath, String tableName, String uniqueData, String uniqueColumnName) {
	//		int rowNumber=getRowNumberByUniqueColumnRowID(tableXpath, tableName, uniqueData, uniqueColumnName);
	//		List<String>listRowData=getRowDataListByRowNumber(tableXpath, tableName, rowNumber);
	//		return listRowData;
	//	}

	//Explicit wait

	public JavascriptExecutor sendKeysbyJavascript()
	{

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
	}
}




