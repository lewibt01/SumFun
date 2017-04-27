package model;

import datacontainers.LeaderboardEntry;

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


public class LeaderboardModel extends Observable implements Serializable {
    private static LeaderboardModel singletonLink = new LeaderboardModel();
    private ArrayList<LeaderboardEntry> entries = new ArrayList<>();
    private File leaderFile;

    private LeaderboardModel(){
        leaderFile = new File("LeaderBoard.txt");
        try {
            save();
            load();
        }catch(Exception ex){
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void load(){
        ArrayList<LeaderboardEntry> lbm;
        try {
            FileInputStream fis = new FileInputStream(leaderFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //cast the object back into a usable format
            lbm = (ArrayList<LeaderboardEntry>) ois.readObject();
            entries = lbm;

            //clean up & update
            fis.close();
            ois.close();
            forceUpdate();

        }catch(FileNotFoundException fnfex) {
            System.err.println("Leaderboard Load() FNF error");
        }catch (IOException ioex) {
            System.err.println("Leaderboard Load() IO error");
            ioex.printStackTrace();
        }catch (Exception ex){
            System.err.println("Leaderboard Load() generic error");
            ex.printStackTrace();
        }
    }

    private void sort(){
        Collections.sort(entries);
    }

    public Boolean getIsFilled(int pos) {
        return entries.get(pos).getIsFilled();
    }

    public void addEntry(LeaderboardEntry entry){
        entries.add(entry);
        forceUpdate();
    }

    public LeaderboardEntry getEntry(int pos){
        forceUpdate();
        return entries.get(pos);
    }

    public void setIsFilled(int index, boolean input) {
        entries.get(index).setIsFilled(input);
        forceUpdate();
    }

    public int getScore(int pos) {
        return entries.get(pos).getScore();
    }

    public String getDate(int pos){
        return entries.get(pos).getDateString();
    }

    public void setDate(int pos){
        entries.get(pos).setDate(Calendar.getInstance().getTime());
        forceUpdate();
    }

    public void setScore(int pos, int score) {
        entries.get(pos).setScore(score);
        forceUpdate();
    }

    public String getUserName(int pos) {
        return entries.get(pos).getUserName();
    }

    public void setUserName(String name, int pos) {
        entries.get(pos).setUserName(name);
        forceUpdate();
    }

    public int getSize(){
        return entries.size();
    }

    private void forceUpdate() {
        sort();
        setChanged();
        notifyObservers(singletonLink);
    }

}
