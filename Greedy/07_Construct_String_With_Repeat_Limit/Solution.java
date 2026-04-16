class Pair {
    char ch;
    int freq;

    Pair(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

class Solution {
    // Brute Force : Find all subsequences and then find the lexicographically largest with repeatLimit constraint

    // Better : Using priority Queues
    public String better(String s, int repeatLimit) {
        // Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Max-heap ordered by character (largest char has highest priority)
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.ch - a.ch);
        for (Map.Entry<Character, Integer> e : freqMap.entrySet()) {
            pq.offer(new Pair(e.getKey(), e.getValue()));
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            Pair top = pq.poll();
            char ch = top.ch;
            int freq = top.freq;

            // How many times we can place this character in one go
            int place = Math.min(freq, repeatLimit);
            for (int i = 0; i < place; i++) sb.append(ch);

            if (freq <= repeatLimit) {
                // Fully placed — move on to next character
                continue;
            }

            // Now : ( freq > repeatLimit) 
            // We need a different character in between 
            if (pq.isEmpty()) return sb.toString(); // No other character available — we're done

            // Place one occurrence of the next largest character as a delimiter
            Pair delimiter = pq.poll();
            sb.append(delimiter.ch);

            // Re-add current character with remaining freq
            pq.offer(new Pair(ch, freq - repeatLimit));

            // Re-add delimiter character if it still has remaining freq
            if (delimiter.freq - 1 > 0) {
                pq.offer(new Pair(delimiter.ch, delimiter.freq - 1));
            }
        }

        return sb.toString();
    }

    // Best : Using Arrays : Only 26 letters
    public String best(String s, int repeatLimit) {
        int[] freqMap = new int[26];
        for(char c : s.toCharArray()) freqMap[c - 'a'] += 1;

        StringBuilder sb = new StringBuilder("");

        int i = 25; // start from largest character

        while(i >= 0) {
            // If freq == 0 => continue
            if(freqMap[i] == 0) {
                i -= 1;
                continue;
            }

            int freq = freqMap[i];
            // How many times we can place this character in one go
            int place = Math.min(freq, repeatLimit);
            for(int j = 0 ; j < place ; j++) sb.append((char)(i + 'a'));

            if (freq <= repeatLimit) {
                // Fully placed — move on to next character
                i -= 1;
                continue;
            }

            // Now : ( freq > repeatLimit) 
            // We need a different character in between => Need to find the 2nd largest lexicographic character with non zero frequency
            int j = i - 1;
            while(j >= 0 && freqMap[j] == 0) j -= 1;

            if(j < 0) {
                // No other character available — we're done
                return sb.toString();
            }

            // Place one occurrence of the next largest character as a delimiter
            sb.append((char)(j + 'a'));

            // Update frequencies
            freqMap[i] -= repeatLimit;
            freqMap[j] -= 1;
        }

        return sb.toString();
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        // return better(s, repeatLimit);
        return best(s, repeatLimit);
    }
}