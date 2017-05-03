package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GridModelTest {
    private GridModel gridModel;
    private ArrayList<TileModel> tileModels;

    @Before
    public void setUp() throws Exception {
        // Create models used in tests
        gridModel = new GridModel();
        tileModels = new ArrayList<>(8);
    }

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
    /**
     * Tests one performCalc with no match
     *
     * @throws Exception If test fails.
     */
    @Test
    public void testOneNeighborCalc() throws Exception {

        // Fill neighbors list with empty TileModels
        for (int j = 0; j < 8; j++) {
            TileModel model = new TileModel();
            model.setNumber(0);
            tileModels.add(model);

        }
        System.out.println(tileModels.toString());
        // Make ONE neighbor filled
        tileModels.get(0).setNumber(1);
        tileModels.get(0).setBoolean(true);
        // Make our tile
        TileModel myTile = new TileModel();
        myTile.setNumber(2);
        myTile.setBoolean(true);

        assertTrue(myTile.getBool());

        // TEST METHOD
        gridModel.performCalc(tileModels, myTile);
        // TEST

        // Still should be true
        assertTrue(myTile.getBool());
        assertEquals(50, gridModel.getIntMoves());
        assertEquals("0", gridModel.getCurrentScore());


    }


}