package week2.ungraded_assignments.dutch_national_flag;

import java.util.Arrays;

public class DutchNationalFlag {

    private final int[] buckets;
    public DutchNationalFlag(int[] buckets){
        this.buckets = buckets;
    }

    private void swap(int i,int j){
        buckets[i] = buckets[i] + buckets[j];
        buckets[j] = buckets[i] - buckets[j];
        buckets[i] = buckets[i] - buckets[j];
    }

    private  int color(int i){
        return buckets[i];
    }

    public int[] threeWaySort(){
        int low = 0;
        int high = buckets.length-1;
        int mid = 0;

        while(mid<=high){
            if(buckets[mid]==0){
                swap(low,mid);
                low+=1;
                mid+=1;
            }else if (buckets[mid]==1){
                mid+=1;
            }else{ // buckets[mid]==2
                swap(mid, high);
                high-=1;
            }
        }
        return buckets;
    }

    public static void main(String[] args) {
        int[] buckets = new int[]{2, 0, 1, 2, 1, 0,2,1,0,0,2,2,1};
        DutchNationalFlag dnf = new DutchNationalFlag(buckets);

        System.out.println("Sorted flag is : " + Arrays.toString(dnf.threeWaySort()));
    }
}
