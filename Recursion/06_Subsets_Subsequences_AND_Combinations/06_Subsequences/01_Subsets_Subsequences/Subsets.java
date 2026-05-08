class Solution {
    // TC : O(n * 2^n), SC : O(n * 2^n)
    public List<List<Integer>> iterative(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>()); // Empty subset.

        for(int i = 0; i < nums.length; i++) {
            int size = ans.size(); // Snapshot current size to avoid processing newly added subsets, because size keeps on changing
            for(int j = 0; j < size; j++) {
                // Copy existing subset and add current number
                List<Integer> newSubset = new ArrayList<>(ans.get(j));
                newSubset.add(nums[i]);
                ans.add(newSubset);
            }
        }

        return ans;
    }
    
    // Recursion + backtracking - "Add at every leaf"
    // TC : O(n * 2^n) , SC : O(n * 2^n)
    public void f(int[] nums, int idx, List<Integer> currSubset, List<List<Integer>> ans) {
        if(idx == nums.length) {
            // !Dont't do reference copy : ans.add(currSubset);

            // As currSubset keeps on changing , therefore snapshot(shallow/deep copy) of currSubset at this moment
            ans.add(new ArrayList<>(currSubset)); 
            return;
        }

        currSubset.add(nums[idx]);
        f(nums, idx + 1, currSubset, ans);
        currSubset.removeLast();
        f(nums, idx + 1, currSubset, ans);
    }


    // Recursive + backtracking - "Add at every node"
    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Every state of current is a valid subset
        result.add(new ArrayList<>(current));

        // Which element to pick next from [start, n - 1] to extend my current subset .
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);          // choose
            backtrack(nums, i + 1, current, result);  // explore
            current.remove(current.size() - 1);        // un-choose (backtrack)
        }
    }

    // Bit-manipulation(Later)

    public List<List<Integer>> subsets(int[] nums) {
        // *Iterative*
        return iterative(nums);

        // *Recursive*
        // List<List<Integer>> ans = new ArrayList<>();
        // f(nums, 0, new ArrayList<>(), ans);
        // return ans;
    }
}