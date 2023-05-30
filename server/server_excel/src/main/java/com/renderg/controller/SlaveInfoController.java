package com.renderg.controller;


import com.renderg.service.RamService;
import com.renderg.service.SlaveInfoService;
import com.renderg.util.R;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SlaveInfoService infoService;

    @Autowired
    private RamService ramService;

    @GetMapping("/find")
    public R findAll() {
        System.out.println("---------------------");
        String msg = infoService.findSlave();
        return R.ok().data("msg", msg);
    }


    @GetMapping("/findRam")
    public R findRam() {
        String ram = ramService.findRam();
        return R.ok().data("msg", ram);
    }



}

