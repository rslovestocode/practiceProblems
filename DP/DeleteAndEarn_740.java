/*

Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:
Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
Example 2:
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].

*/


class Solution {
    public int deleteAndEarn(int[] nums) {
        int n = 10001;
        int[] values = new int[n];
        for (int num : nums)
            values[num] += num;

        int take = 0, skip = 0;
        for (int i = 0; i < n; i++) {
            int takei = skip + values[i];
            int skipi = Math.max(skip, take);
            take = takei;
            skip = skipi;
        }
        return Math.max(take, skip);
    }
}
/*
class Solution {
    public int deleteAndEarn(int[] nums) {
    
        if(nums == null ||nums.length == 0 )
            return 0;
        int maxNum = 0;
    
        for (int num: nums)
        {
            if(maxNum < num)
            {
                maxNum = num;
            }
        }
        int[] array = new int[maxNum+1];
        for(int num: nums)
        {
            array[num] +=num;
        }
        
        int[] dp = new int[array.length +1];
        dp[0] = 0;
        dp[1] = array[0]; // array of 0 is 0 numbr, if we del 0 we earn 0 points
        
        for(int i = 2; i<array.length+1; i++)
        {
            dp[i] = Math.max(dp[i-1], dp[i-2]+array[i-1]);
        }
        
        return dp[array.length]; 
    }
}

*/
