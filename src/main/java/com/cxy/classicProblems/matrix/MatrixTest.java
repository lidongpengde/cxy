package com.cxy.classicProblems.matrix;

/**
 * Created by lidongpeng on 2018/4/25.
 */
public class MatrixTest {
    static int[][] matrix={{1,5,9},{10,11,13},{12,13,15}};
    public static void main(String[] args) {
        int k=11;
        foundlocation(matrix, k);
    }
    public static void foundlocation(int[][] matrix,int k){

        int row=matrix.length-1; int col=0;
        while(row>0){
            if (matrix[row][col]<k){
                col++;
            }
            if (matrix[row][col]>k){
                row--;
            }
            if (matrix[row][col]==k){
                System.out.println(row+" "+col);
                return;
            }
        }

        System.out.println(row+" "+col);
    }
}
