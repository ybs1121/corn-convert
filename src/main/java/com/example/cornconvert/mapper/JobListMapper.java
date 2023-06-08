package com.example.cornconvert.mapper;

import com.example.cornconvert.dao.JobListDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobListMapper {

    List<JobListDAO> getList();
}
