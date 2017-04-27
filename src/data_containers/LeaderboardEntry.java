package data_containers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class LeaderboardEntry implements Comparable<LeaderboardEntry>,Serializable{

    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private Date thisDate = new Date();
    private String currentDate = "";
    private int score;
    private String userName;
    private boolean isFilled;

    public LeaderboardEntry(){

    }

    public LeaderboardEntry(String username){
        this.userName = username;
        this.score = 0;
        this.isFilled = true;
        this.currentDate = new Date().toString();
    }

    public LeaderboardEntry(String username, int score, boolean isFull) {
        this.userName = username;
        this.score = score;
        this.isFilled = isFull;
        this.currentDate = new Date().toString();
    }

    public Date getDate() {
        return thisDate;
    }

    public void setDate(Date thisDate) {
        this.thisDate = thisDate;
    }

    public String getDateString(){
        return dateFormat.format(thisDate);
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(Boolean isFilled) {
        this.isFilled = isFilled;
    }

    public int compareTo(LeaderboardEntry entry){
        //if less than...
        if(this.score < entry.getScore()){
            return -1;
        }
        //if greater than...
        if(this.score > entry.getScore()){
            return 1;
        }

        //otherwise it must be equal....
        return 0;
    }

    @Override
    public String toString(){
        return "LeaderboardEntry [dateFormat="+dateFormat+", thisDate="+thisDate+", currentDate="+currentDate
                +", score="+score+", userName="+userName+", isFilled="+isFilled+"]";
    }


}
