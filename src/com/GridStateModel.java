package com;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GridStateModel {
    private Tiles[][] grid;

    //constructor
    public GridStateModel() {
        grid = new Tiles[9][9];

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                int randomVal = ThreadLocalRandom.current().nextInt(0, 10);
                grid[i][j].setNumber(randomVal);
                grid[i][j].setBoolean(true);
            }
        }
    }

    
}
