package model;

import java.util.Observable;

//populates the values used for the currentScore, as well as retains those values when changed
public class CurrentScoreModel extends Observable {
    private int numberMoves;
    private int currentScore;

    /**
     *
     */
    public CurrentScoreModel() {
        numberMoves = 50;
        currentScore = 0;
        forceUpdate();
    }

    public String getNumberMoves() {
        return this.numberMoves + "";
    }

    public String getCurrentScore() {
        return this.currentScore + "";
    }

    //will set score calculated

    public void setCurrentScore(int score) {
        this.currentScore = score;
        setChanged();
        notifyObservers(this);
    }

    public void setNumberMoves() {
        numberMoves--;
        setChanged();
        notifyObservers(this);
    }

    public void forceUpdate() {
        setChanged();
        notifyObservers(this);
    }
}