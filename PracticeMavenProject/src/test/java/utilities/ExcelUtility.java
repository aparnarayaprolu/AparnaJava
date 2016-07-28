package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static Object[][] getWorksheetData(String file, String sheetName){
		Object[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(new File(file));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			int rowCount = sheet.getLastRowNum()+1;
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new Object[rowCount][columnCount];
			
			//System.out.println("rows - " + rowCount);
			//System.out.println("cols - " + columnCount);
			
			Iterator<Row> rows = sheet.iterator();
			
			for(int i =0;i<rowCount;i++){
				Row r = sheet.getRow(i);
				for(int j=0;j<columnCount;j++){
					Cell c = r.getCell(j);
					switch(c.getCellType()){
					case Cell.CELL_TYPE_STRING:
						data[i][j]=c.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						data[i][j] =c.getBooleanCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						data[i][j] =c.getNumericCellValue();
						break;
					case Cell.CELL_TYPE_BLANK:
						data[i][j] = c.getRichStringCellValue();
					default:
						data[i][j] =c.getStringCellValue();
					}//switch
				}//column for
			}//row
			
			//System.out.println(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  data;

	}

}
