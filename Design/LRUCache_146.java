/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/



    
    /*
    LRU DS 
        create a doubly linked list of nodes  - which has *** DUMMY HEAD & TAIL *** pointer,  they r extra nodes, dont del them
        head is neded to add to the beg..
        tail to remove the last one
        
        this DS also stores the capacity and count 
        hashmap stores the key and the node
    
    addNodeAtBeg() to hash and to list
    removeNode() --> from hash and from list
    moveNodeToBeg -> remove and add 
    
    class Node - key /value/next/pre
    
    get() -> get the Node from the hash and move this node to the beginning of the list
    put() -> put the node into hash and add it to the beg. if capacity remove tail 

    */
    /*fastest solution */
class LRUCache {
    private HashMap<Integer,LinkedNode> cache;
    private LinkedNode head;
    private LinkedNode tail;
    private int _capacity;
    private int count;
    
    class LinkedNode {
        int key;
        int value;
        LinkedNode pre;
        LinkedNode next;
    }
    
    private void addNode(LinkedNode node){
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }
    
    private void removeNode(LinkedNode node){
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }
    
    private void moveToHead(LinkedNode node){
        removeNode(node);
        addNode(node);
    }
    
    public LRUCache(int capacity) {
        _capacity = capacity;
        cache = new HashMap<>();
        
        head = new LinkedNode();
        head.pre = null;
        
        tail = new LinkedNode();
        tail.next = null;
        
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)){
            LinkedNode node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        LinkedNode node  = cache.get(key);
        if (node == null){
            node = new LinkedNode();
            node.key = key;
            node.value = value;
            addNode(node);
            cache.put(key,node); 
            ++count;
            if (count > _capacity){
                cache.remove(tail.pre.key);
                removeNode(tail.pre);
                count--;
            }           
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}

 /*   private class Node{
        int key, value;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
        Node(){
            this(0, 0);
        }
    }
    private int capacity, count;
    private Map<Integer, Node> map;
    private Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if(null==n){
            return -1;
        }
        update(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        Node n = map.get(key);
        if(null==n){
            n = new Node(key, value);
            map.put(key, n);
            add(n);
            ++count;
        }
        else{
            n.value = value;
            update(n);
        }
        if(count>capacity){
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
            --count;
        }
    }
    
    private void update(Node node){
        remove(node);
        add(node);
    }
    private void add(Node node){
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }
    
    private void remove(Node node){
        Node before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }
}*/
