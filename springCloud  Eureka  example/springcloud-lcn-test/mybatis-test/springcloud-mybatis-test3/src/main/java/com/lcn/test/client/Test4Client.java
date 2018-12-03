package com.lcn.test.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "lcntest4",fallback = Test4ClientHystric.class)
public interface Test4Client {
	@PostMapping(value = "/message/save")
    int save(@RequestParam(value = "message")String message);
}
