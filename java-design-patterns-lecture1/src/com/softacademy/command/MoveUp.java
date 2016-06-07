package com.softacademy.command;

public class MoveUp implements Command<String> {

    private int distance;
    private String result;
    private String someResult;

    public MoveUp(int distance) {
        this.distance = distance;
    }

    @Override
    public void execute() {
        System.out.println("Robot moves up " + distance);
        someResult = "Executed";
    }

    @Override
    public String getResult() {
        return someResult;
    }

}
