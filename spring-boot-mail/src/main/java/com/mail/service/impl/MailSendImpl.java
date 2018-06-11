package com.mail.service.impl;

import com.mail.model.MailDo;
import com.mail.service.MailSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 描述:
 *
 * @author LJL
 * @date 2018/5/21 0021 13:56
 */
@Service
public class MailSendImpl implements MailSendService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendMail(MailDo mailDo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailDo.getFrom());
        simpleMailMessage.setSubject(mailDo.getSubject());
        simpleMailMessage.setText(mailDo.getText());
        simpleMailMessage.setTo(mailDo.getTo());

        mailSender.send(simpleMailMessage);
    }



    @Override
    public void sendHtmlMail(MailDo mailDo) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(mailDo.getFrom());
            helper.setTo(mailDo.getTo());
            helper.setSubject(mailDo.getSubject());
            helper.setText(mailDo.getText(), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void sendAttachmentsMail(MailDo mailDo) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(mailDo.getFrom());
            helper.setTo(mailDo.getTo());
            helper.setSubject(mailDo.getSubject());
            helper.setText(mailDo.getText(), true);
            String filePath = mailDo.getFilePath();
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void sendInlineResourceMail(MailDo mailDo) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(mailDo.getFrom());
            helper.setTo(mailDo.getTo());
            helper.setSubject(mailDo.getSubject());
            helper.setText(mailDo.getText(), true);
            String filePath = mailDo.getFilePath();
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            FileSystemResource res = new FileSystemResource(new File(mailDo.getImgPath()));
            helper.addInline(mailDo.getRscId(), res);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
