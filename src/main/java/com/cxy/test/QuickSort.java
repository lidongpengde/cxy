package com.cxy.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongpeng on 2017/8/24.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] x = { 9, 2, 4, 7, 3, 7, 10 };
        /*System.out.println(Arrays.toString(x));
        int low = 0;
        int high = x.length - 1;
        quickSort(x, low, high);
        System.out.println(Arrays.toString(x));*/
        System.out.println(Arrays.toString(x));
        popsort(x);
        //System.out.println(Arrays.toString(x));
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;
        if (low >= high)
            return;
// pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
// make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
// recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);
        if (high > i)
            quickSort(arr, i, high);
    }
    public static  void popsort(int[] array){
        for (int i = 0; i <array.length-1 ; i++) {
            for (int j = 0; j <array.length-i-1 ; j++) {
                if (array[j]>array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }

        }
        System.out.println(Arrays.toString(array));

    }
    @Test
    public void hashm(){
        Map<String,String> map=new HashMap<>();
        map.put("name","aaa");
        String key="name";
        int h;
        int a= (key == null) ? 0 :( (h = key.hashCode()) ^ (h >>> 16));
        System.out.println("key.hashCode()=="+key.hashCode());
        int hashcode=key.hashCode();
        System.out.println("h >>> 16=="+(hashcode>>>16));
        System.out.println("异或之后的值"+(hashcode ^ 51));
        System.out.println("异或之后的值"+(15&(hashcode ^ 51)));
    }
}
