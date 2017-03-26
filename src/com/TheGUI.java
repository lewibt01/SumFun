package com;
import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Aaron on 3/26/2017.
 */
public class TheGUI {
    //Tiles_____
    Tiles[][] tiles = new Tiles[9][9];
    //Listeners_____
    TileListener tileHandler;
    //Panels____
    JPanel tilePanel;
    JPanel queuePanel;
    JPanel buttonsPanel;

    private class TileListener implements ActionListener {
        Tiles t;

        public TileListener(Tiles tile) {
            this.t = tile;
        }

        public void actionPerformed(ActionEvent e) {
            t = (Tiles) e.getSource();
        }
    }
}
