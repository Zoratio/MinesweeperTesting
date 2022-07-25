package com.TSI.minesweeper;

import java.util.Scanner;

public class ScannerInputs {
    static private Scanner myScanner = new Scanner(System.in);
    static public int CheckRange(){
        System.out.print("Please input the size of the grid:");
        int size = Integer.parseInt((myScanner.nextLine()));
        while (size <= 3){
            System.out.print("Unplayable size, please input a size greater than 3:");
            size = Integer.parseInt((myScanner.nextLine()));
        }
        return size;
    }

    static public int CheckDifficulty(int size){
        System.out.println("Please pick the difficulty number you want:\n - 1: Easy\n - 2: Medium\n - 3: Hard");
        int difficulty = Integer.parseInt((myScanner.nextLine()));
        int bombCount = size * difficulty;
        return bombCount;
    }
    static public int[] CheckInput(int[] pos){
        System.out.print("Please input if this is a 1. flag or 2. reveal (1/2):");
        pos[2] = Integer.parseInt(myScanner.nextLine());
        //CHECK IF VALID

        System.out.print("Please input the row coordinates of your input:");
        pos[0] = Integer.parseInt(myScanner.nextLine());
        //CHECK IF VALID

        System.out.print("Please input the column coordinates of your input:");
        pos[1] = Integer.parseInt(myScanner.nextLine());
        //CHECK IF VALID

        return pos;
    }
}
