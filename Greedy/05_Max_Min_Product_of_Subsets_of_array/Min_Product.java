public class Min_Product {
    // Brute Force : Generate all subsets => TC: O(2^n)

    // Optimal 
    public long subset_with_minimum_product(int[] arr) {
        int negCount = 0, posCount = 0, zeroCount = 0;
        int largestNegative  = Integer.MIN_VALUE; // Closest to zero among negatives
        int smallestPositive = Integer.MAX_VALUE; // Smallest positive element
        long productOfPositives = 1;
        long productOfNegatives = 1;

        for (int num : arr) {
            if (num == 0) zeroCount++;
            else if (num > 0) { 
                posCount++;  
                smallestPositive = Math.min(smallestPositive, num); 
                productOfPositives *= num; }
            else { 
                negCount++;  
                largestNegative  = Math.max(largestNegative,  num); 
                productOfNegatives *= num; 
            }
        }

        // 1) No negatives 
        if (negCount == 0) {
            if(zeroCount > 0) return 0;
            else return smallestPositive;
        }

        // 2) Negatives are present :
        // Odd negatives 
        if (negCount % 2 != 0) return productOfNegatives * productOfPositives;
        else return (productOfNegatives/largestNegative) * productOfPositives;
    }

    
}
