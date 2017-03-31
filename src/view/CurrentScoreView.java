package view;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Aaron on 3/30/2017.
 */
public class CurrentScoreView extends JPanel implements Observer {
    private JTextField jTextField;
    private JTextArea jTextArea;
    @Override
    public void update(Observable o, Object arg) {
      
    }
}
