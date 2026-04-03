class Solution {
    public int maxFreqSum(String s) {
        int arr[] = new int[128]; // only lower case letters

        for(char c : s.toCharArray()) {
            arr[c] += 1;
        }
        
        int maxFreqVowel = 0; // Question told us to consider 0 if no vowels
        int maxFreqConsonant = 0; // Question told us to consider 0 if no consonants

        for(char c = 'a'; c <= 'z'; c++) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                maxFreqVowel = Math.max(maxFreqVowel, arr[c]);
            }else{
                maxFreqConsonant = Math.max(maxFreqConsonant, arr[c]);
            }  
        }

        return maxFreqVowel + maxFreqConsonant;
    }
}
