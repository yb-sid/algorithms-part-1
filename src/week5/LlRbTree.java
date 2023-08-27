package week5;

public class LlRbTree<Key extends Comparable<Key>,Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public Value get(Key key){
        Node x = root;
        while (x!=null){
            int comp = key.compareTo(x.key);
            if(comp<0) x = x.left;
            else if (comp>0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public void put(Key key , Value value){
        root = put(root,key,value);
    }
    private Node put(Node root , Key key , Value value){

        if (root== null ) return new Node(key,value,RED);

        int comp = key.compareTo(root.key);

        if (comp<0) root.left = put(root.left , key,value);
        else if (comp>0) root.right = put(root.right , key , value);
        else root.value = value; // replace same key

        // rotate operations
        if(isRed(root.right) && !isRed(root.left)) root = rotateLeft(root);
        if (isRed(root.left) && isRed(root.left.left)) root = rotateRight(root);
        if (isRed(root.right) && isRed(root.left)) flipColor(root);

        return root;
    }


    private void flipColor(Node h){
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);

        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node rotateRight(Node h){ // do a temp right
        Node x = h.left;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node rotateLeft(Node h){ // constant number of operations O(1)
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private boolean isRed(Node x){
        if (x==null) return false; // null links are black
        return x.color==RED;
    }

    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color;// color of link from parent

        public Node(Key key,Value value,boolean color){
            this.key = key;
            this.value = value;
            this.color = color;
        }

    }
}
