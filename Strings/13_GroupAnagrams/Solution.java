class Solution {
    // brute Force
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] freqS = new int[128];
        for(int i = 0; i < s.length();i++) {
            freqS[s.charAt(i)] += 1;
        }

        for(int i = 0; i < s.length();i++) {
            freqS[t.charAt(i)] -= 1;
            if(freqS[t.charAt(i)] < 0) return false;
        }

        return true;
    }
    
    public List<List<String>> brute(String[] strs) {
        Set<Integer> alreadyGrouped = new HashSet<>();
        
        List<List<String>> l = new ArrayList<>();

        for(int i = 0 ; i < strs.length; i++) {
            List<String> temp = new ArrayList<>();
            for(int j = 1; j < strs.length; j++) {
                if(!alreadyGrouped.contains(j) && isAnagram(strs[i], strs[j])) {
                    temp.add(strs[j]);
                    alreadyGrouped.add(j);
                }
            }
            if(!alreadyGrouped.contains(i)) {
                temp.add(strs[i]);
                alreadyGrouped.add(i);
            }
            if(!temp.isEmpty()) {
                l.add(temp);
            }
        }

        return l;
    }

    // Sorting Approach
    // Property of anagrams : If we sort a all strings of single anagram group , they give same output
    // TC : O(n * klogk) , where k is the average length of strings, SC :O(n*k)
    public List<List<String>> sortingApproach(String[] strs) {
        Map<String, List<String>> mp = new HashMap<>();

        for(int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            // Sort temp
            Arrays.sort(temp);
            
            String key = new String(temp);

            List<String> list = mp.getOrDefault(key, new ArrayList<>());
            list.add(strs[i]);
            mp.put(key, list);
        }

        return new ArrayList<>(mp.values());
    }

    // Approach 3 (Best)
    // TC : O(n * k), SC :O(n*k)
    public List<List<String>> best(String[] strs) {
        Map<String, List<String>> mp = new HashMap<>();

        for(int i = 0 ; i < strs.length; i++) {
            String temp = strs[i];

            // Build freq array
            int[] freqMap = new int[26]; // only lowercase english letters
            for(int j=0; j < temp.length();j++) {
                freqMap[temp.charAt(j) - 'a'] += 1;
            }

            // Convert this freq array to key
            StringBuilder key = new StringBuilder("");
            for(int j = 0; j < freqMap.length ; j++) {
                key.append(freqMap[j] + "#");
            }

            String skey = key.toString();
            List<String> val = mp.getOrDefault(skey, new ArrayList<>());
            val.add(strs[i]);
            mp.put(skey, val);
        }

        return new ArrayList<>(mp.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        
        // return sortingApproach(strs);

        return best(strs);
    }
}
