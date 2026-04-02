class Solution {
    public boolean f(int pos, Set<Integer> stonesSet, int lastStone, int prevJump) {
        // Base case
        if(pos == lastStone) return true;
        if(!stonesSet.contains(pos)) return false;
        if(prevJump == 0) return false;

        return 
            f(pos + prevJump - 1, stonesSet, lastStone, prevJump-1) || // Path 1
            f(pos + prevJump    , stonesSet, lastStone, prevJump  ) || // Path 2
            f(pos + prevJump + 1, stonesSet, lastStone, prevJump+1); // Path 3
    }

    public boolean canCross(int[] stones) {

        Set<Integer> stonesSet = new HashSet<>();

        for(int i = 0 ; i < stones.length ; i++) {
            stonesSet.add(stones[i]);
        }

        return f(1, stonesSet, stones[stones.length - 1], 1);
    }
}