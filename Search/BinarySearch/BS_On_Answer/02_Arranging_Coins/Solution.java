class Solution {

    // Method 3 :
    // Linear Search on Answer -> TLE
    public int linearMethodThree(int n) {
        int k = 1;
        while((k*(k+1)/2) <= n) {
            k++;
        }
        return k - 1; // code won't reach here;
    }

    // Binary Search on Answer
    public int binarySearch(int n) {
        long start = 1;
        long end = n;
        long ans = 0;
        while(start <= end) {
            long mid = start + (end - start) / 2;

            // Can we make mid complete rows ?
            boolean result = (mid * (mid + 1)) / 2 <= n;

            if(result == true) {
                ans = mid;
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return (int)ans;
    }

    public int arrangeCoins(int n) {
        // return linearMethodThree(n);
        return binarySearch(n);
    }
}