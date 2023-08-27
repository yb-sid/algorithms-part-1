package week3.quick_sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSortRecursive {

    public int partition(Comparable[] arr , int lo , int hi){
        Comparable pivot = arr[lo];
        int i = lo+1;

        for(int j=lo+1;j<=hi;j++){
            if(arr[j].compareTo(pivot)<0){
                // swap j and i
                swap(arr,i,j);
                i+=1;
            }
        }
        swap(arr,lo,i-1);
        return i-1;
    }

    public void sort(Comparable[] arr){
        StdRandom.shuffle(arr); // required for perf guarantees
        sort(arr,0,arr.length-1);
    }

    private void sort(Comparable[] arr,int lo , int hi){
        if(lo<hi){
            int partitionIndex = partition(arr,lo,hi);
            sort(arr,lo,partitionIndex-1);
            sort(arr,partitionIndex+1,hi);
        }
    }

    public void swap(Comparable[] arr , int i , int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 12, 2, 9, 1, 4};
        System.out.println("Array before quick-sort :"+ Arrays.toString(arr));
        QuickSortRecursive quickSort = new QuickSortRecursive();
        quickSort.sort(arr);
        System.out.println("Array after quick-sort :"+ Arrays.toString(arr));
    }
}
