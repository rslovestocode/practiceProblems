/*

Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?


*/

class Solution {
    public boolean isAnagram(String s, String t) {
        char[] sarr = s.toCharArray();  
        char[] tarr = t.toCharArray();
        Arrays.sort(sarr);                  //nlgn solution
        Arrays.sort(tarr);
        //if (String.valueOf(sarr).equals(String.valueOf(tarr))){
        if (Arrays.equals(sarr,tarr)){
            return true;
        }
        return false;
        
       /* int[] scount = new int[26];       /o(n)
        int[] tcount = new int[26];
        
        return true;*/
    }
}
