package com.tm.uni.Utilities

import java.awt.Robot
import java.awt.event.KeyEvent
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.util.concurrent.TimeUnit
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


public class Essentials {


	//Method to Wait for the Element to Visible - Explicit Wait
	public static void WaitForElementVisible(String Element){

		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, 90)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""+Element+"")))
		println("Element is Visible")
	}


	//Method to Wait for the Element to Invisible - Explicit Wait
	public static void WaitForElementInvisible(String Element){

		WebDriver driver = DriverFactory.getWebDriver()

		WebDriverWait wait = new WebDriverWait(driver, 90)
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(""+Element+"")))
		println("Element is Invisible")
	}


	//Implicit Wait Method
	public static void Wait(int Time) {

		WebDriver driver = DriverFactory.getWebDriver()

		driver.manage().timeouts().implicitlyWait(Time,TimeUnit.SECONDS)
	}


	//Method for Zoom Out
	public static void ZoomOut() {

		Robot robot = new Robot()
		println("About to zoom out")
		for (int i = 0; i < 4; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL)
			robot.keyPress(KeyEvent.VK_SUBTRACT)
			robot.keyRelease(KeyEvent.VK_SUBTRACT)
			robot.keyRelease(KeyEvent.VK_CONTROL)
		}
		println("Zoom out completed")
		WebUI.delay(1)
	}


	//Method to Scroll Up
	public static void ScrollUp() {

		WebDriver driver = DriverFactory.getWebDriver()

		JavascriptExecutor js = (JavascriptExecutor) driver
		js.executeScript("window.scrollBy(0,-600)")
		WebUI.delay(1)
	}


	//Method to Scroll Up for Multiple times
	public static void ScrollUps(int Rep) {

		WebDriver driver = DriverFactory.getWebDriver()

		for (int i=1; i<=Rep; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver
			js.executeScript("window.scrollBy(0,-600)")
		}
		WebUI.delay(1)
	}


	//Method to Scroll Down
	public static void ScrollDown() {

		WebDriver driver = DriverFactory.getWebDriver()

		JavascriptExecutor js = (JavascriptExecutor) driver
		js.executeScript("window.scrollBy(0,600)")
		WebUI.delay(1)
	}


	//Method to Scroll Down for Multiple times
	public static void ScrollDowns(int Rep) {

		WebDriver driver = DriverFactory.getWebDriver()

		for (int i=1; i<=Rep; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver
			js.executeScript("window.scrollBy(0,600)")
		}
		WebUI.delay(1)
	}


	//Method that Scrolls to the Exact Element
	public static void ScrollToElement(WebElement Field) {

		WebDriver driver = DriverFactory.getWebDriver()

		JavascriptExecutor js = (JavascriptExecutor) driver
		WebElement Element = Field
		js.executeScript("arguments[0].scrollIntoView();", Element)
		WebUI.delay(1)
	}


	//Method that Scrolls to the Element into View
	public static void ScrollIntoView(WebElement Field) {

		WebDriver driver = DriverFactory.getWebDriver()

		WebElement Element = Field
		Actions actions = new Actions(driver)
		actions.moveToElement(Element)
		actions.perform()
		WebUI.delay(1)
	}


	public static void MouseHover(WebElement Element) {

		WebDriver driver = DriverFactory.getWebDriver()

		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(""+Element+""));
		action.moveToElement(we).build().perform();
	}


	//Java Script Click
	public static void ClickJS(WebElement Field) {

		WebDriver driver = DriverFactory.getWebDriver()

		JavascriptExecutor js = (JavascriptExecutor) driver
		WebElement Element = Field
		js.executeScript("arguments[0].click();", Element)
	}


	//Method for Double click on element
	public static void DoubleClick(WebElement Field) {

		WebDriver driver = DriverFactory.getWebDriver()

		Actions act = new Actions(driver);
		WebElement ele = Field
		act.doubleClick(ele).perform();
	}


	//Java Script Send Keys
	public static void SendKeysJS(WebElement Field, String InpTxt) {

		WebDriver driver = DriverFactory.getWebDriver()

		JavascriptExecutor js = ((JavascriptExecutor)driver)
		WebElement Element = Field
		js.executeScript("arguments[0].value = '"+InpTxt+"';", Element)
	}


	//Method to select Value in Drop down
	public static void SelectDropdownValue(String ValueToSelect) {

		WebDriver driver = DriverFactory.getWebDriver()

		try {
			driver.findElement(By.xpath("//div[normalize-space() = '"+ValueToSelect+"' and contains(@id,'select')]")).click()
		}
		catch(e) {
			driver.findElement(By.xpath("//div[contains(normalize-space(), '"+ValueToSelect+"') and contains(@id,'select')]")).click()
		}
	}


	//Method that Scrolls to the Exact Value in Drop down and Click
	public static void ScrollDropdownClick(String Value) {

		WebDriver driver = DriverFactory.getWebDriver()

		JavascriptExecutor js = (JavascriptExecutor) driver
		WebElement Element = driver.findElement(By.xpath("//div[normalize-space(.) = '"+Value+"'  and contains(@id,'select')]"))
		js.executeScript("arguments[0].scrollIntoView();", Element)

		driver.findElement(By.xpath("//div[normalize-space(.) = '"+Value+"'  and contains(@id,'select')]")).click()
	}


	//To close the Notification Messages
	public static void CloseNotification() {

		WebDriver driver = DriverFactory.getWebDriver()

		WebUI.delay(0.5)
		List<WebElement> NotiMsg = driver.findElements(By.xpath("//div[@class = 'notification-message']"))
		int Size = NotiMsg.size()
		println("No of Notifcations = "+Size)
		if(Size != 0) {
			for(int i = 1; i <= Size;  i++) {
				driver.findElement(By.xpath("(//div[@class = 'notification-message'])[1]")).click()
				WebUI.delay(0.5)
			}
		}
		WebUI.delay(1)
	}


	public static void DatePicker() {

		WebDriver driver = DriverFactory.getWebDriver()

		//def currentDate = "2024-02-27"
		String currentDate = LocalDate.now().toString();

		// Extract year, month, and day using substring
		def Year = currentDate.substring(0, 4)
		def Month = currentDate.substring(5, 7)
		def Day = Integer.parseInt(currentDate.substring(8, 10))

		// Print results
		println("Year: " + Year)
		println("Month: " + Month)
		println("Day: " + Day)

		int T3 = Day+3
		println("T+3: "+ T3)

		if (Month.equals("01") || Month.equals("03") || Month.equals("05") || Month.equals("07") || Month.equals("08") || Month.equals("10") || Month.equals("12")) {
			// Handle months with 31 days
			switch (Day) {
				case 28:
					println("Click 31");
					break;
				case 29:
					println("Click 01");
					break;
				case 30:
					println("Click 02");
					break;
				case 31:
					println("Click 03");
					break;
				default:
					println("Click T3: " + T3);
					driver.findElement(By.xpath("//div[text() = '"+T3+"']//following::div[1]")).click()
					break;
			}
		}
		else if (Month.equals("04") || Month.equals("06") || Month.equals("09") || Month.equals("11")) {
			// Handle months with 30 days
			switch (Day) {
				case 28:
					println("Click 01");
					break;
				case 29:
					println("Click 02");
					break;
				case 30:
					println("Click 03");
					break;
				default:
					println("Click T3: " + T3);
					driver.findElement(By.xpath("//div[text() = '"+T3+"']//following::div[1]")).click()
					break;
			}
		}
		else if (Month.equals("02")) {
			// Handle February
			switch (Day) {
				case 26:
					println("Click 01");
					break;
				case 27:
					println("Click 02");
					break;
				case 28:
					println("Click 03");
					break;
				default:
					println("Click T3: " + T3);
					driver.findElement(By.xpath("//div[text() = '"+T3+"']//following::div[1]")).click()
					break;
			}
		}
	}


	//Method to set the Date
	public static void setDate(String GivenDate) {

		WebDriver driver = DriverFactory.getWebDriver()

		//Date
		def Date = GivenDate.substring(6, 8)
		println(Date)

		//Year
		def Year = GivenDate.substring(0, 4)
		println(Year)

		//Month
		Date dateFormat = new SimpleDateFormat('yyyyMMdd').parse(GivenDate)
		Calendar calendar = Calendar.getInstance()
		calendar.setTime(dateFormat)
		def Month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
		println(Month)

		//Current Month
		LocalDate Currentdate = LocalDate.now()
		Month CM = Currentdate.getMonth()
		String str = CM
		String cap = str.toLowerCase()
		String CurrentMonth = cap.substring(0, 1).toUpperCase() + cap.substring(1);
		int CurrentYear = Currentdate.getYear()
		println(CurrentMonth)
		println(CurrentYear)

		println(Date +'/'+ Month +'/'+ Year)


		//Year Field
		if(Year != CurrentYear) {
			try {
				Select LstYear = new Select (driver.findElement(By.xpath("//div[@class = 'react-datepicker']//following::select[1]")))
				LstYear.selectByVisibleText(Year)
			}
			catch(e) {
				driver.findElement(By.xpath("//span[@class = 'react-datepicker__year-read-view--down-arrow']")).click()
				driver.findElement(By.xpath("//div[text() = '"+Year+"']")).click()
			}
		}

		//Month Field
		if(Month != CurrentMonth) {
			try {
				Select LstMonth = new Select (driver.findElement(By.xpath("//div[@class = 'react-datepicker']//following::select[2]")))
				LstMonth.selectByVisibleText(Month)
			}
			catch(e) {
				driver.findElement(By.xpath("//span[@class = 'react-datepicker__month-read-view--down-arrow']")).click()
				driver.findElement(By.xpath("//div[text() = '"+Month+"']")).click()
			}
		}

		//Date Field
		driver.findElement(By.xpath("//div[text() = '"+Date+"']")).click()
	}


	//Method to set the date CMS
	public static void setDateCMS(String GivenDate) {

		WebDriver driver = DriverFactory.getWebDriver()

		//Date
		def Date = GivenDate.substring(6, 8)
		println(Date)

		//Year
		def Year = GivenDate.substring(0, 4)
		println(Year)

		//Month
		Date dateFormat = new SimpleDateFormat('yyyyMMdd').parse(GivenDate)
		Calendar calendar = Calendar.getInstance()
		calendar.setTime(dateFormat)
		def Month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
		println(Month)

		//Current Month
		LocalDate Currentdate = LocalDate.now()
		Month CM = Currentdate.getMonth()
		String str = CM
		String cap = str.toLowerCase()
		String CurrentMonth = cap.substring(0, 1).toUpperCase() + cap.substring(1);
		int CurrentYear = Currentdate.getYear()
		println(CurrentMonth)
		println(CurrentYear)

		println(Date +'/'+ Month +'/'+ Year)

		//Month Field
		if(Month != CurrentMonth) {
			driver.findElement(By.xpath("//span[@class = 'react-datepicker__month-read-view--down-arrow']")).click()
			driver.findElement(By.xpath("//div[text() = '"+Month+"']")).click()
		}

		//Year Field
		if(Year != CurrentYear) {
			driver.findElement(By.xpath("//span[@class = 'react-datepicker__year-read-view--down-arrow']")).click()
			driver.findElement(By.xpath("//div[text() = '"+Year+"']")).click()
		}

		//Date Field
		driver.findElement(By.xpath("//div[text() = '"+Date+"']")).click()
	}
}





