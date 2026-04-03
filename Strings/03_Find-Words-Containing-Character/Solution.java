class Solution {
    // TC : O(n^2), SC : O(n) if we consider the returning list;
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> l = new ArrayList<>();

        for(int i = 0; i < words.length;i++) {
            String word = words[i];
            // Inbuilt method 
            // int index = word.indexOf(x);
            // if( index >= 0 && index < word.length()) {
            //     l.add(i);
            // }

            // Own implementation
            int index = 0;
            while(index < word.length()) {
                if(word.charAt(index) == x) {
                    break;
                }
                index += 1;
            }

            if(index < word.length()) {
                l.add(i);
            }
        }

        return l;
    }
}