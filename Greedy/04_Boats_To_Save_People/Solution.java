class Solution {
    // TC : O(nlogn), SC:O(sort)
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people); // Sort (ascending) based on weight

        int i = 0; 
        int j = people.length - 1;
        int boatCount = 0;
        
        while(i <= j) {
            if(people[i] + people[j] <= limit) { // Place pair(heavyWeight + lessWeight)
                i += 1;
                j -= 1;
            }else {
                // Place only heaviest person
                j -= 1;
            }
            boatCount += 1;
        }

        return boatCount;
    }
}