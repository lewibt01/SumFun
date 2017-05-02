package model;

import datacontainers.LeaderboardEntry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Observable;


public class LeaderboardModel extends Observable implements Serializable {
    public static final int LIST_LENGTH = 10;//
    private static final File leaderFile = new File("LeaderBoard.txt");
    public static final String filename = "/LeaderBoard.txt";

    private static LeaderboardModel singletonLink = new LeaderboardModel();
    private ArrayList<LeaderboardEntry> entries = new ArrayList<>();

    private LeaderboardModel() {
        singletonLink = this;
        try {
            //save();

            load();
            forceUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //returns an instance of this singleton
    public static LeaderboardModel getInstance() {
        return singletonLink;
    }

    public void save() {
        try {
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(leaderFile));

            //write each element to its own line
            for (LeaderboardEntry el : entries) {
                buffWriter.write(el.toString() + "\n");
            }
            buffWriter.flush();
            buffWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        forceUpdate();
    }

    public void load() {
        ArrayList<String> tmp = new ArrayList<>();
        try {
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));
            LeaderboardEntry[] localEntries;
            String currentLine;
            //read & store all the lines of the text file for ease of parsing
            do {
                currentLine = buffReader.readLine();
                //only add the line if there is text in it
                if (currentLine != null) {
                    tmp.add(currentLine);
                }
            } while ((currentLine) != null);

            buffReader.close();

            //convert the serialized LeaderboardEntries back into objects
            localEntries = parseFileText(tmp);

            //add local entries to the static entries
            entries.clear();
            entries.addAll(Arrays.asList(localEntries));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        forceUpdate();
    }

    private LeaderboardEntry[] parseFileText(ArrayList<String> lines) {
        ArrayList<LeaderboardEntry> decodedEntries = new ArrayList<>();
        LeaderboardEntry[] localEntries = new LeaderboardEntry[LIST_LENGTH];
        String tmpString;
        String[][] elements = new String[lines.size()][LeaderboardEntry.NUM_PARAMETERS];//2D Array to store serialized elements

        //trim the serialized class name & brackets away from the string
        for (int i = 0; i < lines.size(); i++) {
            tmpString = lines.get(i).substring(lines.get(i).indexOf("[") + 1, lines.get(i).indexOf("]"));
            elements[i] = tmpString.split(",");
        }

        //split the variables apart for each line and assign the values to their respective data container
        for (int i = 0; i < lines.size(); i++) {
            //5=username, 4=time, 3=score
            //  only grab the useful characters, trim the rest away with substring()
            String localUsername = elements[i][5].substring(9);
            int localScore = Integer.parseInt(elements[i][3].substring(6));
            int localTime = Integer.parseInt(elements[i][4].substring(5));

            decodedEntries.add(new LeaderboardEntry(localUsername, localScore, localTime));
        }

        return decodedEntries.toArray(localEntries);
    }

    private void sort() {
        //only sort if there are elements to be sorted
        if (entries.size() > 0) {
            entries.sort(Collections.reverseOrder());
        }
    }

    public Boolean getIsFilled(int pos) {
        return entries.get(pos).getIsFilled();
    }

    public void addEntry(LeaderboardEntry entry) {
        entries.add(entry);
        forceUpdate();
    }

    public LeaderboardEntry getEntry(int index) {
        forceUpdate();
        return entries.get(index);
    }

    public void setIsFilled(int index, boolean input) {
        entries.get(index).setIsFilled(input);
        forceUpdate();
    }

    public int getScore(int pos) {
        return entries.get(pos).getScore();
    }

    public String getDate(int pos) {
        return entries.get(pos).getDateString();
    }

    public void setDate(int pos) {
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

    public boolean isTimed(int index) {
        return (entries.get(index).getTime() != 0);
    }

    public int getSize() {
        return entries.size();
    }

    private void forceUpdate() {
        sort();
        setChanged();
        notifyObservers(singletonLink);
    }

}
