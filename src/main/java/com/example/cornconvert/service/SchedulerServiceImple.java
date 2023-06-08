package com.example.cornconvert.service;

import com.example.cornconvert.SchedulerDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SchedulerServiceImple implements SchedulerService {

    List<SchedulerDto> store = new ArrayList<>();
    @Override
    public void add(SchedulerDto schedulerDto) {
        store.add(schedulerDto);
    }

    @Override
    public List<SchedulerDto> get() {
        return store;
    }
}
