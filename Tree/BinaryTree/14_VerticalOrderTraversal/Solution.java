import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Info {
    TreeNode node;
    int row;
    int col;

    Info(TreeNode node, int row, int col) {
        this.node = node;
        this.row = row;
        this.col = col;
    }
}

class CustomComparator implements Comparator<Info> {
    @Override
    public int compare(Info i1, Info i2) {
        if(i1.row == i2.row && i1.col == i2.col) return i1.node.val - i2.node.val; // 3) If both row and col is same , then sort by val(ascending)
        if(i1.col == i2.col) return i1.row - i2.row; // 2) If column same , then sort by row (top to bottom == low to high)
        return i1.col - i2.col;  // 1) Sort by column (low to high)
    }
}

class Solution {
    // TC : O(n logn) , SC : O(n)
    // Why heap ? I need some custom ordering to be followed .
    public List<List<Integer>> usingPriorityQueue(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null) return ans;

        PriorityQueue<Info> pq = new PriorityQueue<>(new CustomComparator());

        Queue<Info> q = new ArrayDeque<>(); // For doing level-order bfs traversal
        q.offer(new Info(root, 0, 0));

        // /To rack column boundaries to size the answer list correctly
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        while(!q.isEmpty()) {
            int levelSize = q.size();

            for(int i = 0; i < levelSize ; i++) {
                Info front = q.poll();    

                pq.offer(front);
                minCol = Math.min(minCol, front.col);
                maxCol = Math.max(maxCol, front.col);

                if(front.node.left != null) q.offer(new Info(front.node.left, front.row + 1, front.col - 1));
                if(front.node.right != null) q.offer(new Info(front.node.right, front.row + 1, front.col + 1));
            }
        }

        int sizeOfAns = maxCol - minCol + 1;
        for(int i = 0; i < sizeOfAns ; i++) {
            ans.add(new ArrayList<>());
        }


        while(!pq.isEmpty()) {
            Info front = pq.poll();
            // col - minCol maps a column index → 0-based bucket
            ans.get(front.col - minCol).add(front.node.val);
        }

        return ans;
    }

    
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // return usingPriorityQueue(root);

        return usingMap(root);
    }
}