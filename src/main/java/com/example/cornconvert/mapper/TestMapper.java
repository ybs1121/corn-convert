package com.example.cornconvert.mapper;


import com.example.cornconvert.dao.EmailDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    String test();

    List<EmailDAO> getEmailList();

    void updateEmail(String email);
}
