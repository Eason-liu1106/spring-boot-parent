package com.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 描述:解决spring注入Job
 *
 * @author LJL
 * @date 2018/6/12 0012 13:44
 */
@Component
public class SpringJobFactory extends AdaptableJobFactory{
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    /**
     * Create an instance of the specified job class.
     * <p>Can be overridden to post-process the job instance.
     *
     * @param bundle the TriggerFiredBundle from which the JobDetail
     *               and other info relating to the trigger firing can be obtained
     * @return the job instance
     * @throws Exception if job instantiation failed
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object instance = super.createJobInstance(bundle);
        autowireCapableBeanFactory.autowireBean(instance);
        return instance;
    }
}
