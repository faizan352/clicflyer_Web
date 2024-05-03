package clicflyer.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.common.WebLogging;

public class Xls_Reader{
	
	
	public String path="C:\\Users\\MohdFaizanAnsari\\eclipse-workspace\\clicflyer_Web\\test-input\\testData.xlsx";
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	//private WebLogging logging=null;

	public Xls_Reader(){
		
		//this.path = path;
		try {
			//logging=new WebLogging("AL","Testing","AppConfigurations");
			//logging.logDebugInfo("In method Xls_Reader(Constructor) of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			//logging.logErrorInfo("Error In method Xls_Reader(Constructor) of Xls_Reader Class.  message: "+e.getMessage());
			e.printStackTrace();
		}
	}
	// returns the row count in a sheet

	public int getRowCount(String sheetName) {
		//logging.logDebugInfo("In method getRowCount of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

	/**
	 * Code has been updated as per new POI version - 4.x.x
	 * 
	 * @author NaveenKhunteta
	 * @param sheetName
	 * @param colNum
	 * @param rowNum
	 * @return
	 */
	// returns the data from a cell
	@SuppressWarnings("static-access")
	public String getCellData(String sheetName, String colName, int rowNum) {
		//logging.logDebugInfo("In method getCellData of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";

			//System.out.println(cell.getCellType().name());
			//
			if (cell.getCellType().name().equals("STRING"))
				return cell.getStringCellValue();

			// if (cell.getCellType().STRING != null)

			// if(cell.getCellType()==Xls_Reader.CELL_TYPE_STRING)
			// return cell.getStringCellValue();
			else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				//if (HSSFDateUtil.isCellDateFormatted(cell)) {
				if (DateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					//cal.setTime(HSSFDateUtil.getJavaDate(d));
					cal.setTime(DateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					// System.out.println(cellText);

				} 

				return cellText;
			} else if (cell.getCellType().BLANK != null)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {
			//logging.logErrorInfo("Error In method getCellData of Xls_Reader Class.  message: "+e.getMessage());

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}

	// returns the data from a cell
	@SuppressWarnings("static-access")
	public String getCellData(String sheetName, int colNum, int rowNum) {
		//logging.logDebugInfo("In method getCellData of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			//
			if (cell.getCellType().name().equals("STRING"))
				return cell.getStringCellValue();

			//
			// if (cell.getCellType().STRING != null)
			// return cell.getStringCellValue();
			else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				//if (HSSFDateUtil.isCellDateFormatted(cell)) {
				if (DateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					//cal.setTime(HSSFDateUtil.getJavaDate(d));
					cal.setTime(DateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

					// System.out.println(cellText);

				}

				return cellText;
			} else if (cell.getCellType().BLANK != null)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		//logging.logDebugInfo("In method setCellData of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			// cell style
			// CellStyle cs = workbook.createCellStyle();
			// cs.setWrapText(true);
			// cell.setCellStyle(cs);
			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			//logging.logErrorInfo("Error In method setCellData of Xls_Reader Class.  message: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// returns true if data is set successfully else false
	// public boolean setCellData(String sheetName,String colName,int rowNum,
	// String data,String url){
	// //System.out.println("setCellData setCellData******************");
	// try{
	// fis = new FileInputStream(path);
	// workbook = new XSSFWorkbook(fis);
	//
	// if(rowNum<=0)
	// return false;
	//
	// int index = workbook.getSheetIndex(sheetName);
	// int colNum=-1;
	// if(index==-1)
	// return false;
	//
	//
	// sheet = workbook.getSheetAt(index);
	// //System.out.println("A");
	// row=sheet.getRow(0);
	// for(int i=0;i<row.getLastCellNum();i++){
	// //System.out.println(row.getCell(i).getStringCellValue().trim());
	// if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
	// colNum=i;
	// }
	//
	// if(colNum==-1)
	// return false;
	// sheet.autoSizeColumn(colNum);
	// row = sheet.getRow(rowNum-1);
	// if (row == null)
	// row = sheet.createRow(rowNum-1);
	//
	// cell = row.getCell(colNum);
	// if (cell == null)
	// cell = row.createCell(colNum);
	//
	// cell.setCellValue(data);
	// XSSFCreationHelper createHelper = workbook.getCreationHelper();
	//
	// //cell style for hyperlinks
	// //by default hypelrinks are blue and underlined
	// CellStyle hlink_style = workbook.createCellStyle();
	// XSSFFont hlink_font = workbook.createFont();
	// hlink_font.setUnderline(XSSFFont.U_SINGLE);
	// hlink_font.setColor(IndexedColors.BLUE.getIndex());
	// hlink_style.setFont(hlink_font);
	// //hlink_style.setWrapText(true);
	//
	// XSSFHyperlink link = createHelper.createHyperlink(Xls_Reader.LINK_FILE);
	// link.setAddress(url);
	// cell.setHyperlink(link);
	// cell.setCellStyle(hlink_style);
	//
	// fileOut = new FileOutputStream(path);
	// workbook.write(fileOut);
	//
	// fileOut.close();
	//
	// }
	// catch(Exception e){
	// e.printStackTrace();
	// return false;
	// }
	// return true;
	// }

	// returns true if sheet is created successfully else false
	public boolean addSheet(String sheetname) {
		//logging.logDebugInfo("In method addSheet of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			//logging.logErrorInfo("Error In method addSheet of Xls_Reader Class.  message: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if sheet is removed successfully else false if sheet does
	// not exist
	public boolean removeSheet(String sheetName) {
		//logging.logDebugInfo("In method removeSheet of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			//logging.logErrorInfo("Error In method removeSheet of Xls_Reader Class.  message: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName) {
		//logging.logDebugInfo("In method addColumn of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		// System.out.println("**************addColumn*********************");

		try {
			
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = workbook.createCellStyle();
			// style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			// cell = row.getCell();
			// if (cell == null)
			// System.out.println(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			//logging.logErrorInfo("Error In method addColumn of Xls_Reader Class.  message: "+e.getMessage());
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// removes a column and all the contents
	public boolean removeColumn(String sheetName, int colNum) {
		//logging.logDebugInfo("In method removeColumn of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		try {
			
			if (!isSheetExist(sheetName))
				return false;
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			// style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			//XSSFCreationHelper createHelper = workbook.getCreationHelper();
			// style.setFillPattern(XSSFCellStyle.NO_FILL);
			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			//logging.logErrorInfo("Error In method removeColumn of Xls_Reader Class.  message: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;

	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		//logging.logDebugInfo("In method isSheetExist of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		//	logging.logDebugInfo("In method getColumnCount of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}

	// String sheetName, String testCaseName,String keyword ,String URL,String
	// message
	// public boolean addHyperLink(String sheetName,String
	// screenShotColName,String testCaseName,int index,String url,String
	// message){
	// //System.out.println("ADDING addHyperLink******************");
	//
	// url=url.replace('\\', '/');
	// if(!isSheetExist(sheetName))
	// return false;
	//
	// sheet = workbook.getSheet(sheetName);
	//
	// for(int i=2;i<=getRowCount(sheetName);i++){
	// if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
	// //System.out.println("**caught "+(i+index));
	// setCellData(sheetName, screenShotColName, i+index, message,url);
	// break;
	// }
	// }
	//
	//
	// return true;
	// }
	public int getCellRowNum(String sheetName, String colName, String cellValue) {
		//logging.logDebugInfo("In method getCellRowNum of Xls_Reader class", WebLogging.LOG_TYPE_DEBUG);

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;

	}

}


