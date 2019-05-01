import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Assignment08Tests {
    private Sorter sorter;
    private Field display;
    private Method actionPerformed;
    private static final String SELECTION_SORT_TEXT = "Selection Sort";
    private static final String INSERTION_SORT_TEXT = "Insertion Sort";
    private static final String MERGE_SORT_TEXT = "Merge Sort";
    private static final String DISPLAY_HEADER_TEXT = "N\t\tRuntime (ms)";
    private int numRuns = 9;

    @BeforeEach
    public void setUp() throws Exception {
        sorter = new Sorter();
        Field sortingAlgorithms = Sorter.class.getDeclaredField("sortingAlgorithms");
        sortingAlgorithms.setAccessible(true);
        display = Sorter.class.getDeclaredField("display");
        display.setAccessible(true);
        Field sortButton = Sorter.class.getDeclaredField("sortButton");
        sortButton.setAccessible(true);
        Field loadingIcon = Sorter.class.getDeclaredField("loadingIcon");
        loadingIcon.setAccessible(true);
        Field panel = Sorter.class.getDeclaredField("panel");
        panel.setAccessible(true);

        Class<?> listenerClass = Class.forName("Sorter$SortButtonListener");
        actionPerformed = listenerClass.getDeclaredMethod("actionPerformed", ActionEvent.class);
        actionPerformed.setAccessible(true);
    }

    @Test
    public void testConstructor() {
        try {
            //check high level stuff
            assertEquals("Sorter", sorter.getTitle(), "Wrong title");
            assertEquals(400, sorter.getSize().getWidth(), "Wrong Sorter width");
            assertEquals(300, sorter.getSize().getHeight(), "Wrong Sorter height");
            assertEquals(JFrame.EXIT_ON_CLOSE, sorter.getDefaultCloseOperation(), "Wrong close operation");
            assertTrue(sorter.isVisible(), "Sorter should be visible");
        } catch (Exception e) {
            fail("Sorter constructor is not working correctly");
        }
    }

    @Test
    public void testFrameAndPanel() {
        try {
            //check JFrame
            List<Component> jFrameComponents = Arrays.asList(sorter.getContentPane().getComponents());
            assertEquals(2, jFrameComponents.size(), "Wrong number of components in your JFrame (should be 2)");
            assertEquals(BorderLayout.class, sorter.getLayout().getClass(), "Wrong JPanel layout manager");
            assertEquals(JPanel.class, jFrameComponents.get(0).getClass(), "JPanel not present in your JFrame as expected");
            assertEquals(display.get(sorter), jFrameComponents.get(1), "Display not present in your JFrame as expected");

            //check JPanel
            JPanel panel = (JPanel)jFrameComponents.get(0);
            List<Component> jPanelComponents = Arrays.asList(panel.getComponents());
            assertEquals(FlowLayout.class, panel.getLayout().getClass(), "Wrong JPanel layout manager");
            assertEquals(4, jPanelComponents.size(), "Wrong number of components in your JPanel (should be 4)");

            //label
            assertEquals(JLabel.class, panel.getComponent(0).getClass(), "Wrong first component in JPanel");

            //combo box
            assertEquals(JComboBox.class, panel.getComponent(1).getClass(), "Wrong second component in JPanel");
            JComboBox<String> jComboBox = (JComboBox<String>)panel.getComponent(1);
            assertEquals(3, jComboBox.getItemCount(), "JComboBox should have 3 options");
            assertTrue(jComboBox.isEnabled(), "JComboBox should initially be enabled");
            assertEquals(SELECTION_SORT_TEXT, jComboBox.getItemAt(0), "First JComboBox option incorrrect");
            assertEquals(INSERTION_SORT_TEXT, jComboBox.getItemAt(1), "First JComboBox option incorrrect");
            assertEquals(MERGE_SORT_TEXT, jComboBox.getItemAt(2), "First JComboBox option incorrrect");

            //button
            assertEquals(JButton.class, panel.getComponent(2).getClass(), "Wrong third component in JPanel");
            JButton sortButton = (JButton)panel.getComponent(2);
            assertTrue(sortButton.isEnabled(), "Sort button should initially be enabled");

            //label icon
            assertEquals(JLabel.class, panel.getComponent(3).getClass(), "Wrong fourth component in JPanel");
            assertFalse(panel.getComponent(3).isVisible(), "Loading gif should not initially be visible");

            //check display
            JTextArea d = (JTextArea)jFrameComponents.get(1);
            assertFalse(d.isEnabled(), "Display should be disabled");
     //       assertTrue(d.getText().isEmpty(), "Display contents should initially be empty");
            assertEquals(EmptyBorder.class, d.getBorder().getClass(), "Display boarder incorrect");
        } catch (Exception e) {
            fail("Sorter JFrame, JPanel, and JTextArea not constructed correctly");
        }
    }

    @Test
    public void testSortButtonPress() {
        try {
            List<Component> jFrameComponents = Arrays.asList(sorter.getContentPane().getComponents());
            JPanel panel = (JPanel)jFrameComponents.get(0);
            JButton sortButton = (JButton)panel.getComponent(2);
            JTextArea d = (JTextArea)jFrameComponents.get(1);
            Object listener =  sortButton.getActionListeners()[0];
            ActionEvent e = new ActionEvent(sortButton, 0, sortButton.getText());
            actionPerformed.invoke(listener, e);

            JLabel loadingIcon = (JLabel)panel.getComponent(3);
            assertTrue(loadingIcon.isVisible(), "Loading gif should be visible after button press");
    //        assertEquals(DISPLAY_HEADER_TEXT, d.getText(), "Display text incorrect after button press");
            assertFalse(d.isEnabled(), "Display should be still be disabled after button press");
        } catch (Exception e) {
            fail("Sorter sort button doesn't work correctly");
        }
    }

    @Test
    public void testSelectionSort() {
        numRuns = 9;
        testSort(SELECTION_SORT_TEXT, true);
    }

    @Test
    public void testInsertionSort() {
        numRuns = 9;
        testSort(INSERTION_SORT_TEXT, true);
    }

    @Test
    public void testMergeSort() {
        numRuns = 15;
        testSort(MERGE_SORT_TEXT, false);
    }

    private void testSort(String sort, boolean isQuadratic) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Sorter sorter = new Sorter();
        int[] arr;
        SortRunnable sortRunnable;

        try {
            for(int i=0; i<numRuns; ++i) {
                arr = new int[(int) Math.pow(2,i) * 1000];
                fa(arr);
                sortRunnable = new SortRunnable(sort, arr, sorter);
                es.execute(sortRunnable);
            }

            assertEquals(JTextArea.class, sorter.getContentPane().getComponent(1).getClass(), "GUI display not located correctly");
            JTextArea display = (JTextArea)sorter.getContentPane().getComponent(1);

            assertTrue(checkDisplayUpdates(display), "Execution does not appear to be occurring in correct thread");

            es.shutdown();
            es.awaitTermination(2, TimeUnit.MINUTES);

            assertTrue(checkDisplayTimes(display.getText(), isQuadratic), sort + " runtimes don't check out");
        } catch (Exception e) {
            fail("Sorter constructor is not working correctly");
        }
    }

    private boolean checkDisplayUpdates(JTextArea display) {
        int numDifferences = 0;
        String displayText = display.getText();
        int waitTime = 100;
        for(int i=0; i<numRuns; ++i) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!displayText.equals(display.getText())) {
                displayText = display.getText();
                numDifferences++;
            }
            waitTime+=100;
        }
        //System.out.println("numDifferences: " + numDifferences);
        return numDifferences >= 3;
    }

    private boolean checkDisplayTimes(String displayText, boolean isQuadratic) {
        List<Integer> sortTimes = new ArrayList<Integer>();
        populateSortTimes(sortTimes, displayText);
        return isGrowthAccurate(sortTimes, isQuadratic);
    }

    private boolean isGrowthAccurate(List<Integer> sortTimes, boolean isQuadratic) {
        int numTimesToCheck = 4;
        List<Double> growth = new ArrayList<Double>();
        int i = sortTimes.size()-1;
        double ratio = 0.0;
        int n = 0;
        while(n < numTimesToCheck) {
            growth.add(sortTimes.get(i-n) / (0.0+sortTimes.get(i-n-1)));
            n++;
        }
        for(Double r : growth) {
            ratio += r;
        }
        ratio /= numTimesToCheck;
        if(isQuadratic) {
            return 3.0 < ratio && ratio < 5.0;
        } else {
            return ratio < 3.0;
        }
    }

    private void populateSortTimes(List<Integer> sortTimes, String displayText) {
        Scanner scanner = new Scanner(displayText);
        String line;
        //consume header
        if(scanner.hasNextLine()) {
            scanner.nextLine();
        }
        //consume sort times
        while(scanner.hasNext()) {
            //consume N value
            scanner.next();
            //consume time
            if(scanner.hasNextInt()) {
                sortTimes.add(scanner.nextInt());
            }
        }
    }

    private void fa(int[] n) {
        Random r = new Random();
        for(int i=0; i<n.length; ++i) {
            n[i] = r.nextInt();
        }
    }
}
