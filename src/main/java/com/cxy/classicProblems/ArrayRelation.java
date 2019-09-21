package com.cxy.classicProblems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lidongpeng on 2018/4/14.
 */
public class ArrayRelation {
    //向右移动元素
    //For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
    public static void rotateArray(int[] nums,int k){
        if(k > nums.length)
            k=k%nums.length;

        int[] result = new int[nums.length];

        for(int i=0; i < nums.length; i++){
            result[i] = nums[(k+i)%nums.length];
        }
        System.arraycopy( result, 0, nums, 0, nums.length );
    }
    //Reverse Words in a String ,For example,Given s = "the sky is blue",return "blue is sky the".
    public static void ReverseString(String s){
        String[] sarray=s.split(" ");
        StringBuilder builder=new StringBuilder();
        int n=sarray.length;

        for (int i =sarray.length-1; i >=0 ; i--) {
            builder.append(sarray[i]).append(" ");
        }

            System.out.println(builder.substring(0, builder.length() - 1));



    }
    //Merge Sorted Array
    public static void merge2SortedArray(int[] A,int m,int[] B,int n){
        int[] C=new int[m+n];
        while (m>0 && n>0){
            if (A[m-1]>B[n-1]){
                C[m+n-1]=A[m-1];
                m--;
            }else{
                C[m+n-1]=B[n-1];
                n--;
            }
        }
        while(m>0){
            C[m-1]=A[m-1];
            m--;
        }
        while(n>0){
            C[n-1]=B[n-1];
            n--;
        }
        System.out.println(C);
    }
    //Search Insert Position (Java)
    public static int searchInsertPosition(int[] array,int x){
        if (array==null){
            return -1;
        }
        if (x>array[array.length-1]){
            return array.length;
        }
        for (int i = 0; i <array.length ; i++) {
            if (array[i]==x) {
                return i;
            }

            if (array[i]<x&&x<array[i+1]){
                return i+1;
            }

        }
        return  -1;
    }


    //Contains Duplicate (Java)
    public static boolean containsDuplicate(int nums[]){
        if (nums.length==0 || nums==null){
            return true;
        }
        Set<Integer> set=new HashSet<>();
        for (int num:nums) {
            if (!set.add(num)){
                return true;
            }
        }
        return false;
    }
    //For example, given sorted array A = [1,1,1,2,2,3], your function should return length = 5, and A is now [1,1,2,2,3].
    // Create an array with all unique elements
    public static int[] removeDuplicates(int[] A) {
        if (A.length < 2)
            return A;

        int j=0;int i=1;
        while (i<A.length){

            if (A[i]==A[j]){
                i++;
            }else{
                j++;
                A[j]=A[i];
                i++;
            }
        }
        int[] newArray=Arrays.copyOf(A,j+1);

        return newArray;
    }

    public int guessNumber(int n){
        int low=1;
        int high=n;
        int mid=low+(high-low)/2;
        while (high>low){
            int result=guess(mid);
            if (result==1){
                low=mid+1;
            }else if (result==-1){
                high=mid-1;
            }else {
                return mid;
            }
        }
        return low;
    }
    public int guess(int n){
        return -1;
    }

    public static int [] gettargetsum(int[] array,int target){
        if (array==null||array.length==0){
            return null;
        }
        int []  result=new int[2];
        for (int i = 0; i <array.length; i++) {
            for (int j = 1; j <array.length ; j++) {
                if (array[i]+array[j]==target){
                    result[0]=array[i];
                    result[1]=array[j];
                    return result;
                }

            }
        }

        return null;
    }
    public static void main(String[] args) {
        int[]  arrayb= {1,1,3,5,6,6};
        int tar=8;
        int[]  res=gettargetsum(arrayb,tar);
        //merge2SortedArray(array,array.length,arrayb,arrayb.length);
        //rotateArray(array,5);
        //ReverseString("the sky is blue");
       //[1,3,5,6], 5 -> 2
       //[1,3,5,6], 2 -> 1
       //[1,3,5,6], 7 -> 4
       //[1,3,5,6], 0 -> 0
       int[] i= removeDuplicates(arrayb);
        System.out.println(i);

    }
}
