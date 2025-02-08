package com.tm.uni.Utilities

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.util.CellReference
import org.apache.poi.util.Units
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable


public class Utilities extends Essentials {


	Properties properties = null;

	public Utilities() {
	}


	//Main Directory of the project
	public static final String currentDir = System.getProperty("user.dir");

	//Excel WorkBook
	private static XSSFWorkbook excelWBook;

	//Excel Sheet
	private static XSSFSheet excelWSheet;

	//Excel cell
	private static XSSFCell cell;

	//Excel row
	private static XSSFRow row;


	@Keyword //Keyword to get Excel Path
	def String getExcelFilePath(String strExcelFileName){
		String strExcelFilePath=RunConfiguration.getProjectDir().replace('/', '\\')+ "\\Data Files\\" +strExcelFileName
		return strExcelFilePath
	}


	//Method to get File Path
	public static String getFilePath(String strFileName){
		String strFilePath=RunConfiguration.getProjectDir().replace('/', '\\')+ "\\Data Files\\" +strFileName
		return strFilePath
	}


	//Method to get File Path for Others
	public static String getFilePathFile(String strFileNameinFile){
		String strpathFile=RunConfiguration.getProjectDir().replace('/', '\\')+ "\\Files\\" +strFileNameinFile
		return strpathFile
	}


	//Method to get Project Folder Path
	public static String getProjectFolderPath(){
		String strPath=RunConfiguration.getProjectDir().replace('/', '\\')
		return strPath
	}


	// Method to get Test Results Folder Path
	public static String getTestResultsFolderPath(){
		String strPath=RunConfiguration.getProjectDir().replace('/', '\\')+ "\\Test Results\\"
		return strPath
	}


	//Method to get screenshot
	public static String getScreenShotFolder() {
		if (GlobalVariable.TCExecutionStatus == ""){
			//String path=RunConfiguration.getProjectDir().replace('/', '\\')+ "\\Test Results\\"
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yy_hh:mm:ss");
			String time = dateFormat.format(now)
			String tcFolderPath = GlobalVariable.TestSuiteResultsFolderPath+"\\"+GlobalVariable.TCName+"_"+time
			File dir = new File(tcFolderPath)
			dir.mkdir()
			String ssFolderPath=tcFolderPath+"\\ScreenShots"
			File ssdir = new File(ssFolderPath)
			ssdir.mkdir()
			println(ssFolderPath)
			return ssFolderPath
		}
	}


	@Keyword //Keyword to Write Data to Excel File
	def  static writeDataIntoExcel(String columnName,String data)throws IOException{
		int rowNo=GlobalVariable.rowNumber
		if (rowNo != 0) {
			FileInputStream file=new FileInputStream(new File(GlobalVariable.excelFilePath))
			XSSFWorkbook workbook=new XSSFWorkbook(file);
			workbook.setForceFormulaRecalculation(true)
			XSSFSheet sheet=workbook.getSheet(GlobalVariable.sheetName);
			//get total column numbers in excel file
			int colNo=sheet.getRow(0).getLastCellNum();
			for(int i=0;i<=colNo;i++){
				String data_fromcell = sheet.getRow(0).getCell(i)
				if(data_fromcell==columnName){
					//write data into excel
					sheet.getRow(rowNo).createCell(i).setCellValue(data)
					break;
				}
			}

			file.close()
			FileOutputStream outfile=new FileOutputStream(new File(GlobalVariable.excelFilePath))
			workbook.write(outfile)
			outfile.close()
		}
	}


	//Method to generate Random Number, used to generate NRIC
	public static String  GetRandomNumber() {
		Random r = new Random( System.currentTimeMillis() );
		println(((1 + r.nextInt(2)) * 10000 + r.nextInt(79000)))
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(79000))
	}


	//Method to get Random Name, used to input Random Names
	public static String GetRandomName(String File) {
		String fileName = getFilePathFile(File+".txt")
		File wordList = new File(fileName)
		//Scanner input = new Scanner(wordList)
		Random rand = new Random()
		List<String> words = new ArrayList<>()
		Scanner reader = null
		try {
			reader = new Scanner(wordList);
		} catch (FileNotFoundException e) {
			println("file \"" + fileName + "\" not found");
		}

		while(reader.hasNextLine()) {
			String word = reader.nextLine();
			words.add(word);
		}
		int wordNum = words.size();
		String attempt;
		int place;
		int correct = 0;
		place = rand.nextInt(wordNum);
		String randomName = words.get(place);
		println(randomName);
		return randomName
	}


	// Method to read data from Test data file
	public static String getTestData(String columnName) {
		return findTestData(GlobalVariable.dataFilePath).getValue(columnName, GlobalVariable.rowNumber)
	}


	//Update Word Document
	public static void createWordDoc(String testCaseName) {

		String folderPath = GlobalVariable.ScreenShotFolderPath
		println folderPath
		String projectName = testCaseName
		XWPFDocument docx = new XWPFDocument();
		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		File folder = new File(folderPath);
		//run.setText("Product: FWD B40 Kasih");
		//run.setFontSize(20);
		InputStream is = null;
		for (String file : folder.list()) {
			is = new FileInputStream(folder.absolutePath + "\\" + file);
			run.addBreak();
			run.setText(file)
			run.addBreak();
			run.addBreak();
			run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, file, Units.toEMU(350), Units.toEMU(350));
			run.addBreak();
			run.addBreak();
			run.addBreak();
			is.close();
		}

		FileOutputStream out = new FileOutputStream(folderPath + "/" + projectName + ".docx");
		docx.write(out);
		out.flush();
		out.close();
		TimeUnit.SECONDS.sleep(1);
	}



	public static void captureScreenShot(String screenShotName) {
		GlobalVariable.ScreenShotNumber = GlobalVariable.ScreenShotNumber+1
		WebUI.takeFullPageScreenshot(GlobalVariable.ScreenShotFolderPath+"\\"+GlobalVariable.ScreenShotNumber+"."+screenShotName+".png")
	}



	public static boolean elementExists(String locator) {
		WebDriver driver = DriverFactory.getWebDriver()
		try {
			driver.findElement(By.xpath(locator));
			return true;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}


	public static String getDOBFromAge(String dob) {
		def DOB="-"+dob
		DOB=Integer.parseInt(DOB)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR,DOB);
		c.add(Calendar.MONTH,4)
		Date date = c.getTime();
		println(dateFormat.format(date))
		return dateFormat.format(date)
	}


	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}


	public static String getCellData(int RowNum, int ColNum) {
		try {
			excelWBook.setForceFormulaRecalculation(true);
			FormulaEvaluator evaluator = excelWBook.getCreationHelper().createFormulaEvaluator();
			cell = excelWSheet.getRow(RowNum).getCell(ColNum);
			String cellValue;
			println("Cell t py : " + cell.getCellTypeEnum())
			if(cell.getCellTypeEnum() == CellType.NUMERIC) {
				//cell type numeric
				System.out.println("Value: " + cell.getNumericCellValue());
				cellValue = Double.toString(cell.getNumericCellValue());
			}
			else if(cell.getCellTypeEnum() == CellType.STRING) {
				//cell type string

				System.out.println("Value: " + cell.getStringCellValue());
				cellValue = cell.getStringCellValue();
			}
			else if(cell.getCellTypeEnum() == CellType.FORMULA) {
				System.out.println("Value: " + cell.getRawValue());
				cellValue = cell.getRawValue()
			}
			else
				return null;

			return cellValue;
		} catch (Exception e) {
			throw (e);
		}
	}


	// MatchData used for AS400
	public static List<String> matchData(List<String> asData, List<String> pdValues) {
		List<String> matches = new ArrayList<>();

		//check matched data
		String currentValue;
		for (int i = 0 ; i < asData.size() ; i++) {
			currentValue = asData.get(i);
			if (pdValues.contains(currentValue) && !currentValue.isEmpty())
				matches.add(currentValue);
		}
		System.out.println("Matches : " + matches);
		return matches;
	}



	public static void writeMatchedStatus(Sheet sheet) {
	}


	//return sheet from a worksheet
	public static Sheet getTargetSheet(String worksheetPath, String sheetName) throws Exception {
		FileInputStream file= null;
		Workbook workbook = null;

		File fi = new File(worksheetPath);
		System.out.println("Can read: " + fi.canRead());
		System.out.println("Can write: " + fi.canWrite());

		file = new FileInputStream(fi);
		if (sheetName.startsWith(GlobalVariable.sheetName))
			workbook = new XSSFWorkbook(file);
		else
			workbook = new HSSFWorkbook(file);

		return workbook.getSheet(sheetName);
	}



	public static List<String> readColumnFromSheet(String filePath, String sheetName, String column) {

		try {
			Sheet sheet = getTargetSheet(filePath , sheetName);
			List<String> values =  loopColumn(sheet, column);
			return values;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	private static List<String> loopColumn(Sheet sheet, String column ) {

		CellReference cellReference = new CellReference(column);
		Cell cell;
		List<String> values = new ArrayList();

		Row currentRow;
		//read protect direct file
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			currentRow = sheet.getRow(i);

			cell = currentRow.getCell(cellReference.getCol());
			if (cell != null) {
				values.add(cell.getStringCellValue());
				System.out.println("Cell : " + cell.getStringCellValue());
			}
			else
				values.add("");
		}
		return values;
	}


	//Combine The matched policies and states in a map
	public static Map<String,String> combineMatchedPoliciesAndStates (List<String> matches, List<String> policy, List<String> state) {
		int index = -1;
		String currentValue;
		Map<String,String> matchedPoliciesAndStates = new HashMap<>();

		for (int i = 0 ; i <matches.size(); i ++) {

			currentValue = matches.get(i);
			index = policy.indexOf(currentValue)
			if (index > 0)
				matchedPoliciesAndStates.put(currentValue, state.get(index))
		}
		System.out.println(matchedPoliciesAndStates);
		return matchedPoliciesAndStates
	}
}