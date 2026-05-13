class Solution {
    private void f(int n, StringBuilder temp, List<String> result, int openingBrackets, int closingBrackets) {
        
        if(openingBrackets == n && closingBrackets == n) {
            result.add(temp.toString());
            return;
        }

        if(openingBrackets < n) { // This check is did for optimization
            temp.append('(');
            f(n, temp, result, openingBrackets + 1, closingBrackets);
            temp.deleteCharAt(temp.length() - 1);
        }
        
        if(openingBrackets > closingBrackets){ // Must needed condition
            temp.append(')');
            f(n, temp, result, openingBrackets, closingBrackets + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        f(n, new StringBuilder(""), result, 0, 0);
        return result;
    }
}