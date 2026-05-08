class Solution {
    // Backtracking: pick or skip each number from num to n
    public void f(int num, int n, int k, List<Integer> subset, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(subset));
            return;
        }
        if (k > n - num + 1) return; // not enough numbers left to fill k spots
        if(num > n) return;

        // Include num
        subset.add(num);
        f(num + 1, n, k - 1, subset, result);

        subset.removeLast();
        // Exclude num
        f(num + 1, n, k, subset, result);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        f(1, n, k, new ArrayList<>(), result);
        return result;
    }
}