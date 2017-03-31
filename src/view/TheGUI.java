package view;

import model.TileModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TheGUI extends JFrame implements Observer {
    //Panels____
    GridView gridView;
    QueueView queueView;
    JPanel buttonsPanel;

    //make an array of buttons then in the update method update all of the values to the tile array


    //constructor
    public TheGUI() {
        setTitle("Sum Fun!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,900);
        setLayout(new BorderLayout());

        gridView = new GridView();
        add(gridView, BorderLayout.CENTER);
        queueView = new QueueView();
        //queueView.setSize(300,300);
        queueView.setMinimumSize(new Dimension(100,0));
        queueView.setPreferredSize(queueView.getMinimumSize());
        add(queueView, BorderLayout.EAST);

        setVisible(true);

    }

    public GridView getGridView(){
        return gridView;
    }
    public QueueView getQueueView(){
        return queueView;
    }
    @Override
    public void update(Observable o, Object arg) {

    }

/*    private class TileListener implements ActionListener {
        TileModel t;

        public TileListener(TileModel tile) {
            this.t = tile;
        }

        public void actionPerformed(ActionEvent e) {
            t = (TileModel) e.getSource();
            t.setColor(Color.GREEN);


        }
    }*/
}