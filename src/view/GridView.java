package view;

import model.GridModel;
import model.TileModel;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

public class GridView extends JPanel implements Observer{

    private JButton[][] boardButtons = new JButton[9][9];

    public GridView() {
        super();

        GridLayout grid = new GridLayout(9,9,0,0);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(123,255,24));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardButtons[i][j] = new JButton();
                add(boardButtons[i][j]);
            }
        }
    }

    public void addObserver(Observable o) {
        o.addObserver(this);
        //register a model with this view...
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO

        if(o.getClass().getSimpleName().equals("GridModel")){
            TileModel[][] model = ((GridModel) o).getGrid();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boardButtons[i][j].setText(model[i][j].getInt() + "");
                }
            }
        }
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
        TileModel t;

        public TileListener(TileModel tile) {
            this.t = tile;
        }

        public void actionPerformed(ActionEvent e) {
            t = (TileModel) e.getSource();
            t.setColor(Color.GREEN);


        }
    }
}
