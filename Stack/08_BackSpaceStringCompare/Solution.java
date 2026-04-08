class Solution {
    // Better 
    public boolean better(String s, String t) {
        ArrayDeque<Character> stackS = new ArrayDeque<>();
        ArrayDeque<Character> stackT = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            if(c == '#') {
                if(!stackS.isEmpty()) {
                    stackS.pop();
                }
            }else {
                stackS.push(c);
            }
        }

        for(char c : t.toCharArray()) {
            if(c == '#') {
                if(!stackT.isEmpty()) {
                    stackT.pop();
                }
            }else {
                stackT.push(c);
            }
        }

        // Compare whether stackS and stackT is equal
        while(!stackS.isEmpty() && !stackT.isEmpty()) {
            if(stackS.pop() != stackT.pop()) {
                return false;
            }
        }

        return stackS.isEmpty() && stackT.isEmpty();
    }

    // Optimal (Two pointer from end)
    public String resultAfterRemovalString(String s) {
        int i = s.length() - 1;
        StringBuilder res = new StringBuilder("");
        int count = 0;
        while(i >= 0) {
            char ch = s.charAt(i);
            if(ch == '#') {
                count += 1;
            } else {
                if(count == 0) {
                    res.append(ch);
                }else {
                    count -= 1;
                }
            }
            i -= 1;
        }
        return res.toString();
    }

    public boolean best(String s, String t) {
        String resS = resultAfterRemovalString(s);
        String resT = resultAfterRemovalString(t);

        return resS.equals(resT);
    }

    public boolean backspaceCompare(String s, String t) {
        
        return best(s, t);
    }
}
