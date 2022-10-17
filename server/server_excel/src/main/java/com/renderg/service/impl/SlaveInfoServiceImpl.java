package com.renderg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renderg.entity.SlaveInfoMongo;
import com.renderg.entity.SlaveSettingsMongo;
import com.renderg.mapper.SlaveInfoMapper;
import com.renderg.pojo.*;
import com.renderg.service.SlaveInfoService;
import com.renderg.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

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


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public String findSlave() {
        List<DiskStr> diskStrList = new ArrayList<>();
        ArrayList<Enable> enableList = new ArrayList<>();
        ArrayList<RamError> ramErrors = new ArrayList<>();
        ArrayList<DiskOther> diskOthers = new ArrayList<>();
        ArrayList<DiskStr> diskStrLists = new ArrayList<>();
//        ArrayList<SlaveInfoMongo> infoLists = new ArrayList<>();
//        ArrayList<SlaveInfoMongo> infoList = new ArrayList<>();


        List<SlaveInfoMongo> info = mongoTemplate.findAll(SlaveInfoMongo.class);
        List<SlaveSettingsMongo> settings = mongoTemplate.findAll(SlaveSettingsMongo.class);


        for (int i = 0; i < info.size(); i++) {

            //获取硬盘内存
            String diskStr = info.get(i).getDiskStr();
            int indexOf = diskStr.indexOf("(");
            int indexOf1 = diskStr.indexOf("o");
//            int indexOf2 = diskStr.indexOf(",");
//            int indexOf3 = diskStr.indexOf("D");

//            SlaveInfoMongo slaveInfoMongo = new SlaveInfoMongo();
            DiskStr diskStrs = new DiskStr();
            RamError ramError = new RamError();
            SlaveInfo slaveInfo = new SlaveInfo();
            DiskOther diskOther = new DiskOther();
            String substring = null;
            int diskStrC = 0;

            //判断是否是无效ID，true直接跳过处理。
            if (!Pattern.matches("w-.*", info.get(i).getId())) {
                continue;
            }
            if (info.get(i).getDiskStr().length() <= 2) {
                continue;
            }

            //盘符列为空或者是-1直接报出
            //对C盘进行筛选 不足10GB
            if (info.get(i).getDiskStr().length() <= 12) {
                try {
                    if (!info.get(i).getDiskStr().contains("TB")) {
                        if (!info.get(i).getDiskStr().contains("GB")) {
                            diskStrs.setId(info.get(i).getId());
                            diskStrs.setDiskStr(info.get(i).getDiskStr());
                            diskStrs.setStat(info.get(i).getStat());
                            diskStrList.add(diskStrs);
                        } else {
                            int intC = Integer.parseInt(info.get(i).getDiskStr().substring(0, info.get(i).getDiskStr().indexOf(".")));
                            if (intC <= 9) {
                                diskStrs.setId(info.get(i).getId());
                                diskStrs.setDiskStr(info.get(i).getDiskStr());
                                diskStrs.setStat(info.get(i).getStat());
                                diskStrList.add(diskStrs);
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage() + "-----");
                }


            } else {
                try {
                    substring = diskStr.substring(indexOf + 1, indexOf1 - 1);
                    if (!substring.contains("TB")) {
                        if (!substring.contains("GB")) {
                            diskStrs.setId(info.get(i).getId());
                            diskStrs.setDiskStr(substring);
                            diskStrs.setStat(info.get(i).getStat());
                            diskStrList.add(diskStrs);
                        } else {
                            if (substring.contains(".")) {
                                diskStrC = Integer.parseInt(substring.substring(0, substring.indexOf(".")));
                                if (diskStrC <= 9) {
                                    diskStrs.setId(info.get(i).getId());
                                    diskStrs.setDiskStr(substring);
                                    diskStrs.setStat(info.get(i).getStat());
                                    diskStrList.add(diskStrs);
                                }
                            } else {
                                diskStrC = Integer.parseInt(substring.substring(0, substring.indexOf("G") - 1));
                                if (diskStrC <= 9) {
                                    diskStrs.setId(info.get(i).getId());
                                    diskStrs.setDiskStr(substring);
                                    diskStrs.setStat(info.get(i).getStat());
                                    diskStrList.add(diskStrs);
                                }
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage() + ":" + diskStr + "--- " + info.get(i).getId());
                }
            }


            //对D盘进行筛选 不足10GB
//            try {
//                String dSubstring = diskStr.substring(indexOf2 + 2, indexOf3 - 4);
//                if (!dSubstring.contains("TB")) {
//                    if (!dSubstring.contains("GB")) {
//                        slaveInfoMongo.setDiskStr(dSubstring);
//                        slaveInfoMongo.setId(info.get(i).getId());
//                        infoList.add(slaveInfoMongo);
//                    } else {
//                        if (dSubstring.contains(".")) {
//                            int diskStrD = Integer.parseInt(dSubstring.substring(0, dSubstring.indexOf(".")));
//                            if (diskStrD <= 9) {
//                                slaveInfoMongo.setDiskStr(dSubstring);
//                                slaveInfoMongo.setId(info.get(i).getId());
//                                infoList.add(slaveInfoMongo);
//                            }
//                        } else {
//                            int diskStrZ = Integer.parseInt(dSubstring.substring(0, dSubstring.indexOf("G") - 1));
//                            if (diskStrZ <= 9) {
//                                slaveInfoMongo.setDiskStr(dSubstring);
//                                slaveInfoMongo.setId(info.get(i).getId());
//                                infoList.add(slaveInfoMongo);
//                            }
//                        }
//                    }
//                }
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage() + ":" + diskStr + "DD" + info.get(i).getId());
//            }


            //非c、d盘检查
            try {
                //获取enable字段
                Boolean enable = mongoTemplate.findById(info.get(i).getId(), SlaveSettingsMongo.class).getEnable();
                //未标记检查
                if (enable) {
                    if (info.get(i).getDiskStr().length() > 15) {
                        diskOther.setId(info.get(i).getId());
                        diskOther.setDiskStr(info.get(i).getDiskStr());
                        diskOthers.add(diskOther);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage() + "----" + info.get(i).getId());
            }

            //运行内存是否变小大于5GB
            QueryWrapper<SlaveInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("id", info.get(i).getId());
            SlaveInfo slave = baseMapper.selectOne(wrapper);
            if (slave == null) {
                slaveInfo.setId(info.get(i).getId());
                slaveInfo.setArm(info.get(i).getRam());
                baseMapper.insert(slaveInfo);
            }

            try {
                if (slave.getArm() > 34359738368L) {
                    if (slave.getArm() - 5368709120L >= info.get(i).getRam()) {
                        ramError.setAgoRam(slave.getArm());
                        ramError.setId(info.get(i).getId());
                        ramError.setRam(info.get(i).getRam());
                        ramErrors.add(ramError);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + "异常运行内存" + info.get(i).getId());
            }
            slaveInfo.setId(info.get(i).getId());
            slaveInfo.setArm(info.get(i).getRam());
            baseMapper.updateById(slaveInfo);

        }


        for (int i = 0; i < settings.size(); i++) {

            Enable enable = new Enable();
            //是否被标记修改
            try {
                if (settings.get(i).getCmmt().length() == 0) {
                    Integer stat = mongoTemplate.findById(settings.get(i).getId(), SlaveInfoMongo.class).getStat();
                    if (stat == 0 || stat == 3 || stat == 4) {
                        if (!settings.get(i).getEnable()) {
                            enable.setId(settings.get(i).getId());
                            enable.setEnable(settings.get(i).getEnable());
                            enable.setCmmt(settings.get(i).getCmmt());
                            enableList.add(enable);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + "异常标记" + settings.get(i).getId());
            }
        }

        int size = diskStrList.size() + ramErrors.size() + enableList.size() + diskOthers.size();
        //对id进行排序
//        Collections.sort(diskStrList, new Comparator<DiskStr>() {
//            @Override
//            public int compare(DiskStr o1, DiskStr o2) {
//                if (o1.getId().compareTo(o2.getId()) >= 1) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        });
        List<DiskStr> diskStrBytes = new ArrayList<>();
        List<DiskStr> diskStrMB = new ArrayList<>();
        List<DiskStr> diskStrGB = new ArrayList<>();
        ArrayList<DiskStr> diskStrNull = new ArrayList<>();
        System.out.println(diskStrList);

        for (DiskStr diskStr : diskStrList) {
            if (diskStr.getDiskStr().contains("Bytes")) {
                diskStrBytes.add(diskStr);
            } else if (diskStr.getDiskStr().length() == 0) {
                diskStrNull.add(diskStr);
            } else if (diskStr.getDiskStr().contains("MB")) {
                diskStrMB.add(diskStr);
            } else if (diskStr.getDiskStr().contains("GB")) {
                diskStrGB.add(diskStr);
            }
        }

        Collections.sort(diskStrBytes);
        Collections.sort(diskStrMB);
        Collections.sort(diskStrGB);

        for (DiskStr diskStr : diskStrNull) {
            diskStrLists.add(diskStr);
        }
        for (DiskStr diskStr : diskStrBytes) {
            diskStrLists.add(diskStr);
        }
        for (DiskStr diskStr : diskStrMB) {
            diskStrLists.add(diskStr);
        }
        for (DiskStr diskStr : diskStrGB) {
            diskStrLists.add(diskStr);
        }


        String str = "异常节点数量:" + size + "\n" + "C盘可用空间不足10GB: " + diskStrLists.size() + "台" + "\n";

        for (DiskStr diskStr : diskStrLists) {
            String desc = null;
            String ex3 = null;
            try {
                String stat = TestEnums.showDesc(diskStr.getStat());
                SlaveSettingsMongo setting = mongoTemplate.findById(diskStr.getId(), SlaveSettingsMongo.class);
                desc = setting.getDesc();
                ex3 = setting.getEx3();
                str = str + "ID:" + diskStr.getId() + "; 状态:" + stat + "; 系统:" + desc + "; C盘大小:" + ex3 + "; C盘可用存储:" + diskStr.getDiskStr() + "\n";
            } catch (Exception e) {
                System.out.println(e.getMessage() + ":" + diskStr);
            }
        }

//        str = str + "D盘可用空间不足10GB:" + infoList.size() + "台" + "\n";

//        if (infoList.size() > 0) {
//            Collections.sort(infoList, new Comparator<SlaveInfoMongo>() {
//                @Override
//                public int compare(SlaveInfoMongo o1, SlaveInfoMongo o2) {
//                    if (o1.getId().compareTo(o2.getId()) >= 1) {
//                        return 1;
//                    } else {
//                        return -1;
//                    }
//                }
//            });
//        }


//        List<SlaveInfoMongo> SlaveInfoMongoBytes = new ArrayList<>();
//        List<SlaveInfoMongo> SlaveInfoMongoMB = new ArrayList<>();
//        List<SlaveInfoMongo> SlaveInfoMongoGB = new ArrayList<>();
//        ArrayList<SlaveInfoMongo> SlaveInfoMongoNull = new ArrayList<>();
//
//        for (SlaveInfoMongo infoMongo : infoList) {
//            if (infoMongo.getDiskStr().contains("Bytes")) {
//                SlaveInfoMongoBytes.add(infoMongo);
//            } else if (infoMongo.getDiskStr().length() == 0) {
//                SlaveInfoMongoNull.add(infoMongo);
//            } else if (infoMongo.getDiskStr().contains("MB")) {
//                SlaveInfoMongoMB.add(infoMongo);
//            } else if (infoMongo.getDiskStr().contains("GB")) {
//                SlaveInfoMongoGB.add(infoMongo);
//            }
//        }
//
//        Collections.sort(SlaveInfoMongoBytes);
//        Collections.sort(SlaveInfoMongoMB);
//        Collections.sort(SlaveInfoMongoGB);
//
//        for (SlaveInfoMongo InfoMongo : SlaveInfoMongoNull) {
//            infoLists.add(InfoMongo);
//        }
//        for (SlaveInfoMongo InfoMongo : SlaveInfoMongoBytes) {
//            infoLists.add(InfoMongo);
//        }
//        for (SlaveInfoMongo InfoMongo : SlaveInfoMongoMB) {
//            infoLists.add(InfoMongo);
//        }
//        for (SlaveInfoMongo InfoMongo : SlaveInfoMongoGB) {
//            infoLists.add(InfoMongo);
//        }
//
//
//        for (SlaveInfoMongo infos : infoLists) {
//            str = str + "ID:" + infos.getId() + ";D盘可用存储:" + infos.getDiskStr() + "\n";
//        }

        str = str + "存在非C盘符的节点:" + diskOthers.size() + "台" + "\n";

        if (diskOthers.size() > 0) {
            Collections.sort(diskOthers, new Comparator<DiskOther>() {
                @Override
                public int compare(DiskOther o1, DiskOther o2) {
                    if (o1.getId().compareTo(o2.getId()) >= 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
        }

        for (DiskOther diskOther : diskOthers) {
            String desc = null;
            try {
                SlaveSettingsMongo setting = mongoTemplate.findById(diskOther.getId(), SlaveSettingsMongo.class);
                desc = setting.getDesc();
                str = str + "ID:" + diskOther.getId() + "; 系统:" + desc + "\n";
            } catch (Exception e) {
                System.out.println(e.getMessage() + ":" + diskOther);
            }
        }

        str = str + "运行内存减小数量: " + ramErrors.size() + "台" + "\n";

        if (ramErrors.size() > 0) {
            Collections.sort(ramErrors, new Comparator<RamError>() {
                @Override
                public int compare(RamError o1, RamError o2) {
                    if (o1.getId().compareTo(o2.getId()) >= 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
        }
        for (RamError ramError : ramErrors) {
            long agoRam = ramError.getAgoRam() / 1073741824 + 1;
            long ram = ramError.getRam() / 1073741824;
            str = str + "ID:" + ramError.getId() + " 昨日内存:" + agoRam + "GB; 实时内存:" + ram + "GB" + "\n";
        }

        str = str + "Disabled数量: " + enableList.size() + "台" + "\n";
        if (enableList.size() > 0) {
            Collections.sort(enableList, new Comparator<Enable>() {
                @Override
                public int compare(Enable o1, Enable o2) {
                    if (o1.getId().compareTo(o2.getId()) >= 1) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
        }
        for (Enable enable : enableList) {
            String desc = null;
            try {
                SlaveSettingsMongo setting = mongoTemplate.findById(enable.getId(), SlaveSettingsMongo.class);
                desc = setting.getDesc();
                str = str + "ID:" + enable.getId() + "; enable:" + enable.getEnable() + "; os:" + desc + "; cmmt:" + enable.getCmmt() + "\n";
            } catch (Exception e) {
                System.out.println(e.getMessage() + ":" + enable);
            }

        }

        System.out.println(str + new Date());
        httpUtils.feishu(str, "oc_1b4eec9c7b8bf2077930a1a7b42614eb");
        return str;

    }


}
