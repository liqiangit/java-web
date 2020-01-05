package com.hawkingfoo.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestNameReqDTO implements Serializable {

    /**
     * 名称
     */
    private String name;
    private String chn;
    private Long size;


}
