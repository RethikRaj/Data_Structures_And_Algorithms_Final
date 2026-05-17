class Solution {
    
    // Fills list with BST values in sorted (ascending) order
    private static void getInorder(Node root, ArrayList<Integer> inorder) {
        if(root == null) return;
        getInorder(root.left, inorder);
        inorder.add(root.data);
        getInorder(root.right, inorder);
    }
    
    // Method 1: Flatten both BSTs into sorted arrays, then merge-intersect
    // Time: O(m+n) | Space: O(m+n) for the two lists
    private static ArrayList<Integer> methodOne(Node r1, Node r2) {
        ArrayList<Integer> inorderOne = new ArrayList<>();
        ArrayList<Integer> inorderTwo = new ArrayList<>();
        
        getInorder(r1, inorderOne);
        getInorder(r2, inorderTwo);
        
        int p1 = 0, p2 = 0;
        ArrayList<Integer> common = new ArrayList<>();
        
        // Classic two-pointer intersection on sorted arrays
        while(p1 < inorderOne.size() && p2 < inorderTwo.size()) {
            if(inorderOne.get(p1) < inorderTwo.get(p2))       p1++;
            else if(inorderOne.get(p1) > inorderTwo.get(p2))  p2++;
            else { 
                common.add(inorderOne.get(p1)); 
                p1++; 
                p2++; 
            }
        }
        
        return common;
    }
    
    // Method 2: Iterative inorder traversal on both BSTs simultaneously
    // Avoids storing full arrays — stacks simulate the call stack
    // Time: O(m+n) | Space: O(h1+h2) where h = height
    private static ArrayList<Integer> methodTwo(Node r1, Node r2) {
        ArrayList<Integer> common = new ArrayList<>();
        
        ArrayDeque<Node> stack1 = new ArrayDeque<>();
        ArrayDeque<Node> stack2 = new ArrayDeque<>();
        
        // Prime each stack by pushing the leftmost path (smallest elements first)
        Node temp = r1;
        while(temp != null) { 
            stack1.push(temp); 
            temp = temp.left; 
        }
        temp = r2;
        while(temp != null) { 
            stack2.push(temp); 
            temp = temp.left; 
        }
        
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            Node top1 = stack1.peek();
            Node top2 = stack2.peek();
            
            if(top1.data < top2.data) {
                // Advance tree1's iterator: pop and descend into right subtree
                stack1.pop();
                Node ptr = top1.right;
                while(ptr != null) { 
                    stack1.push(ptr); 
                    ptr = ptr.left; 
                }
                
            } else if(top1.data > top2.data) {
                stack2.pop();
                Node ptr = top2.right;
                while(ptr != null) { 
                    stack2.push(ptr); 
                    ptr = ptr.left; 
                }
                
            } else {
                // Match found — record and advance both iterators
                common.add(top1.data);
                
                stack1.pop();
                Node ptr = top1.right;
                while(ptr != null) { 
                    stack1.push(ptr); 
                    ptr = ptr.left; 
                }
                
                stack2.pop();
                ptr = top2.right;
                while(ptr != null) { 
                    stack2.push(ptr); 
                    ptr = ptr.left; 
                }
            }
        }
        
        return common;
    }
    
    public static ArrayList<Integer> findCommon(Node r1, Node r2) {
        return methodTwo(r1, r2); // Preferred: O(h) space vs O(n) in method one
    }
}