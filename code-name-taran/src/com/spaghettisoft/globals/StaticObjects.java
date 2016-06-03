package com.spaghettisoft.globals;

import java.util.Random;
import java.util.Scanner;

public class StaticObjects {
    public static final Scanner scanner;
    public static final Random rand;
	
    static {
        scanner = new Scanner(System.in);
        rand = new Random();
        
    }
}
