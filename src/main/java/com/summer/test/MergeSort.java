package com.summer.test;
/*
* 归并排序
* 分而治之(divide - conquer);每个递归过程涉及三个步骤
* 第一, 分解: 把待排序的 n 个元素的序列分解成两个子序列, 每个子序列包括 n/2 个元素.
* 第二, 治理: 对每个子序列分别调用归并排序MergeSort, 进行递归操作
* 第三, 合并: 合并两个排好序的子序列,生成排序结果.
* */
public class MergeSort {

    public static void merSort(int[] arr,int left,int right){

        if (left < right) {
            int mid = (left + right) / 2;
            merSort(arr, left, mid);//左边归并排序，使得左子序列有序
            merSort(arr, mid + 1, right);//右边归并排序，使得右子序列有序
            merge(arr, left, mid, right);//合并两个子序列
        }
    }
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];//ps：也可以从开始就申请一个与原数组大小相同的数组，因为重复new数组会频繁申请内存
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[k++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[k++] = arr[j++];
        }
        //将temp中的元素全部拷贝到原数组中
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[k2 + left] = temp[k2];
        }
    }
    public static void main(String args[]){
        int[] test = {9,2,6,3,5,7,10,11,12};
        merSort(test,0,test.length-1);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }

}