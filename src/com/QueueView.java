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
        //this.setLayout(new GridLayout(model.getNumElements(),1));

        //create a boxlayout with vertically stacked components
        BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(box);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.setAlignmentY(CENTER_ALIGNMENT);



        this.setPreferredSize(new Dimension(50,50));

        display = new Tiles[5];
        //initialize some dummy tiles for the display
        for(int i = 0;i < display.length;i++) {//count backwards to ensure correct ordering
            Tiles tmp = new Tiles(0, false, new Color(38, 170, 160));
            tmp.setPreferredSize(new Dimension(70,50));

            display[i] = tmp;
            this.add(tmp);
        }

        setVisible(true);
    }

    //resize the queue and all its elements
    public void setSize(Dimension size){
        this.setPreferredSize(size);
    }

    public void addObserver(Observer o){
        //register a model with this view...
    }

    public void update(Observable o, Object arg){
        //grab data from the model to update what is seen on the view
        //this is probably the wrong way to do this...
        model.update(o,arg);
    }

    private class TinyController implements ActionListener{
        public void actionPerformed(ActionEvent e){
            switch(e.getActionCommand()){
                case "FUBAR":
                    System.exit(0);
                    break;
                case "RETRIEVE":
                    model.retrieve();
                    break;
                case "SHUFFLE":
                    model.shuffle();
                    //update();
            }
        }
    }



}
