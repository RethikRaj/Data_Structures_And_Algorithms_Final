public class Solution {
    static int[] a = {6, 8, 2, 7, 4, 2, 7};
    static int[] b = {7, 8, 5, 8, 6, 3, 5};
    static int[] c = {8, 3, 2, 6, 8, 4, 1};
    
    public static int f(int day, char prevActivity) {
        if(day < 0) return 0;

        int ans = 0;
        if(prevActivity == 'A') {
            ans = Math.max(f( day - 1, 'B') + b[day] , f(day - 1, 'C') + c[day]);
        }
        else if(prevActivity == 'B') {
            ans = Math.max(f( day - 1, 'A') + a[day]  , f(day - 1, 'C') + c[day]);
        }
        else if(prevActivity == 'C') {
            ans = Math.max(f(day - 1, 'A') + a[day] , f(day - 1, 'B') + b[day]);
        }else {
            ans = Math.max(Math.max(f(day - 1, 'A') + a[day], f( day - 1, 'B') + b[day]), f( day - 1 , 'C') + c[day]);
        }

        return ans;
    }


    public static void main(String[] args) {
        int N = 7;
        
        int ans = f(N - 1, '-');
        System.out.println(ans);
    }
}