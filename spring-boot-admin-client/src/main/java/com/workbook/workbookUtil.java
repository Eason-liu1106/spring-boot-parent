package com.workbook;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.format.datetime.DateFormatter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author LJL
 * @date 2018/7/10 0010 15:41
 */
public class workbookUtil {
    private static final Integer pageNum = 6;


    public static void create(List<TaskModel> arrayList) throws IOException {
        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        int right = 0;
        int down = 0;
        XSSFSheet sheet = null;
        int count = 1;
        XSSFRow row = null;
        XSSFCellStyle style = workBook.createCellStyle();
        //下边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workBook.createFont();
        font.setBold(true);
        style.setFont(font);
        for (int i = 0; i < arrayList.size(); i++) {
            TaskModel taskModel = arrayList.get(i);
            int location = i % pageNum;
            if (i == 0) {
                sheet = workBook.createSheet("0");
                for (int j = 0; j < 16; j++) {
                    Row row1 = sheet.createRow(j);
                    row1.setRowStyle(style);

                }

            }
            if (location == 0 && i != 0) {
                //创建工作表
                sheet = workBook.createSheet(count + "");
                for (int j = 0; j < 16; j++) {
                    Row row1 = sheet.createRow(j);
                    row1.setRowStyle(style);


                }
                count++;

            }

            switch (location) {
                case 0:
                    right = 0;
                    down = 0;
                    break;
                case 1:
                    right = 5;
                    down = 0;
                    break;
                case 2:
                    right = 0;
                    down = 5;
                    break;
                case 3:
                    right = 5;
                    down = 5;
                    break;
                case 4:
                    right = 0;
                    down = 10;
                    break;
                case 5:
                    right = 5;
                    down = 10;
                    break;
                default:
                    break;

            }
            //创建行
            row = sheet.getRow(down);
            row.setHeight((short) (25 * 20));
            //创建单元格，操作第1行第1列
            XSSFCell cell = row.createCell(right, CellType.STRING);
            cell.setCellValue("任务名称");


            CellRangeAddress rangeAddress = new CellRangeAddress(down, down, 1 + right, 3 + right);

            sheet.addMergedRegion(rangeAddress);
            //创建单元格，操作第1行第2 3 4列
            cell = row.createCell(right + 1, CellType.STRING);
            cell.setCellStyle(style);
            cell.setCellValue(taskModel.getTaskName());
            cell.setCellStyle(style);
            sheet.setColumnWidth(1 + right, 12 * 256);
            rangeAddress = new CellRangeAddress(1 + down, 1 + down, 1 + right, 3 + right);
            sheet.addMergedRegion(rangeAddress);
            row = sheet.getRow(1 + down);
            cell = row.createCell(right, CellType.STRING);
            cell.setCellValue("任务描述");
            cell = row.createCell(1 + right, CellType.STRING);
            row.setHeight((short) (100 * 20));
            cell.setCellValue(taskModel.getTaskDescripe());
            cell.setCellStyle(style);
            row = sheet.getRow(2 + down);
            cell = row.createCell(right, CellType.STRING);
            cell.setCellValue("截止日期");
            cell = row.createCell(1 + right, CellType.STRING);
            cell.setCellValue(taskModel.getDate());
            cell.setCellStyle(style);
            cell = row.createCell(2 + right, CellType.NUMERIC);
            cell.setCellValue("优先级");
            cell = row.createCell(3 + right, CellType.STRING);
            cell.setCellValue(taskModel.getPriority());
            cell.setCellStyle(style);
            row = sheet.getRow(3 + down);
            cell = row.createCell(right, CellType.STRING);
            cell.setCellValue("指派给");
            cell = row.createCell(1 + right, CellType.STRING);
            cell.setCellValue(taskModel.getExecutor());
            cell.setCellStyle(style);
            cell = row.createCell(2 + right, CellType.STRING);
            cell.setCellValue("创建者");
            cell = row.createCell(3 + right, CellType.STRING);
            cell.setCellValue(taskModel.getCreateName());
            cell.setCellStyle(style);


        }
        FileOutputStream outputStream = new FileOutputStream(new File("d:\\poi.xlsx"));
        workBook.write(outputStream);
        workBook.close();//记得关闭工作簿

    }

    public static void task() throws Exception {
        // Workbook workbook = WorkbookFactory.create();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("d:/Task_3.xlsx"));
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
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("d:/ddd.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        List<TaskModel> arrayList = new ArrayList();
        int num = sheet.getLastRowNum();
        for (Row r : sheet) {
            if (r.getRowNum() == 0) {
                continue;
            }
            TaskModel taskModel = new TaskModel();
            taskModel.setTaskName(r.getCell(0).getStringCellValue());
            if (r.getCell(1) == null) {
                taskModel.setTaskDescripe("");
            } else {
                taskModel.setTaskDescripe(r.getCell(1).getStringCellValue());
            }

            if (r.getCell(2) == null) {
                taskModel.setPriority("");
            } else {
                r.getCell(2).setCellType(CellType.STRING);
                taskModel.setPriority(r.getCell(2).getStringCellValue());
            }

            Cell cell = r.getCell(3);
            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    String datestr = formater.format(r.getCell(3).getDateCellValue());
                    taskModel.setDate(datestr);
                } else {
                    taskModel.setDate("");
                }
            }
            taskModel.setCreateName(r.getCell(4).getStringCellValue());
            taskModel.setExecutor(r.getCell(5).getStringCellValue());
            arrayList.add(taskModel);
        }
        workbookUtil.create(arrayList);

    }
}
