package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

//model Imports
import com.sun.org.apache.xalan.internal.xsltc.runtime.ErrorMessages_ca;
import model.QueueModel;



public class QueueView extends JPanel implements Observer {
    private JButton[] display;
    private QueueModel queueMod;//link to registered queue model
    private GridView gridLink;//start with no registered grid
    //will be used to show the queue
    QueueView() {

        super();
        JPanel shufflePanel = new JPanel();
        JButton shuffleJB = new JButton("Shuffle");
        display = new JButton[5];
        queueMod = new QueueModel();

        this.setLayout(new GridLayout(6, 1, 0, 0));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Available Moves"),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEtchedBorder(0),
                        BorderFactory.createBevelBorder(0))));
        this.setAlignmentY(CENTER_ALIGNMENT);
        //for loop used to iterate through the 5 queue buttons
        //add action Listener to jButtons
        shuffleJB.addActionListener(new ShuffleListener());
        //add buttons to panel
        shufflePanel.add(shuffleJB);
        add(shufflePanel);
        for (int i = 0; i < 5; i++) {
            //int randomVal = ThreadLocalRandom.current().nextInt(0, 10);
            display[i] = new JButton();
            //display[i].setText(randomVal+"");
            //initially set to false so that user cannot interact with the queue
            display[i].setEnabled(false);
            //when the tile is the first in the queue
            if (i == 0) {
                display[i].setEnabled(true);
                display[i].addMouseListener(new ButtonListener());
                //moved overridden method to the ButtonListener for cleaner code
                display[i].addMouseMotionListener(new ButtonListener());
                //display[i].setTransferHandler(new GameController.ValueExportTransferHandler(display[i].getText()));
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
    @SuppressWarnings("unchecked")
    public void update(Observable o, Object arg) {
        //if (o.getClass().getSimpleName().equals("QueueModel")) {
            ArrayList<Integer> queueModel = (ArrayList<Integer>)arg;
        //iterate through 5 or fewer elements in the queue
        for (int i = 0; i < 5 && i<queueMod.size(); i++) {
                display[i].setText(queueModel.get(i) + "");
                display[i].setBackground(Color.CYAN);
                display[i].setForeground(Color.BLACK);
        }
        //System.out.println(queueModel.toString());

    }

    public void registerGridView(GridView g){
        gridLink = g;
    }

    public GridView getRegisteredGridView(){
        return gridLink;
    }

    public void registerQueueModel(QueueModel q){
        queueMod = q;
    }

    public QueueModel getRegisteredQueueModel(){
        return queueMod;
    }

    public JButton[] getDisplay(){
        return display;
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
        public void mouseClicked(MouseEvent e){
            if(display[0] == e.getSource()){
                System.out.println("You clicked on the queue");

            }
        }
        /*public void mouseDragged(MouseEvent e) {
            JButton btn = (JButton) e.getSource();
            TransferHandler handle = btn.getTransferHandler();
            handle.exportAsDrag(btn, e, TransferHandler.MOVE);
        }*/
    }

    private class ShuffleListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            if(queueMod.getHasShuffled()) {
                JOptionPane.showMessageDialog(null, "Queue has been shuffled already!");

            }else{
                queueMod.shuffle();
                queueMod.setHasShuffled();
        }

        }
    }



}
