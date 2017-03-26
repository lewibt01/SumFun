package com;
import javax.swing.*;

/**
 * Created by Aaron on 3/26/2017.
 */
public class Tiles extends JButton{
    private int value;
    private boolean isEmpty;
    //constructor
    public Tiles(int val, boolean isEmpty_){
        this.value = val;
        this.isEmpty = isEmpty_;
        setText(value + "");

    }
    //used to grab integer value
    public int getInt(){
        return value;
    }
    //will be used to grab the boolean
    public boolean getBool(){
        return isEmpty;
    }
    //will be used to create numbers
    public void setNumber(int number){
        this.value = number;
        setText(number + "");
    }
    //will be used to set the boolean
    public void setBoolean(boolean empty){
        this.isEmpty = empty;
    }
}
