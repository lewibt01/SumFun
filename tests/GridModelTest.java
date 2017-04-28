import java.util.ArrayList;

import model.GridModel;
import model.TileModel;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Garrett on 4/27/2017.
 * CS360
 */
public class GridModelTest {
    private GridModel gridModel;
    private ArrayList<TileModel> tileModels;

    @Before
    public void setUp() throws Exception {
        // Create models used in tests
        gridModel = new GridModel();
        tileModels = new ArrayList<>(8);
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

    @Test
    public void testgetNeighborsOne() throws Exception {
    }

}