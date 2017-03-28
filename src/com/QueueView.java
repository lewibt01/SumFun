package com;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class QueueView extends JFrame implements Observer{
    private QueueModel model;
    private Tiles display[];


    public QueueView(boolean visible){
        super();
        model=new QueueModel();
        //should add itself as an observer to its own model
        model.addObserver(this);


        Container pane = getContentPane();
        //generate a gridlayout for the number of elements in the view
        pane.setLayout(new GridLayout(model.getNumElements(),1));

        display = new Tiles[5];
        //initialize some dummy tiles for the display
        for(int i=0;i<display.length;i++){
            Tiles tmp = new Tiles(0,false,new Color(150,170,170));
            tmp.setText("");
            display[i]=tmp;
        }
    }

    public QueueView(int x, int y, int w, int h){
        super();
        model=new QueueModel();

    }

    public void addObserver(Observer o){

    }

    public void update(Observable o, Object arg){
        //grab data from the model to update what is seen on the view
    }

    private class TinyController implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //do things here...
        }
    }



}
