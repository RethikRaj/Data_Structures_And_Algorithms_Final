import java.util.ArrayDeque;
import java.util.Scanner;

class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
        // By default left and right is set to null
    }
}

public class LevelOrderBased {
    public static void preOrder(Node root) {
        if(root == null) return;

        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    // Build tree using level-order input (-1 means no child)
    public static Node createBinaryTree() {
        Scanner sc = new Scanner(System.in);
        ArrayDeque<Node> queue = new ArrayDeque<>();

        System.out.print("Enter the value for root : ");
        int x = sc.nextInt();
        Node root = new Node(x);
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node front = queue.poll();

            System.out.print("Enter the value for left child of " + front.val + " : ");
            int leftVal = sc.nextInt();
            if(leftVal != -1) {
                front.left = new Node(leftVal);
                queue.offer(front.left);
            }
            System.out.print("Enter the value for right child of " + front.val + " : ");
            int rightVal = sc.nextInt();
            if(rightVal != -1) {
                front.right = new Node(rightVal);
                queue.offer(front.right);
            }
        }
        sc.close();
        return root;
        
    }

    public static void main(String[] args) {
        

        // Taking input on the go
        Node root = createBinaryTree();

        // If in question :  Input is stored in array(level-order), then do accordingly
        

        preOrder(root);

        
    }
}
