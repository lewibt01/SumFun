package view;

import model.QueueModel;
import model.TileModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class QueueView extends JPanel implements Observer{
    private JButton display[];

    public QueueView(){
        super();


        //create a boxlayout with vertically stacked components
        //BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
        //this.setLayout(box);
        this.setLayout(new GridLayout(5,1,0,0));
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setBackground(new Color(123,255, 24));


        display = new JButton[5];
        //initialize some dummy tiles for the display
        for(int i = 0;i < display.length;i++) {

            TileModel tmp = new TileModel(new Color(38, 170, 160));
            //tile model no longer has buttons therefore all view components do not work
            //tmp.setPreferredSize(new Dimension(100,100));

            display[i] = new JButton();
            display[i].setMinimumSize(new Dimension(100,100));
            add(display[i]);
        }
    }


    public void addObserver(Observable o){
        o.addObserver(this);
        //register a model with this view...
    }

    public void update(Observable o, Object arg){
        //grab data from the model to update what is seen on the view
        //this is probably the wrong way to do this...
        if(o.getClass().getSimpleName().equals("QueueModel")){
            int[] model = ((QueueModel) o).getQueue();
            for(int i = 0 ; i < model.length; i++){
                display[i].setText(model[i] + "");
            }
        }
    }

    private class TinyController implements ActionListener{
        public void actionPerformed(ActionEvent e){
            switch(e.getActionCommand()){
                case "FUBAR":
                    System.exit(0);
                    break;
                case "RETRIEVE":
                    //model.retrieve();
                    break;
                case "SHUFFLE":
                    //model.shuffle();
                    //update();
            }
        }
    }



}
