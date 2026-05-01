class Solution {
    public int maxRotateFunction(int[]A) {
        int sum=0, F=0;
        int nums=A.length;
        for(int i=0; i<nums; i++){
            sum+=A[i];
            F+=i*A[i];
        }
        int max=F;
        for(int i=1; i<nums; i++){
            F+=sum-nums*A[nums-i];
            max=Math.max(max, F);
        }
        return max;
    }
}