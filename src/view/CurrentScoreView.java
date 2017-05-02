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
import model.GridModel;

public class CurrentScoreView extends JPanel implements Observer {

    private JTextField scoreTextField;
    private JTextField movesRemaining;
    private GridModel currentScore;//link to registered score model

    CurrentScoreView() {
        currentScore = new GridModel();
        Font font = new Font("SansSerif", Font.BOLD, 20);
        JLabel scoreLabel;
        JLabel movesLabel;
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
        scoreTextField.setText(getRegisteredScoreModel().getCurrentScore());
        scoreTextField.setFont(font);
        scoreTextField.setHorizontalAlignment(JTextField.CENTER);
        scoreTextField.setEditable(false);
        add(scoreTextField);
        movesLabel = new JLabel();
        movesLabel.setText("Current Number of Moves: ");
        add(movesLabel);
        movesRemaining = new JTextField();
        movesRemaining.setText(currentScore.getNumberMoves());
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

    public void addObserver(Observable o) {
        o.addObserver(this);
        //register a model with this view...
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().getSimpleName().equals("GridModel")) {
            scoreTextField.setText(currentScore.getCurrentScore());
            movesRemaining.setText(currentScore.getNumberMoves());
        }

    }

    public void registerScoreModel(GridModel cs) {
        currentScore = cs;
    }

    private GridModel getRegisteredScoreModel() {
        return currentScore;
    }
}
