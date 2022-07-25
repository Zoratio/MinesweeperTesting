package com.TSI.minesweeper;

import java.util.Random;
/*
ToDo
- Input validation
*/

public class Map {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_MAGENTA = "\u001B[35m";
    public static final String ANSI_BLACK = "\u001B[30m";

    final private Tile[][] grid;
    final private int size;
    private int totalBombs;

    public enum gameState {INCOMPLETE, COMPLETE, FAILED}
    public gameState state = gameState.INCOMPLETE;

    public Map() {
        System.out.println("Welcome to Minesweeper");
        size = ScannerInputs.CheckRange();   //Do this method
        grid = new Tile[size][size]; //makes grid then sets all values to 0. i then j.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new NumberTile();
            }
        }
        pickStartingTile();

        gameLoop();
    }

    private void pickStartingTile() {
        totalBombs = ScannerInputs.CheckDifficulty(size);
        displayGrid();
        int[] pos = new int[3];
        pos = ScannerInputs.CheckInput(pos);
        placeBombs(totalBombs, pos);
        buildGrid();
        grid[pos[0]][pos[1]].Reveal(this, pos);
        displayGrid();
    }

    public void placeBombs(int totalBombs, int[] startingPos) {
        int bombs = 0;
        while(bombs != totalBombs)
        {
            Random random = new Random();
            int i = random.nextInt(size);
            int j = random.nextInt(size);
            if (grid[i][j] instanceof BombTile || i == startingPos[0] && j == startingPos[1] || i == startingPos[0]-1 && j == startingPos[1] || i == startingPos[0]+1 && j == startingPos[1] || i == startingPos[0] && j == startingPos[1]+1 || i == startingPos[0] && j == startingPos[1]-1
                    || i == startingPos[0]-1 && j == startingPos[1]-1 || i == startingPos[0]-1 && j == startingPos[1]+1 || i == startingPos[0]+1 && j == startingPos[1]+1 || i == startingPos[0]+1 && j == startingPos[1]-1){
                continue;
            }
            BombTile bt = new BombTile();
            bt.SetValue("B");
            grid[i][j] = bt;

            bombs++;
        }
    }

    public void buildGrid() {
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                int BombsConnected = 0;
                if(!(grid[i][j] instanceof BombTile))   //ensures the tile being checked for connected bombs is not a bomb itself
                {

                    if(i!=0)
                    {
                        if(grid[i-1][j] instanceof BombTile) BombsConnected++;  //above
                        if(j!=0)
                        {
                            if(grid[i-1][j-1] instanceof BombTile) BombsConnected++;    //above left
                        }

                    }
                    if(i!=size-1)
                    {
                        if(grid[i+1][j] instanceof BombTile) BombsConnected++;  //below
                        if(j!=size-1)
                        {
                            if(grid[i+1][j+1] instanceof BombTile) BombsConnected++;    //below right
                        }
                    }
                    if(j!=0)
                    {
                        if(grid[i][j-1] instanceof BombTile) BombsConnected++;  //left
                        if(i!=size-1)
                        {
                            if(grid[i+1][j-1] instanceof BombTile) BombsConnected++;    //below left
                        }
                    }
                    if(j!=size-1)
                    {
                        if(grid[i][j+1] instanceof BombTile) BombsConnected++;  //right
                        if(i!=0)
                        {
                            if(grid[i-1][j+1] instanceof BombTile) BombsConnected++;    //above right
                        }
                    }

                    (grid[i][j]).SetValue(String.valueOf(BombsConnected));  //sets how many bombs are connected to this tile
                }
            }
        }
    }

    public void displayGrid() {

        int solved = 0;
        System.out.print("\t ");
        for(int i=0; i<size; i++)
        {
            if (i >9 ){
                System.out.print(" " + i + " ");
            }
            else{
                System.out.print(" " + i + "  ");

            }
        }
        System.out.print("\n");
        for(int i=0; i<size; i++)
        {
            System.out.print(i + "\t| ");

            for(int j=0; j<size; j++)
            {
                if(grid[i][j] instanceof BombTile){
                    if ((grid[i][j]).GetRevealed()){
                        System.out.print((grid[i][j]).GetValue());
                    }
                    else if ((grid[i][j]).GetFlagged()){
                        System.out.print("?");
                    }
                    else{
                        System.out.print("#");
                    }
                }
                else{
                    if ((grid[i][j]).GetRevealed()){
                        solved++;
                        switch ((grid[i][j]).GetValue()){
                            case "0":
                                System.out.print(" ");
                                break;
                            case "1":
                                System.out.print(ANSI_BLUE + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "2":
                                System.out.print(ANSI_GREEN + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "3":
                                System.out.print(ANSI_RED + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "4":
                                System.out.print(ANSI_YELLOW + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "5":
                                System.out.print(ANSI_CYAN + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "6":
                                System.out.print(ANSI_MAGENTA + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            default:
                                System.out.print(ANSI_BLACK + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                        }

                    }
                    else if ((grid[i][j]).GetFlagged()){
                        System.out.print("?");
                    }
                    else{
                        System.out.print("#");
                    }
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
        if (solved == size*size-totalBombs){
            state = gameState.COMPLETE;
        }
    }

    private void gameLoop(){
        while (state == gameState.INCOMPLETE){
            inputPosition();
            displayGrid();
        }
        if (state == gameState.COMPLETE){
            completeGame();
        }
        else{
            failedGame();
        }
    }

    private void inputPosition() {
        int[] pos = new int[3];
        pos = ScannerInputs.CheckInput(pos);
        if (pos[2] == 1){
            grid[pos[0]][pos[1]].Flag(this, pos);
        }
        else{
            grid[pos[0]][pos[1]].Reveal(this, pos);
        }

    }

    public Tile[][] getGrid(){
        return grid;
    }

    private void completeGame() {
        System.out.println("You win");
    }

    public void failedGame() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int[] pos = new int[2];
                pos[0] = i;
                pos[1] = j;
                grid[i][j].Reveal(this, pos);
            }
        }
        displayGrid();
        System.out.println("You lost");
    }
}
