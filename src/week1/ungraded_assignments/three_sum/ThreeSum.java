package week1.ungraded_assignments.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums , int target ){
        int n = nums.length;
        Arrays.sort(nums); // sort O (nlogn)
        List<List<Integer>> answer = new ArrayList<>();
        for(int i=0;i<n-2;i++){
            int j = i+1;
            int k = n-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum == target){
                    answer.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j+=1;
                    k-=1;
                }else if (sum>target){
                    k-=1;
                }else{
                    j+=1;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        ThreeSum threeSum = new ThreeSum();
        System.out.println("All nums of sum 12 is : "+threeSum.threeSum(nums , 12));
        System.out.println("All nums of sum 8 is : "+threeSum.threeSum(nums , 8));
        System.out.println("All nums of sum 20 is : "+threeSum.threeSum(nums , 20));

    }
}
