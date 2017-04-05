package view;

import model.GridModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TheGUI extends JFrame implements Observer {
    //Panels____
    GridView gridView;
    QueueView queueView;
    CurrentScoreView currentScoreView;

    GridModel gridMod;

    //make an array of buttons then in the update method update all of the values to the tile array

    //constructor
    public TheGUI() {
        // used to combine currentScore view and queue view via the same panel
        JPanel sidePanel = new JPanel();
        setTitle("Sum Fun!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setLayout(new BorderLayout());

        gridView = new GridView();
        add(gridView, BorderLayout.CENTER);
        queueView = new QueueView();
        //queueView.setSize(300,300);
       // queueView.setMinimumSize(new Dimension(100,0));
        //queueView.setPreferredSize(queueView.getMinimumSize());
        sidePanel.setLayout(new GridLayout(2,1,0,0));
        sidePanel.add(queueView);
        currentScoreView = new CurrentScoreView();
        sidePanel.add(currentScoreView);
        add(sidePanel, BorderLayout.EAST);
        setVisible(true);

    }

    public GridView getGridView(){
        return gridView;
    }
    public QueueView getQueueView(){
        return queueView;
    }
    public CurrentScoreView getCurrentScoreView(){
        return currentScoreView;
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