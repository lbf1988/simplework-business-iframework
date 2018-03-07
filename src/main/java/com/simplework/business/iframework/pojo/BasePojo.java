package com.simplework.business.iframework.pojo;

import com.alibaba.fastjson.JSONObject;
import com.simplework.business.iframework.json.ToJson;

import java.io.Serializable;

/**
 * @Author Brant Liu
 * @Desc TODO POJO超类
 * @MailTo lbf1988@qq.com
 * @Date 2017/08/14
 */
public class BasePojo implements ToJson,Serializable {
    @Override
    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
