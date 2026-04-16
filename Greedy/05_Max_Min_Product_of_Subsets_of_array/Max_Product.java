public class Max_Product {
    // Brute Force : Generate all subsets => TC: O(2^n)

    // Optimal 
    public long subset_with_maximum_product(int[] arr) {
        int negCount = 0, posCount = 0, zeroCount = 0;
        int largestNegative = Integer.MIN_VALUE; // Closest to zero among negatives
        long productOfPositives = 1;
        long productOfNegatives = 1;

        for (int num : arr) {
            if (num == 0) zeroCount++;
            else if (num > 0) { 
                posCount++;  
                productOfPositives *= num; 
            }
            else { 
                negCount++;  
                largestNegative = Math.max(largestNegative, num); 
                productOfNegatives *= num; 
            }
        }

    
        // Edge case 1 : All elements are zero
        if (zeroCount == arr.length) return 0;
        // Edge case 2 : Only one non-zero element and it's negative → best subset is just {0}
        if (zeroCount == arr.length - 1 && negCount == 1) return 0;

        // Logic 
        // No negatives → product of all positives is the max
        if (negCount == 0) return productOfPositives;

        // Even negatives → all negatives contribute a positive product
        if (negCount % 2 == 0) return productOfNegatives * productOfPositives;

        // Odd negatives → drop the largest negative (least impact) to make product positive
        return (productOfNegatives / largestNegative) * productOfPositives;
    }
}
