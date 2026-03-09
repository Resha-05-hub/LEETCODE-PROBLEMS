class Solution {
    public int numberOfStableArrays(int z0, int o1, int l) {
        final int MOD=1000000007;
        long [][][]dp=new long[o1+1][z0+1][2];
        dp[0][0][0]=dp[0][0][1]=1;
        for(int i=0; i<=o1; i++){
            for(int j=0; j<=z0; j++){
                for(int k=1; k<=l; k++){
                        if(i-k>=0){
                            dp[i][j][1]=dp[i][j][1]+dp[i-k][j][0]%MOD;
                        }
                        if(j-k>=0){
                            dp[i][j][0]=dp[i][j][0]+dp[i][j-k][1]%MOD;
                        }
                    }
                }
            }
            return (int)((dp[o1][z0][0]+dp[o1][z0][1])%MOD);
    }
}