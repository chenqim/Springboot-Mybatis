package com.summer.test;

import com.summer.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListTest {
    public static void main(String[] args) {

        List<User> arrayListUser = new ArrayList<>();
        List<User> linkedListUser = new LinkedList<>();
        List<User> vectorUser = new Vector<>();

        User user = new User();
        user.setUserId(1);
        user.setUserName("Mike");
        user.setPassword("abc123");
        user.setPhone("15623628888");

        getTime(arrayListUser, user);
        getTime(linkedListUser, user);
        getTime(vectorUser, user);

    }

    public static void getTime (List<User> list, User user) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(user);
        }
        Long end = System.currentTimeMillis();
        Long time = end - start;
        System.out.println(time);
    }
}
