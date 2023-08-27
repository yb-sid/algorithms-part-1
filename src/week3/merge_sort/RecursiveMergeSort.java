package week3.merge_sort;

import java.util.Arrays;

public class RecursiveMergeSort {

    private boolean isSorted(Comparable[] arr,int lo,int hi){
        for(int i=lo+1;i<=hi;i++){
            if(arr[i].compareTo(arr[i-1])<0){
                return false;
            }
        }
        return true;
    }

    private void merge(Comparable[] arr , Comparable[] aux , int lo, int hi , int mid){
        assert isSorted(arr,lo,mid);
        assert isSorted(arr,mid+1,hi);

        for (int k = lo; k <= hi; k++)
            aux[k] = arr[k];
        //System.out.println("aux is : "+Arrays.toString(aux));

        int i=lo;
        int j = mid+1;
        int k = lo;
        while(i<=mid && j<=hi){
            System.out.println("aux[i]="+aux[i]+" aux[j]="+aux[j]);
            if(aux[i].compareTo(aux[j])<0){
                arr[k++] = aux[i++];
            }else{
                arr[k++] = aux[j++];
            }
        }
        while(i<=mid) arr[k++] = aux[i++];
        while(j<=hi) arr[k++] = aux[j++];
        assert isSorted(arr,lo,hi);
    }

    private void sort(Comparable[] arr , Comparable[] aux , int lo , int hi ){
        System.out.println("sort called for : lo="+lo+" hi="+hi);
        if(hi<=lo) return;
        int mid = (lo+hi)/2;
        sort(arr,aux,lo,mid);
        sort(arr,aux,mid+1,hi);
        merge(arr,aux,lo,hi,mid);
    }

    private void sort(Comparable[] arr){
        Comparable[] aux = new Comparable[arr.length];
        sort(arr,aux,0,arr.length-1);
    }
    public static void main(String[] args) {
        RecursiveMergeSort mergeSort = new RecursiveMergeSort();
        String[] arr = { "qr", "st","ab","kl", "mn", "op", "cd", "ef", "gh", "ij"};
        System.out.println(mergeSort.isSorted(arr,0,arr.length-1));
        System.out.println("Array before merge sort : "+ Arrays.toString(arr));
        mergeSort.sort(arr);
        System.out.println("Array after  merge sort : "+ Arrays.toString(arr));
    }
}
