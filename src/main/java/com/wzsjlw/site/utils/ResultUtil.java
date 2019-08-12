package com.wzsjlw.site.utils;

import java.io.Serializable;

/**
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
public class ResultUtil<T> implements Serializable {

    private static final long serialVersionUID = 9104048092175632249L;
    /**
     * 成功 200
     */
    private static final Integer SUCCESS = 200;
    /**
     * 失败 400
     */
    private static final Integer FAIL = 400;
    /**
     * 路径/接口不存在 404
     */
    private static final Integer NOT_FOUND = 404;
    /**
     * 内部服务器错误状态码 500
     */
    private static final Integer INTERNAL_SERVER_ERROR = 500;
    /**
     * 响应的代码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 相应的数据
     */
    private T data;

    public ResultUtil() {
    }

    public static <T> ResultUtil<T> success() {
        return new ResultUtil<T>()
                .setCode(SUCCESS)
                .setMsg("成功");
    }

    public static <T> ResultUtil<T> success(String msg, T data) {
        return new ResultUtil<T>()
                .setCode(SUCCESS)
                .setMsg(msg)
                .setData(data);
    }

    public static <T> ResultUtil<T> success(T data) {
        return new ResultUtil<T>()
                .setCode(SUCCESS)
                .setMsg("成功")
                .setData(data);
    }

    public static <T> ResultUtil<T> fail() {
        return new ResultUtil<T>()
                .setCode(FAIL)
                .setMsg("失败");
    }

    public static <T> ResultUtil<T> fail(String msg) {
        return new ResultUtil<T>()
                .setCode(FAIL)
                .setMsg(msg);
    }

    public Integer getCode() {
        return code;
    }

    public ResultUtil<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultUtil<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultUtil<T> setData(T data) {
        this.data = data;
        return this;
    }
}
