package com.lcn.test.service.impl;

import com.codingapi.tx.netty.service.TxManagerHttpRequestService;
import com.lorne.core.framework.utils.http.HttpUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TxManagerHttpRequestServiceImpl implements TxManagerHttpRequestService{
	private Logger logger = LoggerFactory.getLogger(TxManagerHttpRequestServiceImpl.class);
	
    @Override
    public String httpGet(String url) {
    	logger.info("调用httpGet方法 url = " + url);
        String res = HttpUtils.get(url);
        return res;
    }

    @Override
    public String httpPost(String url, String params) {
    	logger.info("调用httpPost方法 url = " + url + ",params = " + params);
        String res = HttpUtils.post(url,params);
        return res;
    }
}
