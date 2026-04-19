class Solution {
    public int maxProduct(int[] nums) {
        int maxProd=nums[0];
        int minProd=nums[0];
        int ans=nums[0];
        for(int i=1; i<nums.length; i++){
            int a=nums[i]*maxProd;
            int b=nums[i]*minProd;
            maxProd=Math.max(nums[i], Math.max(a, b));
            minProd=Math.min(nums[i], Math.min(a, b));
            ans=Math.max(ans, maxProd);
        }
        return ans;
    }
}