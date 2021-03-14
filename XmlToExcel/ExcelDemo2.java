package XmlToExcel;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDemo2 {

	public static void main(String[] args) throws Exception	{   
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Bill");
		
		Object[][] data = {
				{"Customer name :","Arun","","Bill no :","102","","bill date :","08/03/2021"},
				{"S.no","item","unit","price","quantity","amount"},
				{1,"Daal","100g",120,1,120},
				{2,"Rice","50g",60,2,120}
		};
		
		int rowCount=0;
		for(Object[] obj:data) {
			Row row = sheet.createRow(rowCount++);
			int columnCount=0;
			
			for(Object o:obj) {
				Cell cell = row.createCell(columnCount++);
				if(o instanceof String) {
					cell.setCellValue((String)o);
				}
				else if(o instanceof Integer) {
					cell.setCellValue((Integer)o);
				}
			}
		}
		try(FileOutputStream fos = new FileOutputStream("bill.xlsx")) {
			workbook.write(fos);
			System.out.println("file writtend successfully");
		}
	}   
}

