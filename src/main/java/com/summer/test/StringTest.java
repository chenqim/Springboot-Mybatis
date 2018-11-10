package com.summer.test;

public class StringTest {

    public static void main(String[] args) {

        String s1 = "apple";
        String s2 = new String("apple");
        String s22 = new String("apple");
        String s3 = "app" + "le";
        String s4 = "app";
        String s5 = "le";
        StringBuffer s6 = new StringBuffer("apple");
        StringBuilder s7 = new StringBuilder("apple");

        System.out.println(s1 == "apple"); // true
        System.out.println(s2 == s22); // false
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true
        System.out.println(s1 == s4 + s5); // false
        System.out.println(s1 == s6.toString()); // false
        System.out.println(s1 == s7.toString()); // false
        System.out.println(s2);
        System.out.println(s6);

    }

}
