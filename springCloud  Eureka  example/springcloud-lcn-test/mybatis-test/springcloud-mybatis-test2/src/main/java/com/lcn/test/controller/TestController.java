package com.lcn.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcn.test.service.TestService;

@RestController
@RequestMapping("/message")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(TestController.class);
		
	@Autowired
	private TestService demoService;
	
	@PostMapping("/save")
	@ResponseBody
	public int saveMessage(@RequestParam(defaultValue = "",required = false)String message){
		logger.info("接受到/message/save请求:" + message);
	    return demoService.saveMessage(message);
	}
}
