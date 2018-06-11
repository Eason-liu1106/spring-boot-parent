package com.mail.web;

import com.mail.model.MailDo;
import com.mail.service.MailSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/5/21 0021 14:03
 */
@RestController
@RequestMapping("api/mail")
public class MailController {
    private static Logger logger = LoggerFactory.getLogger(MailController.class);
    @Autowired
    MailSendService mailSendService;
    @Autowired
    TemplateEngine templateEngine;
    @RequestMapping("sendSimple")
    public void sendSimple(){
        MailDo mailDo = new MailDo();
        mailDo.setFrom("liujialin1106@163.com");
        mailDo.setTo("liujialin1106@163.com");
        mailDo.setSubject("测试邮件");
        mailDo.setText("这是一封测试邮件");
        mailSendService.sendMail(mailDo);
    }
    @RequestMapping("sendHtmlMail")
    public void sendHtmlMail(){
        MailDo mailDo = new MailDo();
        mailDo.setFrom("liujialin1106@163.com");
        mailDo.setTo("liujialin1106@163.com");
        mailDo.setSubject("测试邮件");

//        String text="<html>\n" +
//                "<body>\n" +
//                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
//                "</body>\n" +
//                "</html>";

        Context context = new Context();
        context.setVariable("id", "006");
        //TemplateEngine templateEngine = new TemplateEngine();
        String emailContent = templateEngine.process("emailTemplate", context);
        mailDo.setText(emailContent);
        mailSendService.sendHtmlMail(mailDo);
    }
    @RequestMapping("sendAttachmentsMail")
    public void sendAttachmentsMail(){
        MailDo mailDo = new MailDo();
        mailDo.setFrom("liujialin1106@163.com");
        mailDo.setTo("liujialin1106@163.com");
        mailDo.setSubject("测试邮件");
        mailDo.setFilePath("D:\\info.txt");
        String text="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封HTML邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailDo.setText(text);
        mailSendService.sendAttachmentsMail(mailDo);
    }
    @RequestMapping("sendInlineResourceMail")
    public void sendInlineResourceMail(){
        MailDo mailDo = new MailDo();
        mailDo.setFrom("liujialin1106@163.com");
        mailDo.setTo("liujialin1106@163.com");
        mailDo.setSubject("测试邮件");
        mailDo.setFilePath("D:\\info.txt");
//        String text="<html>\n" +
//                "<body>\n" +
//                "    <h3>hello world ! 这是一封HTML邮件!</h3>\n" +
//                "</body>\n" +
//                "</html>";
//        mailDo.setText(text);
        String rscId = "neo006";
        String text="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "D:\\轻推20180412171607.png";
        mailDo.setText(text);
        mailDo.setImgPath(imgPath);
        mailDo.setRscId(rscId);
        mailSendService.sendInlineResourceMail(mailDo);
    }

}
