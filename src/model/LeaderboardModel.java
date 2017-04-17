package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;


public class LeaderboardModel extends Observable implements Serializable {
    private static LeaderboardModel singletonLink = new LeaderboardModel();
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private Date[] thisDate = new Date[10];
    private String currentDate = "";
    private int[] number = new int[10];
    private int currentPos;
    private String[] userName = new String[10];
    private int[] score = new int[10];
    private File leaderFile = new File("LeaderBoard.txt");
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
            thisDate[i] = new Date();

        }
        forceUpdate();
    }

    //returns an instance of this singleton
    public static LeaderboardModel getInstance(){
        return singletonLink;
    }

    public void save(){

        try {
            FileOutputStream fos = new FileOutputStream(leaderFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            forceUpdate();
        } catch (Exception e) {
            System.err.println("Leaderboard Save() error");
            e.printStackTrace();
        }
    }

    private void load(){
        LeaderboardModel lbm;
        try {
            FileInputStream fis = new FileInputStream(leaderFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lbm = (LeaderboardModel) ois.readObject();

        }catch(FileNotFoundException fnfex) {
            System.err.println("Leaderboard Load() FNF error");
        }catch (IOException ioex) {
            System.err.println("Leaderboard Load() IO error");
            ioex.printStackTrace();
        }catch (Exception ex){
            System.err.println("Leaderboard Load() generic error");
            //ex.printStackTrace();
        }
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
    public String getDate(int pos){
        return dateFormat.format(thisDate[pos]);
    }
    public void setDate(int pos){
        thisDate[pos] = Calendar.getInstance().getTime();

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
