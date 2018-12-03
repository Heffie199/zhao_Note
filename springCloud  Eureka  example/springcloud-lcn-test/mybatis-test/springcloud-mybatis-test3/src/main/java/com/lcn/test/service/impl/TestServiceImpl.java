package com.lcn.test.service.impl;

import com.codingapi.tx.annotation.ITxTransaction;
import com.codingapi.tx.aop.bean.TxTransactionLocal;
import com.google.common.util.concurrent.ExecutionError;
import com.lcn.test.client.Test4Client;
import com.lcn.test.dao.Message3Mapper;
import com.lcn.test.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService,ITxTransaction{
    @Autowired
    private Test4Client test4Client;

    @Autowired
    private Message3Mapper testMapper;

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    @Transactional
    public int saveMessage(String message) {
    	String groupId = TxTransactionLocal.current().getGroupId();
    	String saveMsg = "message3:" + message + ",groupId:" + groupId;
    	
        int rs1 = testMapper.saveMessage(saveMsg);
        logger.info("***********************message3 save done");
        int rs2 = test4Client.save(message);
        return rs1+rs2;
    }
    
//    @Override
//    @Transactional
//    public int saveMessage(String message) {
//    	String groupId = TxTransactionLocal.current().getGroupId();
//    	String saveMsg = "message3:" + message + ",groupId:" + groupId;
//    	
//        int rs1 = testMapper.saveMessage(saveMsg);
//        logger.info("***********************message3 save done");
//        return rs1;
////        if(rs1 == 1){
////        	throw new RuntimeException("saveMessage runtime error!");
////        }
////        int v = 100/0;
////        int rs2 = 0;
////        
////        try{
////        	rs2 = test4Client.save(message);
////        }catch(Exception e){
////        	e.printStackTrace();
////        }
////        int rs2 = test4Client.save(message);
//        
////        logger.info("***********************mybatis-hello-1 commit groupId=" + groupId);
////        return rs1+rs2;
//    }
}
