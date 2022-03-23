package com.renderg.util;

import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Request;
import org.springframework.stereotype.Component;

@Component
public interface HttpUtils {

    @Request(
            url = "http://10.6.6.151:8080/api/slaves?Data=infosettings")
    String forest();


    @Post("http://support.sfbp.cn/api/v1/wechat/push/text")
    String feishu(@JSONBody("msg") String msg, @JSONBody("wxid") String wxid);

}
