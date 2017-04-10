package model;

import java.awt.Color;
import java.util.Observable;

//Model class used to help the grid model and the queue model to store its respective values
public class TileModel extends Observable {
    //fields
    private int value = 0;
    private boolean occupied = false;
    private Color color = Color.WHITE;

    //constructors
    TileModel() {
    }

    //used to grab integer value
    public int getInt() {
        return value;
    }

    //will be used to grab the boolean
    public boolean getBool() {
        return occupied;
    }

    //used to grab current color (used when user clicks and tile is removed)
    public Color getColor() {
        return this.color;
    }

    //will be used to create numbers
    void setNumber(int number) {
        this.value = number;
        setChanged();
        notifyObservers();
    }

    //will change background color of tile
    void setColor(Color colorSet) {
        this.color = colorSet;
        setChanged();
        notifyObservers();
    }

    //will be used to set the boolean
    void setBoolean(boolean occupied) {

        this.occupied = occupied;
    }
}
