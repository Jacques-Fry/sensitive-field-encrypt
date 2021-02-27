package com.jacques.sensitive.utils;

import java.io.Serializable;

/**
 * JSON返回格式
 *
 * @info JSON返回格式
 * @version 1.0.0
 * @author Jacques·Fry
 * @since 2021/1/4 17:56
 */
public class Result<T> implements Serializable {

    private boolean flag;
    private Integer code;
    private String msg;
    private T data;


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
        this.flag = true;
        this.code = 200;
        this.msg = "执行成功";
    }

    public Result(String msg) {
        this.flag = true;
        this.code = 200;
        this.msg =msg;
    }

    public Result(String msg, T data) {
        this.flag = true;
        this.code = 200;
        this.msg = msg;
        this.data=data;
    }

    public Result(boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code =code;
        this.msg = msg;
    }
}
