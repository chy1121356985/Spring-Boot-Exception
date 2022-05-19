package com.renderg.task;

import com.renderg.service.RamService;
import com.renderg.service.SlaveInfoService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class testTask {


    @Autowired
    private SlaveInfoService infoService;

    @Autowired
    private RamService ramService;



//    @XxlJob("findAll")
    @Scheduled(cron = "0 0 6 * * ?")
    @Scheduled(cron = "0 0 7 * * ?")
    @Scheduled(cron = "0 30 8 * * ?")
    @Scheduled(cron = "0 0 14 * * ?")
    @Scheduled(cron = "0 0 18 * * ?")
    void findAll(){
        infoService.findSlave();
    }

    //@Scheduled(fixedRate = 3600000)
    @XxlJob(("findRam"))
    void findRam() throws Exception{
        ramService.findRam();

    }
}
