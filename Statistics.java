/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Rodas Nega
* @version 1.0
* @since   2022-09-27
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
* This is the statistics program.
*/
final class Statistics {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Statistics() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mean of the integers
    */
    public static float mean(final Integer[] arrayOfIntegers) {
        float sum = 0;
        final float arraySize = arrayOfIntegers.length;
        for (int loopCounter = 0; loopCounter
                        < arraySize; loopCounter++) {
            sum += arrayOfIntegers[loopCounter];
        }
        final float mean = sum / arraySize;
        return mean;
    }
    // https://stackoverflow.com/questions/11955728/
    //     how-to-calculate-the-median-of-an-array

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the median of the integers
    */
    public static float median(final Integer[] arrayOfIntegers) {
        float median = 0;
        if (arrayOfIntegers.length % 2 == 0) {
            median = ((float) arrayOfIntegers[arrayOfIntegers.length / 2]
                + (float) arrayOfIntegers[arrayOfIntegers.length / 2 - 1]) / 2;
        } else {
            median = (float) arrayOfIntegers[arrayOfIntegers.length / 2];
        }
        return median;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        Integer tempNumber;
        Integer elementNumber = 0;

        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("./", args[0]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
                elementNumber += 1;
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer[] arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        Arrays.sort(arrayOfNumbers);

        System.out.println("\nCalculating stats...");
        final float mean = mean(arrayOfNumbers);
        final float median = median(arrayOfNumbers);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);

        System.out.println("\nDone.");
    }
}

