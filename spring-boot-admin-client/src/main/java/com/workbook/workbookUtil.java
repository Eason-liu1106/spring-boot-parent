package com.workbook;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * 描述:
 *
 * @author LJL
 * @date 2018/7/10 0010 15:41
 */
public class workbookUtil {
    private static final String FIRST = "3.52";
    private static final String SECOND = "6.54";
    private static final String THRID = "1.24";
    private static final String FOURTH = "2.38";
    private static final String FIVETH = "1.75.";
    private static final String SIXTH = "1.75";

    public static void create() throws IOException {
        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = workBook.createSheet("helloWorld");
        //创建行
        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) (25 * 20));

        //创建单元格，操作第1行第1列
        XSSFCell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("任务名称");
        XSSFCellStyle style = workBook.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 1, 3);
        sheet.addMergedRegion(rangeAddress);
        //创建单元格，操作第1行第2 3 4列
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("helloWorld232323232323sdfdfd");
        //sheet.setColumnWidth(2,30*256);
        rangeAddress = new CellRangeAddress(1, 29, 0, 0);
        sheet.addMergedRegion(rangeAddress);
        rangeAddress = new CellRangeAddress(1, 29, 1, 3);
        sheet.addMergedRegion(rangeAddress);
        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("任务描述");
        cell.setCellStyle(style);
        //创建单元格，操作第2-30行第2 3 4列
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("helloWorld232323232323sdfdfd科技考虑点击开房记录杜绝浪费");
        cell.setCellStyle(style);
        FileOutputStream outputStream = new FileOutputStream(new File("d:\\test\\poi.xlsx"));
        workBook.write(outputStream);

        workBook.close();//记得关闭工作簿

    }

    public static void task() throws Exception {
        // Workbook workbook = WorkbookFactory.create();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("E:/Task_3.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        //sheet.setDefaultRowHeight();
        sheet.setDefaultRowHeightInPoints((short) 144.75);
        for (int i = 1; i < rows + 1; i++) {

            sheet.setColumnWidth(0, (short) (28 * 3.52 * 35.7));
            sheet.setColumnWidth(1, (short) (28 * 6.54 * 35.7));
            sheet.setColumnWidth(2, (short) (28 * 1.24 * 35.7));

            sheet.setColumnWidth(3, (short) (28 * 2.38 * 35.7));

            sheet.setColumnWidth(4, (short) (28 * 1.75 * 35.7));

            sheet.setColumnWidth(5, (short) (28 * 1.75 * 35.7));
        }

        File filewrite = new File("E:/2.xls");

        filewrite.createNewFile();

        OutputStream os = new FileOutputStream(filewrite);
    }
    //测试

    public static void main(String args[]) throws Exception {
        // workbookUtil.task();
        workbookUtil.create();
    }
}
