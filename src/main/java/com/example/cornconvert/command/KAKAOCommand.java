package com.example.cornconvert.command;

import java.util.Map;

public class KAKAOCommand extends AbstractCommand{

    @Override
    public void execute() throws InterruptedException {
        run();
    }


    private String run() throws InterruptedException {
        System.out.println("Add KAKAO Corn");
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
//        System.out.println(allStackTraces);
        Thread.sleep(4000);
        return "OK";
    }
}
