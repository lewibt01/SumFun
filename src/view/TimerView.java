package view;
//imports
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TimedGameModel;
//class imports

public class TimerView extends JPanel implements Observer {

    private JTextField timerTextField;

    private TimedGameModel timedModel;

    TimerView() {
        Font font = new Font("SansSerif", Font.BOLD, 18);
        JLabel timerLabel;
        timedModel = new TimedGameModel();
        GridLayout grid = new GridLayout(1, 1, 0, 0);
        this.setLayout(grid);

        timerLabel = new JLabel();
        add(timerLabel);
        timerTextField = new JTextField();
        timerTextField.setText(timedModel.getTimer());
        timerTextField.setFont(font);
        timerTextField.setHorizontalAlignment(JTextField.CENTER);
        timerTextField.setEditable(false);
        add(timerTextField);

    }

   TimedGameModel getRegisteredTimeModel() {
        return timedModel;
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
