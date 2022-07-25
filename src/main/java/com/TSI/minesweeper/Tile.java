package com.TSI.minesweeper;

abstract class Tile {
    boolean revealed = false;   //false by default
    boolean flagged = false;
    String value;
    public void SetValue(String value){
        this.value = value;
    }
    String GetValue(){ return value; };
    boolean GetRevealed(){ return revealed; }
    boolean GetFlagged(){ return flagged; }
    public void Reveal(Map game, int[] pos) {
        revealed = true;
        ResultOfReveal(game, pos);
    }
    void Flag(Map game, int[] pos) { flagged = true; };
    abstract void ResultOfReveal(Map game, int[] pos);
}
