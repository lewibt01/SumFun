package model;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

//Grid Model used to make the grid of the game board
public class GridModel extends Observable {
    private TileModel[][] grid;
    private int val = 0;
    private final int rowMax = 9;
    private final int colMax = 9;

    //constructor
    public GridModel() {
        grid = new TileModel[colMax][rowMax];
        for (int i = 0; i < colMax; i++) {
            for (int j = 0; j < rowMax; j++) {

                val = ThreadLocalRandom.current().nextInt(0, 10);
                grid[i][j] = new TileModel();
                grid[i][j].setBoolean(false);
                if (!((i == 0 || j == 0) || (i == colMax - 1 || j == rowMax - 1))) {

                    grid[i][j].setNumber(val);
                    grid[i][j].setBoolean(true);

                }
            }
        }

        updateGrid();
    }

    public int getMaxCol() {
        return colMax;
    }

    public int getValue() {
        return val;
    }

    public int getMaxRow() {
        return rowMax;
    }

    //stores all neighbors (and the tile in question : 9 values max) in an array
    public void getNeighbors(TileModel tm, GridModel gm, Position pos) {
        int[] arr = new int[9];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (gm.getGrid()[pos.getRow()-1][pos.getCol()].getInt());

        }
        arr[0] = gm.getGrid()[pos.getRow()-1][pos.getCol()].getInt();
    }

    public Position getTilePosition(TileModel tm, GridModel gm) {
        Position pos = new Position(0,0);
        for (int i = 0; i < getMaxRow(); i++) {
            for (int j = 0; j < getMaxCol(); j++) {
                if (tm == gm.getGrid()[i][j]) {
                    pos = new Position(i,j);
                }
            }
        }
        return pos;
    }

    //need to change number from queue and take it and move it to the grid's unoccupied tile
    //need to change number of queue to move up one when this happens
    //need to change value of occupied to true if placed
    public void changeNum(TileModel grid, QueueModel queue) {
        int queueVal = queue.getQValue();
        boolean occupied = grid.getBool();
        if (grid.getInt() == 0 && !occupied) {
            grid.setNumber(queueVal);
            grid.setBoolean(true);
            queue.updateQueue();
        }
        setChanged();
        notifyObservers();
    }

    //returns the grid for the gameBoard
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

    //helper class to get the position of the tile in the grid
    //helps with getNeighbors()
    private class Position {
        private int row;
        private int col;

        Position(int r, int c) {
            row = r;
            col = c;
        }

        public int getRow() {return row;}
        public int getCol() {return col;}
    }
}
