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
    private boolean timesUp;

    public TimedGameModel() {
        super();
        //used as a flag to determine end of game
        setTimesUp(false);
        timer.setRepeats(true);
    }

    /**
     * Start the timer.
     *
     * @return True if timer was started
     */
    private boolean startTimer() {
        if (timer.isRunning()) {
            return false;
        } else {
            timer.start();
            return true;
        }

    }
    public boolean getTimesUp(){
        return timesUp;
    }

    public int getSeconds(){
        return seconds;
    }

    private void setTimesUp(boolean timeUp){
     this.timesUp = timeUp;
     setChanged();
     notifyObservers();
    }

    /**
     * Fires once a second when timed game is active
     */
    class TimerListener implements java.awt.event.ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e Something.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            if (seconds < 0) {
                seconds = 0;
                setTimesUp(true);
                getMessage();
            }
            //System.out.println("Timer at:" + getTimer());
            setChanged();
            notifyObservers();
        }
    }

    public void reset(int minutes) {
        seconds = 60 * minutes;
        System.out.println("Starting timer...");
        timer.setRepeats(true);
        if (startTimer()) {
            System.out.println("Actually starting timer!");
        }
        setChanged();
        notifyObservers();
    }

    /**
     * @return The timedGameModel itself.
     */
    public TimedGameModel getTimedModel() {
        return this;
    }

    /**
     * @return The time in 00:00 format. (No leading zero)
     */
    public String getTimer() {
        return String.format("%2d:%02d", seconds / 60, seconds % 60);
    }

    public String getMessage() {
        //TODO
        return "Your time has run out!!";
    }
}
