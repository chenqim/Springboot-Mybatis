package com.summer.test;

/*
*   插入排序
*   1、将指针指向某个元素，假设该元素左侧的元素全部有序，将该元素抽取出来，然后按照从右往左的顺序分别与其左边的元素比较，
*      遇到比其大的元素便将元素右移，直到找到比该元素小的元素或者找到最左面发现其左侧的元素都比它大，停止；
*   2、此时会出现一个空位，将该元素放入到空位中，此时该元素左侧的元素都比它小，右侧的元素都比它大；
*   3、指针向后移动一位，重复上述过程。每操作一轮，左侧有序元素都增加一个，右侧无序元素都减少一个。
* */
public class InsertSort {

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
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
        * {23, 43, 33, 54, 22, 88, 67, 66, 77, 89, 34, 10}
        * {23, 33, 43, 54, 22, 88, 67, 66, 77, 89, 34, 10}
        * {23, 33, 43, 54, 22, 88, 67, 66, 77, 89, 34, 10}
        * {22, 23, 33, 43, 54, 88, 67, 66, 77, 89, 34, 10}
        * ...
        * */
        insertSort(arr);

    }

}
