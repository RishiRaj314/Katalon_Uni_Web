import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// Get the current date
//LocalDate currentDate = LocalDate.now();
//println(currentDate)

def currentDate = "2024-06-31"

// Extract year, month, and day using substring
def year = currentDate.substring(0, 4)
def month = currentDate.substring(5, 7)
def day = currentDate.substring(8, 10)

// Print results
println("Year: " + year)
println("Month: " + month)
println("Day: " + day)

int T3 = Integer.parseInt(day) + 3
println("T+3: "+T3)

if (month.equals("01") || month.equals("03") || month.equals("05") || month.equals("07") || month.equals("08") || month.equals("10") || month.equals("12")) {
	// Handle months with 31 days
	if(day.equals("28")) {
		println("Click 31");
	}
	if(day.equals("29")) {
		println("Click 01");
	}
	if(day.equals("30")) {
		println("Click 02");
	}
	if(day.equals("31")) {
		println("Click 03");
	}
}
else {
	println("Click Nothing");
}	











