package com.lcn.test.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "lcntest2",fallback = Test2ClientHystric.class)
public interface Test2Client {
    @PostMapping(value = "/message/save")
    int save(@RequestParam(value = "message")String message);
}
