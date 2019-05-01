import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Sorter extends JFrame {
    public static final String SELECTION_SORT_TEXT = "Selection Sort";
    public static final String INSERTION_SORT_TEXT = "Insertion Sort";
    public static final String MERGE_SORT_TEXT = "Merge Sort";
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final String SORTINGALGO = "Sorting algorithm";
    ImageIcon loading = new ImageIcon("loading.gif");
    JLabel loadingIcon = new JLabel(loading);
    private JTextArea display;
    private JButton sortButton;
    private JComboBox sortingAlgorithms;
    private JPanel panel;
    private JLabel sortAlgo = new JLabel(SORTINGALGO);


    public Sorter() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createContents();
    }

    public static void main(String[] args) {
        new Sorter();
    }

    private void createContents() {
        panel = new JPanel(new FlowLayout());
        add(panel, BorderLayout.NORTH);
        display = new JTextArea();
        display.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(sortAlgo);
        add(display, BorderLayout.CENTER);
        sortButton = new JButton("Sort");
        String[] selectionString = {SELECTION_SORT_TEXT, INSERTION_SORT_TEXT, MERGE_SORT_TEXT};
        sortingAlgorithms = new JComboBox(selectionString);
        panel.add(sortingAlgorithms);
        panel.add(sortButton);
        loadingIcon.setSize(25, 25);
        panel.add(loadingIcon);
        loadingIcon.setVisible(false);
        display.setEnabled(false);
        setTitle("Sorter");
        setVisible(true);
        setSize(WIDTH, HEIGHT);

        SortButtonListener listener = new SortButtonListener();
        sortButton.addActionListener(listener);
        sortingAlgorithms.addActionListener(listener);


        //TODO: Implement
    }

    public synchronized void displayResult(int n, long runtime) {
        String outPut = "" + n + "\t\t" + runtime;
        display.setText(display.getText() + "\n" + outPut);


        if (n == 256000) {
            loadingIcon.setVisible(false);
            sortingAlgorithms.setEnabled(true);

           // display.setText(display.getText() + outPut);
        }

    }

    private class SortButtonListener implements ActionListener {
        private int[] arr;
        private SortRunnable sr;

        public void actionPerformed(ActionEvent e) {
            ExecutorService es = Executors.newSingleThreadExecutor();

                    if (e.getSource() == sortButton) {
                        loadingIcon.setVisible(true);
                        display.setText("N\t\tRuntime (ms)");
                        sortingAlgorithms.setEnabled(false);

                        for (int i = 0; i <= 8; i++) {
                            arr = new int[1000 * (int) Math.pow(2, i)];
                            fillArr();
                            sr = new SortRunnable(sortingAlgorithms.getSelectedItem() + "", arr, Sorter.this);
                            es.execute(sr);
                        }
                    }


            //TODO: Finish Implementation

            es.shutdown();
        }

        private void fillArr() {
            Random r = new Random();
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = r.nextInt();
            }
        }
    }
}

//name function CPS