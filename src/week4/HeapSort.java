package week4;

import java.util.Arrays;

public class HeapSort <T extends Comparable<T>>{

    public void sort(T[] arr){
        int n = arr.length;
        // Build the heap , rearrange array
        for(int i=n/2-1;i>=0;i--){
            sink(arr,i,n);
        }
        System.out.println("Array after building heap : "+ Arrays.toString(arr));
        // take last element , swap with first
        for(int i=n-1;i>0;i--){
            swap(arr,0,i);
            sink(arr,0,i);
        }
    }

    private void sink(T[] arr , int k , int n){
        int largest = k;
        int left = 2*k+1;
        int right = 2*k+2;
        // compare root with left and right
        if(left<n && arr[left].compareTo(arr[largest])>0)
            largest = left;

        if(right<n && arr[right].compareTo(arr[largest])>0)
            largest = right;

        // largest index is sinked after swap
        if(largest!=k){
            swap(arr, k , largest);
            sink(arr,largest,n);
        }
    }

    // Helper method to perform the "swim" operation
    private void swim(T[] arr, int k) {
        while (k > 0 && arr[(k - 1) / 2].compareTo(arr[k]) < 0) {
            swap(arr, k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    private void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"TT","KK","LL","XX","AA","NN","PP"};
        HeapSort<String> heapSort = new HeapSort<>();
        heapSort.sort(arr);
        System.out.println("Sorted array is : "+ Arrays.toString(arr));
    }
}
