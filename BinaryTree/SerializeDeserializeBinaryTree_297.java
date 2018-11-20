/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.



*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
    PREORDER 
    Integer.toString(root.val) + "/" .....

ONLY PREORDER & POSTORDER GIVE  ACCURATE DEPICTION OF THE TREE - NOT INORDER

*/


public class Codec {


    // Encodes a tree to a single string.
    private static final String DELIMITER = "/";
    private static final String NULL_NODE = "NULL";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)   return NULL_NODE + DELIMITER;
        return Integer.toString(root.val) + DELIMITER + serialize(root.left) + serialize(root.right); //do a dfs --preorder
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(DELIMITER)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(LinkedList<String> nodes) {
        String node = nodes.remove();                        // remember REMOVE..not just get(0)
        if (node.equals(NULL_NODE))     return null;
        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.left = buildTree(nodes);
        root.right = buildTree(nodes);
        return root;
    }


}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
