package gr.aueb.cf.ch10miniprojects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Disclaimer : Due to random generation of ints it might not find adequate filtered group on the first run !
 *
 * Int filtering Application.(Firsts makes a file with 49 random ints).
 * Then reads int numbers from that file with values from 1 to 49. Creates all possible combinations of 6 numbers
 * and filters them so that they fill the following criteria: 1) Consists of max 4 even numbers. 2) consists of max 4 odd numbers
 * 3) consists at most two consecutive numbers. 4) consists at most three numbers with same last digits. 5)Has at most 3 numbers
 * in same range of magnitude.
 *
 * @author Kountouris Panagiotis
 */
public class IntFilteringApp {
    public static void main(String[] args) {
        int[] arr;

        //Filling File with 49 random ints with default append (false). (new ints every time)
        messageRandomFill();
        try {
            fillFileRandomInts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //Reading from that file
        try {
            arr = Arrays.copyOf(readFile() , readFile().length);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nBefore sorting:");
        printArrayOnConsole(arr);
        System.out.println();
        System.out.println("\nAfter sorting: ");
        printArrayOnConsole(sortMyArray(arr));
        System.out.println();

        //Filtering and printing
        System.out.println("\nGroup of 6 that meet the criteria:");
        filterAndPrintSix(arr);

    }

    /**
     * Refills each time from the start the file with 49 random ints. (append = FALSE).
     * Prints them also for the user.
     * @throws FileNotFoundException    Throws file not found exception.
     */
    public static void fillFileRandomInts() throws FileNotFoundException {
        try (PrintStream randomNumbers = new PrintStream(new FileOutputStream("C:/tmp/randomNumbers.txt"))) {
            for(int i = 1; i <= 49; i++) {
                randomNumbers.printf(getRandomNumber() + " ");
            }
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
            throw e1;
        }
    }

    /**
     * Reads ints from a file and returns an array of ints.
     * @return                              the int array.
     * @throws FileNotFoundException        throws file not found exception.
     */
    public static int[] readFile() throws FileNotFoundException {
        int[] arr = new int[49];

        try (Scanner intFile = new Scanner(new File("C:/tmp/randomNumbers.txt"))) {
            for (int i = 0; i < 49; i++) {
                if (intFile.hasNextInt()) {
                    arr[i] = intFile.nextInt();
                }
            }
        }catch(FileNotFoundException e2){
            e2.printStackTrace();
            throw e2;
        }

        return arr;
    }

    /**
     * Sorts the array. Returns the array.
     * @param arr       the array to sort.
     * @return          the sorted array.
     */
    public static int[] sortMyArray(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    /**
     * Groups from the array 6 numbers each time and filters them. Prints the ones that do NOT contain
     * 4 even , 4 odd , 4 continuous , 3 with same ending number or 3 in the same range of tens , at the
     * console (Standard Output)
     * @param arr       the array to be examined.
     */
    public static void filterAndPrintSix(int[] arr) {
        final int EVEN_ODD = 4;
        final int CONTINUOUS = 2;
        final int IDENTICAL_MOD = 3;
        final int SAME_RANGE = 3;
        int[] sixArray = new int[6];

        for( int i = 0, j = 1 , k = 2 , l = 3 , m = 4 , n = 5; n < arr.length; i++ ,j++ , k++ , l++ , m++ , n++ ){
            sixArray[0] = arr[i];
            sixArray[1] = arr[j];
            sixArray[2] = arr[k];
            sixArray[3] = arr[l];
            sixArray[4] = arr[m];
            sixArray[5] = arr[n];

            if(!isEven(sixArray,EVEN_ODD)  && (!isOfOdd(sixArray,EVEN_ODD)) && (!isContinuous(sixArray,CONTINUOUS))
                    && (!isSameEnding(sixArray,IDENTICAL_MOD)) && (!isSameTen(sixArray,SAME_RANGE)) ){

                printArrayOnConsole(sixArray);
            }
        }
    }

    /**
     * Takes as input an array and an int threshold. Returns true if the array
     * contains more even numbers than the given threshold.
     * @param arr               The given array.
     * @param threshold         The given threshold.
     * @return                  True if it contains more even ints than the threshold.
     */
    public static boolean isEven(int[] arr , int threshold) {
        int count = 0;

        for (int i : arr) {
            if ( i % 2 == 0) count++;
        }
        return (count > threshold);
    }

    /**
     * Takes as input an array and an int threshold. Returns true if the array
     * contains more odd numbers than the given threshold.
     * @param arr               The given int array.
     * @param threshold         The given int threshold.
     * @return                  True if it contains more odd ints than the threshold.
     */
    public static boolean isOfOdd(int[] arr , int threshold) {
        int count = 0;

        for(int i : arr) {
            if( i % 2 != 0) count++;
        }

        return (count > threshold);
    }

    /**
     * Takes as input an array and an int threshold. Returns true if the array
     * contains more than two continuous numbers than the given threshold (e.g. 13 14 15).
     * @param arr           The given int array.
     * @param threshold     The given int threshold.
     * @return              True if array contains more than two continuous numbers.
     */
    public static boolean isContinuous(int[] arr , int threshold) {
        int count = 0;
        int maxCount = 0;

        for (int i = 0; i < arr.length - 1; i++) {
                if(arr[i] == ((arr[i + 1]) - 1) ){
                    count++;
                    if(count >= maxCount){
                        maxCount = count;
                    }
                }else{
                    count = 0;
                }
        }

        return (maxCount >= threshold);
    }

    /**
     * Takes as input an array and an int threshold. Returns true if the array contains
     * more numbers with the same ending digit than the given threshold.
     * @param arr           The given int array.
     * @param threshold     The given int threshold.
     * @return              True if the array contains more numbers
     *                      with same suffix than the threshold.
     */
    public static boolean isSameEnding(int[] arr , int threshold) {
        int[] count = new int[10];
        boolean maxSameLastDigits = false;

        for (int i = 0; i < arr.length; i++) {
            count[(arr[i] % 2)] += 1;
        }
        for (int i : count){
            if (i > threshold){ maxSameLastDigits = true;}
        }

        return maxSameLastDigits;
    }

    /**
     * Takes as input an array and an int threshold. Returns true if the
     * array contains more numbers in the same number range of magnitude
     * than the given threshold.
     * @param arr           The given int array.
     * @param threshold     The given int threshold.
     * @return              True if the array contains more numbers
     *                      in same magnitude than given threshold.
     */
    public static boolean isSameTen(int[] arr , int threshold) {
        int[] count = new int[5];
        boolean hasInSameRange = false;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] >= 0 && arr[i] <= 9){
                count[0]++;
            }else if(arr[i] >= 10 && arr[i] <= 19){
                count[1]++;
            }else if(arr[i] >= 20 && arr[i] <= 29){
                count[2]++;
            }else if(arr[i] >= 30 && arr[i] <= 39){
                count[3]++;
            }else if(arr[i] >= 40 && arr[i] <= 49){
                count[4]++;
            }
        }

        for(int i : count){
            if( i > threshold){
                hasInSameRange = true;
            }
        }

        return hasInSameRange;
    }

    /**
     * Prints an array on console (std output)
     * @param arr       The given array.
     */
    public static void printArrayOnConsole(int[] arr) {
        System.out.println();
        for(int i : arr){
            System.out.print(i + " ");
        }
    }

    /**
     * Generates a random number within the range of 1 and 49.
     * @return          The random int number.
     */
    public static int getRandomNumber(){

        return ((int)(Math.random() * 49) + 1);
    }

    /**
     * User message for file writing and file reading.
     */
    public static void messageRandomFill() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Filled the .txt file with random 49 int numbers. Proceeds to Reading and sorting. . .");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

}
