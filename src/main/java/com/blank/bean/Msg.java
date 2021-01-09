package com.blank.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    //状态码1位成功，0为失败
    private Integer code;
    //提示信息
    private String Msg;
    //用户要返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg("操作成功！");
        return result;
    }
    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(0);
        result.setMsg("操作失败！");
        return result;
    }

    //定义用户添加信息的方法
    public Msg add(String key,Object value){
        this.extend.put(key,value);
        return this;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
