class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(n==0 || grid[0].length==0){
            return -1;
        }
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }
        int[][] directions = {{-1,-1}, {0,-1}, {1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0,0,1});
        boolean[][] vis = new boolean[n][n];
        vis[0][0] = true;

        while(!q.isEmpty()) {
            int [] curr = q.poll();
            int r=curr[0], c=curr[1], d=curr[2];

            if(r==n-1 && c==n-1) {
                return d;
            }

            for(int[] dir : directions) {
                int nRow = r + dir[0];
                int nCol = c+ dir[1];
                if (nRow>=0 && nCol>=0 && nRow<n && nCol<m && grid[nRow][nCol]==0 && !vis[nRow][nCol]) {
                    vis[nRow][nCol] = true;
                    q.offer(new int[]{nRow,nCol, d+1});
                }
            }
        }
        return -1;
    }
}




