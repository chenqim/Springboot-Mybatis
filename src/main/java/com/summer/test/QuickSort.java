package com.summer.test;

/*
*    快速排序算法实现
*    1）设置两个变量i、j，排序开始的时候：i=0，j=N-1；
*    2）以第一个数组元素作为关键数据，赋值给key，即key=A[0]；
*    3）从j开始向前搜索，即由后开始向前搜索(j--)，找到第一个小于key的值A[j]，将A[j]和A[i]互换；
*    4）从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于key的A[i]，将A[i]和A[j]互换；
*    5）重复第3、4步，直到i=j； (3,4步中，没找到符合条件的值，即3中A[j]不小于key,4中A[i]不大于key的时候改变j、i的值，
*       使得j=j-1，i=i+1，直至找到为止。找到符合条件的值，进行交换的时候i， j指针位置不变。
*       另外，i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束）。
* */
public class QuickSort {

    public static void quickSort(int arr[], int low, int high) {

        int l = low;
        int h = high;
        int base = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= base) {
                h--;
            }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }
            while (l < h && arr[l] <= base) {
                l++;
            }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
            print(arr);
            System.out.print("(l = " + l + ", h = " + h + ", base = " + base + ")\n");
        }

        if (l > low) {
            quickSort(arr, low, l - 1);
        }
        if (h < high) {
            quickSort(arr, l + 1, high);
        }

    }
    
    public static void print (int arr[]) {
        for (int i: arr) {
            System.out.print(i + ", ");
        }
    }
    
    public static void main(String[] args) {
        int arr[] = {23, 43, 33, 54, 22, 88, 67, 66, 77, 89, 34, 10};
        /*
        * {10, 23, 33, 54, 22, 88, 67, 66, 77, 89, 34, 43}
        *       l                                  h
        * {10, 22, 23, 54, 33, 88, 67, 66, 77, 89, 34, 43}
        *          l    h
        * */
        int low = 0;
        int high = arr.length - 1;
        QuickSort.quickSort(arr, low, high);
    }

}
