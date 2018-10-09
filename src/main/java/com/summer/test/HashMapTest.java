package com.summer.test;

import java.util.*;

/*
* HashMap/LinkedHashMap/TreeMap区别以及Map.Entry的用法
*
* */
public class HashMapTest {

    public static void main(String[] args) {

        // HashMap是一个最常用的Map，它根据键的hashCode值存储数据，根据键可以直接获取它的值，具有很快的访问速度。HashMap最多只允许一条记录的键为NULL，允许多条记录的值为NULL。
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("keyA", "valueA");
        hashMap.put("keyB", "valueB");
        hashMap.put("keyC", "valueC");
        hashMap.put("keyD", "valueD");
        hashMap.put("keyE", "valueE");
        Set<Map.Entry<String, String>> entrySet = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " " + value);
        }
        System.out.println("-------");

        // LinkedHashMap保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的。
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("keyD", "valueD");
        linkedHashMap.put("keyE", "valueE");
        linkedHashMap.put("keyA", "valueA");
        linkedHashMap.put("keyB", "valueB");
        linkedHashMap.put("keyC", "valueC");
        Set<Map.Entry<String, String>> entrySet2 = linkedHashMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet2) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " " + value);
        }
        System.out.println("-------");

        // TreeMap实现SortMap接口，能够把它保存的记录根据键排序,默认是按键值的升序排序，也可以指定排序的比较器。
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("keyD", "valueD");
        treeMap.put("keyE", "valueE");
        treeMap.put("keyA", "valueA");
        treeMap.put("keyB", "valueB");
        treeMap.put("keyC", "valueC");
        Set<Map.Entry<String, String>> entrySet3 = treeMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet3) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }

}
