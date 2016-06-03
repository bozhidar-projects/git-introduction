package com.softacademy.decorator;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamsExample {

    public static void main(String[] args) {
        InputStream stream = System.in;
        BufferedInputStream inputStream = new BufferedInputStream(stream);

        InputStreamReader normalReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(normalReader);
    }

}
