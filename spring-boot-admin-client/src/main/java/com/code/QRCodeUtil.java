package com.code;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qrcode.model.Vcard;
import net.glxn.qrgen.core.vcard.VCard;

/**
 * 二维码工具类
 * 请自行添加 google zxing包
 * 
 */
public class QRCodeUtil {
 private static final String CHARSET = "utf-8";
  
 private static final String FORMAT_NAME = "JPG";
 // 二维码尺寸
 private static final int QRCODE_SIZE = 300;
 // LOGO宽度
 private static final int WIDTH = 60;
 // LOGO高度
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
 // hints.put(EncodeHintType.MARGIN, 1);
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
  QRCodeUtil.insertImage(image, imgPath, needCompress);
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
 private static void insertImage(BufferedImage source, String imgPath,
   boolean needCompress) throws Exception {
  File file = new File(imgPath);
  if (!file.exists()) {
   System.err.println(""+imgPath+"   该文件不存在！");
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
  * 生成文字图片 
  * @param str 文字
  * @param font 字体
  * @param outFile 输出文件
  * @throws Exception 抛出异常
  * @author ljl
  *
  */
 public static void createImageForName(String str,Font font,File outFile) throws Exception{
   //获取font的样式应用在str上的整个矩形
   Rectangle2D r=font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1),false,false));
   //获取单个字符的高度
   int unitHeight=(int)Math.floor(r.getHeight());
   //获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
   int width=(int)Math.round(r.getWidth())+1;
   //把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
   int height=unitHeight+3;
   //创建图片
   BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
   Graphics g=image.getGraphics();
   g.setColor(Color.WHITE);
   //先用白色填充整张图片,也就是背景
   g.fillRect(0, 0, width, height);
     //在换成黑色
   g.setColor(Color.black);
     //设置画笔字体
   g.setFont(font);
     //画出字符串
   g.drawString(str, 0, font.getSize());
   g.dispose();
   //输出png图片
   ImageIO.write(image, "png", outFile);
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
  BufferedImage image = QRCodeUtil.createImage(content, imgPath,
    needCompress);
  mkdirs(destPath);
  String file = new Random().nextInt(99999999)+".jpg";
  ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
 }
 /**
  * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
  * @author lanyuan
  * Email: mmm333zzz520@163.com
  * @date 2013-12-11 上午10:16:36
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
  QRCodeUtil.encode(content, imgPath, destPath, false);
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
  QRCodeUtil.encode(content, null, destPath, needCompress);
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
  QRCodeUtil.encode(content, null, destPath, false);
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
  * @throws Exception
  */
 public static void encode(String content, String imgPath,
   OutputStream output, boolean needCompress) throws Exception {
  BufferedImage image = QRCodeUtil.createImage(content, imgPath,
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
  QRCodeUtil.encode(content, null, output, false);
 }
 /**
  * 解析二维码
  * 
  * @param file
  *            二维码图片
  * @return
  * @throws Exception
  */
 public static String decode(File file) throws Exception {
  BufferedImage image;
  image = ImageIO.read(file);
  if (image == null) {
   return null;
  }
  BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
    image);
  BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
  Result result;
  Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
  hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
  result = new MultiFormatReader().decode(bitmap, hints);
  String resultStr = result.getText();
  return resultStr;
 }
 /**
  * 解析二维码
  * 
  * @param path
  *            二维码图片地址
  * @return
  * @throws Exception
  */
 public static String decode(String path) throws Exception {
  return QRCodeUtil.decode(new File(path));
 }
 /**
  * Test
  * @param args
  * @throws Exception
  * @author The_Monster　2015年3月17日
  * @version 1.0
  */
 public static void main(String[] args) throws Exception {

  //先创建文字图片
  //createImageForName(text,new Font("宋体",Font.BOLD,18),new File("e://logo2.png"));
  VCard vCard = new VCard();
  vCard.setName("何春婷");
  vCard.setAddress("重庆两江新区财富大道19号重庆高科财富园三号B幢");
  vCard.setCompany("重庆高科");
  vCard.setPhoneNumber("023-63118686");
  vCard.setTitle("重庆高科");
  vCard.setEmail("910527645@qq.com");
  vCard.setWebsite("www.cqgaokecom");
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

     StringBuilder card = new StringBuilder();
     card.append("BEGIN:VCARD");
     card.append("\r\nFN:" + "何春婷");  //这里扫出来的结果为：姓名：XXX，
     card.append("\r\nTITLE:" + "重庆高科");
     card.append("\r\nORG:" + "重庆高科" );    //组织：XXX就出来，ORG它好像直接就翻译出来组织似的
     card.append("\r\nTEL;CELL:" + "18523826173");
     card.append("\r\nTEL;WORK:" + "023-63118686");
     card.append("\r\nTEL;WORK;FAX:" + "023-63020522");
     card.append("\r\nADR;WORK:" + "重庆两江新区财富大道19号重庆高科财富园三号B幢");
     card.append("\r\nURL:" + "http://www.cqgaokecom");
     card.append("\r\nEMAIL;WORK:" + "910527645@qq.com");
     card.append("\r\nNOTE:");
     card.append("\r\nX-QQ:");
     card.append("\r\nROLE:"+"副部长");
     card.append("\r\nTITLE:"+"投资发展部");
     //card.append("\r\nPHOTO;ENCODING=b;TYPE=PNG:data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACIAAAAiCAYAAAA6RwvCAAAAAXNSR0IArs4c6QAAAotJREFUWAndlz1oFEEUx3NJCDFgUialKWxsrMQiWFilkRSi6QISCMED01kZg9jYSOxTCPnotEqQcF3Q4iQBCyFpBYvjCCIIIhrU8/cnMzDs7d7szGwQ8uDHzs3He/+8fTOz6es7j9bpdMagDg34DL+gDU1Yhskz/7sJIgFfoJdJ2CoMVy4Ip0OwASG2z+TxysTgTCJ2HAV/aa/DbbgMF+E6LMIHcE1i0jODk6yII/puwCDMwCN4YtqjPGvwHFxbTcoKnrIi3tGnQtVf/xGy1qJjFpQl11QzcQXMQonYdrzt0h4BvQ45DrXl4KwQIZuJV6bvHs/foQrM/GaQEBPQzcRL+gZgCVSksdYuLYQI/eDuDgW9Ak/VSLSfEjLoU0OQIeZcgrdwC6wd2kbi81jr+3s5MSJeM+c97MESdKBKO+rpTCLArYlv/J6CBfgDVdn9QiFEyIqwQb/TuAlzELtLrC89dTeN5QphILtFtcC1H/yYhrtw4g5EtPOzgaOiTGRj6ODSMS5iDjH528jNhDoZ3NSMkqZsKCvKjrIUYjoKtBu7jYF6iCczV3WielHdqH7KmDZAoQhdWL6PmqIg2kHaSdpR2lm9rDgTyg0rY7LhBtQR/wCuwVd3wGkXZ8K+ICY3nAUpzYcsvgrHGSd+ESYj+tCtylZwpHuoZRyWE2GExG7BIvHPGNBH0BrkF6Z9He6Tyfrkr9rm3Ri+tr30PvkmBo7vMH8rZI0V8iZkkWeuRNyp1Wonnnndw7yTSaiiTsoXZreM0x6EvEgsknQRkoKIC3AQKaYaETZLiJiIEKN/N8tvURvM98SpMqPX5KsZ3U11n7/kcYKogB9DE3TOSJhO4Abobsr/skqO/J8d/AMwE+jkSwZyRwAAAABJRU5ErkJggg==");
     card.append("\r\nEND:VCARD\r\n");
  encode(vcard.toString(), "e://logo3.png", "e://", true);

 }
}