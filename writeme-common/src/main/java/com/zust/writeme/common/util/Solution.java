package com.zust.writeme.common.util;

import java.util.Arrays;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int i=0;
        for(i=0;i<nums.length;i++){
            if(nums[i]>target) {
                break;
            }
        }

        int l = 0;
        int h = i-1;
        return getNum(nums,l,h,target);
    }
    public int[] getNum(int[] nums, int low, int high, int target){
        int[] ns = new int[2];
        if(low<high){
            if(nums[low]+nums[high]==target){
                ns[0]=low;
                ns[1]=high;
            }
            if(nums[low]+nums[high]<target){
                ns = getNum(nums,low+1,high,target);
            }
            if(nums[low]+nums[high]>target){
                ns = getNum(nums,low,high-1,target);
            }
        }
        return ns;
    }

    public static void main(String[] args) {
        Solution s= new Solution();
        int[] arrays = {
                3,2,4
        };


        int[] arrays2 = s.twoSum(arrays,9);
        for (int i = 0; i < arrays2.length; i++) {
            System.out.println(arrays2[i]);
        }
    }
}
