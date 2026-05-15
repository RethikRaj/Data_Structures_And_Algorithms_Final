class Solution {
    // Binary tree
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

    // N-ary tree
    private void g(int[] candidates, int startIndex, int target, List<Integer> temp, List<List<Integer>> result) {
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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        f(candidates, 0, target, new ArrayList<>(), result);

        // g(candidates, 0, target, new ArrayList<>(), result);

        return result;
    }
}