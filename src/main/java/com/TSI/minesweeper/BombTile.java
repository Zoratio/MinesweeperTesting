package com.TSI.minesweeper;

public class BombTile extends Tile {

    @Override
    void ResultOfReveal(Map game, int[] pos) {
        game.state = Map.gameState.FAILED;
    }
}
