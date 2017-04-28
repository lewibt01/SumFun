

import model.TimedGameModel;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Garrett on 4/27/2017.
 * CS360
 */
public class TimedGameModelTest {
    private TimedGameModel timedGameModel = new TimedGameModel();

    @org.junit.Before
    public void setUp() throws Exception {
        assertSame(timedGameModel, timedGameModel.getTimedModel());
    }

    @org.junit.Test
    public void timerTest() throws Exception {
        assertEquals(" 5:00", timedGameModel.getTimer());
        timedGameModel.reset(4);
        assertEquals(" 4:00", timedGameModel.getTimer());
        timedGameModel.reset(1);
        assertEquals(" 1:00", timedGameModel.getTimer());


    }

    @Test
    public void getMessage() throws Exception {
        assertEquals("Your time has run out!!", timedGameModel.getMessage());
    }
}