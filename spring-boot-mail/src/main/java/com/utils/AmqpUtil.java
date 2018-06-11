package com.utils;

import com.amqp.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;

/**
 * 描述:
 *
 * @Author LJL
 * @Date 2018/6/7 0007 17:20
 */
@Component  // 关键1，将该工具类注册为组件，   加粗！！！
public class AmqpUtil {
    @Autowired
    public AmqpService amqpService;

        public static AmqpUtil amqpUtil;  // 关键2

        public AmqpUtil() {
        }


        // 关键3
        @PostConstruct
        public void init() {
            amqpUtil = this;
            amqpUtil.amqpService=this.amqpService;
        }
}
