package view;
//imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.GridModel;
import model.TimedGameModel;

public class TheGui extends JFrame implements Observer {
    //Panels____
    private GridView gridView;
    private QueueView queueView;
    private CurrentScoreView currentScoreView;
    private GridModel gridModel;
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
        gridModel = new GridModel();
        gridView = new GridView();
        gridView.registerGridModel(gridModel);
        add(gridView, BorderLayout.CENTER);
        queueView = new QueueView();
        currentScoreView = new CurrentScoreView();

        //register the views to each other...
        gridView.registerQueueView(queueView);
        queueView.registerGridView(gridView);
        gridView.registerScoreView(currentScoreView);
        //Format the GUI to make it look pretty...
        sidePanel.setLayout(new GridLayout(2, 1, 0, 0));

        sidePanel.add(queueView);
        sidePanel.add(currentScoreView);
        add(sidePanel, BorderLayout.EAST);
        //setVisible(true);

    }
    public GridModel getGridModel(){
        return  gridModel;
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
        currentScoreView.addTimer(timerPanel);
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
