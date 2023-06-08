package com.example.cornconvert.controller;

import com.example.cornconvert.SchedulerDto;
import com.example.cornconvert.corn.CornDto;
import com.example.cornconvert.corn.CornService;
import com.example.cornconvert.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@Slf4j
@RestController
@RequiredArgsConstructor
public class cornController {

    private final CornService cornService;

    private final SchedulerService schedulerService;

    @PostMapping("/corn-convert")
    public String cornConvert(@RequestBody CornDto cornDto){

        String corn  = cornService.cornConvert(cornDto);

        log.info("new Corn : {}",corn);

        SchedulerDto schedulerDto = new SchedulerDto();
        schedulerDto.setCorn(corn);
        schedulerDto.setName(UUID.randomUUID().toString());
        schedulerService.add(schedulerDto);
        return corn;
    }
}
