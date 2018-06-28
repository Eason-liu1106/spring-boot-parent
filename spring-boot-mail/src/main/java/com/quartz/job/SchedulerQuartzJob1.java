package com.quartz.job;

import com.amqp.service.AmqpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mail.model.MailDo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/5/22 0022 16:30
 */
@Component
public class SchedulerQuartzJob1 implements Job {
    @Autowired
    private AmqpService amqpService;

    private void before(){
        System.out.println("任务开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        long time1= System.currentTimeMillis();
        System.out.println("开始："+time1);
        // TODO 业务
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
        try {
            amqpService.sendEmail(mailDo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long time2= System.currentTimeMillis();
        System.out.println("耗时："+(time2-time1));
        after();
    }

    private void after(){
        System.out.println("任务结束执行");
    }

}