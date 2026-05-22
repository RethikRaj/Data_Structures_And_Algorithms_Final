import java.util.* ;
import java.io.*; 
import java.util.ArrayList;

public class Solution {

    private static ArrayList<Integer> methodTwo(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int ele : arr1) result.add(ele);
        for(int ele : arr2) result.add(ele);

        // Build heap using heapify(step-down)
        int totalSize = result.size();
        for(int i = totalSize/2 -1  ; i >= 0; i--) {
            // heapify(i)
            int curr = i;
            while(curr < totalSize) {
                int left = 2*curr + 1;
                int right = 2*curr + 2;
                int largest = curr;

                if(left < totalSize && result.get(left) > result.get(largest)) largest = left;
                if(right < totalSize && result.get(right) > result.get(largest)) largest = right;

                if(largest != curr) {
                    int temp = result.get(curr);
                    result.set(curr, result.get(largest));
                    result.set(largest, temp);

                    curr = largest;
                }else {
                    break;
                }
            }
        }
        return result;
    }

    public static ArrayList<Integer> mergeHeap(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        return methodTwo(arr1, arr2);
    }
}