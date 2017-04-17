package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Observable;
import data_containers.LeaderboardEntry;


public class LeaderboardModel extends Observable implements Serializable {
    private static LeaderboardModel singletonLink = new LeaderboardModel();
    private ArrayList<LeaderboardEntry> entries = new ArrayList<>();
    private int currentPos = entries.size()-1;

    private File leaderFile = new File("LeaderBoard.txt");


    private LeaderboardModel(){
        CurrentScoreModel currentScore = new CurrentScoreModel();
        try {
            load();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            entries.add(new LeaderboardEntry());

            /*
            entries[i].setCurrentPos(i);
            entries[i].setScore(currentScore.getCurrentScore());
            entries[i].setNumber(entries[i].getCurrentPos()+1);
            entries[i].setUserName("");
            entries[i].setIsFilled(false);
            entries.setDate(new Date());
            */
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
            oos.writeObject(entries);
            oos.close();
            fos.close();
            forceUpdate();
        }catch(FileNotFoundException fnfex) {
            System.err.println("Leaderboard Save() FNF error");
        }catch (IOException ioex) {
            System.err.println("Leaderboard Save() IO error");
            ioex.printStackTrace();
        }catch (Exception ex){
            System.err.println("Leaderboard Save() generic error");
            //ex.printStackTrace();
        }
    }

    private void load(){
        LeaderboardEntry lbm[];
        try {
            FileInputStream fis = new FileInputStream(leaderFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lbm = (LeaderboardEntry[]) ois.readObject();
            fis.close();
            ois.close();

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

    public void sort(){
        Collections.sort(entries);
    }

    public Boolean getIsFilled(int pos) {
        return entries.get(pos).getIsFilled();
    }

    public void setIsFilled(int pos, boolean input) {
        entries.get(pos).setIsFilled(input);
        setChanged();
        notifyObservers(singletonLink);
    }

    public int getScore(int pos) {
        return entries.get(pos).getScore();
    }

    public int getNumber(int pos) {
        return entries.get(pos).getNumber();
    }

    public String getDate(int pos){
        return entries.get(pos).getDateString();
    }

    public void setDate(int pos){
        entries.get(pos).setDate(Calendar.getInstance().getTime());
        setChanged();
        notifyObservers(singletonLink);
    }

    public void setNumber(int pos) {
        entries.get(pos).setNumber((entries.get(pos).getNumber()+1));
        setChanged();
        notifyObservers(singletonLink);
    }

    public void setScore(int pos, int score) {
        entries.get(pos).setScore(score);
        setChanged();
        notifyObservers(singletonLink);
    }

    public String getUserName(int pos) {
        return entries.get(pos).getUserName();
    }

    public void setUserName(String name, int pos) {
        entries.get(pos).setUserName(name);
        setChanged();
        notifyObservers(singletonLink);
    }

    //test method plz ignore
    //nothing to see here
    public int getCurrentPos(){
        return currentPos;
    }

    public void setCurrentPos(int input){
        currentPos = input;
    }

    private void forceUpdate() {
        setChanged();
        notifyObservers(singletonLink);
        save();
    }


}
