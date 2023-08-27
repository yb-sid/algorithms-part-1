package week2.basic_sorting;

import java.util.Arrays;

public class Shell {

    public void sort(Comparable[] a){
        int N = a.length;

        int h = 1;
        while(h<N/3) h = 3*h+1;
        while(h>=1){
            for(int i=h; i<N;i++){
                for(int j=i;j>=h && less( a[j] , a[j-h]);j-=h ){
                    exchange(a , j , j-h);
                }
            }
            h = h/3;
        }
    }
    private static boolean less(Comparable v , Comparable w){
        return v.compareTo(w) <0;
    }

    private static void exchange(Comparable[] a , int i , int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Shell shellSort = new Shell();
        String[] arr = new String[]{"bb","aa","cc","gg","zz","kk","mm"};
        System.out.println("Array before insertion sort : "+ Arrays.toString(arr));
        shellSort.sort(arr);
        System.out.println("Array after insertion sort : "+ Arrays.toString(arr));
    }
}
