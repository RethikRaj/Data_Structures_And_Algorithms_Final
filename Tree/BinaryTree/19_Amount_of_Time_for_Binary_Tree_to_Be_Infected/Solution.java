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
class Info {
    TreeNode node;
    TreeNode parent;

    Info(TreeNode node, TreeNode parent) {
        this.node = node;
        this.parent = parent;
    }
}

class Solution {
    private TreeNode find(TreeNode root, int start) {
        if(root == null) return null;
        if(root.val == start) return root;

        TreeNode result1 = find(root.left, start);
        if(result1 != null) return result1;
        TreeNode result2 = find(root.right, start);
        // if(result2 != null) return result2;
        // return null;

        return result2;
    }

    private int adjacentList(TreeNode root, int start) {
        // Step 1 : prepare adjacency list
        Map<TreeNode, List<TreeNode>> adjList = new HashMap<>();

        Queue<Info> q = new ArrayDeque<>();
        q.offer(new Info(root, null));

        while(!q.isEmpty()) {
            int levelSize = q.size();
            for(int i = 0; i < levelSize; i++) {
                Info front = q.poll();

                adjList.put(front.node, new ArrayList<>());
                if(front.parent != null) {
                    adjList.get(front.node).add(front.parent);
                }

                if(front.node.left != null) {
                    q.offer(new Info(front.node.left, front.node));
                    adjList.get(front.node).add(front.node.left);
                }

                if(front.node.right != null) {
                    q.offer(new Info(front.node.right, front.node));
                    adjList.get(front.node).add(front.node.right);
                }  
            }
        }

        // Step 2 : Find the given infected node
        TreeNode startNode = find(root, start);
        if(startNode == null) { // read constraints this case is not possible as `A node with a value of start exists in the tree.`
            return -1;
        }

        // Step 3 : From the infected node start infecting its adjacent who are not infected
        Set<TreeNode> infectedNodes = new HashSet<>();
        infectedNodes.add(startNode);
        
        Queue<TreeNode> q2 = new ArrayDeque<>();
        q2.add(startNode);

        int minutes = -1;

        while(!q2.isEmpty()) {
            int size = q2.size();

            for(int i = 0; i < size; i++) {
                TreeNode front = q2.poll();

                // infect all adjacents which are not already infected.
                for(TreeNode node : adjList.get(front)) {
                    if(!infectedNodes.contains(node)) {
                        infectedNodes.add(node);
                        q2.offer(node);
                    }
                }
            }    

            minutes += 1;
        }

        return minutes;
    }

    public int amountOfTime(TreeNode root, int start) {
        return adjacentList(root, start);
    }
}