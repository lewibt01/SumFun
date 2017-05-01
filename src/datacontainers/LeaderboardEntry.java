package datacontainers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaderboardEntry implements Comparable<LeaderboardEntry>,Serializable{

    public static final int NUM_PARAMETERS = 8;//the number of variables stored in LeaderboardEntry
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private Date thisDate = new Date();
    private String currentDate = "";
    private int score;
    private String userName;
    private boolean isFilled;
    private int time;
    private boolean isTimed;

    public LeaderboardEntry(){
        this.isFilled = (userName == null || userName.equals(""));
        this.isTimed = !(time==0);
        this.currentDate = new Date().toString();
    }

    public LeaderboardEntry(String username){
        this.userName = username;
        this.score = 0;
        this.time=0;
        this.isFilled = true;
        this.isTimed = false; //if time is not zero, it must be timed
        this.currentDate = new Date().toString();
    }

    public LeaderboardEntry(String username, int score) {
        this.userName = username;
        this.score = score;
        this.isFilled = !(userName == null || userName.equals(""));
        this.currentDate = new Date().toString();
        this.isTimed=!(this.time==0);
    }

    public LeaderboardEntry(String username, int score,int time) {
        this.userName = username;
        this.score = score;
        this.isFilled = (userName == null || userName.equals(""));
        this.currentDate = new Date().toString();
        this.isTimed=!(time==0);
        this.time = time;
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

    public int getTime(){
        return time;
    }

    public void setTime(int input){
        //negative time is not allowed
        if(input>0) {
            this.time = input;
            isTimed=true;
        }
        //if set to zero, entry becomes untimed
        if(input==0){
            this.time=input;
            isTimed=false;
        }
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
        //check for sameness of type
        if(!isTimed && !entry.isTimed) {
            //accruing more points is beneficial to leader board placement
            //if less than...
            if (this.score < entry.getScore()) {
                return -1;
            }
            //if greater than...
            if (this.score > entry.getScore()) {
                return 1;
            }
        }
        if(isTimed && entry.isTimed){
            //spending more time is detrimental to leader board placement
            //if the player took more time...
            if(this.time > entry.getTime()){
                return -1;
            }
            //if the player took less time...
            if(this.time < entry.getTime()){
                return 1;
            }
        }

        //otherwise it must be equal...
        return 0;
    }

    @Override
    public String toString(){
        return "LeaderboardEntry [dateFormat="+dateFormat+",thisDate="+thisDate+",currentDate="+currentDate
                +",score="+score+",time="+time+",userName="+userName+",isFilled="+isFilled+",isTimed="+isTimed+"]";
    }


}
