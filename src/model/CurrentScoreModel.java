package model;

import java.util.Observable;

//populates the values used for the currentScore, as well as retains those values when changed
public class CurrentScoreModel extends Observable {
    private int numberMoves;
    private int currentScore;

    public CurrentScoreModel() {
        numberMoves = 50;
        currentScore = 0;
    }

    public int getNumberMoves() {
        return this.numberMoves;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    //will set score calculated
/*
    public void setCurrentScore(int score) {
        this.currentScore = score;
        setChanged();
        notifyObservers();
    }

    public void setNumberMoves(int moves) {
        this.numberMoves = moves;
        setChanged();
        notifyObservers();
    }
*/

    public void changeScore(int num) {
        currentScore += num;
        setChanged();
        notifyObservers(currentScore);
    }

    public void decrMoves() {
        numberMoves = numberMoves -1;
        System.out.println(numberMoves);
        setChanged();
        notifyObservers();
    }

}
