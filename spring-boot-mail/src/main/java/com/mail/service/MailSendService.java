package com.mail.service;

import com.mail.model.MailDo;

/**
 * 描述:
 *
 * @author LJL
 * @date 2018/5/21  13:50
 */
public interface MailSendService {
    void sendMail(MailDo mailDo);
    void sendHtmlMail(MailDo mailDo);
    void sendAttachmentsMail(MailDo mailDo);
    void sendInlineResourceMail(MailDo mailDo);
}
