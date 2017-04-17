package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//class imports
import model.CurrentScoreModel;
import model.GridModel;

/**
 * Created by Aaron on 3/30/2017.
 */
public class CurrentScoreView extends JPanel implements Observer {

    private JTextField scoreTextField;
    private JTextField movesRemaining;
    private CurrentScoreModel currentScore; //link to registered score model
    private GridView gridLink; //start with no registered grid

    CurrentScoreView() {
        Font font = new Font("SansSerif", Font.BOLD, 20);
        JLabel scoreLabel;
        JLabel movesLabel;
        currentScore = new CurrentScoreModel();
        GridLayout grid = new GridLayout(0, 2, 0, 0);
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
        scoreTextField.setText(currentScore.getCurrentScore() + "");
        scoreTextField.setFont(font);
        scoreTextField.setHorizontalAlignment(JTextField.CENTER);
        scoreTextField.setEditable(false);
        add(scoreTextField);
        movesLabel = new JLabel();
        movesLabel.setText("Current Number of Moves: ");
        add(movesLabel);
        movesRemaining = new JTextField();
        movesRemaining.setText(currentScore.getNumberMoves() + "");
        movesRemaining.setFont(font);
        movesRemaining.setHorizontalAlignment(JTextField.CENTER);
        movesRemaining.setEditable(false);
        add(movesRemaining);

    }

    void addTimer(TimerView timerView) {
        JLabel timerLabel = new JLabel();
        timerLabel.setText("Time remaining: ");
        add(timerLabel);
        add(timerView);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().getSimpleName().equals("CurrentScoreModel")) {
            scoreTextField.setText(currentScore.getCurrentScore() + "");
            movesRemaining.setText(currentScore.getNumberMoves() + "");
        }
    }

    public void registerScoreModel(CurrentScoreModel cs) {
        currentScore = cs;
    }

    public CurrentScoreModel getRegisteredScoreModel() {
        return currentScore;
    }
    public void registerGridView(GridView g){
        gridLink = g;
    }

    public GridView getRegisteredGridView(){
        return gridLink;
    }
}
