package com.renderg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renderg.pojo.Info;
import com.renderg.pojo.JsonContent;
import com.renderg.pojo.Settings;
import com.renderg.service.RamService;
import com.renderg.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author 11213
 * 2022年4月7日14:03:16
 */
@Service
public class RamServiceImp implements RamService {

    @Autowired
    private HttpUtils httpUtils;

    @Override
    public String findRam() {

        List<Info> infoList = new ArrayList<>();

        //获取接口数据
        String forest = httpUtils.forest();
        //转换json格式
        List<JsonContent> jsonContents = JSONObject.parseArray(forest, JsonContent.class);
        for (JsonContent jsonContent : jsonContents) {
            List<Info> info = jsonContent.getInfo();
            List<Settings> settings = jsonContent.getSettings();

            for (int i = 0; i < info.size(); i++) {
                Info inf = new Info();

                if (!Pattern.matches("w-.*", info.get(i).getId())) {
                    continue;
                }

                if (!settings.get(i).getEnable()) {
                    continue;
                }
                if (settings.get(i).getCmmt().isEmpty()) {
                    continue;

                }


                if (info.get(i).getStat() == 2) {
                    if (settings.get(i).getEnable()) {
                        if (settings.get(i).getCmmt().length() == 0) {
                            try {
                                if (info.get(i).getRam() * 0.70 >= info.get(i).getRamFree()) {
                                    inf.setId(info.get(i).getId());
                                    inf.setRam(info.get(i).getRam());
                                    inf.setRamFree(info.get(i).getRamFree());
                                    inf.setStat(info.get(i).getStat());
                                    infoList.add(inf);

                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage() + ":" + info.get(i).getRam() + info.get(i).getRamFree());
                            }
                        }
                    }

                }
            }
        }
        Collections.sort(infoList, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if (o1.getId().compareTo(o2.getId()) >= 1) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        String str = "空闲节点内存占用率异常:" + infoList.size() + "台" + "\n";
        if (infoList.size() > 0) {
            for (Info info : infoList) {
                DecimalFormat df = new DecimalFormat("0");
                Long Ram = info.getRam() / 1073741824 + 1;
                Long RamFree = info.getRamFree() / 1073741824;
                double l = (double) RamFree / Ram;
                double v = 1 - l;
                System.out.println(v);
                String format = df.format(v * 100);
                str = str + "ID:" + info.getId() + " 可用内存:" + RamFree + "GB" + " 内存总量:" + Ram + "GB" + " 占用率:" + format + "%" + "\n";
            }
            httpUtils.feishu(str, "oc_aff73dae5b339595bf52d611d05a8abf");
            System.out.println(str + new Date());
        }

        return str;
    }
}
