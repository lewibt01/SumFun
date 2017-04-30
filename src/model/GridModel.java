package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

//Grid Model used to make the grid of the game board
public class GridModel extends Observable {
    private TileModel[][] grid;
    private int val = 0;
    private int numberMoves;
    private int currentScore;
    private int scoreTot;
    private final int rowMax = 9;
    private final int colMax = 9;
    private boolean removeSameBool = false;
    private int hintCounter;

    // constructor
    public GridModel() {
        resetGrid();
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

    // stores all neighbors (and the tile in question : 9 values max) in an
    // array
    // the value from the queue will need to be set in the new tile before this
    // function is called
    public ArrayList<TileModel> getNeighbors(Position pos) {
        // TileModel[] neighbors = new TileModel[9];
        ArrayList<TileModel> neighbors = new ArrayList<>();
        ////////////
        // CORNERS
        ////////////
        // TOP LEFT
        if (pos.getRow() == 0 && pos.getCol() == 0) {
            // neighbors[0] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
        } else if ((pos.getRow() == 0) && (pos.getCol() == 8)) {
            // TOP RIGHT
            neighbors.add(grid[0][7]);
            // neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[1][7]);
            neighbors.add(grid[1][8]);
        } else if (pos.getRow() == 8 && pos.getCol() == 0) {
            // BOTTOM LEFT
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);
            // neighbors[2] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
        } else if (pos.getRow() == 8 && pos.getCol() == 8) {
            // BOTTOM RIGHT
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            // neighbors[3] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
        } else if (pos.getRow() == 0) {
            ////////////
            // BORDERS
            ///////////
            // NORTH BORDER
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            // neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
        } else if (pos.getRow() == 8) {
            // SOUTH BORDER
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            // neighbors[4] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
        } else if (pos.getCol() == 0) {
            // WEST BORDER
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            // neighbors[1] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
        } else if (pos.getCol() == 8) {
            // EAST BORDER
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            // neighbors[4] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
        } else {
            ///////////
            // INTERIOR
            ///////////
            // for (int i = 0; i < ; i++) {
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() - 1][pos.getCol() + 1]);

            neighbors.add(grid[pos.getRow()][pos.getCol() - 1]);
            // neighbors[i] = (gm.getGrid()[pos.getRow()][pos.getCol()]);
            neighbors.add(grid[pos.getRow()][pos.getCol() + 1]);

            neighbors.add(grid[pos.getRow() + 1][pos.getCol() - 1]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol()]);
            neighbors.add(grid[pos.getRow() + 1][pos.getCol() + 1]);
            // }
        }
        // for testing purposes
/*
        for (TileModel tile : neighbors) {
            System.out.println("value: " + tile.getInt());
            Position pos2 = this.getTilePosition(tile);
            System.out.println("    row: " + pos2.getRow() + " col: " + pos2.getCol());
        }
*/
        return neighbors;
    }

    // gets the [i][j] for the clicked tile so it can be found in the grid
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

    // modify the value of the tile at the given position
    public void setTileValue(int row, int col, int value) {
        TileModel reference = grid[row][col];
        reference.setBoolean(true);
        reference.setNumber(value);

        forceUpdate();
    }
    // need to change number from queue and take it and move it to the grid's
    // unoccupied tile
    // need to change number of queue to move up one when this happens
    // need to change value of occupied to true if placed
    /*
     * public void changeNum(TileModel grid, QueueModel queue) { int queueVal =
	 * queue.peek(); boolean occupied = grid.getBool(); if (grid.getInt() == 0
	 * && !occupied) { grid.setNumber(queueVal); grid.setBoolean(true);
	 * //queue.updateQueue(); } setChanged(); notifyObservers(grid);//passes the
	 * grid to registered observers }
	 */

    // returns the grid for the gameBoard
    public TileModel[][] getGrid() {
        return grid;
    }

    // takes values from neighboring tiles and performs game logic calculation
    // then updates the number of moves and
    public void performCalc(ArrayList<TileModel> neighbors, TileModel tile) {
        removeHintColor();
        System.out.println("begin calculation");
        int result = 0;
        int tempScore = 0;
        for (TileModel t : neighbors) {
            result = result + t.getInt();
        }
        System.out.println("result: " + result);
        if (result % 10 == tile.getInt()) {
            tile.setBoolean(false);
            tile.setNumber(0);
            System.out.println("computation successful!");
            scoreTot = getIntScore();
            for (TileModel t : neighbors) {
                // if the tile is currently occupied, increment score
                if (t.getBool()) {
                    tempScore++;
                }
                t.setBoolean(false);
                t.setNumber(0);
            }
            // use score counter to determine points earned from move
            if (tempScore >= 3) {
                scoreTot += tempScore * 10;
                System.out.println("temp score: " + tempScore);
                System.out.println("total score: " + scoreTot);
                setCurrentScore(scoreTot + "");
            }
        }

        forceUpdate();

    }

    //helper method which removes the color from the previous hint
    public void removeHintColor() {
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                if (grid[i][j].getColor() == Color.cyan) {
                    grid[i][j].setColor(Color.green);
                }
            }
        }
    }

    //removes all tiles on the board which share a value with the parameter (works once in game)
    public void removeSame(int num) {
        if (!removeSameBool) {
            removeSameBool = true;
            for (int i = 0; i < rowMax; i++) {
                for (int j = 0; j < colMax; j++) {
                    if (grid[i][j].getInt() == num) {
                        grid[i][j].setBoolean(false);
                        grid[i][j].setNumber(0);
                    }
                }
            }
        }
        forceUpdate();
    }

    public Position hint(int theNumber) {
        Position pos;
        if (hintCounter < 3) {
            Map<Position, Integer> collectiveScores = new HashMap<>();
            for (int i = 0; i < rowMax; i++) {
                for (int j = 0; j < colMax; j++) {
                    //if (grid[i][j].getInt() == theNumber) {
                        ArrayList<TileModel> neighbors = getNeighbors(getTilePosition(grid[i][j]));
                        int result = 0;
                        int tempScore = 0;
                        //get sum of neighbors
                        for (TileModel t : neighbors) {
                            result = result + t.getInt();
                        }
                        //test for score logic
                        if (result % 10 == theNumber) {
                            for (TileModel t : neighbors) {
                                // if the tile is currently occupied, increment score
                                if (t.getBool()) {
                                    tempScore++;
                                }
                            }
                            //use score counter to determine points earned from each iteration
                            //then store those values in a new tile and then in a hashmap
                            if ((tempScore >= 3) && (!grid[i][j].getBool())) {
                                tempScore = tempScore * 10;
                                collectiveScores.put(new Position(i, j), tempScore);
                            }
                        }
                    //}
                }
            }
            //iterate over hashmap and find the best possible score
            //if two scores are the same, it will recommend the first (greedy algorithm)
            //pos will default to tile (0,0) ; however, this should never happen.
            //      -it is in here to satisfy the compiler
            pos = new Position(0,0);
            int hintScore = 0;
            for (Map.Entry<Position, Integer> entry : collectiveScores.entrySet()) {
                if ((entry.getValue() > hintScore)) {
                    hintScore = entry.getValue();
                    pos = new Position(entry.getKey().getRow(), entry.getKey().getCol());
                    System.out.println("HINT: \n    row: "+pos.getRow() + " col: "+pos.getCol());
                }
            }
            //this checks if there are no moves that return a score and alerts the player
            //this does NOT increment the hintCounter at this time
            if (hintScore == 0) {
                JOptionPane.showMessageDialog(null,
                        "There are no moves that will add to your points. \n"
                + "You have " + (3-hintCounter) + " hints remaining.");
                return null;
            }
            hintCounter++;
            return pos;
        }
        JOptionPane.showMessageDialog(null, "You have already used all three hints.");
        return null;
    }

    //this method will count through [i] and [j]
    public int countFilledTiles() {
        int count = 0;
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                if (grid[i][j].getBool()) {
                    count++;
                }
            }
        }
        System.out.println("tiles counted: " + count);
        return count;
    }

    //used for score tracking and move tracking
    public String getNumberMoves() {
        return this.numberMoves + "";
    }

    public String getCurrentScore() {
        return this.currentScore + "";
    }

    //will set score calculated
    public void setCurrentScore(String score) {
        currentScore = Integer.parseInt(score);
        forceUpdate();
    }

    public int getIntMoves() {
        return numberMoves;
    }

    private int getIntScore() {

        return currentScore;
    }

    public void setNumberMoves() {
        numberMoves--;
        if (numberMoves < 0) {
            numberMoves = 0;
        }
        forceUpdate();
    }


    public void forceUpdate() {
        setChanged();
        notifyObservers(grid);
    }

    // helper class to store the position of the tile in the grid
    // helps with getNeighbors()
    // also helps with passing proper tile to hint function
    public class Position {
        int row;
        int col;

        Position(int r, int c) {
            this.row = r;
            this.col = c;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
    //initializes the grid
    public void resetGrid() {
        scoreTot = 0;
        numberMoves = 50;
        currentScore = 0;
        hintCounter = 0;
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

}
