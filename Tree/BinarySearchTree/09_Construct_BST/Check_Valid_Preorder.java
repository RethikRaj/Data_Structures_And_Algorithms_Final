

class Solution {
    // Recursive
    private static int preorderIndex;
    static void simulateBuildBST(int preorder[], int lowerRange, int upperRange) {
        if(preorderIndex == preorder.length) return;

        int element = preorder[preorderIndex];
        if(element < lowerRange || element > upperRange) return;
        
        int root = element;
        preorderIndex += 1;

        simulateBuildBST(preorder, lowerRange, element);
        simulateBuildBST(preorder, element, upperRange);
    }
    
    // Iterative - Stack based
    static class Pair {
        int lowerBound;
        int upperBound;
        Pair(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }
    
    static int iterative(int preorder[]) {
        ArrayDeque<Pair> stack = new ArrayDeque<>();
        
        stack.push(new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE));
        
        for(int i = 0; i < preorder.length ; i++) {
            int element = preorder[i];
            
            if(element < stack.peek().lowerBound) {
                return 0; // false;
            }
            
            while(element > stack.peek().upperBound) {
                stack.pop();
            }
            
            Pair temp = stack.pop();
            // push right range
            stack.push(new Pair(element, temp.upperBound));
            // push left range
            stack.push(new Pair(temp.lowerBound, element));
        }
        
        return 1;
    }
    
    static int canRepresentBST(int arr[], int N) {
        // preorderIndex = 0;
        // simulateBuildBST(arr, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // return preorderIndex == N ? 1 : 0;
        
        return iterative(arr);
    }
}