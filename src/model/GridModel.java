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
        forceUpdate();
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
    //the value from the queue will need to be set in the new tile before this function is called
    public void getNeighbors(GridModel gm, Position pos) {
        TileModel[] neighbors = new TileModel[9];
        ////////////
        //CORNERS
        ////////////
        //TOP LEFT
        if (pos.getRow()==0 && pos.getCol()==0) {
            //neighbors[0] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors[0] = (gm.getGrid()[pos.getRow()][pos.getCol()+1]);
            neighbors[1] = (gm.getGrid()[pos.getRow()+1][pos.getCol()]);
            neighbors[2] = (gm.getGrid()[pos.getRow()+1][pos.getCol()+1]);
        }
        //TOP RIGHT
        else if (pos.getRow()==0 && pos.getCol()==9) {
            neighbors[0] = (gm.getGrid()[pos.getRow()][pos.getCol()-1]);
            //neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors[1] = (gm.getGrid()[pos.getRow()+1][pos.getCol()-1]);
            neighbors[2] = (gm.getGrid()[pos.getRow()+1][pos.getCol()]);
        }
        //BOTTOM LEFT
        else if (pos.getRow()==9 && pos.getCol()==0) {
            neighbors[0] = (gm.getGrid()[pos.getRow()-1][pos.getCol()]);
            neighbors[1] = (gm.getGrid()[pos.getRow()-1][pos.getCol()+1]);
            //neighbors[2] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors[2] = (gm.getGrid()[pos.getRow()][pos.getCol()+1]);
        }
        //BOTTOM RIGHT
        else if (pos.getRow()==9 && pos.getCol()==9) {
            neighbors[0] = (gm.getGrid()[pos.getRow()-1][pos.getCol()-1]);
            neighbors[1] = (gm.getGrid()[pos.getRow()-1][pos.getCol()]);
            neighbors[2] = (gm.getGrid()[pos.getRow()][pos.getCol()-1]);
            //neighbors[3] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
        }
        ////////////
        //BORDERS
        ///////////
        //NORTH BORDER
        else if (pos.getRow()==0) {
            neighbors[0] = (gm.getGrid()[pos.getRow()][pos.getCol()-1]);
            //neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()+1]);
            neighbors[2] = (gm.getGrid()[pos.getRow()+1][pos.getCol()-1]);
            neighbors[3] = (gm.getGrid()[pos.getRow()+1][pos.getCol()]);
            neighbors[4] = (gm.getGrid()[pos.getRow()+1][pos.getCol()+1]);
        }
        //SOUTH BORDER
        else if (pos.getRow()==9) {
            neighbors[0] = (gm.getGrid()[pos.getRow()-1][pos.getCol()-1]);
            neighbors[1] = (gm.getGrid()[pos.getRow()-1][pos.getCol()]);
            neighbors[2] = (gm.getGrid()[pos.getRow()-1][pos.getCol()+1]);
            neighbors[3] = (gm.getGrid()[pos.getRow()][pos.getCol()-1]);
            //neighbors[4] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors[4] = (gm.getGrid()[pos.getRow()][pos.getCol()+1]);
        }
        //WEST BORDER
        else if (pos.getCol()==0) {
            neighbors[0] = (gm.getGrid()[pos.getRow()-1][pos.getCol()]);
            //neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors[1] = (gm.getGrid()[pos.getRow()+1][pos.getCol()]);
            neighbors[2] = (gm.getGrid()[pos.getRow()-1][pos.getCol()+1]);
            neighbors[3] = (gm.getGrid()[pos.getRow()][pos.getCol()+1]);
            neighbors[4] = (gm.getGrid()[pos.getRow()+1][pos.getCol()+1]);
        }
        //EAST BORDER
        else if (pos.getCol()==9) {
            neighbors[0] = (gm.getGrid()[pos.getRow()-1][pos.getCol()-1]);
            neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()-1]);
            neighbors[2] = (gm.getGrid()[pos.getRow()+1][pos.getCol()-1]);
            neighbors[3] = (gm.getGrid()[pos.getRow()-1][pos.getCol()]);
            //neighbors[4] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors[4] = (gm.getGrid()[pos.getRow()+1][pos.getCol()]);
        }
        ///////////
        //INTERIOR
        ///////////
        else {
            for (int i = 0; i < neighbors.length; i++) {
                neighbors[i] = (gm.getGrid()[pos.getRow()-1][pos.getCol()-1]);
                neighbors[i] = (gm.getGrid()[pos.getRow()-1][pos.getCol()]);
                neighbors[i] = (gm.getGrid()[pos.getRow()-1][pos.getCol()+1]);

                neighbors[i] = (gm.getGrid()[pos.getRow()][pos.getCol()-1]);
                //neighbors[i] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
                neighbors[i] = (gm.getGrid()[pos.getRow()][pos.getCol()+1]);

                neighbors[i] = (gm.getGrid()[pos.getRow()+1][pos.getCol()-1]);
                neighbors[i] = (gm.getGrid()[pos.getRow()+1][pos.getCol()]);
                neighbors[i] = (gm.getGrid()[pos.getRow()+1][pos.getCol()+1]);
            }
        }
        //for testing purposes
        for (TileModel tile : neighbors) {
            System.out.println(tile.getInt());
        }
    }

    //gets the [i][j] for the clicked tile so it can be found in the grid
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

    //modify the value of the tile at the given position
    public void setTileValue(int row, int col, int value){
        TileModel reference = grid[row][col];
        reference.setBoolean(true);
        reference.setNumber(value);

        setChanged();
        notifyObservers(grid);

    }
    //need to change number from queue and take it and move it to the grid's unoccupied tile
    //need to change number of queue to move up one when this happens
    //need to change value of occupied to true if placed
    public void changeNum(TileModel grid, QueueModel queue) {
        int queueVal = queue.peek();
        boolean occupied = grid.getBool();
        if (grid.getInt() == 0 && !occupied) {
            grid.setNumber(queueVal);
            grid.setBoolean(true);
            //queue.updateQueue();
        }
        setChanged();
        notifyObservers(grid);//passes the grid to registered observers
    }

    //returns the grid for the gameBoard
    public TileModel[][] getGrid() {
        return grid;
    }

    //takes values from neighboring tiles and performs game logic calculation
    public void performCalc(TileModel[] neighbors, TileModel tile) {
        int result = 0;
        for (TileModel t : neighbors) {
            result = result + t.getInt();
        }

        setChanged();
        notifyObservers(grid);
    }

    public void forceUpdate() {
        setChanged();
        notifyObservers(grid);
    }

    //helper class to store the position of the tile in the grid
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
