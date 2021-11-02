package Recursion;

import java.util.ArrayList;

import cs1c.FHsort;

/**
 * A java class to analyze the efficiency of different recursion limits along with
 * different array sizes. The output of this program can then be converted to Excel format enabling
 * us to print tables.
 *
 * @author Ali Zargari
 */
public class RecursionAnalysis {


    /**
     *
     * Analyzes different array sizes and recursion limits for the quicksort(QS) algorithm.
     * Prints a comma seperated list of time values corresponding to the recursion limits and array sizes,
     * in a format that can easily be converted in excel.
     *
     * @param size the size of the array
     */
    public void analyzeQSLimit(int size) {
        //an unsorted array to be populated with random values.
        Integer[] unsorted = new Integer[size];

        //fills an array with randomly generated numbers.
        for (int i = 0; i < unsorted.length; i++) {
            unsorted[i] = ((int) (Math.random() * 10020000));
        }

        //ArrayList to help with the creation of a CSV output.
        ArrayList<Double> orderedTime = new ArrayList<>();

        // forloop to go through all recursion limits in increments of 2. [2, 300]
        for (int i = 2; i <= 300; i += 2) {
            double[] timeSum = new double[3];

            // Will test the given recursion limit 3 times
            for (int j = 0; j < 3; j++) {
                Integer[] sorted = unsorted.clone();

                FHsort.setRecursionLimit(i);

                //start timer
                long startTime = System.nanoTime();
                //quick sort
                FHsort.quickSort(sorted);
                //end timer
                long endTime = System.nanoTime();

                //calculate time, store in array.
                timeSum[j] = (endTime - startTime) / (Math.pow(10, 9));

            }

            //calculate the average of the 3 time totals, add it to the list.
            double avgTime = (timeSum[0] + timeSum[1] + timeSum[2]) / 3;
            orderedTime.add(avgTime);


        }

        //print header
        for (int i = 0; i < 300 && size == 20000; i+=2){
            if(i == 0){
                System.out.print("Recursion Limits, ");
            }
            System.out.print (i+2 + ", ");
        }
        System.out.println();

        //print the result
        CSV_PRINT(orderedTime, "array size: " + size);


    }

    /**
     * Takes in an array as a parameter and prints it in CSV format. Function made specifically for 
     * making the resultant spreadsheet graph easier to interpret by using the title parameter.
     *
     * @param arr list to be printed.
     * @param title  descriptive title for list being printed before the list
     */
    public void CSV_PRINT(ArrayList arr, String title) {

        String str = "";

        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i) + ", ";
        }

        System.out.print(title + ", " + str);
    }


    /**
     * Tests the RescursionAnalysis with multiple array sizes ranging from 20,000 to 10,020,000.
     *
     */
    public static void main(String[] args) {

        RecursionAnalysis analyze = new RecursionAnalysis();

        //for-loop that tests recursion limits array sizes from min: 20,000 to max:10,020,000
        // which will be a total of 20 intervals. I chose the max as 10,020,000 because it will give
        //nicer intervals, making it easier to make sense of the resulting graphs.
        for (int i = 20000; i < 10020000; i += (10000000 / 20)) {
            analyze.analyzeQSLimit(i);
        }

    }

}
