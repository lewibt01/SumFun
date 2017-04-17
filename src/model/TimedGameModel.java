package model;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 * Created by Garrett on 4/12/2017.
 * CS360
 */
public class TimedGameModel extends GameModel {
    private Timer timer = new Timer(1000, new TimerListener());
    private int seconds = 0;

    /**
     * @param minutes Time in minutes
     */
    public TimedGameModel(int minutes) {
        super();
        seconds = 60 * minutes;
        System.out.println("Starting timed game");
        timer.setRepeats(true);
        startTimer();
    }

    /**
     * Start the timer.
     *
     * @return True if timer was started
     */
    protected boolean startTimer() {
        if (timer.isRunning()) {
            return false;
        } else {
            timer.start();
            return true;
        }

    }

    /**
     * Fires once a second when timed game is active
     */
    class TimerListener implements java.awt.event.ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            if (seconds < 0) {

                notifyObservers();
            }
            //System.out.println("Timer at:" + getTimer());
            setChanged();
            notifyObservers();
        }
    }

    public String getTimer() {
        return String.format("%2d:%02d", seconds / 60, seconds % 60);
    }

}
