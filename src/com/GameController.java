package com;

//import model.TileModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//import java.awt.event.ActionEvent;

public class GameController implements EventListener {
    private static GameController singletonLink = new GameController();

    //ensures that ther is only one game controller ever created
    private GameController() {

    }

    //returns the singleton instance of the class
    public static GameController getInstance(){
        return singletonLink;
    }

    ////////////
    //TEST
    ///////////
    public static class Test {
        public Test() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }

                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(new TestPane());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
        }

        public class TestPane extends JPanel {

            public TestPane() {
                setLayout(new GridLayout(1, 2));
                add(createLeftPanel());
                add(createRightPanel());

            }

            protected JPanel createLeftPanel() {
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1;
                for (int index = 0; index < 10; index++) {
                    JButton btn = new JButton(Integer.toString(index + 1));
                    panel.add(btn, gbc);
                    btn.setTransferHandler(new ValueExportTransferHandler(Integer.toString(index + 1)));

                    btn.addMouseMotionListener(new MouseAdapter() {
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            JButton button = (JButton) e.getSource();
                            TransferHandler handle = button.getTransferHandler();
                            handle.exportAsDrag(button, e, TransferHandler.COPY);
                        }
                    });
                }
                return panel;
            }

            protected JPanel createRightPanel() {
                JPanel panel = new JPanel(new GridBagLayout());
                JLabel label = new JLabel("Drop in");
                label.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(20, 20, 20, 20)));
                label.setTransferHandler(new ValueImportTransferHandler());
                panel.add(label);
                return panel;
            }

        }
    }

    public static class ValueExportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
        private String value;

        public ValueExportTransferHandler(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = new StringSelection(getValue());
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            super.exportDone(source, data, action);
            // Decide what to do after the drop has been accepted
        }
    }

    public static class ValueImportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

        public ValueImportTransferHandler() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        if (component instanceof JLabel) {
                            ((JLabel) component).setText(value.toString());
                            accept = true;
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }
    }

    /*
    public void actionPerformed(ActionEvent evt) {
        JButton selectedBtn = (JButton) evt.getSource();
        for (int row = 0; row < gridMod.getGrid().length; row++) {
            for (int col = 0; col < gridMod.getGrid()[row].length; col++) {
                if (gridMod.getGrid()[row][col] == (TileModel) selectedBtn) {
                    System.out.printf("Selected row and column: %d %d%n", row, col);
                }
            }
        }
    }
*/


}
