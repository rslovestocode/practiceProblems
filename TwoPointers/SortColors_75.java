/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?


*/


class Solution {
    // https://leetcode.com/problems/sort-colors/discuss/148221/Java-2-pass-counting-sort-and-1-pass-quick-partition-(with-video-tutorial-links)
    //-------------  SOLUTION 2: 1 SCAN ----------------//
    // 3-way partition used in quick sort
    public void sortColors(int[] nums) {
        int lt = 0, i = 0, gt = nums.length - 1;
        while (i <= gt) {
            if (nums[i] == 0) {
                swap(nums, lt++, i++);
            } else if (nums[i] == 2) {
                swap(nums, i, gt--);
            } else { // nums[i] == 1
                i++;
            }
        }
    }
    private void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }

}
/*
 //-------------  SOLUTION 1: 2 SCANs ----------------//
    // counting sort
    public void sortColors(int[] A) {
        // count
        int[] counts = new int[3];
        for (int i: A) {
            counts[i]++;
        }
        // fill
        for (int i = 0; i < A.length; i++) {
            if (i < counts[0]) {
                A[i] = 0;
            } else if (i < counts[0] + counts[1]) {
                A[i] = 1;
            } else {
                A[i] = 2;
            }
        }
    }

*/
