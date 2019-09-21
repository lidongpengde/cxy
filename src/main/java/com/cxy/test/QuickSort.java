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
        int high=x.length-1;
        int[] newaa=testStrToInt(x, 0, high);
        /*System.out.println(Arrays.toString(x));
        int low = 0;
        int high = x.length - 1;
        quickSort(x, low, high);
        System.out.println(Arrays.toString(x));*/
        //System.out.println(Arrays.toString(x));
        //popsort(x);
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

    public int atoi(String str) {
        if (str == null || str.length() < 1)
            return 0;
        // trim white spaces
        str = str.trim();
        char flag = '+';
        // check negative or positive
        int i = 0;
        if (str.charAt(0) == '-') {
            flag = '-';
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        // use double to store result
        double result = 0;
        // calculate value
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }
        if (flag == '-')
            result = -result;
        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int) result;
    }



    public int atoi11(String str) {
        if (str==null || str.length()<0){
            return 0;
        }
        char flag='+';
        int i=0;
        if (str.charAt(i)=='+'){
            i++;
        }else if (str.charAt(i)=='-'){
            flag='-';
            i++;
        }
        double result=0;
        while (str.length()>i && str.charAt(i)>='0' &&str.charAt(i)<='9'){
            result=result*10+(str.charAt(i)-'0');
            i++;
        }

        if (flag=='-'){
            result=-result;
        }

        return (int)result;

    }

    public static int[]  testStrToInt(int[] array,int low,int high){
        if (array==null|| array.length==0){
            return null;
        }
        //求中间值
        int middleIndex=low+(high-low)/2;
        int middleValue=array[middleIndex];
        int i=low;int j=high;
        while (array[i]<middleValue){
            i++;
        }
        while (array[j]>middleValue){
            j--;
        }
        if (i<=j){
            int temp=array[i];
            array[i]=array[j];
            array[j]=temp;
            i++;
            j--;
        }
        if (low<j){
            testStrToInt(array,low,j);
        }
        if (high>i){
            testStrToInt(array,i,high);
        }
        return array;
    }
}


























