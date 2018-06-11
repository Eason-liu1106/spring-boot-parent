package com.file;

import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 描述:
 *
 * @author LJL
 * @date 2018/5/24 0024 11:45
 */
@RestController
@RequestMapping("api/districtCompany")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    private static final String  EXCELL_MODEL_NAME = "区内企业信息模板";
    @Autowired
    Servie servie;
    @ApiOperation(value = "批量导入企业Excel模板" , tags = "区内企业管理")
    @GetMapping("downCompanyExcelModel")
    public void downCompanyExcelModel(HttpServletRequest request, HttpServletResponse response, String costBatch) throws Exception{
        String fileName =  EXCELL_MODEL_NAME + ".xlsx";
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
        response.setCharacterEncoding("utf-8");
        OutputStream fos = response.getOutputStream();
        XSSFWorkbook excel = new XSSFWorkbook(); //  创建excel
        XSSFSheet excelSheet = excel.createSheet(EXCELL_MODEL_NAME);
        excelSheet.setColumnWidth(0, 22 * 256);
        excelSheet.setColumnWidth(1, 22 * 256);
        excelSheet.setColumnWidth(2, 22 * 256);
        excelSheet.setColumnWidth(3, 22 * 256);
        excelSheet.setColumnWidth(4, 22 * 256);
        excelSheet.setColumnWidth(5, 22 * 256);
        excelSheet.setColumnWidth(6, 22 * 256);
        // 创建sheet之后，就创建表头
        XSSFRow row = excelSheet.createRow(0);// 第一行
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("企业名称");// 给单元格赋值
        cell = row.createCell(1);
        cell.setCellValue("企业地址");
        cell = row.createCell(2);
        cell.setCellValue("联系人姓名");
        cell = row.createCell(3);
        cell.setCellValue("联系人职位");
        cell = row.createCell(4);
        cell.setCellValue("联系人电话");
        cell = row.createCell(5);
        cell.setCellValue("联系人邮箱");
        cell = row.createCell(6);
        cell.setCellValue("备注");
        response.flushBuffer();
        excel.write(fos);
        fos.flush();
        fos.close();


    }
    @GetMapping("set")
    public void set(HttpServletRequest request, HttpServletResponse response, String costBatch) throws Exception{

        servie.set();
    }
}
