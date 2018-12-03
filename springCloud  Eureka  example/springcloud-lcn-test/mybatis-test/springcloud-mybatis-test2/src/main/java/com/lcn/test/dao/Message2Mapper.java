package com.lcn.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Message2Mapper {
    @Insert("INSERT INTO message2(message) VALUES(#{message})")
    int saveMessage(@Param("message") String message);
}
