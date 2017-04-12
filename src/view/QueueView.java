package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
//model Imports
import model.QueueModel;
import model.TileModel;


public class QueueView extends JPanel implements Observer {
    private JButton[] display = new JButton[5];

    //will be used to show the queue
    QueueView() {
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
        //for loop used to iterate through the 5 queue buttons
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
    public void update(Observable o, Object arg) {

        if (o.getClass().getSimpleName().equals("QueueModel")) {
            TileModel[] model = ((QueueModel) o).getQueue();
            for (int i = 0; i < 5; i++) {
                display[i].setText(model[i].getInt() + "");
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


}
