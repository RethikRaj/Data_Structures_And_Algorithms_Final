class Solution {
    
    public void sortedInsert(Stack<Integer> st, int val) {
        if(st.isEmpty() || st.peek() < val) {
            st.push(val);
            return;
        } 
        
        int poppedEle = st.pop();
        sortedInsert(st, val);
        st.push(poppedEle);
    }
    
    public void recAns(Stack<Integer> st) {
        if(st.isEmpty()) return;
        
        int poppedEle = st.pop();
        recAns(st);
        sortedInsert(st, poppedEle);
    }
    
    
    public void sortStack(Stack<Integer> st) {
        // Idea : After given stack becomes empty => Now it is a sorted stack but empty => we can do sorted Insert while backtracking.
        recAns(st);
    }
}