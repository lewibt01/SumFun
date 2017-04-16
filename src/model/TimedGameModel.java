package model;

import java.awt.EventQueue;

/**
 * Created by Garrett on 4/12/2017.
 * CS360
 */
public class TimedGameModel extends GameModel {
    public TimedGameModel() {
        super();
        System.out.println("Starting timed game");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
