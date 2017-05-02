package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import static org.junit.Assert.*;
import org.junit.Test;

public class GridModelTest {
    @Test
    public void getNeighbors() throws Exception {
        System.out.println("getNeighbors test");
        GridModel gm = new GridModel();
        ArrayList<TileModel> n = new ArrayList<>();
        n.add(gm.getGrid()[4][4]);
        n.add(gm.getGrid()[4][5]);
        n.add(gm.getGrid()[4][6]);
        n.add(gm.getGrid()[5][4]);
        n.add(gm.getGrid()[5][6]);
        n.add(gm.getGrid()[6][4]);
        n.add(gm.getGrid()[6][5]);
        n.add(gm.getGrid()[6][6]);
        assertEquals(n, gm.getNeighbors(gm.getTilePosition(gm.getGrid()[5][5])));
        System.out.println("getNeighbors test successful\n");
    }

    @Test
    public void performCalc() throws Exception {
        System.out.println("performCalc test");
        GridModel gm = new GridModel();
        ArrayList<TileModel> n = new ArrayList<>();
        TileModel t1 = new TileModel();
        t1.setNumber(25);
        t1.setBoolean(true);
        n.add(t1);
        TileModel t2 = new TileModel();
        t2.setNumber(10);
        t2.setBoolean(true);
        n.add(t2);
        TileModel t3 = new TileModel();
        t3.setNumber(10);
        t3.setBoolean(true);
        n.add(t3);
        TileModel t4 = new TileModel();
        t4.setNumber(20);
        t4.setBoolean(true);
        n.add(t4);
        TileModel t = new TileModel();
        t.setNumber(5);
        t.setBoolean(true);

        gm.performCalc(n, t);
        assertEquals(40,  Integer.parseInt(gm.getCurrentScore()));
        System.out.println("performCalc test successful\n");
    }

/*
    @Test
    public void removeHintColor() throws Exception {
        System.out.println("removeHintColor test");
        GridModel gm = new GridModel();
        gm.getGrid()[5][5].setColor(Color.CYAN);
        gm.removeHintColor();
*/
/*
        for (int i = 0; i < gm.getMaxRow(); i++) {
            for (int j = 0; j < gm.getMaxCol(); j++) {
                if (gm.getGrid()[i][j].getColor() == Color.CYAN) {
                    gm.getGrid()[i][j].setColor(Color.GREEN);
                }
            }
        }
*//*

        assertEquals(Color.WHITE, gm.getGrid()[5][5].getColor());
        System.out.println("removeHintColor test successful\n");
    }

    @Test
    public void removeSame() throws Exception {
        System.out.println("removeSame test");
        GridModel gm = new GridModel();
        //gm.getGrid()[5][5].setColor(Color.CYAN);
        gm.removeSame(5);
        int count = 0;
        for (int i = 0; i < gm.getMaxRow(); i++) {
            for (int j = 0; j < gm.getMaxCol(); j++) {
                if (gm.getGrid()[i][j].getInt() == 5) {
                    count++;
                }
            }
        }
        if (count == 0) {
            assertEquals(Color.WHITE, gm.getGrid()[5][5].getColor());
            System.out.println("removeSame test successful\n");
        } else {
            System.out.println("removeSame failure");
        }
    }
*/
}