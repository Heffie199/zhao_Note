package com.lcn.test.service.impl;

import com.codingapi.tx.config.service.TxManagerTxUrlService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService{
	private Logger logger = LoggerFactory.getLogger(TxManagerTxUrlServiceImpl.class);
	
    @Value("${tm.manager.url}")
    private String url;

    @Override
    public String getTxUrl() {
    	logger.info("load tm.manager.url = " + url);
        return url;
    }
}
