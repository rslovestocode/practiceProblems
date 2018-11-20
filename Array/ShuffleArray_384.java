/*
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

*/

//https://www.geeksforgeeks.org/shuffle-a-given-array/
// dec loop give O(n) frequency


public class Solution {
    private int[] nums;
    private int[] copy;

    public Solution(int[] nums) {
        this.nums = nums;
        this.copy = nums.clone();
    }

    public int[] reset() {
        return copy;
    }

    public int[] shuffle() {
        Random random = new Random();
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); // i+1 is not included .. 
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
