class Solution {
    public TreeNode f(int[] nums, int start, int end) {
        if(start > end) return null;

        int mid = start + (end - start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = f(nums, start, mid - 1);
        root.right = f(nums, mid + 1, end);

        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return f(nums, 0, nums.length - 1);
    }
}