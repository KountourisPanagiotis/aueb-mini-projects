package gr.aueb.cf.ch10miniprojects;

import java.util.Scanner;

/**
 * Theater booking management application. Uses characters for columns and numbers for rows. Example: Seat C2 is at second
 * row , third column. The whole theater has 30 rows and 12 columns. Has methods for booking a row if it is not
 * already booked. Method for booking cancellation for booked seats.
 */
public class TheaterSeatManagementApp {

    public static void main(String[] args) {
        boolean[][] seats = new boolean[12][30];
        printCinemaLabel();
        unBookAllSeats(seats);
        printMenu(seats);
    }

    public static void printMenu(boolean[][] seats){
        int selection = 0;

        System.out.print("\nΚαλωσήρθατε στο σύστημα booking θέσεων του Coding Factory Cinema!! ");

        do {
            Scanner in = new Scanner(System.in);
            System.out.println("\nΠαρακαλώ επιλέξτε ένα από τα παρακάτω: ");

            System.out.printf("1. Εμφάνιση της κάτοψης του θεάτρου %n2. Έλεγχος διαθεσιμότητας για θέση και κράτηση %n3. Διαγραφή κράτησης %n4. Διαγραφή όλων των κρατήσεων %n5. Έξοδος%n");
            if(in.hasNextInt()){
                selection = in.nextInt();
            }
            if(selection == 1){
                printVisualSeparator();
                System.out.println("Επιλέξατε εμφάνιση κάτοψης.");
                printVisualSeparator();
                revealCinemaDiagram(seats);
            }else if(selection == 2){
                System.out.println("Επιλέξατε διαθεσιμότητα και κράτηση. Δώστε στήλη και σειρά θέσης.");
                book(getUserInput(),seats);
            }else if(selection == 3){
                System.out.println("Επιλέξατε διαγραφή κράτησης  Δώστε στήλη και σειρά θέσης.");
                cancel(getUserInput(),seats);
            }else if(selection == 4){
                printVisualSeparator();
                System.out.println("Επιλέξατε διαγραφή όλων των κρατήσεων.");
                unBookAllSeats(seats);
                System.out.println("Επιτυχημένη διαγραφή όλων των κρατήσεων");
            }else if(selection <= 0 | selection>5){
                printVisualSeparator();
                System.out.println("Η επιλογή που δώσατε δεν ήταν σωστή!!!!!");
            }

        }while(selection != 5);
        System.out.println("Ευχαριστούμε πολύ!");
    }

    /**
     * Service layer. Manages the booking process , and provides feedback at the user. Has as input the seat
     * of choice as an array of int and the theater as an array of boolean.
     * @param seat          The column-row seat selection as an array with length of two.
     * @param seats         The theater seats as a boolean array (columns , rows)
     */
    public static void book(int[] seat , boolean[][] seats) {
        if(seatIsAvailable(seat,seats)) {
            seats[seat[0]][seat[1]] = true;
            printVisualSeparator();
            System.out.println("Η θέση : [" + ((char)( (seat[0]) + 65) ) + "][" + (seat[1] + 1) + "] ήταν διαθέσημη και μόλις την κλείσατε!");
            printVisualSeparator();
        }else{
            printVisualSeparator();
            System.out.println("Συγνώμη η θέση: [" + ((char)( (seat[0]) + 65) ) + "][" + (seat[1] + 1) + "] είναι ήδη κλεισμένη !");
            printVisualSeparator();
        }
    }

    /**
     * Returns true if the selected seat is available. Has as signature an array with the selected seat and
     * an array with the theater seats.
     * @param seat          The selected theater seat.
     * @param seats         The theater seats as a boolean array (columns , rows)
     * @return              True if the selected seat is available(if it is false)
     */
    public static boolean seatIsAvailable(int[] seat , boolean[][] seats){
        boolean available = false;

        if (!seats[seat[0]][seat[1]]){
            available = true;
        }
        return  available;
    }

    /**
     * Takes as signature an array with the selected seat column and rows as the two numbers in an array.
     * Takes as signature the theater seats as a boolean array.
     * @param seat          The selected theater seat.
     * @param seats         The theater seats as a boolean array (columns , rows)
     */
    public static void cancel(int[] seat , boolean[][] seats) {
        if(!seatIsAvailable(seat,seats)) {
            seats[seat[0]][seat[1]] = false;
            printVisualSeparator();
            System.out.println("Η θέση : [" + ((char) ((seat[0]) + 65) ) + "][" + (seat[1] + 1)+ "] ήταν κλεισμένη και μόλις την ακυρώσατε!");
            printVisualSeparator();
        }else{
            printVisualSeparator();
            System.out.println("Συγνώμη η θέση: [" + ((char) ((seat[0]) + 65)) + "][" + (seat[1] + 1) + "] ΔΕΝ είναι κλεισμένη");
            printVisualSeparator();
        }
    }

    /**
     * Returns the two user inputs as an array with size of two. First for column , second for row.
     * Handles exception in order to restore the state and provides message in case of invalid input.
     * @return      The array with the two user inputs.
     */
    public static int[] getUserInput() {
        int[] userInput = new int[2];
        String letterNumber = "";
        boolean invalidInput;
        Scanner in = new Scanner(System.in);

        do{
            try{
                invalidInput = false;
                letterNumber = in.nextLine().trim().toUpperCase();
                userInput[0] = letterNumber.charAt(0) - 65;
                letterNumber = letterNumber.substring(1).trim();
                userInput[1] =  Integer.parseInt(letterNumber) - 1;
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Λάθος εισαγωγή. Παρακαλώ δώστε ξανά τα στοιχεία της θέσης: ");
                invalidInput = true;
            }

            if(userInput[0] > 11 || userInput[0] < 0 || userInput[1] < 0 || userInput[1] > 29) {
                System.out.println("Λάθος εισαγωγή. Παρακαλώ δώστε ξανά τα στοιχεία της θέσης: ");
                invalidInput = true;
            }

        }while(invalidInput);

        return userInput;
    }

    /**
     * Fills the seats array of booleans with false. indicating the empty bookings.
     * @param seats     The theater seats as a boolean array (columns , rows)
     */
    public static void unBookAllSeats(boolean[][] seats) {
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = false;
            }
        }
        printVisualSeparator();
    }

    /**
     * Prints on console (std output) the current state of the cinema seats as an array of booleans
     * @param seats     The theater seats as a boolean array (columns , rows)
     */
    public static void revealCinemaDiagram(boolean[][] seats) {
        for (int i = 0; i < seats.length; i++){
            System.out.println();
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print("["+ seats[i][j]+ "]");
            }
        }
    }

    /**
     * Prints a visual separator on the std output wherever it is needed.
     */
    public static void printVisualSeparator() {
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Prints the Cinema label on the std output
     */
    public static void printCinemaLabel() {
        System.out.printf("    ╔═══╗    ╔═══╗    ╔═══╗    ╔══╗    ╔═╗ ╔╗    ╔═══╗         ╔═══╗    ╔═══╗    ╔═══╗    ╔════╗    ╔═══╗    ╔═══╗    ╔╗  ╔╗        ╔═══╗    ╔══╗    ╔═╗ ╔╗    ╔═══╗    ╔═╗╔═╗    ╔═══╗\n" +
                "    ║╔═╗║    ║╔═╗║    ╚╗╔╗║    ╚╣╠╝    ║║╚╗║║    ║╔═╗║         ║╔══╝    ║╔═╗║    ║╔═╗║    ║╔╗╔╗║    ║╔═╗║    ║╔═╗║    ║╚╗╔╝║        ║╔═╗║    ╚╣╠╝    ║║╚╗║║    ║╔══╝    ║║╚╝║║    ║╔═╗║\n" +
                "    ║║ ╚╝    ║║ ║║     ║║║║     ║║     ║╔╗╚╝║    ║║ ╚╝         ║╚══╗    ║║ ║║    ║║ ╚╝    ╚╝║║╚╝    ║║ ║║    ║╚═╝║    ╚╗╚╝╔╝        ║║ ╚╝     ║║     ║╔╗╚╝║    ║╚══╗    ║╔╗╔╗║    ║║ ║║\n" +
                "    ║║ ╔╗    ║║ ║║     ║║║║     ║║     ║║╚╗║║    ║║╔═╗         ║╔══╝    ║╚═╝║    ║║ ╔╗      ║║      ║║ ║║    ║╔╗╔╝     ╚╗╔╝         ║║ ╔╗     ║║     ║║╚╗║║    ║╔══╝    ║║║║║║    ║╚═╝║\n" +
                "    ║╚═╝║    ║╚═╝║    ╔╝╚╝║    ╔╣╠╗    ║║ ║║║    ║╚╩═║        ╔╝╚╗      ║╔═╗║    ║╚═╝║     ╔╝╚╗     ║╚═╝║    ║║║╚╗      ║║          ║╚═╝║    ╔╣╠╗    ║║ ║║║    ║╚══╗    ║║║║║║    ║╔═╗║\n" +
                "    ╚═══╝    ╚═══╝    ╚═══╝    ╚══╝    ╚╝ ╚═╝    ╚═══╝        ╚══╝      ╚╝ ╚╝    ╚═══╝     ╚══╝     ╚═══╝    ╚╝╚═╝      ╚╝          ╚═══╝    ╚══╝    ╚╝ ╚═╝    ╚═══╝    ╚╝╚╝╚╝    ╚╝ ╚╝\n" +
                "                                                                                                                                                                                       \n");

    }

}
