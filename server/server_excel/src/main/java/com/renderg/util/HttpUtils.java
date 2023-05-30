package com.renderg.util;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.*;
import org.springframework.stereotype.Component;

@Component
public interface HttpUtils {

    @Request(
            url = "http://10.6.6.151:8080/api/slaves?Data=infosettings")
    String forest();


    @Post("http://support.sfbp.cn/api/v1/wechat/push/text")
    String feishu(@JSONBody("msg") String msg, @JSONBody("wxid") String wxid);

    @Post(
            url = "http://10.6.6.85:3001/lark/pushMessage",
            headers = {
                    "larkToken: K5k5YEXLe40HUjb1aKKKmfhU5cxav4Ur9CZ5cbn9EF0",
                    "Content-Type: application/json"}
    )
    String feishu_type(@JSONBody JSONObject data);


}
