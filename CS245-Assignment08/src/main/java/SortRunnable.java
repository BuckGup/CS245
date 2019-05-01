import javax.swing.*;

public class SortRunnable implements Runnable {

    private String sortingAlgorithm;
    private int[] arr;
    private Sorter sorter;
    long startTime;
    long endTime;
    long runTime;
    double n;

    public SortRunnable(String sortingAlgorithm, int[] arr, Sorter sorter) {
        this.sortingAlgorithm = sortingAlgorithm;
        this.arr = arr;
        this.sorter = sorter;
    }

    @Override
    public void run() {


        if (sortingAlgorithm == "Selection Sort"){
            startTime = System.currentTimeMillis();
            Thread.yield();
            SelectionSort.sort(arr);

            endTime = System.currentTimeMillis();
            runTime = endTime - startTime;
        }

        else if(sortingAlgorithm == "Insertion Sort"){
            startTime = System.currentTimeMillis();
            Thread.yield();
            InsertionSort.sort(arr);


            endTime = System.currentTimeMillis();
            runTime = endTime - startTime;
        }
        else {
            startTime = System.currentTimeMillis();
            Thread.yield();
            MergeSort.mergeSort(arr);

            endTime = System.currentTimeMillis();
            runTime = endTime - startTime;
        }



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                sorter.displayResult(arr.length, runTime);

            }
        });

        // sorter.displayResult();


    }
}