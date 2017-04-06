package model;

import java.util.Observable;
//populates the values used for the currentScore, as well as retains those values when changed
public class CurrentScoreModel extends Observable{
    private int numberMoves = 50;
    private int currentScore = 0;

    public int getNumberMoves(){
        return this.numberMoves;
    }
    public int getCurrentScore(){
        return this.currentScore;
    }
    //will set score calculated
    public void setCurrentScore(int score){
        this.currentScore = score;
        setChanged();
        notifyObservers();
    }
    public void setNumberMoves(int moves){
        this.numberMoves = moves;
        setChanged();
        notifyObservers();
    }
    public int scoreCh(int num){
        num = currentScore;
        return num;
    }
    public int move(int num){
        num = numberMoves;
       numberMoves = num - 1;
       return numberMoves;
    }

}
