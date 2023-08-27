package week3.merge_sort;

import java.util.Arrays;

/**
 * idea:
 * merge sort is expensive for arrays lesser than size 7 : use insertion sort
 * for recursive calls to internal sort , check if sorted before merging
 *
 */
public class ImprovedMergeSortRecursive {

    public static void main(String[] args) {
        ImprovedMergeSortRecursive mergeSort = new ImprovedMergeSortRecursive();
        String[] arr = { "qr", "st","ab","kl", "mn", "op", "cd", "ef", "gh", "ij"};
        System.out.println(mergeSort.isSorted(arr,0,arr.length-1));
        System.out.println("Array before merge sort : "+ Arrays.toString(arr));
        mergeSort.sort(arr);
        System.out.println("Array after  merge sort : "+ Arrays.toString(arr));
    }

    public void sort(Comparable[] arr){
        Comparable[] aux = Arrays.copyOf(arr,arr.length);
        sort(aux,arr,0,arr.length-1);
    }

    private void sort(Comparable[] arr , Comparable[] aux,int lo , int hi){
        System.out.println("sort called for : lo="+lo+" hi="+hi);
        if(hi<=lo)return;
        int mid = (lo+hi)/2;
        sort(aux,arr,lo,mid);
        sort(aux,arr,mid+1,hi);
        merge(arr,aux,lo,mid,hi);
    }

    private void merge(Comparable[] arr, Comparable[] aux,int lo , int mid , int hi){
        assert isSorted(arr,lo,mid);
        assert isSorted(arr,mid+1,hi);

        int i=lo;
        int j = mid+1;
        int k = lo;
        while(i<=mid && j<=hi){
            System.out.println("aux[i]="+aux[i]+" aux[j]="+aux[j]);
            if(arr[i].compareTo(arr[j])<0){
                aux[k++] = arr[i++];
            }else{
                aux[k++] = arr[j++];
            }
        }
        while(i<=mid) aux[k++] = arr[i++];
        while(j<=hi) aux[k++] = arr[j++];

        System.out.println("arr after merge ="+Arrays.toString(aux));
        assert isSorted(arr,lo,hi);
    }

    private boolean isSorted(Comparable[] arr , int lo , int hi){
        for(int i=lo+1;i<=hi;i++){
            if(arr[i].compareTo(arr[i-1])<0){
                return false;
            }
        }
        return true;
    }
}
