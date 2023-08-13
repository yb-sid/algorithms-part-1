package week3.merge_sort;
import java.util.Arrays;

public class MergeSortBottomUp {

    Comparable[] aux;
    public static void main(String[] args) {
        MergeSortBottomUp mergeSortBU = new MergeSortBottomUp();
        String[] arr = { "qr", "st","ab","kl", "mn", "op", "cd", "ef", "gh", "ij"};
        System.out.println("Array before merge sort BU : "+ Arrays.toString(arr));
        mergeSortBU.sort(arr);
        System.out.println("Array after  merge sort BU: "+ Arrays.toString(arr));
    }

    public void sort(Comparable[] arr){
        int N = arr.length;
        aux = new Comparable[N];
        // outer loop subarry size to be merged
        for(int sz = 1;sz<N;sz = sz+sz){ // log N times executed
            for(int lo=0;lo< N-sz; lo += sz+sz){
                merge(arr,lo , lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }

    }

    private void merge(Comparable[] arr, int lo , int mid , int hi){

        System.arraycopy(arr,0,aux,0,arr.length);
        int i=lo;
        int j = mid+1;
        int k = lo;

        while(i<=mid && j<=hi){
            if(aux[i].compareTo(aux[j])<=0){
                arr[k++] = aux[i++];
            }else{
                arr[k++] = aux[j++];
            }
        }

        while(i<=mid) arr[k++] = aux[i++];
        while(j<=hi) arr[k++] = aux[j++];
    }
}
