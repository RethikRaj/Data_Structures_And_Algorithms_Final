class Solution {
    public void reverse(char[] arr, int start, int end) {
        int left = start;
        int right = end;
        while(left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left += 1;
            right -= 1;
        }
    }

    public String reverseStr(String s, int k) {
        int i = 0;

        char[] ans = s.toCharArray();
        int remainingNumberOfCharacters = ans.length - i;

        while(remainingNumberOfCharacters >= 2*k) {
            // reverse the first k characters and make i point to 2*k;
            reverse(ans, i, i + k-1);
            i = i + 2*k;

            // Update 
            remainingNumberOfCharacters = ans.length - i;
        }

        // Now there are less than 2k characters left
        if(remainingNumberOfCharacters >= k) {
            // reverse first k characters and leave the others as original
            reverse(ans, i, i + k-1);
        }

        if(remainingNumberOfCharacters < k) {
            // reverse all of them 
            reverse(ans, i, ans.length-1);
        }

        return new String(ans);
    }
}
