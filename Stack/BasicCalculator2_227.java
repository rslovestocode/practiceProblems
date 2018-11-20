
/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.



*/
class Solution {
    /* no parenthesis .. so only 2 level of results - +/- or 
      op = 0 ..no operation
          op = 1 is mult *
          op = -1 is division
    
    */
  public int calculate(String s) {
    int pre = 0, curr = 0, sign = 1, op = 0, num = 0;
    
    for (int i = 0; i < s.length(); i++) {
        if (Character.isDigit(s.charAt(i))) {
            num = num * 10 + (s.charAt(i) - '0');
            if (i == s.length() - 1 || !Character.isDigit(s.charAt(i + 1))) {  // i is last or i+1 is not a digit 
            	curr = (op == 0 ? num : (op == 1 ? curr * num : curr / num));
            }
            
        } else if (s.charAt(i) == '*' || s.charAt(i) == '/') {
            op = (s.charAt(i) == '*' ? 1 : -1);
            num = 0;
            
        } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            pre += sign * curr;
            sign = (s.charAt(i) == '+' ? 1 : -1);
            op = 0;
            num = 0;
        }
    }
    
    return pre + sign * curr;
}
}
/*
//start with + and push num.. then store the sign/operator ..and find the next number and now work based on the sign/operator

4 - 6 * 2

sign = +        
 currchar = -,sign = + , num = 4 --> push (+4)  
 sign = - , 
 currchar = 6 = num 
 currchar = * , num = 6, sign = - -> push(-6)
 currchar =2=num, last,sign=*-> push (-6*2) = push (-12)
 
 in the end loop and add all -> -8 




public class Solution {
public int calculate(String s) {
    int len;
    if(s==null || (len = s.length())==0) return 0;
    Stack<Integer> stack = new Stack<Integer>();
    int num = 0;
    char sign = '+';
    for(int i=0;i<len;i++){
        if(Character.isDigit(s.charAt(i))){
            num = num*10+s.charAt(i)-'0';
        }
        if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
            if(sign=='-'){
                stack.push(-num);
            }
            if(sign=='+'){                  // sign is set to + by default so first time num is pushed..
                stack.push(num);
            }
            if(sign=='*'){
                stack.push(stack.pop()*num);
            }
            if(sign=='/'){
                stack.push(stack.pop()/num);
            }
            
            sign = s.charAt(i);             //now set the sign ..reset num to 0 
            num = 0;
        }
    }

    int re = 0;
    for(int i:stack){
        re += i;
    }
    return re;
}
}
*/
