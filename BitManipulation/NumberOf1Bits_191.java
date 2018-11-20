/*

Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Example 1:

Input: 11
Output: 3
Explanation: Integer 11 has binary representation 00000000000000000000000000001011 
Example 2:

Input: 128
Output: 1
Explanation: Integer 128 has binary representation 00000000000000000000000010000000


*/

public class Solution {
    // you need to treat n as an unsigned value
    //https://leetcode.com/problems/number-of-1-bits/discuss/55099/Simple-Java-Solution-Bit-Shifting
    
    public static int hammingWeight(int n) {
	int ones = 0;
    	while(n!=0) {                           //** IMP - n!=0 ..not n >0 
    		ones = ones + (n & 1);
    		n = n>>>1;                          // unsigned shift >>> ..not >>
    	}
    	return ones;
}
    
    
}
