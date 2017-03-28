package com;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class QueueView extends JPanel implements Observer{
    private QueueModel model;
    private Tiles display[];


    public QueueView(){
        super();
        model=new QueueModel();
        //should add itself as an observer to its own model
        model.addObserver(this);




        //generate a gridlayout for the number of elements in the view
        this.setLayout(new GridLayout(model.getNumElements(),1));

        display = new Tiles[5];
        //initialize some dummy tiles for the display
        for(int i = 0;i < display.length;i++) {//count backwards to ensure correct ordering
            Tiles tmp = new Tiles(0, false, new Color(150, 170, 170));
            tmp.setText("");
            display[i] = tmp;
            this.add(tmp);
        }

        setVisible(true);
    }

    public void addObserver(Observer o){
        //register a model with this view...
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
