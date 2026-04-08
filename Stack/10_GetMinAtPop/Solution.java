class GetMin {
    public static Stack<Integer> _push(int arr[], int n) {
        Stack<Integer> st = new Stack<>();
        
        // Always push minTillNow as top of Stack
        
        for(int ele : arr) {
            
            if(st.empty()) {
                st.push(ele);
            }else{
                st.push(Math.min(st.peek(), ele));
            }
        }
        
        return st;
    }

    static void _getMinAtPop(Stack<Integer> s) {
        while(!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
}
