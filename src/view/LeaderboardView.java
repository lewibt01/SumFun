package view;

import data_containers.LeaderboardEntry;
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
    private JLabel[] rank;//where the leader board entries will be displayed as JLabels
    private LeaderboardModel leaderboard = LeaderboardModel.getInstance();

    public LeaderboardView() throws Exception {
        super();
        leaderboard.addObserver(this);
        rank = new JLabel[10];

        //GUI variables
        Font font = new Font("SansSerif", Font.BOLD, 28);
        GridLayout grid = new GridLayout(10, 1, 0, 0);
        this.setTitle("High Scores!!");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(grid);

        //generate JLabels to display the user ranks
        for (int i = 0; i < 10; i++) {
            //label initialization...
            rank[i] = new JLabel();
            rank[i].setFont(font);
            rank[i].setHorizontalAlignment(SwingConstants.CENTER);
            rank[i].setText("<placeholder>");

            //entry addition
            leaderboard.addEntry(new LeaderboardEntry("testName",i*10,true));
            leaderboard.save();

            //add the label to the panel...
            add(rank[i]);
        }

    }

    //ask for a user's name to be added to the leaderboard
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
            rank[i].setText("Rank:" + i+2 + "\t Name: " + tmp.getUserName(i) + "\t Score: " + tmp.getScore(i));
        }
    }
}
