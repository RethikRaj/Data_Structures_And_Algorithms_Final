class Solution {
    public int secondHighest(String s) {
        // Similar to second largest in array.
        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for(int i = 0; i < s.length() ; i++) {
            char ch = s.charAt(i);
            
            if(isDigit(ch)) {
                int num = ch - '0';
                if(num > firstLargest) {
                    secondLargest = firstLargest;
                    firstLargest = num;
                }else if(num != firstLargest && num > secondLargest) {
                    secondLargest = num;
                }
            }
        }

        if(secondLargest == Integer.MIN_VALUE) {
            return -1;
        }

        return secondLargest;
    }

    public static boolean isDigit(char ch) {
        int ich = ch-'0';

        return ich >= 0 && ich <= 9;
    }
}