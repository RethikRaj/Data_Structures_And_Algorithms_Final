class Solution {
    // N - ary tree
    /*
    temp - holds the current combination
    result - contains all combinations
    */
    // f(start, n, k) - returns all combinations of k size by using numbers in range : [start, n];
    private void f(int start, int n, int k, List<Integer> temp, List<List<Integer>> result) {
        if(k == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if(n - start + 1 < k) return; // available range < required size.

        for(int i = start ; i <= n ; i++) {
            temp.add(i);
            f(i + 1, n, k - 1, temp, result);
            temp.removeLast();
        }
    }

    // Binary tree
    // Combinations = Find all subset of size k for the set = { 1, 2, 3, ... n}
    private void g(int num, int n, int k, List<Integer> temp, List<List<Integer>> result) {
        if(k == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if(n - num + 1 < k) return; // available range < required size.

        // Include
        temp.add(num);
        g(num + 1, n, k - 1, temp, result);
        temp.removeLast();

        // Exclude
        g(num + 1, n, k , temp, result);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // f(1, n, k, new ArrayList<>(), result);

        g(1, n, k, new ArrayList<>(), result);

        return result;
    }
}

