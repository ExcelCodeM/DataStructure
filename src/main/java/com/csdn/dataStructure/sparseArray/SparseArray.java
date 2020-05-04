package com.csdn.dataStructure.sparseArray;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/4 23:09
 * @description： 稀疏数组
 */


/**
 * 稀疏数组格式
 *      cow          col          value
 *  [0] 原数组总行数  原数组总列数  值
 *  [1]
 *  [2]
 *  [...]
 */

/**
 * 用时间换取空间
 */

public class SparseArray {

    public static void main(String[] args) {
        //初始化棋盘数组  11*11
        //黑子为1， 蓝子为2， 没有棋子默认为0
        int defaultArr[][] = new int[11][11];
        defaultArr[1][2] =1;
        defaultArr[2][3] =2;

        int sum = 0;    //有效数据个数
        for (int[] rowArr: defaultArr) {
            for (int temp: rowArr) {
                if(temp != 0 ){
                    sum++;
                }
            }
        }

        //创建稀疏数组, 并对第一行进行初始化
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = defaultArr.length;
        sparseArr[0][1] = defaultArr[0].length;
        sparseArr[0][2] = sum;
        int count = 0;      //稀疏数组行数计数器
        for (int i=0; i<defaultArr.length; i++){
            for (int j = 0; j <defaultArr[i].length ; j++) {
                if(defaultArr[i][j] != 0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=defaultArr[i][j];
                }
            }
        }

        //打印稀疏数组
        System.out.println("生成的稀疏数组：");
        for (int[] rowArr: sparseArr) {
            for (int temp : rowArr) {
                System.out.print(temp);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
