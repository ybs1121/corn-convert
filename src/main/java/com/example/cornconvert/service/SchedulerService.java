package com.example.cornconvert.service;

import com.example.cornconvert.SchedulerDto;

import java.util.List;

public interface SchedulerService {

    void add(SchedulerDto cornDto);
    List<SchedulerDto> get();

}
