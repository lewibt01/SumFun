package view;

import model.TimedGameModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class TheGui extends JFrame implements Observer {
    //Panels____
    private GridView gridView;
    private QueueView queueView;
    private CurrentScoreView currentScoreView;
    private final JPanel sidePanel;
    //make an array of buttons then in the update method update all of the values to the tile array

    //constructor
    public TheGui() {
        super();
        // used to combine currentScore view and queue view via the same panel
        sidePanel = new JPanel();
        setTitle("Sum Fun!!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1200, 1000);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        gridView = new GridView();
        add(gridView, BorderLayout.CENTER);
        queueView = new QueueView();

        //register the views to each other...
        gridView.registerQueueView(queueView);
        queueView.registerGridView(gridView);
        //Format the GUI to make it look pretty...
        sidePanel.setLayout(new GridLayout(2, 1, 0, 0));

        sidePanel.add(queueView);
        currentScoreView = new CurrentScoreView();
        sidePanel.add(currentScoreView);
        add(sidePanel, BorderLayout.EAST);
        //setVisible(true);

    }

    public GridView getGridView() {
        return gridView;
    }

    public QueueView getQueueView() {
        return queueView;
    }

    public CurrentScoreView getCurrentScoreView() {
        return currentScoreView;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void addTimer(TimedGameModel timedGameModel) {
        TimerView timerPanel = new TimerView(timedGameModel);
        timerPanel.addObserver(timedGameModel);
        sidePanel.add(timerPanel);
        sidePanel.updateUI();

    }
}

/*    private class TileListener implements ActionListener {
        TileModel t;

        public TileListener(TileModel tile) {
            this.t = tile;
        }

        public void actionPerformed(ActionEvent e) {
            t = (TileModel) e.getSource();
            t.setColor(Color.GREEN);


        }
    }*/
