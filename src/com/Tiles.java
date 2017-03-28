package com;
import javax.swing.*;
import java.awt.*;


public class Tiles extends JButton{
    private int value;
    private boolean occupied;
    private Color color_;

    //constructors
    public Tiles(int val, boolean occupied_, Color c){
        this.value = val;
        this.occupied = occupied_;
        this.color_ = c;
        setText(value + "");
    }
    //used to grab integer value
    public int getInt(){
        return value;
    }
    //will be used to grab the boolean
    public boolean getBool(){
        return occupied;
    }
    public Color getColor(){
        return this.color_;
    }
    //will be used to create numbers
    public void setNumber(int number){
        this.value = number;
        setText(number + "");
    }
    public void setColor(Color color){
        this.color_ = color;
        setBackground(color);
    }
    //will be used to set the boolean
    public void setBoolean(boolean occupied){
        this.occupied = occupied;
    }
}
