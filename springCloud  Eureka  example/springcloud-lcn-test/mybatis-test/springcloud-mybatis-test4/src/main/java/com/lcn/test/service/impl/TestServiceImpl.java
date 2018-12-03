package com.lcn.test.service.impl;

import com.codingapi.tx.annotation.ITxTransaction;
import com.codingapi.tx.aop.bean.TxTransactionLocal;
import com.lcn.test.dao.Message4Mapper;
import com.lcn.test.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService,ITxTransaction{
    @Autowired
    private Message4Mapper testMapper;

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    @Transactional
    public int saveMessage(String message) {
    	String groupId = TxTransactionLocal.current().getGroupId();
    	String saveMsg = "message4:" + message + ",groupId:" + groupId;
    	
        int rs = testMapper.saveMessage(saveMsg);
        logger.info("***********************message4 save done");
        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return rs;
    }
}
