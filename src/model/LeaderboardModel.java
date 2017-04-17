package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;


public class LeaderboardModel extends Observable implements Serializable {
    private static LeaderboardModel singletonLink = new LeaderboardModel();
    private int[] number = new int[10];
    private int currentPos;
    private String[] userName = new String[10];
    private int[] score = new int[10];
    private File leaderFile;
    private Boolean[] isFilled = new Boolean[10];

    private LeaderboardModel(){
        CurrentScoreModel currentScore = new CurrentScoreModel();
        try {
            load();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            currentPos = i;
            score[i] = currentScore.getCurrentScore();
            number[i] = currentPos + 1;
            userName[i] = "";
            isFilled[i] = false;
        }
        forceUpdate();
    }

    public static LeaderboardModel getInstance(){
        return singletonLink;
    }

    public void save(){

        try {
            leaderFile = new File("LeaderBoard.txt");
            FileOutputStream fos = new FileOutputStream(leaderFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            forceUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() throws Exception {
        FileInputStream fis = new FileInputStream(leaderFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        LeaderboardModel lbm = (LeaderboardModel) ois.readObject();
    }

    public Boolean getIsFilled(int pos) {
        return isFilled[pos];
    }

    public void setIsFilled(int pos) {
        isFilled[pos] = true;
        setChanged();
        notifyObservers(singletonLink);
    }

    public int getScore(int pos) {
        return score[pos];
    }

    public int getNumber(int pos) {
        return number[pos];
    }

    public void setNumber(int pos) {
        number[pos]++;
        setChanged();
        notifyObservers(singletonLink);
    }

    public void setScore(int pos, int score) {
        this.score[pos] = score;
        setChanged();
        notifyObservers(singletonLink);
    }

    public String getUserName(int pos) {
        return userName[pos];
    }

    public void setUserName(String name, int pos) {
        this.userName[pos] = name;
        setChanged();
        notifyObservers(singletonLink);
    }

    public int getCurrentPos() {
        return currentPos;
    }
    public void setCurrentPos(int current){
        currentPos = current;
        setChanged();
        notifyObservers(singletonLink);
    }

    private void forceUpdate() {
        setChanged();
        notifyObservers(singletonLink);
    }


}
