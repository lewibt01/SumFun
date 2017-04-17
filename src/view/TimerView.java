package view;


import model.TimedGameModel;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//class imports

/**
 * Created by Aaron on 3/30/2017.
 */
public class TimerView extends JPanel implements Observer {

    private JTextField timerTextField;

    private TimedGameModel timedModel;

    TimerView(TimedGameModel timedGameModel) {
        Font font = new Font("SansSerif", Font.BOLD, 18);
        JLabel timerLabel;
        timedModel = timedGameModel;
        GridLayout grid = new GridLayout(0, 2, 0, 0);
        this.setLayout(grid);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Timer "),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEtchedBorder(0),
                        BorderFactory.createBevelBorder(0))));
        timerLabel = new JLabel();
        timerLabel.setText("Time remaining: ");
        add(timerLabel);
        timerTextField = new JTextField();
        timerTextField.setText(timedModel.getTimer());
        timerTextField.setFont(font);
        timerTextField.setHorizontalAlignment(JTextField.CENTER);
        timerTextField.setEditable(false);
        add(timerTextField);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().getSimpleName().equals("TimedGameModel")) {
            timerTextField.setText(timedModel.getTimer());

        }
    }
    public void addObserver(Observable o) {
        o.addObserver(this);
    }
}
