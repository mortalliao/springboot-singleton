package com.springboot.demo.auth.entity.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后端统一消息定义协议 Message  之后前后端数据交互都按照规定的类型进行交互
 * {
 * meta:{"code":code,“msg”:message}
 * data:{....}
 * }
 *
 * @author Jim
 */
@Setter
@Getter
public class Message {

    /**
     * 消息头meta 存放状态信息 code message
     */
    private Map<String, Object> meta = new HashMap<>();

    /**
     * 消息内容  存储实体交互数据
     */
    private Map<String, Object> data = new HashMap<>();

    public Message addMeta(String key, Object object) {
        this.meta.put(key, object);
        return this;
    }

    public Message addData(String key, Object object) {
        this.data.put(key, object);
        return this;
    }

    public Message ok(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.TRUE);
        this.addMeta("code", statusCode);
        this.addMeta("msg", statusMsg);
        this.addMeta("timestamp", new Timestamp(System.currentTimeMillis()));
        return this;
    }

    public Message ok() {
        this.addMeta("success", Boolean.TRUE);
        this.addMeta("code", StatusCodeEnum.SUCCESS.getStatus());
        this.addMeta("msg", StatusCodeEnum.SUCCESS.getMessage());
        this.addMeta("timestamp", new Timestamp(System.currentTimeMillis()));
        return this;
    }

    public Message ok(Object obj) {
        ok();
        this.data = (Map<String, Object>) JSON.parseObject(JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat));
        return this;
    }

    public Message error(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.FALSE);
        this.addMeta("code", statusCode);
        this.addMeta("msg", statusMsg);
        this.addMeta("timestamp", new Timestamp(System.currentTimeMillis()));
        return this;
    }

}
