package com.TSI.minesweeper;

import java.util.Stack;

public class NumberTile extends Tile {
    public void QuickReveal() {
        revealed = true;
    }
    @Override
    void ResultOfReveal(Map game, int[] pos) {
        if (value.equals("0")){
            floodFillDFS(game.getGrid(), pos);
        }
    }

    public void floodFillDFS(Tile[][] grid, int[] pos) {
        int h = grid.length;
        if (h == 0)
            return;
        int l = grid[0].length;

        boolean[][] visited = new boolean[h][l];

        Stack<String> stack = new Stack<>();

        //stack.push(0 + "," + 0);
        stack.push(pos[0] + "," + pos[1]);

        while (!stack.empty()) {

            String x = stack.pop();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);

            if(row<0 || col<0 || row>=h || col>=l || visited[row][col]) continue;

            if(!grid[row][col].value.equals("0")){
                ((NumberTile)grid[row][col]).QuickReveal();
                continue;
            }

            visited[row][col]=true;
            ((NumberTile)grid[row][col]).QuickReveal();

            stack.push(row + "," + (col-1)); //go left
            stack.push(row + "," + (col+1)); //go right
            stack.push((row-1) + "," + col); //go up
            stack.push((row+1) + "," + col); //go down
            stack.push((row-1) + "," + (col-1)); //go bottom left
            stack.push((row-1) + "," + (col+1)); //go top right
            stack.push((row+1) + "," + (col+1)); //go bottom right
            stack.push((row+1) + "," + (col-1)); //go top left
        }
    }
}
