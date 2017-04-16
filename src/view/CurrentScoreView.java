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
/**
 * Created by Aaron on 3/30/2017.
 */
public class CurrentScoreView extends JPanel implements Observer {

    private JTextField scoreTextField;
    private JTextField movesRemaining;

    private CurrentScoreModel currentScore;

    CurrentScoreView() {
        Font font = new Font("SansSerif", Font.BOLD, 20);
        JLabel scoreLabel;
        JLabel movesLabel;
        currentScore = new CurrentScoreModel();
        GridLayout grid = new GridLayout(2, 2, 0, 0);
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

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().getSimpleName().equals("CurrentScoreModel")) {
            scoreTextField.setText(currentScore.getCurrentScore() + "");
            movesRemaining.setText(currentScore.getCurrentScore() + "");
        }
    }
}
