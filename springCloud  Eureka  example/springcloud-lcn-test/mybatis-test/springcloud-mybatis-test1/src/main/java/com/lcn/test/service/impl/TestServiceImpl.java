package com.lcn.test.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.codingapi.tx.aop.bean.TxTransactionLocal;
import com.lcn.test.client.Test2Client;
import com.lcn.test.client.Test3Client;
import com.lcn.test.dao.Message1Mapper;
import com.lcn.test.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private Test2Client test2Client;
    
    @Autowired
    private Test3Client test3Client;


    @Autowired
    private Message1Mapper testMapper;

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    @TxTransaction(isStart = true)
    @Transactional
    public int saveMessage(String message) {
    	String groupId = TxTransactionLocal.current().getGroupId();
    	String saveMsg = "message1:" + message + ",groupId:" + groupId;
    	
        int rs1 = testMapper.saveMessage(saveMsg);
        logger.info("***********************message1 save done");

        int rs2 = test2Client.save(message);
        int rs3 = test3Client.save(message);
        return rs1+rs2+rs3;
    }
    
    
//    @Override
//    @TxTransaction(isStart = true)
//    @Transactional
//    public int saveMessage(String message) {
//    	String groupId = TxTransactionLocal.current().getGroupId();
//    	String saveMsg = "message1:" + message + ",groupId:" + groupId;
//    	
//        int rs1 = testMapper.saveMessage(saveMsg);
//        logger.info("***********************message1 save done");
//
//        int rs2 = test2Client.save(message);
//        
////        int rs3 = test3Client.save(message);
//
////		if(rs1 == 1){
////			throw new RuntimeException("saveMessage runtime error!");
////		}
////        logger.info("***********************mybatis-hello-1 sleep");
////        try {
////			Thread.sleep(10000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//        
////        logger.info("***********************mybatis-hello-1 commit groupId=" + groupId);
////        return rs1+rs2+rs3;
//        return rs1+rs2;
//    }
}
