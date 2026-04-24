class Solution {
    
    public long factorial(int n) {
        if(n == 0 || n == 1) return 1;
        
        long ans = 1;
        for(int i = 1; i <= n ; i++) {
            ans *= i;
        }
        
        return ans;
    }

    public long nCr(int n, int r) {
        return ( factorial(n) / (factorial(n-r) * factorial(r)) );
    }
    
    
    public long countFriendsPairings(int n) {
        long numberOfWays = 1; // all single 
        
        for(int p  = 1 ; p <= n/2 ; p++) {
            long temp = 1;
            for(int j = 0 ; j <= p - 1 ; j++) {
                temp = temp * (nCr( n-2*j , 2 ));
            }
            
            temp /= factorial(p);
            numberOfWays += temp;
        }
        
        return numberOfWays;
    }
}
