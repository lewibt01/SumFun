package model;

import java.util.Observable;

public class CurrentScoreModel extends Observable{
    private int numberMoves = 50;
    private int currentScore = 0;
    private String string = "";

    public int getNumberMoves(){
        return this.numberMoves;
    }
    public int getCurrentScore(){
        return this.currentScore;
    }
    public String getString(){
        return this.string;
    }
    //will set score calculated
    public void setCurrentScore(int score){
        this.currentScore = score;
        setChanged();
        notifyObservers();
    }
    //will send message to user
    public void setString(String s){
        this.string = s;
        setChanged();
        notifyObservers();
    }
}
