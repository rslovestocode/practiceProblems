/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?


*/


/* 2 PQueues
    i) stores the lower half of the sorted elements ( max is peek() and elements are lesser than peek() ...MAXheap)
    ii) stores the upper half of the sorted elements (minHeap )
    - add to right heap - maxHeap and minHeap. maxHeap(storing the lower half of elements) --> preferred
    - then fix the size 
    - keep the size same or only diff by 1
    - return median if size is same or 1 more 
Tricks
    - handle overflow - (a+b) /2   = a/2 + b/2 !
    - or else 

*/

class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> min = new PriorityQueue<>((o1, o2)->(o2 - o1));
    PriorityQueue<Integer> max = new PriorityQueue<>();
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        if (!min.isEmpty() && num > min.peek()) {
            max.add(num);
        } else {
            min.add(num);
        }
        if (min.size() - max.size() > 1) {
            max.add(min.poll());
        } else if (max.size() - min.size() > 1) {
            min.add(max.poll());
        }
    }
    
    public double findMedian() {
        if (min.size() == max.size()) {
            return (min.peek() + max.peek())/2.0;
        } else if (min.size() > max.size()) {
            return (double)(min.peek());
        } else {
            return (double)(max.peek());
        }
    }
}


/*class MedianFinder {

    private Queue<Long> small = new PriorityQueue(),
                        large = new PriorityQueue();

    public void addNum(int num) {
        large.add((long) num);
        small.add(-large.poll());
        if (large.size() < small.size())
            large.add(-small.poll());
    }

    public double findMedian() {
        return large.size() > small.size()
               ? large.peek()
               : (large.peek() - small.peek()) / 2.0;
    }
};
*/

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
