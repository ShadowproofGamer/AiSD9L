import java.util.Objects;
import java.util.Stack;

public class BinaryExpressionTree {
    Node root;
    int height;

    public BinaryExpressionTree() {
        root = null;
        height = -1;
    }

    public void load(Stack<Object> s) {
        while (!s.isEmpty()) {
            insert2(s.pop());
        }
    }

    public void insert(Object s) {
        //root = insertRecursive(root, s);
        insertRecursive(s, root);
    }


    private void insertRecursive(Object s, Node temp) {
        if (temp == null) {
            root = new Node(s);
            //System.out.println(root.key);
        } else if (Objects.equals(s, "-") || Objects.equals(s, "+") || Objects.equals(s, "*") || Objects.equals(s, "/") || Objects.equals(s, "%") || s.getClass() == String.class) {

            if (temp.right == null) temp.right = new Node(s);
            else if ((temp.right.key.getClass() == Integer.class || temp.right.key.getClass() == Double.class) && temp.left == null)
                temp.left = new Node(s);
            else if (temp.right.key.getClass() == String.class) {
                //System.out.println("r str");
                insertRecursive(s, temp.right);
            } else if (root.left == null) root.left = new Node(s);
            else if (root.left.key.getClass() == String.class) insertRecursive(s, root.left);
        } else {
            if (temp.right == null) temp.right = new Node(s);
            else if (temp.right.key.getClass() == Integer.class || temp.right.key.getClass() == Double.class) {
                if (temp.left == null) temp.left = new Node(s);
                else if (temp.left.key.getClass() == String.class) insertRecursive(s, temp.left);
                else if (root.left == null) root.left = new Node(s);
                else if (root.left.key.getClass() == String.class) {
                    insertRecursive(s, root.left);
                }
            } else {
                //System.out.println(s);
                insertRecursive(s, temp.right);
            }
        }
    }
    public void insert2(Object s){
        root = insertRecursive(root, s, false).root;
    }

    private InsertClass insertRecursive(Node root, Object s, boolean inserted) {
        InsertClass ins = new InsertClass(root, inserted);
        if (root == null) {
            root = new Node(s);
            return new InsertClass(root, true);
        }
        if (root.key.getClass() == String.class) {
            ins = insertRecursive(root.right, s, false);
            if (ins.inserted) root.right=ins.root;
            if (!ins.inserted){
                ins = insertRecursive(root.left, s, false);
                if (ins.inserted) root.left=ins.root;
            }

            return new InsertClass(root, ins.inserted);
        }
        return ins;
    }


    /*
        private Node insertRecursive(Node root, Object key){
            if (root == null){
                root = new Node(key);
                return root;
            }
            else if (root.key.getClass() != Integer.class && root.key.getClass() != Double.class) root.right = insertRecursive(root.right, key);

            return root;
        }

        */
    public void inorder() {
        inorderRecursive(root);
    }

    private void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.key + " ");
            inorderRecursive(root.right);
        }
    }

    public void preorder() {
        preorderRecursive(root);
    }

    private void preorderRecursive(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRecursive(root.left);
            preorderRecursive(root.right);
        }
    }

    public void postorder() {
        postorderRecursive(root);
    }

    private void postorderRecursive(Node root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print(root.key + " ");
        }
    }

    public int count() {
        return counterRecursive(root, 0);
    }

    private int counterRecursive(Node root, int result) {
        if (root != null) {
            result++;
            result += counterRecursive(root.left, 0);
            result += counterRecursive(root.right, 0);
        }
        return result;
    }

    public int heightCount() {
        heightCounterRecursive(root, -1);
        return height;
    }

    private int heightCounterRecursive(Node root, int result) {
        if (root != null) {
            result++;
            int l = heightCounterRecursive(root.left, result);
            if (l > height) height = result;
            int r = heightCounterRecursive(root.right, result);
            if (r > height) height = result;

        }
        return result;
    }

    public int leafCount() {
        return leafCounterRecursive(root, 0);
    }

    private int leafCounterRecursive(Node root, int result) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                return 1;
            }
            if (root.right != null) result += leafCounterRecursive(root.right, 0);
            if (root.left != null) result += leafCounterRecursive(root.left, 0);
        }
        return result;
    }

    public String inorderInfix() {
        return inorderInfixRecursive(root, "");
    }

    private String inorderInfixRecursive(Node root, String result) {
        if (root != null) {
            if (root.key.getClass() == Integer.class || root.key.getClass() == Double.class)
                return String.valueOf(root.key);
            else if (Objects.equals(root.key, "-"))
                return ("(" + inorderInfixRecursive(root.left, "") + "-" + inorderInfixRecursive(root.right, "") + ")");
            else if (Objects.equals(root.key, "+"))
                return ("(" + inorderInfixRecursive(root.left, "") + "+" + inorderInfixRecursive(root.right, "") + ")");
            else if (Objects.equals(root.key, "*"))
                return ("(" + inorderInfixRecursive(root.left, "") + "*" + inorderInfixRecursive(root.right, "") + ")");
            else if (Objects.equals(root.key, "/"))
                return ("(" + inorderInfixRecursive(root.left, "") + "/" + inorderInfixRecursive(root.right, "") + ")");
            else if (Objects.equals(root.key, "%"))
                return ("(" + inorderInfixRecursive(root.left, "") + "%" + inorderInfixRecursive(root.right, "") + ")");
        }
        return result;
    }

    public int result() {
        return resultRecursive(root, 0);
    }

    private int resultRecursive(Node root, int result) {
        if (root != null) {
            if (root.key.getClass() == Integer.class || root.key.getClass() == Double.class) return (int) root.key;
            else if (Objects.equals(root.key, "-"))
                return (resultRecursive(root.left, 0) - resultRecursive(root.right, 0));
            else if (Objects.equals(root.key, "+"))
                return (resultRecursive(root.left, 0) + resultRecursive(root.right, 0));
            else if (Objects.equals(root.key, "*"))
                return (resultRecursive(root.left, 0) * resultRecursive(root.right, 0));
            else if (Objects.equals(root.key, "/"))
                return (resultRecursive(root.left, 0) / resultRecursive(root.right, 0));
            else if (Objects.equals(root.key, "%"))
                return (resultRecursive(root.left, 0) % resultRecursive(root.right, 0));
        }
        return result;
    }

    private class Node {
        public Object key;
        public Node left, right;

        public Node(Object data) {
            key = data;
            left = right = null;
        }
    }
    private class InsertClass {
        public boolean inserted;
        public Node root;
        public InsertClass(Node root, boolean inserted){
            this.root = root;
            this.inserted = inserted;
        }
    }

}
