/* Movie
 *
 * version 1.0
 *
 * Apr 19, 2016
 *
 * The MIT License (MIT)
 * Copyright (c) <2016> <spaghettisoft LTD>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.spaghettisoft.videoteka;

import java.util.List;
import java.util.Set;

/**
 * @author bobi
 *
 *
 *         Movie
 */
public class Movie {
    private List<String> names;
    private Set<String> actors;
    private String name;

    public static void main(String[] args) {
        int[] item1 = { 1, 2, 4, 5, 6 };
        int[] item2 = { 0, 5, 3, 2, 1 };

        mergeArrays(item1, item2);
    }

    private static void mergeArrays(int[] item1, int[] item2) {
        int[] result = new int[item1.length + item2.length];
        System.arraycopy(item1, 0, result, 0, item1.length);
        System.arraycopy(item2, 0, result, item1.length, item2.length);
    }
}
