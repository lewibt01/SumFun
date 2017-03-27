package com;
import javax.swing.*;
import java.awt.*;


public class Tiles extends JButton{
    private int value;
    private boolean flag;
    private Color color_;
    //constructor
    public Tiles(int val, boolean isEmpty_, Color c){
        this.value = val;
        this.flag = isEmpty_;
        this.color_ = c;
        setText(value + "");

    }
    //used to grab integer value
    public int getInt(){
        return value;
    }
    //will be used to grab the boolean
    public boolean getBool(){
        return flag;
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
    public void setBoolean(boolean empty){
        this.flag = empty;
    }
}
