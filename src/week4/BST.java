package week4;


/**
 * Using BST as a Map
 * @param <Key>
 * @param <Value>
 */
public class BST <Key extends Comparable<Key>,Value>{

    private Node root; // tree shape depends on insertion order

    public Value get(Key key){ // 1+logN
        Node x = root;
        while(x!=null){
            int compare = key.compareTo(x.key);
            if(compare<0)x = x.left;
            else if (compare>0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public Key floor(Key key){
        Node x = floor(root, key);
        return x.key;
    }

    private Node floor(Node root, Key key){
        if(root==null) return null;
        int comp = key.compareTo(root.key);

        if(comp==0){
            return root;
        }else if (comp<0){
            return floor(root.left,key);
        }

        Node temp = floor(root.right,key);
        if (temp!=null) return temp;

        return root;
    }

    public void put(Key key , Value value){
        // if search is null , insert , else update
        root = put(root, key,value);
    }

    private Node put(Node root , Key key , Value value){
        if (root==null){
            // no - root , insert first element
            return new Node(key,value); // takes care of inserting on non-empty tree
        }
        int comp = key.compareTo(root.key);
        if (comp<0){ // insert at root's left
            root.left = put(root.left , key,value);
        }else if (comp>0){
            // insert at root's right
            root.right = put(root.right, key , value);
        }else {
            // means key exists with another value
            root.value = value;
        }
        root.count = 1 + size(root.left) + size(root.right);
        return root;
    }

    public int size(){
        return size(root);
    }

    private int size(Node root){
        if (root==null) return 0;
        return root.count;
    }

    public int rank(Key key){
        // number keys < key
        return rank(root,key);
    }

    private int rank(Node root,Key key){
        if (root == null){
            return 0;
        }
        int comp = key.compareTo(root.key);
        if (comp<0) return rank(root.left,key);
        else if (comp>0){
            // key is greater than current root ,
            // root key is smaller than key , 1 found
            // aa left subtree of root , is also smaller
            return 1 + size(root.left) + rank(root.right,key);
        }else{
            // key == root's key , all element smaller in left subtree
            return size(root.left);
        }
    }



    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        private int count;
        public Node(Key key,Value value){
            this.key= key;
            this.value = value;
            this.count = 0;
            // left and right nulls
        }
    }
}



