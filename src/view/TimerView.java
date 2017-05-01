package view;
//imports
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TimedGameModel;
//class imports

public class TimerView extends JPanel implements Observer {

    private JTextField timerTextField;

    private TimedGameModel timedModel;

    TimerView(TimedGameModel timedGameModel) {
        Font font = new Font("SansSerif", Font.BOLD, 18);
        JLabel timerLabel;
        timedModel = timedGameModel;
        GridLayout grid = new GridLayout(1, 1, 0, 0);
        this.setLayout(grid);

        timerTextField = new JTextField();
        timerTextField.setText(timedModel.getTimer());
        timerTextField.setFont(font);
        timerTextField.setHorizontalAlignment(JTextField.CENTER);
        timerTextField.setEditable(false);
        add(timerTextField);

    }

    public JTextField getTimerTextField() {
        return timerTextField;
    }

   TimedGameModel getRegisteredTimeModel() {
        return this.timedModel;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().getSimpleName().equals("TimedGameModel")) {
            if ((boolean) arg) {
                int option = JOptionPane.showConfirmDialog(null, "Score: " +
                                timedModel.getGridModel().getCurrentScore() + " Would you like to start a new game?",
                        "Game Over!", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    timedModel.resetGame();
                }
            }

            timerTextField.setText(timedModel.getTimer());
        }
    }

    void addObserver(Observable o) {
        o.addObserver(this);
    }
}
