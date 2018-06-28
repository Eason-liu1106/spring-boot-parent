package com.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qrcode.model.Vcard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;

/**
 * @author LJL
 * 二维码工具类
 * 请自行添加 google zxing包
 * 
 */
public class QrCodeUtil {
 private static final String CHARSET = "utf-8";
  
 private static final String FORMAT_NAME = "JPG";

 private static final int QRCODE_SIZE = 300;

 private static final int WIDTH = 60;

 private static final int HEIGHT = 60;
 /**
  *
  * @author LJL
  * @param content 参数字段
  * @param imgPath 图片地址
  * @param needCompress 是否压缩logo
  * @date  2018/6/20  14:09
  * @return java.awt.image.BufferedImage
  */
 private static BufferedImage createImage(String content, String imgPath,
   boolean needCompress) throws Exception {
  Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
  hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
  hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
  BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
    BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
  int width = bitMatrix.getWidth();
  int height = bitMatrix.getHeight();
  BufferedImage image = new BufferedImage(width, height,
    BufferedImage.TYPE_INT_RGB);
  for (int x = 0; x < width; x++) {
   for (int y = 0; y < height; y++) {
    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
      : 0xFFFFFFFF);
   }
  }
  if (imgPath == null || "".equals(imgPath)) {
   return image;
  }
  // 插入图片
  QrCodeUtil.insertImage(image, imgPath, needCompress);
  return image;
 }
 /**
  * 插入LOGO
  * 
  * @param source
  *            二维码图片
  * @param imgPath
  *            LOGO图片地址
  * @param needCompress
  *            是否压缩
  * @throws Exception
  */
 private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
  File file = new File(imgPath);
  if (!file.exists()) {
   System.err.println("" + imgPath + "   该文件不存在！");
   return;
  }
  Image src = ImageIO.read(new File(imgPath));
  int width = src.getWidth(null);
  int height = src.getHeight(null);
  if (needCompress) {
   if (width > WIDTH) {
    width = WIDTH;
   }
   if (height > HEIGHT) {
    height = HEIGHT;
   }
   Image image = src.getScaledInstance(width, height,
           Image.SCALE_SMOOTH);
   BufferedImage tag = new BufferedImage(width, height,
           BufferedImage.TYPE_INT_RGB);
   Graphics g = tag.getGraphics();
   g.drawImage(image, 0, 0, null);

   g.dispose();
   src = image;
  }
  // 插入LOGO
  Graphics2D graph = source.createGraphics();
  int x = (QRCODE_SIZE - width) / 2;
  int y = (QRCODE_SIZE - height) / 2;
  graph.drawImage(src, x, y, width, height, null);
  Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
  graph.setStroke(new BasicStroke(3f));
  graph.draw(shape);
  graph.dispose();
 }
  
  
  
 /**
  * 生成二维码(内嵌LOGO)
  * 
  * @param content
  *            内容
  * @param imgPath
  *            LOGO地址
  * @param destPath
  *            存放目录
  * @param needCompress
  *            是否压缩LOGO
  * @throws Exception
  */
 public static void encode(String content, String imgPath, String destPath,
   boolean needCompress) throws Exception {
  BufferedImage image = QrCodeUtil.createImage(content, imgPath, needCompress);
  mkdirs(destPath);
  String file = new Random().nextInt(99999999)+".jpg";
  ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
 }
 /**
  * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
  * @author ljl
  * @param destPath 存放目录
  */
 public static void mkdirs(String destPath) {
  File file =new File(destPath);    
  //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
  if (!file.exists() && !file.isDirectory()) {
   file.mkdirs();
  }
 }
 /**
  * 生成二维码(内嵌LOGO)
  * 
  * @param content
  *            内容
  * @param imgPath
  *            LOGO地址
  * @param destPath
  *            存储地址
  * @throws Exception
  */
 public static void encode(String content, String imgPath, String destPath)
   throws Exception {
  QrCodeUtil.encode(content, imgPath, destPath, false);
 }
 /**
  * 生成二维码
  * 
  * @param content
  *            内容
  * @param destPath
  *            存储地址
  * @param needCompress
  *            是否压缩LOGO
  * @throws Exception
  */
 public static void encode(String content, String destPath,
   boolean needCompress) throws Exception {
  QrCodeUtil.encode(content, null, destPath, needCompress);
 }
 /**
  * 生成二维码
  * 
  * @param content
  *            内容
  * @param destPath
  *            存储地址
  * @throws Exception
  */
 public static void encode(String content, String destPath) throws Exception {
  QrCodeUtil.encode(content, null, destPath, false);
 }
 /**
  * 生成二维码(内嵌LOGO)
  * 
  * @param content
  *            内容
  * @param imgPath
  *            LOGO地址
  * @param output
  *            输出流
  * @param needCompress
  *            是否压缩LOGO
  * @throws Exception 抛出异常
  */
 public static void encode(String content, String imgPath,
   OutputStream output, boolean needCompress) throws Exception {
  BufferedImage image = QrCodeUtil.createImage(content, imgPath,
    needCompress);
  ImageIO.write(image, FORMAT_NAME, output);
 }
 /**
  * 生成二维码
  * 
  * @param content
  *            内容
  * @param output
  *            输出流
  * @throws Exception
  */
 public static void encode(String content, OutputStream output)
   throws Exception {
  QrCodeUtil.encode(content, null, output, false);
 }

 public static void main(String[] args) throws Exception {
     Vcard vcard = new Vcard();
     vcard.setAddress("重庆两江新区财富大道19号重庆高科财富园三号B幢");
     vcard.setCompany("重庆高科集团有限公司");
     vcard.setEmail("910527645@qq.com");
     vcard.setMobilephone("18523826173");
     vcard.setName("何春婷");
     vcard.setRole("副部长");
     vcard.setTitle("投资发展部");
     vcard.setTelPhone("023-63118686");
     vcard.setWebsite("http://www.cqgaokecom");
     encode(vcard.toString(), "e://logo3.png", "e://", true);

 }
}