import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.maybank.pestos.Essentials
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select
import com.tm.uni.Utilities.Essentials


WebUI.openBrowser("https://unifi.com.my/all-in-one")
WebUI.deleteAllCookies()
WebUI.maximizeWindow()
WebUI.refresh()
WebUI.delay(10)

WebDriver driver = DriverFactory.getWebDriver()

WebElement Packs = driver.findElement(By.xpath("//section[@id = 'home-packages']"))
Essentials.ScrollToElement(Packs)
WebUI.delay(3)

driver.findElement(By.xpath("(//section[@id = 'home-packages']//following::a[contains(@class, 'bluearrow')])[1]")).click()
WebUI.delay(5)

driver.findElement(By.xpath("//label[text() = 'House/Unit No ']//following::input[1]")).sendKeys(House)

driver.findElement(By.xpath("//label[text() = 'Street ']//following::input[1]")).sendKeys(Street)

driver.findElement(By.xpath("//label[text() = 'Section/Taman ']//following::input[1]")).sendKeys(Section)

driver.findElement(By.xpath("//label[text() = 'Postcode ']//following::input[1]")).sendKeys(Postcode)

driver.findElement(By.xpath("//label[text() = 'City ']//following::input[1]")).sendKeys(City)

Select State = new Select (driver.findElement(By.xpath("//label[text()='State ']//following::select[1]")))
State.selectByVisibleText("SELANGOR")

driver.findElement(By.xpath("//button[text() = 'Proceed']")).click()
WebUI.delay(8)

driver.findElement(By.xpath("(//div[@class = 'ant-modal-body']//following::div[contains(@class, 'aspect-square')])[1]")).click()
WebUI.delay(2)

driver.findElement(By.xpath("//button[text() = 'Confirm']")).click()
WebUI.delay(1)

Essentials.DatePicker()

driver.findElement(By.xpath("//button[text() = '09:30 AM - 12:00 PM']")).click()
WebUI.delay(1)

//driver.findElement(By.xpath("//button[text() = 'Save']")).click()





/*
try {
	Alert alert = driver.switchTo().alert();
	alert.accept();
}
catch (Exception e) {
}

List<WebElement> DQ = driver.findElements(By.xpath("//a[@id = 'smt-close-icon']"))
int DQSize = DQ.size()
if(DQSize != 0) {
	driver.findElement(By.xpath("//a[@id = 'smt-close-icon']")).click()
}

String VisitInfo = "(//div[text() = '100 Mbps'])[1]"
Essentials.ScrollToElement(VisitInfo)
*/























