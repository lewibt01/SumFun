package model;


import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class GridModel extends Observable {
    private TileModel[][] grid;

    //constructor
    public GridModel() {
        grid = new TileModel[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int randomVal = ThreadLocalRandom.current().nextInt(0, 10);
                grid[i][j] = new TileModel();
                if (!((i == 0 || j == 0) || (i == 8 || j == 8))) {
                    grid[i][j].setNumber(randomVal);
                    grid[i][j].setBoolean(true);

                }
            }
        }

        updateGrid();
    }

    public TileModel[][] getGrid() {
        return grid;
    }

    //takes values from neighboring tiles and performs game logic calculation
    public void performCalc() {
        setChanged();
        notifyObservers();

    }

    public void updateGrid() {
        setChanged();
        notifyObservers();

    }
}
