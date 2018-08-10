package com.pdf;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * @author JialinLiu
 * @date 2018/7/19 0019 16:54
 */
public class pdfUtil {
    public static void main(String args[]) throws Exception {
        //BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//中文字体
        BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese);

        //Step 1—Create a Document.
        Document document = new Document();
        //Step 2—Get a PdfWriter instance.
        PdfWriter.getInstance(document, new FileOutputStream("d:\\createSamplePDF1.pdf"));
        //Step 3—Open the Document.
        document.open();
        //Step 4—Add content.
        document.setPageSize(PageSize.A4.rotate());
        document.newPage();
//如果不设置 空白页无法单独显示
        //writer.setPageEmpty(false);

        float[] widths = {0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,};
        PdfPTable table = new PdfPTable(widths);
        //按比例设置单元格宽度


        PdfPCell cell = new PdfPCell(new Phrase("预计新增产出(亿元)",font));
        //PdfPCell cell = new PdfPCell(new Paragraph("测试",font));
        cell.setNoWrap(false);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);
        table.addCell(cell);

        document.add(table);
        //Step 5—Close the Document.
        document.close();

    }
}
