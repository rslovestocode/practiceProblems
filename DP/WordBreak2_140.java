/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/


//fast solution as per leetcode

// i) speed improved by dict into hashset 
//  max length for the dict words.. 

/* Algo is this
    - add dict words to a HashSet
    - calculate max length of any dict word
    - call backrtack apis ( String, 
                            result_list_that_is_filled_up,
                            StringBuilder_tmp -> that is currently holding the path 
                            idx_to_orig_String_to_Start_From
                            dict
                            invali_boolean_tocheck_if_breakable_so_that_we_Do_not_Repeat_checking_it)  //optimization
                            
     - backtrck{
        - idx = s.lentgh --> add string to result
          prelen = tmp.length()  // length before addign a word
        - for(i = idx, i <Math.min(s.length, idx+maxlen) ;i ++ ) {
               newword = s.substring(idx, i+1) -->chk in dict
               if _in_dict
                    tmp.apend(" ").append(newword); 
                       backtravk()
                    tmp.setLength(prelen);

        }
        
     }                            


*/

class Solution {
 //   private int min = Integer.MAX_VALUE;;
    private int max = 0;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<String>();
        for(String word:wordDict){
            set.add(word);
            int curLen = word.length();
          //  min = (curLen<min)? curLen:min;
            max = (curLen>max)? curLen:max;
        }
        List<String> result = new ArrayList<String>();
        boolean[] invalid  = new boolean[s.length()];//invalid[i]: [i:] is unbreakable
        seperate(s,result, new StringBuilder(), 0, set, invalid);
        return result;
    }
    //convert dict to hashset 
    // invalid array to store if i.. is not breakable .. 
    //idx of string to read from
    //result arrray to add to
    // your current solution in StringBuilder(res) 
    
    public boolean seperate(String s,List<String> res, StringBuilder tmp, int index,HashSet<String> set, boolean[] invalid){
        if(index == s.length()){
            res.add(tmp.toString().trim());
            return true;
        }
        boolean breakable = false;
        int prelen = tmp.length();
        
        int rightbound = Math.min(s.length(),index+max); //at most many chars to check.. from current idx .. 
        
        for(int end = index+1; end<=rightbound; end++){ 
            //int curLen = end-index;
            // if(curLen < min || curLen > max){
            //     continue;
            // }
            //end==s.length()
           /* if(end<s.length()&&invalid[end]){
                continue;
            }*/
            String cur = s.substring(index, end);
            if(set.contains(cur)){

                tmp.append(" ").append(cur);        // add the new word.. and go ahead check it
                breakable |= seperate(s,res,tmp,end,set, invalid);
                tmp.setLength(prelen); // back from the api .. remove the new word added by setting the length back again
            }
        }
        invalid[index] = !breakable;
        return breakable;
    }
}


/*  SLOW SOLUTION BY 50% 


class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new LinkedList<String>();
        if (s == null || s.length() == 0) {
            return result;
        }
        if (s.length() != 0 && (wordDict == null || wordDict.size() == 0)) {
            return result;
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        int max = 0;
        for (String item : wordDict) {
            max = Math.max(max, item.length());
        }
        for (int i = 1; i <= len; i++) {
            for (int j = Math.max(i - max, 0); j < i; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        if (!dp[len]) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        calculate(s, wordDict, sb, result, 0);
        return result;
    }
    private void calculate(String s, List<String> wordDict, StringBuilder sb, List<String> result, int start) {
        if (start == s.length()) {
            result.add(sb.toString().trim());
            return;
        }
        int length = sb.length();
        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (wordDict.contains(str)) {
                sb.append(str).append(" ");
                calculate(s, wordDict, sb, result, i);
                sb.setLength(length);
            }
        }
    }
}*/
