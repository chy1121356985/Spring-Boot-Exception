package com.renderg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.renderg.pojo.SlaveInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenhy
 * @since 2022-03-18
 */
public interface SlaveInfoService extends IService<SlaveInfo> {
    String findSlave();

}
