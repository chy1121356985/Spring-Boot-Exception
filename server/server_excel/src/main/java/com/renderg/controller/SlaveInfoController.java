package com.renderg.controller;


import com.renderg.service.SlaveInfoService;
import com.renderg.util.HttpUtils;
import com.renderg.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenhy
 * @since 2022-03-18
 */
@RestController
@RequestMapping("/exception/slave-info")
public class SlaveInfoController {

    @Autowired()
    private SlaveInfoService infoService;

    private HttpUtils httpUtils;

    @GetMapping("/find")
    @Scheduled(cron = "0 30 8 * * ?")
    public R findAll() {

        String msg = infoService.findSlave();
        return R.ok().data("msg", msg);
    }

}

