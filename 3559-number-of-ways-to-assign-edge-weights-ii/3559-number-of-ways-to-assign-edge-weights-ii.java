class Solution {
    int[][] ancestor;
    int[] depth;
    int LOG = 17;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        long MOD = 1_000_000_007;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        ancestor = new int[LOG][n + 1];
        depth = new int[n + 1];
        boolean[] seen = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        seen[1] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (!seen[v]) {
                    seen[v] = true;
                    depth[v] = depth[u] + 1;
                    ancestor[0][v] = u;
                    q.offer(v);
                }
            }
        }

        for (int j = 1; j < LOG; j++)
            for (int i = 1; i <= n; i++)
                ancestor[j][i] = ancestor[j - 1][ancestor[j - 1][i]];

        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++)
            pow2[i] = pow2[i - 1] * 2 % MOD;

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            int d = depth[u] + depth[v] - 2 * depth[lca(u, v)];
            result[i] = d == 0 ? 0 : (int) pow2[d - 1];
        }
        return result;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++)
            if (((diff >> j) & 1) == 1) u = ancestor[j][u];
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--)
            if (ancestor[j][u] != ancestor[j][v]) {
                u = ancestor[j][u];
                v = ancestor[j][v];
            }
        return ancestor[0][u];
    }
}