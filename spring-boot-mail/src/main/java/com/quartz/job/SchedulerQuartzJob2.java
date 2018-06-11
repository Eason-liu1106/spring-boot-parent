package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SchedulerQuartzJob2 implements Job {
    private void before(){
        System.out.println("任务2开始执行");
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        System.out.println("开始2："+System.currentTimeMillis());
        // TODO 业务
        System.out.println("结束2："+System.currentTimeMillis());
        after();
    }

    private void after(){
        System.out.println("任务2结束执行");
    }

}