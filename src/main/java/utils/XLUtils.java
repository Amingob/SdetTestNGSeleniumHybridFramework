package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils {

    public static FileInputStream fileInputStream;
    public static FileOutputStream fileOutputStream;
    public static XSSFWorkbook xssfWorkbook;
    public static XSSFSheet xssfSheet;
    public static XSSFRow xssfRow;
    public static XSSFCell xssfCell;

    public static int getRowCount(String xlFile, String xlSheet) throws IOException {

        fileInputStream = new FileInputStream(xlFile);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(xlSheet);

        int rowCount = xssfSheet.getLastRowNum();

        xssfWorkbook.close();
        fileInputStream.close();

        return rowCount;
    }

    public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException {

        fileInputStream = new FileInputStream(xlFile);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(xlSheet);

        xssfRow = xssfSheet.getRow(rowNum);

        int cellCount = xssfRow.getLastCellNum();

        xssfWorkbook.close();
        fileInputStream.close();

        return cellCount;
    }

    public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException {

        fileInputStream = new FileInputStream(xlFile);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(xlSheet);

        xssfRow = xssfSheet.getRow(rowNum);
        xssfCell = xssfRow.getCell(colNum);

        String data;

        try{
            DataFormatter dataFormatter = new DataFormatter();
            String cellData = dataFormatter.formatCellValue(xssfCell);
            return cellData;
        }catch(Exception e){
            data="";
        }

        xssfWorkbook.close();
        fileInputStream.close();

        return data;
    }
    public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum,String data) throws IOException {
        fileInputStream = new FileInputStream(xlFile);
        xssfWorkbook = new XSSFWorkbook(fileInputStream);
        xssfSheet = xssfWorkbook.getSheet(xlSheet);

        xssfRow = xssfSheet.getRow(rowNum);

        xssfCell = xssfRow.createCell(colNum);
        xssfCell.setCellValue(data);

        fileOutputStream = new FileOutputStream(xlFile);
        xssfWorkbook.write(fileOutputStream);

        xssfWorkbook.close();
        fileInputStream.close();
        fileOutputStream.close();

    }

}
