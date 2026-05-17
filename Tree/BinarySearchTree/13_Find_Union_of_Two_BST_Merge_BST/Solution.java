class Solution {
    
    // Do inorder on both trees using stack-version2
    private ArrayList<Integer> methodTwo(Node root1, Node root2) {
        ArrayDeque<Node> stack1 = new ArrayDeque<>();
        ArrayDeque<Node> stack2 = new ArrayDeque<>();
        
        // Initilaize both the stacks
        Node temp = root1;
        while(temp != null) { stack1.push(temp); temp = temp.left; }
        
        temp = root2;
        while(temp != null) { stack2.push(temp); temp = temp.left; }
        
        ArrayList<Integer> result = new ArrayList<>();
        // Do merging
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            Node top1 = stack1.peek();
            Node top2 = stack2.peek();
            
            if(top1.data < top2.data) {
                result.add(top1.data);
                stack1.pop();
                Node ptr = top1.right;
                while(ptr != null) {
                    stack1.push(ptr);
                    ptr = ptr.left;
                }
            }else  {
                result.add(top2.data);
                stack2.pop();
                Node ptr = top2.right;
                while(ptr != null) {
                    stack2.push(ptr);
                    ptr = ptr.left;
                }
            }
        }
        
        while(!stack1.isEmpty()) {
            Node top1 = stack1.peek();
            result.add(top1.data);
            stack1.pop();
            Node ptr = top1.right;
            while(ptr != null) {
                stack1.push(ptr);
                ptr = ptr.left;
            }
        }
        
        while(!stack2.isEmpty()) {
            Node top2 = stack2.peek();
            result.add(top2.data);
            stack2.pop();
            Node ptr = top2.right;
            while(ptr != null) {
                stack2.push(ptr);
                ptr = ptr.left;
            }
        }
        
        return result;
    }
    
    public ArrayList<Integer> merge(Node root1, Node root2) {
        return methodTwo(root1, root2);
    }
}