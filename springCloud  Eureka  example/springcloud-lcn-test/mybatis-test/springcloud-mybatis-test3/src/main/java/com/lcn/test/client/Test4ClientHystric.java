package com.lcn.test.client;

import org.springframework.stereotype.Component;


@Component
public class Test4ClientHystric implements Test4Client {
    @Override
    public int save(String message) {
        System.out.println("进入断路器-save。。。");
        throw new RuntimeException("save 保存失败.");
    }
}
