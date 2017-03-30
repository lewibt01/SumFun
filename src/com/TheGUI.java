package com;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class TheGUI extends JFrame {
    //Tiles_____
    Tiles[][] tiles = new Tiles[9][9];
    //Grid______
    GridModel grid;
    //Queue______
    //QueueModel[] queue = new QueueModel[5];
    QueueView Qview = new QueueView();
    GridView Gview = new GridView();
    //Listeners_____
    TileListener tileHandler;
    //Panels____
    JPanel tilePanel;
    JPanel queuePanel;
    JPanel buttonsPanel;
    //JPanel buttonsPanel;



    //constructor
    public TheGUI() {
        setTitle("Sum Fun!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //build tile panel
        tilePanel = new JPanel();
        tilePanel.setPreferredSize(new Dimension(800, 800));
        tilePanel.add(Gview);


/*
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tiles(1,true, null);
                tileHandler = new TileListener(tiles[i][j]);
                //tiles[i][j].setSize(new Dimension(5,5));
                tiles[i][j].addActionListener(tileHandler);
                tilePanel.add(tiles[i][j]);
            }
        }
*/

        queuePanel = new JPanel();
        queuePanel.setPreferredSize(new Dimension(150,300));
        queuePanel.setLayout(new GridLayout(5,1));
        queuePanel.setBackground(new Color(255, 137, 39));
        queuePanel.add(Qview);

        add(tilePanel, BorderLayout.WEST);
        add(queuePanel, BorderLayout.EAST);
        //add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);

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