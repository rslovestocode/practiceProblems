/*

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
*/


/*  PQ gives O(1) for only 1 element... even if it keep k elemtes 
        search of other elements is o(k) ..basically iterting over it 
        
        here we need acess to k elements in reasonable time .. able to remove the smaller ones and keep bigger ones
        ** Hence a Deque makes sense         

we need 
  
   dq    
        add() = addLast() in the queue
        first -->[... ]<-- last 
    
*/
/*

We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]


*/

class Solution {
   public int[] maxSlidingWindow(int[] nums, int k) {
	if (nums.length == 0) return new int[0];
	Deque<Integer> d = new ArrayDeque<>();
	
    int[] result = new int[nums.length - k + 1];
       
	for (int i = 0; i < nums.length; i++) {
		int n = nums[i];
		//discard values lower than current
		while (!d.isEmpty() && nums[d.peekLast()] < n) { //[decreasing order of number ....4..3 2 1...] -> 
			d.pollLast();
		}
		//discard right-most index of previous window
		if (!d.isEmpty() && d.peekFirst() == i - k) {
			d.pollFirst();
		}

		//add current
		d.offer(i);                                 //deque stores the indexes though.. to make it easy to keep the size 

		//skip over the first window
		if (i < k - 1) {
			continue;
		}

		//head of d contains the max value over the window
		result[i - k + 1] = nums[d.peekFirst()];
	}
	return result;
}
}
