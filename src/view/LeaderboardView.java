package view;

import datacontainers.LeaderboardEntry;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import model.LeaderboardModel;

public class LeaderboardView extends JFrame implements Observer {
    private Font font;
    private JLabel[] timedRank;
    private JLabel[] rank; //where the leader board entries will be displayed as JLabels
    private LeaderboardModel leaderboard = LeaderboardModel.getInstance();

    public LeaderboardView() {
        super();
        JPanel timedPanel = new JPanel();
        JPanel untimedPanel = new JPanel();
        leaderboard.addObserver(this);
        rank = new JLabel[10];
        timedRank = new JLabel[10];

        //GUI variables
        font = new Font("SansSerif", Font.BOLD, 24);
        GridLayout grid = new GridLayout(LeaderboardModel.LIST_LENGTH, 1, 0, 0);
        this.setLayout(new GridLayout(1, 2, 0, 0));
        this.setTitle("High Scores!!");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(1100, 800);
        this.setLocationRelativeTo(null);
        untimedPanel.setLayout(grid);
        timedPanel.setLayout(grid);

        //generate JLabels to display the user ranks
        for (int i = 0; i < LeaderboardModel.LIST_LENGTH; i++) {
            //label initialization...
            rank[i] = new JLabel();
            rank[i].setFont(font);
            rank[i].setHorizontalAlignment(SwingConstants.CENTER);
            rank[i].setText("<placeholder>");
            timedRank[i] = new JLabel();
            timedRank[i].setFont(font);
            timedRank[i].setHorizontalAlignment(SwingConstants.CENTER);
            timedRank[i].setText("<Timed placeholder>");

            untimedPanel.add(rank[i]);//add the label to the panel...
            timedPanel.add(timedRank[i]);
        }

        /*
        //add dummy entries to the
        for (int i = 0; i < LeaderboardModel.LIST_LENGTH; i++) {
            leaderboard.addEntry(new LeaderboardEntry("testName  ", i, 0));
        }

        leaderboard.setUserName("Bob", 1);
        leaderboard.setScore(1, 1100);

        leaderboard.save();
        */
        this.add(untimedPanel);
        this.add(timedPanel);
        leaderboard.load();

    }

    //ask for a user's name to be added to the leader board
    public void displayMessage() {
        try {
            String message = JOptionPane.showInputDialog("Please enter your name to be added to the list of high scores!");
            LeaderboardEntry tmp = new LeaderboardEntry();
            tmp.setUserName(message);
            tmp.setIsFilled(true);
            leaderboard.save();
        } catch (Exception ex) {
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

        int count = 1;//externalized incrementer to avoid issues with the grid view
        int length = LeaderboardModel.LIST_LENGTH;//the number of leader board entries of each type to be displayed

        //find up to 10 of each type of entry, continuing to search for the opposite type even
        //  if we already have <LIST_LENGTH> of the other
        for (int i = 0; i < length && i<tmp.getSize();i++) {
                rank[i].setText("Rank: " + count
                        + ", Name: " + tmp.getUserName(i)
                        + ", Score: " + tmp.getScore(i));
                rank[i].setHorizontalAlignment(SwingConstants.LEFT);
                rank[i].setFont(font);

            /*
            if(tmp.isTimed(i) && j<length){
                timedRank[j].setText("Rank: " + count
                        + "\tName:\t" + tmp.getUserName(i)
                        + "\tScore:\t" + tmp.getScore(i));

                timedRank[j].setHorizontalAlignment(SwingConstants.RIGHT);
                timedRank[j].setFont(font);
                j++;//found a timed entry

            }
            */
            count++;
        }

    }
}
