package com.lcn.test.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "lcntest3",fallback = Test3ClientHystric.class)
public interface Test3Client {
	@PostMapping(value = "/message/save")
    int save(@RequestParam(value = "message")String message);
}
