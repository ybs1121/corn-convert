package com.example.cornconvert.command;

public class FINGERCommand extends AbstractCommand{
    @Override
    public void execute() {

        run();

    }

    private String run(){
        System.out.println("Add Finger Corn");

        return "OK";
    }
}
