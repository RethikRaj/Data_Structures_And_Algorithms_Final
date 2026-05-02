/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    // Method One : Normal Level order + reverse alternatively(odd levels)
    // TC: O(n) | SC: O(n)
    public void reverse(List<Integer> arr) {
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            int temp = arr.get(left);
            arr.set(left, arr.get(right));
            arr.set(right, temp);
            left++;
            right--;
        }
    }

    public List<List<Integer>> methodOne(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null) return ans;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        boolean toReverse = false;

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < size ; i++) {
                TreeNode front = q.poll();

                level.add(front.val);

                if(front.left != null) q.offer(front.left);
                if(front.right != null) q.offer(front.right);
            }

            if(toReverse) {
                reverse(level);
            }

            ans.add(level);
            toReverse = !toReverse;
        }

        return ans;
    }

    // Method Two : 
    // Queue as traversal structure(plain level order traversal)
    // LinkedList(acts as Deque) as level collector 
    // Zigzag handled in RESULT COLLECTION — traversal is untouched standard BFS
    // addLast → LtoR  |  addFirst → RtoL  (both O(1), no reverse needed)
    // TC: O(n) | SC: O(n)
    public List<List<Integer>> methodTwo(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null) return ans;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        boolean leftToRight = true;

        while(!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> levelList = new LinkedList<>(); // zigzag logic lives here

            for(int i = 0; i < size; i++) {
                TreeNode front = q.poll();

                if(leftToRight) levelList.addLast(front.val);
                else            levelList.addFirst(front.val);

                if(front.left != null) q.offer(front.left);
                if(front.right != null) q.offer(front.right);
            }

            ans.add(levelList);
            leftToRight = !(leftToRight);
        }

        return ans;
    }

    // Method Three : 
    // Deque as traversal structure
    // ArrayList as level collector
    // Zigzag handled in TRAVERSAL ITSELF — pollFirst/pollLast + offerFirst/offerLast
    // Children pushed to opposite end in correct order so next level is naturally ready
    // TC: O(n) | SC: O(n)
    public List<List<Integer>> methodThree(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null) return ans;

        Deque<TreeNode> deque = new ArrayDeque<>(); // zigzag logic lives here
        deque.offerLast(root);

        boolean LtoR = true;

        while(!deque.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            int size = deque.size();

            if(LtoR) {
                for(int i = 0; i < size; i++) {
                    // 1. Pop front
                    TreeNode temp = deque.pollFirst();
                    level.add(temp.val);

                    // 2. Push at rear - left, then right
                    if(temp.left != null) deque.offerLast(temp.left);
                    if(temp.right != null) deque.offerLast(temp.right);
                }
            }
            else {
                for(int i = 0; i < size; i++) {
                    // 1. Pop rear
                    TreeNode temp = deque.pollLast();
                    level.add(temp.val);

                    // 2. push at front - right, then left
                    if(temp.right != null) deque.offerFirst(temp.right);
                    if(temp.left != null) deque.offerFirst(temp.left);
                }
            }

            ans.add(level);
            // Update direction
            LtoR = !(LtoR);
        }
        return ans;
    }



    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // return methodOne(root);

        // return methodTwo(root);

        return methodThree(root);
    }
}