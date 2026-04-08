class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> st = new ArrayDeque<>();


        for(String s : operations) {
            if(s.equals("+")) {
                int temp1 = st.pop();
                int temp2 = st.pop();
                int temp3 = temp1 + temp2;

                st.push(temp2);
                st.push(temp1);
                st.push(temp3);

            }else if(s.equals("C")) {
                st.pop(); // Don't worry about empty see constraint
            }else if(s.equals("D")) {
                st.push(st.peek() * 2);
            }else {
                st.push(Integer.parseInt(s));
            }
        }

        int ans = 0;
        while(!st.isEmpty()) {
            ans += st.pop();
        }
        return ans;
    }
}
