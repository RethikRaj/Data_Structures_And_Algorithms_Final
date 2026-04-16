class Solution {
    public static void reverse(int[] arr) {
        for(int i = 0 ; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
    
    public static int minCost(int n, int m, int[] x, int[] y) {
        int h_segments = 1;
        int v_segments = 1;
        int cost = 0;
        
        Arrays.sort(x);
        reverse(x); // For descending order
        // Note : If you don't want to reverse , then place the pointers at the back and then move left.
        Arrays.sort(y); 
        reverse(y); // For descending order
        
        
        int i = 0; // Points to x
        int j = 0; // Points to y
        
        while(i < x.length && j < y.length) {
            if(x[i] < y[j]) {
                // Make vertical cut
                v_segments += 1;
                
                cost = cost + y[j] * (h_segments);
                
                j += 1;
            }else {
                // Make horizontal cut
                h_segments += 1;
                
                cost = cost + x[i] * (v_segments);
                
                i += 1;
            }
            
        }
        
        while(i < x.length) {
            // Make horizontal cut
            h_segments += 1;
            
            cost = cost + x[i] * (v_segments);
            
            i += 1;
        }
        
        while(j < y.length) {
            // Make vertical cut
            v_segments += 1;
            
            cost = cost + y[j] * (h_segments);
            
            j += 1;
        }
        
        return cost;
    }
}

