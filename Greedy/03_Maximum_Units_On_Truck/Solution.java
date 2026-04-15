import java.util.Arrays;

class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // Idea : Always pick the box with maximum units

        // Step 1 : Sort the boxTypes
        Arrays.sort(boxTypes, (a , b) -> b[1] - a[1]);

        // Step 2 :
        int units = 0; 
        for(int i = 0 ; i < boxTypes.length; i++) {
            int numberOfBoxes = boxTypes[i][0];
            int numberOfUnitsInBox = boxTypes[i][1];

            if(truckSize >= numberOfBoxes) {
                truckSize -= numberOfBoxes;
                units += (numberOfBoxes * numberOfUnitsInBox);
            }else {
                units += (truckSize) * numberOfUnitsInBox;
                break;
            }
        }

        return units;
    }
}
