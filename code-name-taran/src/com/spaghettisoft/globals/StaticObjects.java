package com.spaghettisoft.globals;

import java.util.Scanner;

public class StaticObjects {
    public static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }
}
