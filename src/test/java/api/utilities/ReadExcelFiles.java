package api.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFiles {

    public static FileInputStream inputStream;
    public static XSSFWorkbook workBook;
    public static XSSFSheet excelSheet;
    public static XSSFRow row;
    public static XSSFCell cell;


    public static String getCellValue(
            String fileName,
            String sheetName,
            int rowNo,
            int cellNo) {

        try {

            inputStream = new FileInputStream(fileName);

            workBook = new XSSFWorkbook(inputStream);

            excelSheet = workBook.getSheet(sheetName);

            cell = excelSheet
                    .getRow(rowNo)
                    .getCell(cellNo);

            DataFormatter formatter =
                    new DataFormatter();

            String value =
                    formatter.formatCellValue(cell);

            workBook.close();
            inputStream.close();

            return value.trim();

        } catch (Exception e) {

            e.printStackTrace();

            return "";
        }
    }


    public static int getRowCount(
            String fileName,
            String sheetName) {

        try {

            inputStream = new FileInputStream(fileName);

            workBook = new XSSFWorkbook(inputStream);

            excelSheet =
                    workBook.getSheet(sheetName);

            int ttlRows =
                    excelSheet.getPhysicalNumberOfRows();

            workBook.close();
            inputStream.close();

            return ttlRows;

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }


    public static int getColCount(
            String fileName,
            String sheetName) {

        try {

            inputStream = new FileInputStream(fileName);

            workBook = new XSSFWorkbook(inputStream);

            excelSheet =
                    workBook.getSheet(sheetName);

            int ttlCells =
                    excelSheet
                    .getRow(0)
                    .getLastCellNum();

            workBook.close();
            inputStream.close();

            return ttlCells;

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }
}