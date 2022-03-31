package com.renderg.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务demo
 *
 * @author kuazhen
 * @Date:Created in 3/18/22
 */
@Component
public class TaskDemo {
/*

    @Scheduled(fixedRate = 20000)
    public void fixedRate() {
        //忽略上一次是否执行完毕，到了两秒不管上次有没有执行完依然进行下次任务
        System.out.println("每两秒执行一次：fixedRate>>>" + new Date());
    }
*/

    /*@Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        //不忽略上次执行，等上次执行完毕后间隔两秒执行下次任务
        System.out.println("每两秒执行一次：fixedDelay>>>" + new Date());
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void initialDelay() {
        // initialDelay启动任务时延迟两秒执行，并且不忽略上次执行，等上次执行完毕后间隔两秒执行下次任务
        System.out.println("initialDelay>>>" + new Date());
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void cron5s() {
        // cron表达式的方式执行任务：每五秒触发一次
        System.out.println("我是每五秒触发一次：cron5s>>>" + new Date());
    }*/

   /* @Scheduled(cron = "0 30 8 * * ?")
    public void cron12pm() {
        // cron表达式的方式执行任务：每天中午12点触发一次
        System.out.println("我是每天中午12点触发一次：cron12pm>>>" + new Date());
    }*/

}
