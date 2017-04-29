package view;

import datacontainers.LeaderboardEntry;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import model.LeaderboardModel;

public class LeaderboardView extends JFrame implements Observer {

    private JLabel[] rank = new JLabel[LeaderboardModel.LIST_LENGTH];//where the leader board entries will be displayed as JLabels
    private LeaderboardModel leaderboard = LeaderboardModel.getInstance();

    public LeaderboardView(){
        super();
        leaderboard.addObserver(this);

        //GUI variables
        Font font = new Font("SansSerif", Font.BOLD, 28);
        GridLayout grid = new GridLayout(LeaderboardModel.LIST_LENGTH, 1, 0, 0);
        this.setTitle("High Scores!!");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(grid);

        //generate JLabels to display the user ranks
        for (int i = 0; i < LeaderboardModel.LIST_LENGTH; i++) {
            //label initialization...
            rank[i] = new JLabel();
            rank[i].setFont(font);
            rank[i].setHorizontalAlignment(SwingConstants.CENTER);
            rank[i].setText("<placeholder>");

            add(rank[i]);//add the label to the panel...
        }

        //add dummy entries to the
        for(int i=0;i< LeaderboardModel.LIST_LENGTH;i++){
            leaderboard.addEntry(new LeaderboardEntry("testName",i,0));
        }

        leaderboard.setUserName("Bob",1);
        leaderboard.setScore(1,1100);
        leaderboard.save();
        leaderboard.load();
    }

    //ask for a user's name to be added to the leader board
    public void displayMessage(){
        try {
            String message = JOptionPane.showInputDialog("Please enter your name to be added to the list of high scores!");
            LeaderboardEntry tmp = new LeaderboardEntry();
            tmp.setUserName(message);
            tmp.setIsFilled(true);
            leaderboard.save();
        }catch(Exception ex){
            System.err.println("LeaderboardModel displayMessage() error");
            ex.printStackTrace();
        }
    }

    public void addObserver(Observable o) {
        o.addObserver(this);
        //register a model with this view...
    }

    @Override
    public void update(Observable o, Object arg) {
        LeaderboardModel tmp = (LeaderboardModel) arg;
        for (int i = 0; i < 10 && i<tmp.getSize(); i++) {
            rank[i].setText("Rank:" + i+1
                    + "\t Name: "
                    + tmp.getUserName(i)
                    + "\t Score: " + tmp.getScore(i));
        }
    }
}
