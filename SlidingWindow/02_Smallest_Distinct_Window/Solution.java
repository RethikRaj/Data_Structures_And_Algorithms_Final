class Solution {
    public int findSubString(String str) {
        int countOfDistinctCharacters = 0;
        boolean[] visited = new boolean[255];
        for(int i = 0; i < str.length();i++) {
            if(!visited[str.charAt(i)]) {
                visited[str.charAt(i)] = true;
                countOfDistinctCharacters += 1;
            }
        }
        
        int minLength = str.length(); // you can also initilaize with Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int[] windowCount = new int[255];
        
        while(right < str.length()) {
            // Increase window size until countOfDistinctCharacters becomes 0
            while(right < str.length() && countOfDistinctCharacters > 0) {
                // if we are seeing this character for first time then decrement countOfDistinctCharacters by 1
                if(windowCount[str.charAt(right)] == 0) {
                    countOfDistinctCharacters -= 1;
                }
                
                windowCount[str.charAt(right)] += 1;
                right += 1;
            }
            
            // Decrease window size unitl countOfDistinctCharacters becomes 1
            while(left < right && countOfDistinctCharacters != 1) {
                minLength = Math.min(minLength, right - left); // update minLength becuase if entered loop then that means countOfDistinctCharacters is zero.
                
                windowCount[str.charAt(left)] -= 1;
                if(windowCount[str.charAt(left)] == 0) {
                    countOfDistinctCharacters += 1;
                }
                left += 1;
            }
        }
        return minLength;
    }
}