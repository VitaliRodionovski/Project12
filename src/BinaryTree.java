
public class BinaryTree {
    Node root;

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.left, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node current) {
        return current.left == null ? current.value : findSmallestValue(current.left);
    }

    public void remove(int value) {
        root = deleteRecursive(root, value);
    }
    public void printTree(){
        Node node = root;
        recPrintTree(node);
        System.out.println("________");
    }
    private void recPrintTree(Node node){
        if (node.left != null){
            recPrintTree(node.left);
        }
        System.out.println(node.value);
        if (node.right != null){
            recPrintTree(node.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        b.add(4);
        b.add(6);
        b.add(3);
        b.add(1);
        b.add(2);
        b.add(7);
        b.printTree();
        System.out.println(b.containsNode(4));
        System.out.println(b.containsNode(1));

    }
}
