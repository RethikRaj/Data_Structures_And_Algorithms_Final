class Solution {
    // Method 1 
    private void f(int[] nums, List<Integer> temp, List<List<Integer>> result, Set<Integer> visited) {
        if(temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(!visited.contains(nums[i])) {
                temp.add(nums[i]);
                visited.add(nums[i]);
                
                f(nums, temp, result, visited);

                visited.remove(nums[i]);
                temp.removeLast();
            }
        }
    }

    // Method 2 : Without using temp, visited -> Make changes in original array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void g(int[] nums, int index, List<List<Integer>> result) {
        if(index == nums.length) {
            // Copy nums into result.
            result.add(new ArrayList<>());
            for(int i = 0; i < nums.length ;i++) {
                result.get(result.size() - 1).add(nums[i]);
            }
            return;
        }

        // [0, index) -> already visited, [index, nums.length - 1] -> not visited
        for(int i = index; i < nums.length ; i++) {
            swap(nums, index, i);

            g(nums, index + 1, result);

            swap(nums, index, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // f(nums, new ArrayList<>(), result, new HashSet<>());

        g(nums, 0, result);

        return result;
    }
}