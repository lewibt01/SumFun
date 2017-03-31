package view;

import model.CurrentScoreModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Aaron on 3/30/2017.
 */
public class CurrentScoreView extends JPanel implements Observer {
    private Font font = new Font("SansSerif", Font.BOLD, 20);
    private JTextField scoreTextField, movesRemaining;
    private JLabel scoreLabel;
    private JLabel movesLabel;
    private CurrentScoreModel currentScore;
    public CurrentScoreView(){
        currentScore = new CurrentScoreModel();
        GridLayout grid = new GridLayout(2,2,0,0);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Score "),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEtchedBorder(0),
                        BorderFactory.createBevelBorder(0))));
        scoreLabel = new JLabel();
        scoreLabel.setText("Current Score: ");
        add(scoreLabel);
        scoreTextField = new JTextField();
        scoreTextField.setText(currentScore.getCurrentScore() +"");
        scoreTextField.setFont(font);
        scoreTextField.setHorizontalAlignment(JTextField.CENTER);
        scoreTextField.setEditable(false);
        add(scoreTextField);
        movesLabel = new JLabel();
        movesLabel.setText("Current Number of Moves: ");
        add(movesLabel);
        movesRemaining= new JTextField();
        movesRemaining.setText(currentScore.getNumberMoves() + "");
        movesRemaining.setFont(font);
        movesRemaining.setHorizontalAlignment(JTextField.CENTER);
        movesRemaining.setEditable(false);
        add(movesRemaining);
    }
    @Override
    public void update(Observable o, Object arg) {

    }
}
