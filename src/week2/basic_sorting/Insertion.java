package week2.basic_sorting;

public class Insertion {

    public void sort(Comparable[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            for(int j=i;j>0;j--){
                if(less(a[j] , a[j-1])){
                    exchange(a , j , j -1);
                }else break;
            }
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
}
