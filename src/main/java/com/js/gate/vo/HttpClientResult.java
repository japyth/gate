package com.js.gate.vo;

import java.io.Serializable;

/**
 *@Author: JS
 *@Date: 11:51 2019/4/21
 *@Description: 请求第三方接口返回类
 */
public class HttpClientResult implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public HttpClientResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
