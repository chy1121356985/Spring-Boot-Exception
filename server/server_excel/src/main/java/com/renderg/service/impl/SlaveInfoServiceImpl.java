package com.renderg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renderg.mapper.SlaveInfoMapper;
import com.renderg.pojo.*;
import com.renderg.service.SlaveInfoService;
import com.renderg.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenhy
 * @since 2022-03-18
 */
@Service
public class SlaveInfoServiceImpl extends ServiceImpl<SlaveInfoMapper, SlaveInfo> implements SlaveInfoService {

    @Autowired
    private HttpUtils httpUtils;

    @Override
    public String findSlave() {
        List<OneException> oneExceptions = new ArrayList<>();
        List<Hardware> hardwares = new ArrayList<>();
        HashMap<String, List<DiskStr>> diskMap = new HashMap<>();
        List<DiskStr> diskStrList = new ArrayList<>();
        HashMap<String, List<RAMFree>> ramMap = new HashMap<>();
        ArrayList<RAMFree> ramList = new ArrayList<>();
        HashMap<String, List<Enable>> enableMap = new HashMap<>();
        ArrayList<Enable> enableList = new ArrayList<>();
        ArrayList<RamError> ramErrors = new ArrayList<>();

        //获取接口请求转换json类型
        String forest = httpUtils.forest();
        List<JsonContent> jsonContents = JSONObject.parseArray(forest, JsonContent.class);
        for (JsonContent jsonContent : jsonContents) {


            List<Info> info = jsonContent.getInfo();
            List<Settings> settings = jsonContent.getSettings();

            for (int i = 0; i < info.size(); i++) {
                //获取硬盘内存
                String diskStr = info.get(i).getDiskStr();
                int indexOf = diskStr.indexOf("(");
                int indexOf1 = diskStr.indexOf("o");
                Boolean end = true;
                Hardware hardware = new Hardware();
                DiskStr diskStrs = new DiskStr();
                RAMFree ramFree = new RAMFree();
                Enable enable = new Enable();
                RamError ramError = new RamError();
                SlaveInfo slaveInfo = new SlaveInfo();
                String substring = null;
                int diskStrC = 0;

                try {
                    substring = diskStr.substring(indexOf + 1, indexOf1 - 4);
                    diskStrC = Integer.parseInt(substring.substring(0, substring.indexOf(".")));
                    if (diskStrC <= 9) {
                        hardware.setDiskStr(info.get(i).getDiskStr());
                        diskStrs.setId(info.get(i).getId());
                        diskStrs.setDiskStr(info.get(i).getDiskStr());
                        diskStrList.add(diskStrs);

                        end = false;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage() + ":" + diskStr);
                }
                //C盘内存不足10GB

                //是否被标记修改
                if (settings.get(i).getCmmt().isEmpty()) {
                    if (info.get(i).getStat() == 0 || info.get(i).getStat() == 3 || info.get(i).getStat() == 4) {
                        if (!settings.get(i).getEnable()) {
                            hardware.setCmmt(settings.get(i).getCmmt());
                            hardware.setEnable(settings.get(i).getEnable());

                            enable.setId(info.get(i).getId());
                            enable.setEnable(settings.get(i).getEnable());
                            enable.setCmmt(settings.get(i).getCmmt());
                            enableList.add(enable);

                            end = false;
                        }
                    }

                }

               /* if (info.get(i).getRamFree()>70000000000L&&info.get(i).getRamFree()<138000000000L){
                    if (info.get(i).getRamFree()<82000000000L){
                        end= false;
                    }
                }
                if (info.get(i).getRamFree()>=0L&&info.get(i).getRamFree()<69000000000L){
                    if (info.get(i).getRamFree()<41000000000L){
                        end= false;
                    }
                }*/

                //运行内存是否小于百分之65
                QueryWrapper<SlaveInfo> wrapper = new QueryWrapper<>();
                wrapper.eq("id", info.get(i).getId());
                SlaveInfo slave = baseMapper.selectOne(wrapper);
                System.out.println(info.get(i).getId() + "S");

                try {
                    if (slave.getArm() > info.get(i).getRam()) {
                        ramError.setAgoRam(slave.getArm());
                        ramError.setId(info.get(i).getId());
                        ramError.setRam(info.get(i).getRam());
                        ramErrors.add(ramError);

                        hardware.setRam(info.get(i).getRam());
                        hardware.setRamFree(info.get(i).getRamFree());
                        end = false;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + ":" + info.get(i).getId());
                }
                slaveInfo.setId(info.get(i).getId());
                slaveInfo.setArm(info.get(i).getRam());
                baseMapper.updateById(slaveInfo);

                //满足任意条件报出
                if (end == false) {
                    OneException oneException = new OneException(info.get(i), settings.get(i));
                    oneExceptions.add(oneException);
                    hardware.setId(info.get(i).getId());
                    hardwares.add(hardware);
                }
            }

        }
        diskMap.put("C盘存储不足20GB一共" + diskStrList.size() + "台", diskStrList);
        ramMap.put("内存剩余不到50%一共" + ramList.size() + "台", ramList);
        enableMap.put("节点被标记一共" + enableList.size() + "台", enableList);
        Datas datas = new Datas(oneExceptions.size(), diskMap, ramMap, enableMap);

//        System.out.println(diskMap);
//        System.out.println(ramMap);
//        System.out.println(enableMap);

        int size = diskStrList.size() + ramErrors.size() + enableList.size();
        String str = "异常节点数量:" + size + "\n" + "C盘可用空间不足10GB: " + diskStrList.size() + "台" + "\n";

        for (DiskStr diskStr : diskStrList) {
            try {
                str = str + "ID:" + diskStr.getId() + ";C盘可用存储:" + diskStr.getDiskStr().substring(diskStr.getDiskStr().indexOf("(") + 1, diskStr.getDiskStr().indexOf("o") - 1) + "\n";
            } catch (Exception e) {
                System.out.println(e.getMessage() + ":" + diskStr);
            }
        }

        str = str + "内存减小数量: " + ramErrors.size() + "台" + "\n";
        for (RamError ramError : ramErrors) {
            str = str + ramError + "\n";
        }

        str = str + "Disabled数量: " + enableList.size() + "台" + "\n";
        for (Enable enable : enableList) {
            str = str + enable + "\n";
        }

        System.out.println(str);
        httpUtils.feishu(str, "oc_1b4eec9c7b8bf2077930a1a7b42614eb");
        return str;

    }
}