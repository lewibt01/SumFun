package model;
//

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

//Grid Model used to make the grid of the game board
public class GridModel extends Observable {
    private TileModel[][] grid;
    private CurrentScoreModel currScoreMod;
    private int val = 0;
    private final int rowMax = 9;
    private final int colMax = 9;

    //constructor
    public GridModel() {
        currScoreMod = new CurrentScoreModel();
        grid = new TileModel[rowMax][colMax];
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                val = ThreadLocalRandom.current().nextInt(0, 10);
                grid[i][j] = new TileModel();
                grid[i][j].setBoolean(false);
                if (!((i == 0 || j == 0) || (i == rowMax - 1 || j == colMax - 1))) {
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

    public CurrentScoreModel getCurrScoreMod() {
        return currScoreMod;
    }

    //stores all neighbors (and the tile in question : 9 values max) in an array
    // the value from the queue will need to be set in the new tile before this function is called
    public ArrayList<TileModel> getNeighbors(Position pos) {
        //TileModel[] neighbors = new TileModel[9];
        ArrayList<TileModel> neighbors = new ArrayList<>();
        ////////////
        //CORNERS
        ////////////
        //TOP LEFT
        if (pos.getRow() == 0 && pos.getCol() == 0) {
            //neighbors[0] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
        }
        //TOP RIGHT
        else if ((pos.getRow() == 0) && (pos.getCol() == 8)) {
            neighbors.add(grid[0][7]);
            //neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[1][7]);
            neighbors.add(grid[1][8]);
        }
        //BOTTOM LEFT
        else if (pos.getRow() == 8 && pos.getCol() == 0) {
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);
            //neighbors[2] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
        }
        //BOTTOM RIGHT
        else if (pos.getRow() == 8 && pos.getCol() == 8) {
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            //neighbors[3] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
        }
        ////////////
        //BORDERS
        ///////////
        //NORTH BORDER
        else if (pos.getRow() == 0) {
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            //neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
        }
        //SOUTH BORDER
        else if (pos.getRow() == 8) {
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            //neighbors[4] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
        }
        //WEST BORDER
        else if (pos.getCol() == 0) {
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            //neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
        }
        //EAST BORDER
        else if (pos.getCol() == 8) {
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            //neighbors[4] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
        }
        ///////////
        //INTERIOR
        ///////////
        else {
            //for (int i = 0; i < ; i++) {
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);

            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            //neighbors[i] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);

            neighbors.add(grid[pos.getRow() + 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
            //}
        }
        //for testing purposes
        for (TileModel tile : neighbors) {
            System.out.println("value: " + tile.getInt());
            Position pos2 = this.getTilePosition(tile);
            System.out.println("    row: " + pos2.getRow() + " col: " + pos2.getCol());
        }
        return neighbors;
    }

    //gets the [i][j] for the clicked tile so it can be found in the grid
    public Position getTilePosition(TileModel tm) {
        Position pos = new Position(0, 0);
        for (int i = 0; i < getMaxRow(); i++) {
            for (int j = 0; j < getMaxCol(); j++) {
                if (tm == grid[i][j]) {
                    pos = new Position(i, j);
                }
            }
        }
        return pos;
    }

    //modify the value of the tile at the given position
    public void setTileValue(int row, int col, int value) {
        TileModel reference = grid[row][col];
        reference.setBoolean(true);
        reference.setNumber(value);

        setChanged();
        notifyObservers(grid);
    }
    //need to change number from queue and take it and move it to the grid's unoccupied tile
    //need to change number of queue to move up one when this happens
    //need to change value of occupied to true if placed
/*
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
*/

    //returns the grid for the gameBoard
    public TileModel[][] getGrid() {
        return grid;
    }

    //takes values from neighboring tiles and performs game logic calculation
    //then updates the number of moves and
    public void performCalc(ArrayList<TileModel> neighbors, TileModel tile) {
        System.out.println("begin calculation");
        int result = 0;
        for (TileModel t : neighbors) {
            result = result + t.getInt();
        }
        System.out.println("result: " + result);
        if (result % 10 == tile.getInt()) {
            tile.setBoolean(false);
            tile.setNumber(0);
            System.out.println("computation successful!");
            int score = 0;
            for (TileModel t : neighbors) {
                //if the tile is currently occupied, increment score
                if (t.getBool()) {
                    score++;
                }
                t.setBoolean(false);
                t.setNumber(0);
            }
            //use score counter to determine points earned from move
            if (score >= 3) {
                score = score * 10;
                System.out.println("temp score: " + score);
                currScoreMod.setCurrentScore(currScoreMod.getCurrentScore() + score);
                System.out.println("total score: "+currScoreMod.getCurrentScore());
            }
        }
        //currScoreMod.setNumberMoves();
        System.out.println("moves: " + currScoreMod.getNumberMoves());
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
        int row;
        int col;

        Position(int r, int c) {
            this.row = r;
            this.col = c;
        }

        private int getRow() {
            return row;
        }

        private int getCol() {
            return col;
        }
    }
}
