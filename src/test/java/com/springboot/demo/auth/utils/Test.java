package com.springboot.demo.auth.utils;

import java.util.Arrays;

/**
 * @author Jim
 */
public class Test {

    public static String str;
    public static char[] chars;

    public static void main(String[] args) {
        str = "Hello";
        fun(str);
        System.out.println(str);

        chars = new char[]{'a', 'b', 'c'};
        func(chars);
        System.out.println(Arrays.toString(chars));
    }

    public static void fun(String str) {
        str = "world";
    }

    public static void func(char[] chars) {
        chars[2] = 'y';
    }
}
