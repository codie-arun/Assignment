package XmlToExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileDemo {

	public static void main(String[] args) throws Exception {
        
			HashMap<Integer,ArrayList<Object>> obj = XmlSaxParser.getObj("bill");
		
		//Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook();
	      
	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet( " Employee Info ");

	      //Create row object
	      XSSFRow row;

	      //This data needs to be written (Object[])
//	      Map < String, Object[] > invoice = new TreeMap < String, Object[] >();
//	      empinfo.put( "1", new Object[] {
//	         "Sl No","Item", "Unit", "Price","Qty","Amount" });
//	      
//	      empinfo.put( "2", new Object[] {
//	         "1", "Daal", null,"120" });
//	      
//	      empinfo.put( "3", new Object[] {
//	         "2", "Rice",null ,"60" });
	      

	      //Iterate over data and write to sheet
	      Set < Integer > keyid = obj.keySet();
	      int rowid = 0;
	      
	      for (Integer key : keyid) {
	         row = spreadsheet.createRow(rowid++);
	         ArrayList<Object> objectArr = obj.get(key);
	         int cellid = 0;
	         
	         for (Object o : objectArr){
	            Cell cell = row.createCell(cellid++);
	            cell.setCellValue((String)o);
	         }
	      }
	      //Write the workbook in file system
	      FileOutputStream out = new FileOutputStream(
	         new File("D:/temp/Bill.xlsx"));
	      
	      workbook.write(out);
	      out.close();
	      System.out.println("Writesheet.xlsx written successfully");
	   }

}
