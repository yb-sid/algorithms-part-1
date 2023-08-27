package week3.quick_sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSelect {

    private int partition(Comparable[] arr,int lo , int hi){
        Comparable pivot = arr[lo];
        int i = lo+1;

        for(int j=lo+1;j<=hi;j++){
            if(arr[j].compareTo(pivot)<0){
                swap(arr,i,j);
                i+=1;
            }
        }

        swap(arr,lo , i-1);
        return i-1;
    }


    private void swap(Comparable[] arr , int i , int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public Comparable selection(Comparable[] arr, int k){
        StdRandom.shuffle(arr);
        int lo = 0;
        int hi = arr.length-1;

        while(lo<hi){
            int partitionIndex = partition(arr,lo,hi);
            if(partitionIndex<k){
                lo = partitionIndex+1;
            }else if (partitionIndex>k){
                hi = partitionIndex-1;
            }else{
                return arr[k-1];
            }
        }

        return arr[k-1];
    }

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{5, 8, 12, 2, 9, 1, 4};
        System.out.println("Input array : "+ Arrays.toString(arr));
        int k = 2;
        QuickSelect quickSelect = new QuickSelect();
        System.out.println("for k = 2 , quick-select is :"+quickSelect.selection(arr,k));
    }
}
