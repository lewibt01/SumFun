package view;

import datacontainers.SoundEffect;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//model Imports

import model.GridModel;
import model.QueueModel;

public class QueueView extends JPanel implements Observer {
	private QueueView thisLink = this;
	private JButton[] display;
	private QueueModel queueMod;// link to registered queue model
	private GridView gridLink;// start with no registered grid
	private JButton shuffleJButton;
	private JButton hintJButton;
	private JButton removeJButton;
	private boolean canRemoveNumber = false;
	private boolean numberRemoved = false;

	/**
	 * Will be used to show the queue and buttons
	 */

	QueueView(QueueModel queueModel) {
		super();
		// font used for the shuffle panel
		Font font = new Font("SansSerif", Font.BOLD, 14);
		Font queueFont = new Font("Sanserif", Font.BOLD, 18);
		JPanel shufflePanel = new JPanel();
		shuffleJButton = new JButton("Shuffle");
		hintJButton = new JButton("Hint");
		removeJButton = new JButton("Remove a Number from the Board");
		JButton exitJButton = new JButton("Exit the game");
		display = new JButton[5];
		queueMod = queueModel;

		this.setLayout(new GridLayout(6, 1, 0, 0));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("User Options"),
				BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(0),
						BorderFactory.createBevelBorder(0))));
		this.setAlignmentY(CENTER_ALIGNMENT);
		// for loop used to iterate through the 5 queue buttons
		// add action Listener to jButtons
		hintJButton.addActionListener(new HintListener());
		removeJButton.addActionListener(new RemoveListener());
		shuffleJButton.addActionListener(new ShuffleListener());
		exitJButton.addActionListener(new ExitListener());
		// change the font for the buttons for easier read ability
		hintJButton.setFont(font);
		removeJButton.setFont(font);
		shuffleJButton.setFont(font);
		exitJButton.setFont(font);
		// add opaque to buttons for mac users
		hintJButton.setOpaque(true);
		removeJButton.setOpaque(true);
		shuffleJButton.setOpaque(true);
		exitJButton.setOpaque(true);
		// add Colors to the respective buttons
		hintJButton.setBackground(Color.BLUE);
		hintJButton.setForeground(Color.WHITE);
		removeJButton.setBackground(Color.MAGENTA);
		shuffleJButton.setBackground(Color.GREEN);
		exitJButton.setBackground(Color.ORANGE);
		// change layout to accept 3 buttons
		shufflePanel.setLayout(new GridLayout(2, 2));
		// add buttons to panel
		shufflePanel.add(hintJButton);
		shufflePanel.add(removeJButton);
		shufflePanel.add(shuffleJButton);
		shufflePanel.add(exitJButton);
		add(shufflePanel);
		for (int i = 0; i < 5; i++) {

			display[i] = new JButton();
			// allow for mac users to see color
			display[i].setOpaque(true);
			// initially set to false so that user cannot interact with the
			// queue
			display[i].setFont(queueFont);
			display[i].setEnabled(false);
			// when the tile is the first in the queue
			if (i == 0) {
				display[i].setEnabled(true);
				display[i].addMouseListener(new ButtonListener());
				// moved overridden method to the ButtonListener for cleaner
				// code
				display[i].addMouseMotionListener(new ButtonListener());
				// display[i].setTransferHandler(new
				// GameController.ValueExportTransferHandler(display[i].getText()));
			}
			// adds the display of JButtons to the JPanel
			add(display[i]);
		}

	}

	// register a model with this view...
	public void addObserver(Observable o) {
		o.addObserver(this);
	}

	// grab data from the model to update what is seen on the view
	@SuppressWarnings("unchecked")
	public void update(Observable o, Object arg) {
		ArrayList<Integer> queueModel = (ArrayList<Integer>) arg;
		for (int i = 0; i < 5 && i < queueMod.size(); i++) {
			display[i].setText(queueModel.get(i) + "");
			display[i].setBackground(Color.CYAN);
			display[i].setForeground(Color.BLACK);
			if (queueModel.get(i) <= -1) {
				display[i].setText(" ");
			}
		}

	}

	JButton getShuffleJButton() {
		return shuffleJButton;
	}

	JButton getHintJButton() {
		return hintJButton;
	}

	JButton getRemoveJButton() {
		return removeJButton;
	}

	boolean canRemoveNumber() {
		return canRemoveNumber;
	}

	void registerGridView(GridView g) {
		gridLink = g;
	}

	public GridView getRegisteredGridView() {
		return gridLink;
	}

	public void registerQueueModel(QueueModel q) {
		queueMod = q;
	}

	QueueModel getRegisteredQueueModel() {
		return queueMod;
	}

	public JButton[] getDisplay() {
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

		public void mouseClicked(MouseEvent e) {
			SoundEffect longBeep = new SoundEffect("/soundFiles/LongBeep.wav");
			longBeep.play();
			if (display[0] == e.getSource()) {
				System.out.println("You clicked on the queue");

			}
		}
	}

	// method used to activate shuffle in the queue model
	private class ShuffleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!queueMod.getHasShuffled()) {
				queueMod.shuffle();
				queueMod.setHasShuffled(true);
				shuffleJButton.setEnabled(false);
			}

		}
	}

	// method used to activate hints in gridModel via button click
	private class HintListener implements ActionListener {
		// count used to determine number of hints
		int count = 0;
		public void actionPerformed(ActionEvent e) {
			GridModel gm = gridLink.getRegisteredGridModel();
			try {
				gm.highlightTile(gm.hint(queueMod.peek()));
				count++;
            } catch (NullPointerException ex) {
                if(count >= 3){
                	hintJButton.setEnabled(false);
				}
                //ex.printStackTrace();
            }
        }
    }


    //method used to activate remove all tile method via button click
    private class RemoveListener implements ActionListener {
        SoundEffect revWarble = new SoundEffect("/soundFiles/ReverseWarble.wav");
        @Override
        public void actionPerformed(ActionEvent e) {
            revWarble.play();
            if (numberRemoved && !canRemoveNumber) {
                removeJButton.setEnabled(false);
                canRemoveNumber = false;
            } else {
                canRemoveNumber = true;

				numberRemoved = false; // Maybe set this somewhere else
			}
		}
	}

	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(thisLink);
			ancestor.dispose();
			// System.exit(0);
		}
	}

}
