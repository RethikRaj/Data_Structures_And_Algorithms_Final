class Solution {
    
    public ArrayList<Integer> betterLtoR(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++) {
            ans.add(-1);
        }
        
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // stores indices
        
        for(int i = 0; i < arr.length; i++) {
            
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                // arr[i] is NSE of arr[stack.peek()]
                ans.set(stack.peek(), arr[i]);
                stack.pop();
            }
            
            stack.push(i);
        }
        
        return ans;
    }
    
    public ArrayList<Integer> betterRtoL(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++) {
            ans.add(-1);
        }
        
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // stores indices
        
        for(int i = arr.length-1; i >= 0; i--) {
            
            while(!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            
            // Now either stack is empty or arr[i] > arr[stack.peek()]
            
            if(!stack.isEmpty()) { // arr[stack.peek()] is NSE of arr[i]
                ans.set(i, arr[stack.peek()]);
            }
            
            stack.push(i);
        }
        
        return ans;
    }
    
    public ArrayList<Integer> nextSmallerEle(int[] arr) {
        // return betterLtoR(arr);
        return betterRtoL(arr);
    }
}