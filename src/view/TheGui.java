package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class TheGui extends JFrame implements Observer {
    //Panels____
    private GridView gridView;
    private QueueView queueView;
    private CurrentScoreView currentScoreView;

    //make an array of buttons then in the update method update all of the values to the tile array

    //constructor
    public TheGui() {
        super();

        // used to combine currentScore view and queue view via the same panel
        JPanel sidePanel = new JPanel();
        setTitle("Sum Fun!!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new BorderLayout());

        gridView = new GridView();
        add(gridView, BorderLayout.CENTER);
        queueView = new QueueView();
        //queueView.setSize(300,300);
        // queueView.setMinimumSize(new Dimension(100,0));
        //queueView.setPreferredSize(queueView.getMinimumSize());
        sidePanel.setLayout(new GridLayout(2, 1, 0, 0));
        sidePanel.add(queueView);
        currentScoreView = new CurrentScoreView();
        sidePanel.add(currentScoreView);
        add(sidePanel, BorderLayout.EAST);
        setVisible(true);

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
