package view;

import com.GameController;
import model.QueueModel;
import model.TileModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

public class QueueView extends JPanel implements Observer {
    private JButton display[] = new JButton[5];

    public QueueView() {
        super();
        //create a boxlayout with vertically stacked components
        //BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
        //this.setLayout(box);
        this.setLayout(new GridLayout(5, 1, 0, 0));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Available Moves"),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEtchedBorder(0),
                        BorderFactory.createBevelBorder(0))));
        this.setAlignmentY(CENTER_ALIGNMENT);
        //this.setBackground(new Color(123, 255, 24));
/*
        for (int i = 0; i < 5; i++) {

            //TileModel tmp = new TileModel(new Color(38, 170, 160));
            //tile model no longer has buttons therefore all view components do not work
            //tmp.setPreferredSize(new Dimension(100,100));

            display[i] = new JButton();
            add(display[i]);
            display[i].addMouseListener(new ButtonListener());
        }
*/
        for(int i = 0; i < 5; i++){
            int randomVal = ThreadLocalRandom.current().nextInt(0, 10);
            display[i] = new JButton();
            display[i].setText(randomVal+"");
            //initially set to false so that user cannot interact with the queue
            display[i].setEnabled(false);

            //when the tile is the first in the queue
            if(i == 0) {
                display[i].setEnabled(true);
                display[i].addMouseListener(new ButtonListener());
                display[i].setTransferHandler(new GameController.ValueExportTransferHandler(display[i].getText()));
            }
            //adds the display of JButtons to the JPanel
            add(display[i]);
        }

    }

    //register a model with this view...
    public void addObserver(Observable o) {
        o.addObserver(this);

    }

    //grab data from the model to update what is seen on the view
    //this is probably the wrong way to do this...
    public void update(Observable o, Object arg) {

        if (o.getClass().getSimpleName().equals("QueueModel")) {
            JButton[] model = ((QueueModel) o).getQueue();
            for (int i = 0; i < 5; i++) {
                display[i].setText(model[i].getText());
                display[i].setBackground(Color.CYAN);
                display[i].setForeground(Color.BLACK);
            }
        }
    }

    private class ButtonListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            JButton btnEnt = (JButton) e.getSource();
            btnEnt.setBackground(Color.RED);
        }

        public void mouseExited(MouseEvent e) {
            JButton btnEx = (JButton) e.getSource();
            btnEx.setBackground(Color.CYAN);
            btnEx.setForeground(Color.BLACK);
        }
        public void mouseDragged(MouseEvent e) {
            JButton btn = (JButton) e.getSource();
            TransferHandler handle = btn.getTransferHandler();
            handle.exportAsDrag(btn, e, TransferHandler.MOVE);

        }
    }




}
