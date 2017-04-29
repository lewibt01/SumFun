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

public class TheGui implements Observer {
    //Panels____
    private JFrame theFrame;
    private GridView gridView;
    private QueueView queueView;
    private CurrentScoreView currentScoreView;
    private GridModel gridModel;
    private final JPanel sidePanel;
    private TimerView timerPanel;
    //make an array of buttons then in the update method update all of the values to the tile array

    //constructor
    public TheGui() {
        super();
        //build frame
        theFrame = new JFrame();
        // used to combine currentScore view and queue view via the same panel
        sidePanel = new JPanel();
        theFrame.setTitle("Sum Fun!!");
        theFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        theFrame.setSize(1600, 900);
        theFrame.setLayout(new BorderLayout());
        theFrame.setLocationRelativeTo(null);
        gridModel = new GridModel();
        gridView = new GridView();
        //timerPanel = new TimerView();
        gridView.registerGridModel(gridModel);
        theFrame.add(gridView, BorderLayout.CENTER);
        queueView = new QueueView();
        currentScoreView = new CurrentScoreView();

        //register the views to each other...
        gridView.registerTimerView(timerPanel);
        gridView.registerQueueView(queueView);
        queueView.registerGridView(gridView);
        gridView.registerScoreView(currentScoreView);
        gridView.registerGui(this);
        //Format the GUI to make it look pretty...
        sidePanel.setLayout(new GridLayout(2, 1, 0, 0));

        sidePanel.add(queueView);
        sidePanel.add(currentScoreView);
        theFrame.add(sidePanel, BorderLayout.EAST);
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

    //will add timer to the gui
    public void addTimer(TimedGameModel timedModel) {
        timerPanel = new TimerView(timedModel);
        timerPanel.addObserver(timedModel);
        currentScoreView.addTimer(timerPanel);
        sidePanel.updateUI();

    }

    public JFrame getTheFrame() {
        return theFrame;
    }
}
