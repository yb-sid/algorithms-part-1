package week2.basic_sorting;

import java.util.Arrays;

public class Selection {
    public  void sort(Comparable[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            int minIndex = i;
            for(int j=i+1;j<N;j++){
                if(less(a[j] , a[minIndex])){
                    minIndex = j;
                }
            }
            exchange(a , i , minIndex);
        }
    }

    private  boolean less(Comparable v , Comparable w){
        return v.compareTo(w) <0;
    }

    private  void exchange(Comparable[] a , int i , int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Selection selectionSort = new Selection();
        String[] arr = new String[]{"bb","aa","cc","gg","zz","kk","mm"};
        System.out.println("Array before insertion sort : "+ Arrays.toString(arr));
        selectionSort.sort(arr);
        System.out.println("Array after insertion sort : "+ Arrays.toString(arr));
    }
}
