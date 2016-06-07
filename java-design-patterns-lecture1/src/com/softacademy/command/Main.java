package com.softacademy.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Command> program = new ArrayList<>();
        int option = 0;
        do {
            printMenu();
            option = selectOption(scanner);
            switch (option) {
            case 1:
                int x = 10;
                program.add(new MoveUp(10));
                break;
            case 2:
                program.add(new MoveDown());
                break;
            case 3:
                program.add(new Beep());
                break;
            }
        } while (option != 0);

        executeProgram(program);
    }

    private static void executeProgram(List<Command> program) {
        for (Command command : program) {
            command.execute();
        }
    }

    private static int selectOption(Scanner scanner) {
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("0.Execute program");
        System.out.println("1.Move up");
        System.out.println("2.Move down");
        System.out.println("3.Beep");
    }

}
