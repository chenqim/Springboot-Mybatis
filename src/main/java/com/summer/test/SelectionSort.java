package com.summer.test;

/*
*   选择排序原理即是，遍历元素找到一个最小（或最大）的元素，把它放在第一个位置，
*   然后再在剩余元素中找到最小（或最大）的元素，把它放在第二个位置，依次下去，完成排序。
*   选择排序是不稳定的排序方法。
* */
public class SelectionSort {

    public static void selectionSort(int arr[]) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // 找到最小值的下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换
            int temp;
            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            print(arr);
            System.out.println();
        }

    }

    public static void print(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                System.out.print("{" + arr[i] + ", ");
            } else if (i == arr.length - 1) {
                System.out.print(arr[i] + "}");
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
    }

    public static void main(String[] args) {

        int arr[] = {23, 43, 33, 54, 22, 88, 67, 66, 77, 89, 34, 10};
        /*
        * {10, 43, 33, 54, 22, 88, 67, 66, 77, 89, 34, 23}
        * {10, 22, 33, 54, 43, 88, 67, 66, 77, 89, 34, 23}
        * ...
        * */
        selectionSort(arr);

    }

}
