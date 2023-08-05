package week1.ungraded_assignments.bitonic_array;

public class BitonicSearch {

    private int findPeakIndex(int[] nums){
        int l = 0;
        int r = nums.length-1;
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid]<nums[mid+1]){
                l = mid +1;
            }else {
                r = mid;
            }
        }
        return l;
    }

    private int modBinarySearch(int[] nums , int l , int r , boolean isIncreasing, int target){
        while(l<=r){
            int mid = (l+r)/2;
            if(nums[mid]==target)
                return mid;
            if(nums[mid]<target){
                if(isIncreasing){
                    l = mid+1;
                }else{
                    r = mid-1;
                }
            }else{
                if(isIncreasing){
                    r = mid -1;
                }else{
                    l = mid+1;
                }
            }
        }
        return -1;
    }

    public int search(int[] nums , int target){
        int peakIndex = findPeakIndex(nums);

        int searchIndex = modBinarySearch(nums , 0 , peakIndex , true , target);
        if(searchIndex==-1){
            searchIndex = modBinarySearch(nums , peakIndex+1 , nums.length-1,false,target);
        }

        return searchIndex;

    }

    public static void main(String[] args) {
        BitonicSearch bitonicSearch = new BitonicSearch();
        int[] nums = new int[]{3, 5, 9, 11, 13, 17, 15, 10, 8, 4};
        System.out.println("is 17 present in nums at index : "+bitonicSearch.search(nums , 17));
        System.out.println("is 9 present in nums at index : "+bitonicSearch.search(nums , 9));
        System.out.println("is 8 present in nums at index : "+bitonicSearch.search(nums , 8));
    }
}
