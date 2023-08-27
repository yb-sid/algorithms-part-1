package week3.quick_sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Standard Quick sort has worst case complexity of n^2 for
 * arrays with duplicate keys , use 3 way partitioning to solve this
 */
public class ThreeWayPartitionQuickSort {

    public void sort(Comparable[] arr){
        StdRandom.shuffle(arr);
        sort(arr,0,arr.length-1);
    }

    private void sort(Comparable[] arr,int lo , int hi){
        if(lo<hi){
            int lt = lo;
            int gt = hi;
            Comparable pivot = arr[lo];
            int i = lo;
            while(i<=gt){ // pointer crossing condition
                int compare = arr[i].compareTo(pivot);
                if(compare<0){
                    swap(arr,i,lt); // lower boundary
                    i+=1;
                    lt+=1;
                }else if (compare>0){
                    swap(arr,i,gt); // upper boundary
                    gt-=1;
                }else{
                    i+=1; // middle
                }
            }

            sort(arr,lo,lt-1);
            sort(arr,gt+1,hi);
        }
    }

    private void swap(Comparable[] arr , int i , int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 12, 2, 9, 1, 4,3,2,7,9,12,13,24,78,1,5,3,6};
        System.out.println("Input array : "+ Arrays.toString(arr));
        ThreeWayPartitionQuickSort quick3Sort = new ThreeWayPartitionQuickSort();
        quick3Sort.sort(arr);
        System.out.println("After 3 way partitioning sort :"+Arrays.toString(arr));
    }
}
