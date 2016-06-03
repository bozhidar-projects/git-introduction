package com.softacademy.command;

public class Beep implements Command {

    @Override
    public void execute() {
        System.out.println("Robot beeps");
    }

}
