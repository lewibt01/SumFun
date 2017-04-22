package view;
//


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
import data_containers.LeaderboardEntry;


public class LeaderboardView extends JFrame implements Observer {
    //set up places to store data 10

    private JLabel[] rank;
    private LeaderboardModel leaderboard;

    public LeaderboardView() throws Exception {
        super();
        leaderboard = LeaderboardModel.getInstance();
        rank = new JLabel[10];
        Font font = new Font("SansSerif", Font.BOLD, 28);
        GridLayout grid = new GridLayout(10, 1, 0, 0);
        this.setTitle("High Scores!!");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(grid);

        //for loop to generate the rank
        for (int i = 0; i < 10; i++) {
            rank[i] = new JLabel("Rank: " + leaderboard.getNumber(i) + "     Name: " + leaderboard.getUserName(i) + "     Score: " + leaderboard.getScore(i) + "       Date: " + leaderboard.getDate(i));
            //leaderboard.setCurrentPos(i);
            leaderboard.setNumber(i);
            rank[i].setFont(font);
            rank[i].setHorizontalAlignment(SwingConstants.CENTER);
            add(rank[i]);
        }

    }

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

    @Override
    public void update(Observable o, Object arg) {
        for (int i = 0; i < 10; i++) {
            rank[i].setText("Rank:" + leaderboard.getNumber(i) + "\t Name: " + leaderboard.getUserName(i) + "\t Score: " + leaderboard.getScore(i));

        }
    }
}
