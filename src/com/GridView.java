package com;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

public class GridView extends JPanel implements Observer{
    private GridModel model;
    private Tiles[][] display;

    public GridView() {
        super();
        model = new GridModel();
        model.addObserver(this);

        GridLayout grid = new GridLayout(9,9,0,0);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setPreferredSize(new Dimension(800,800));

        display = new Tiles[9][9];
        //grid initialization logic
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TileListener tileHandler = new TileListener(display[i][j]);
                display[i][j] = new Tiles(0, false, null);
                display[i][j].addActionListener(tileHandler);
                this.add(display[i][j]);
                display[i][j].setText(" ");

                //sets border to disabled initially
                if((i == 0 || j == 0)||(i==8 ||j==8)){
                    for(int k = 0; k < 9;k++){
                        display[i][j].setEnabled(false);
                    }

                }


            }
        }
        //used to populate values to game board
        for (int x = 1; x < 8; x++){
            for(int y=1;y< 8;y++){
                int randomVal = ThreadLocalRandom.current().nextInt(0, 10);
                display[x][y].setNumber(randomVal);
                display[x][y].setBoolean(true);
                display[x][y].setColor(null);
            }
        }


        setVisible(true);
    }

    public void addObserver(Observer o) {
        //register a model with this view...
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO
    }

    private class GMController implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case "FUBAR":
                    System.exit(0);
                    break;
/*
                case "RETRIEVE":
                    model.retrieve();
                    break;
                case "SHUFFLE":
                    model.shuffle();
*/
            }
        }
    }

    private class TileListener implements ActionListener {
        Tiles t;

        public TileListener(Tiles tile) {
            this.t = tile;
        }

        public void actionPerformed(ActionEvent e) {
            t = (Tiles) e.getSource();
            t.setColor(Color.GREEN);


        }
    }
}
