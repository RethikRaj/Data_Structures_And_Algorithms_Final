class Solution {
    // Method 1 : Binary Tree
    private void f(int[] candidates, int idx, int target, List<Integer> temp, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if(idx == candidates.length) return;

        int candidate = candidates[idx];

        // Include
        if(target - candidate >= 0) {
            temp.add(candidate);
            f(candidates, idx, target - candidate, temp, result);
            temp.removeLast();
        }

        // Exlude
        f(candidates, idx + 1, target, temp, result);
    }

    // Method 2 : N-ary tree
    // Way 1 : Similar to subset but not same becuase repitiion is allowed.
    private void g(int[] candidates, int startIndex, int target, List<Integer> temp, List<List<Integer>> result) {
        // Early pruning
        if(target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = startIndex ; i < candidates.length ; i++) {
            int candidate = candidates[i];
            if(target - candidate < 0) continue;

            temp.add(candidate);
            g(candidates, i, target - candidate, temp, result);
            temp.removeLast();
        }
    }

    // Way 2 : Each index can be chosen 0 to maxFreq times.
    private void h(int[] candidates, int index, int target, List<Integer> temp, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if(index == candidates.length) return;

        // Each index can be used as many times as we want
        int candidate = candidates[index];
        int freq = 0;
        while(target - candidate*freq >= 0) {
            h(candidates, index + 1, target - candidate*freq, temp, result);
            temp.add(candidate); // add one more for next iteration
            freq += 1;
        }

        // Backtrack-Remove all added copies
        // freq is exactly how many times we added
        for (int i = 0; i < freq; i++) temp.removeLast();
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // f(candidates, 0, target, new ArrayList<>(), result);

        // g(candidates, 0, target, new ArrayList<>(), result);

        h(candidates, 0, target, new ArrayList<>(), result);

        return result;
    }
}