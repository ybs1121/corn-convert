package com.example.cornconvert;


import com.example.cornconvert.command.AbstractCommand;
import com.example.cornconvert.command.KAKAOCommand;
import com.example.cornconvert.dao.JobListDAO;
import com.example.cornconvert.mapper.JobListMapper;
import com.example.cornconvert.service.SchedulerService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Setter
@RequiredArgsConstructor
public class DynamicScheduler {

    private ThreadPoolTaskScheduler scheduler;



    private final JobListMapper jobListMapper;

    @PostConstruct
    public void postConstruct() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        log.info("Scheduler Setting Start");

        List<JobListDAO> jobList = jobListMapper.getList();

        if(jobList.isEmpty()){
            System.out.println("No Schedule, Please Add Schedule");
        }else{

            scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(jobList.size() + 1);
            scheduler.initialize();



            for (JobListDAO job : jobList) {
                scheduler.schedule(getRunnable(job),getTrigger(job));
            }
            System.out.println("Scheduler Running Start");
        }
    }

    @PreDestroy
    public void preDestroy() throws InterruptedException {
//        Thread.sleep(10000);
        System.out.println("빈 소멸");
    }

    public void schedulerStart() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        if(scheduler != null){
            scheduler.shutdown();
            scheduler.destroy();
        }

        List<JobListDAO> jobList = jobListMapper.getList();

        if(jobList.isEmpty()){
            System.out.println("No Schedule, Please Add Schedule");
        }else{

            scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(jobList.size() + 1);
            scheduler.initialize();

            for (JobListDAO job : jobList) {
                scheduler.schedule(getRunnable(job),getTrigger(job));
            }
            System.out.println("Scheduler Running Start");
        }


    }


    private Trigger getTrigger(JobListDAO jobListDAO) {
//         cronSetting
        // job
        return new CronTrigger(jobListDAO.getCorn());

    }


    private Runnable getRunnable(JobListDAO jobListDAO) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String className = jobListDAO.getClassName();

        Class clazz = Class.forName("com.example.cornconvert.command." + className);
        AbstractCommand command = (AbstractCommand) clazz.newInstance();

        return () ->{
            try {
                command.execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
