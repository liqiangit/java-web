package com.hawkingfoo.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultResDTO implements Serializable {

    /**
     * 返回码
     */
    private String code = "0";

    /**
     * 返回消息
     */
    private String msg = "success";

    /**
     * 返回结果
     */
    private Object result;
}
