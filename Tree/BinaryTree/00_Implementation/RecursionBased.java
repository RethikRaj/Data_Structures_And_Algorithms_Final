import java.util.Scanner;

class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
    }
}

public class RecursionBased {

    static Scanner sc = new Scanner(System.in); // Single shared scanner

    public static Node createBinaryTree(int val) {
        if (val == -1) return null;

        Node root = new Node(val);

        // Left child
        System.out.print("Enter left child of " + root.val + " (-1 for none): ");
        root.left = createBinaryTree(sc.nextInt());

        // Right child
        System.out.print("Enter right child of " + root.val + " (-1 for none): ");
        root.right = createBinaryTree(sc.nextInt());

        return root;
    }

    public static void preOrder(Node root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        System.out.print("Enter value for root (-1 for empty tree): ");
        Node root = createBinaryTree(sc.nextInt());
        sc.close();

        System.out.print("\nPre-Order: ");
        preOrder(root);
        System.out.println();
    }
}