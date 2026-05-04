/*
 * Top View of Binary Tree
 * Returns nodes visible when the tree is viewed from the top.
 * Uses horizontal distance (HD) from root to track column positions.
 */

class Info {
    Node node;
    int HD; // Horizontal distance from root: left = -1, root = 0, right = +1

    Info(Node node, int HD) {
        this.node = node;
        this.HD = HD;
    }
}

class Pair {
    int level;
    int data;

    Pair(int level, int data) {
        this.level = level;
        this.data = data;
    }
}

class Solution {

    // TC: O(N) | SC: O(N)
    public ArrayList<Integer> topViewBFS(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<Info> q = new ArrayDeque<>();
        q.offer(new Info(root, 0));

        // Maps HD -> first (topmost) node value at that column
        Map<Integer, Integer> mp = new HashMap<>();
        int maxHD = Integer.MIN_VALUE;
        int minHD = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                Info front = q.poll();

                // Only record the first (topmost) node at each HD
                if (!mp.containsKey(front.HD)) {
                    mp.put(front.HD, front.node.data);
                    maxHD = Math.max(maxHD, front.HD);
                    minHD = Math.min(minHD, front.HD);
                }

                if (front.node.left != null) q.offer(new Info(front.node.left, front.HD - 1));
                if (front.node.right != null) q.offer(new Info(front.node.right, front.HD + 1));
            }
        }

        for (int i = minHD; i <= maxHD; i++) {
            ans.add(mp.get(i));
        }

        return ans;
    }

    private int maxHD;
    private int minHD;

    // TC: O(N) | SC: O(H) - recursive stack, H = height of tree
    public void topViewDFS(Node root, int HD, int level, Map<Integer, Pair> mp) {
        if (root == null) return;

        // Update if HD is new OR current node is higher (smaller level) than recorded
        if (!mp.containsKey(HD) || mp.get(HD).level > level) {
            mp.put(HD, new Pair(level, root.data));
            maxHD = Math.max(maxHD, HD);
            minHD = Math.min(minHD, HD);
        }

        topViewDFS(root.left, HD - 1, level + 1, mp);
        topViewDFS(root.right, HD + 1, level + 1, mp);
    }

    public ArrayList<Integer> topView(Node root) {
        // return topViewBFS(root);

        Map<Integer, Pair> mp = new HashMap<>();
        maxHD = Integer.MIN_VALUE;
        minHD = Integer.MAX_VALUE;
        topViewDFS(root, 0, 0, mp);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = minHD; i <= maxHD; i++) {
            ans.add(mp.get(i).data);
        }
        return ans;
    }
}