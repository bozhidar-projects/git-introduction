package com.softacademy.factory;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getCarName() {
        printOptions();
        int option = chooseOption();
        switch (option) {
        case 1:
            return CarNames.BMW;
        case 2:
            return CarNames.VOLVO;
        case 3:
            return CarNames.FERRARI;
        }

        throw new IllegalArgumentException("Unknown Option");
    }

    private int chooseOption() {
        System.out.println("Enter option:");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private void printOptions() {
        System.out.println("Choose car:");
        System.out.println("1. BMW");
        System.out.println("2. Volvo");
        System.out.println("3. Ferrari");
    }

}
