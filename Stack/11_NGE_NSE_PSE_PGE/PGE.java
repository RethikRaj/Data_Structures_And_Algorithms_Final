class Solution {
    // Better 1 : R to L traversal and stores pending(elements whose PGE is yet to be found)
    public ArrayList<Integer> betterRtoL(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i < arr.length;i++) {
            ans.add(-1);
        }
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for(int i = arr.length - 1; i >= 0; i--) {
            
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                // arr[i] is PGE of arr[stack.peek()]
                ans.set(stack.peek(), arr[i]);
                stack.pop();
            }
            
            stack.push(i);
            
        }
        return ans;
    }
    
    // Better 2 : L to R traversal and stores candidates(elements which can be PGE of upcoming elements)
    public ArrayList<Integer> betterLtoR(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i < arr.length;i++) {
            ans.add(-1);
        }
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for(int i = 0; i < arr.length; i++) {
            
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            
            if(!stack.isEmpty()) {
                // arr[stack.peek()] is PGE of arr[i]
                ans.set(i, arr[stack.peek()]);
            }
            // else PGE of arr[i] is -1 => initialized with -1
            
            stack.push(i);
        }
        return ans;
        
    }
    
    public ArrayList<Integer> preGreaterEle(int[] arr) {
        // return betterRtoL(arr);
        return betterLtoR(arr);
        
    }
}
