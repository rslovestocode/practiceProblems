/*

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1


*/

class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int h = nums.length-1;
        while (l<=h) {
            int m = l + (h-l)/2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[l] <= nums[m]) {                               // min of arr is in the right half
                if (target >= nums[l] && target < nums[m]) {        // now chk where ur number falls
                    h = m-1;
                }
                else {
                    l = m+1;
                }
            }
            else {                                              // min of arr in left half
                if (target > nums[m] && target <= nums[h]) {
                    l = m + 1;
                }
                else {
                    h = m-1;
                }
            }
            
        }
        return -1;
        
    }
}
