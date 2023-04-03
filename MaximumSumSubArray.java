package gr.aueb.cf.ch10miniprojects;

import static java.lang.Math.max;

/**
 * Finds the maximum sum subArray of a given Array. Popular algorithm called Kadane's algorithm.
 * It is based on the simple notion that , while using a for loop to examine the whole Array once
 * (in order to not increase the time complexity), because each next possible array we
 * examine has next number of the array added to it , we don't need to recalculate. We just examine
 * if the past max array , when having the next array element added to it . . . if it has bigger sum than the previous.
 *
 * @author kountouris panagiotis
 */
public class MaximumSumSubArray {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1};

        printSeparator();
        System.out.println("The expanded solution for the Max Sum Sub Array gives us: " + myLargeSumSubArraySolution(arr));
        System.out.println("The short solution with the use of java.lang.math method we learned: " + myCompactSolution(arr));
        printSeparator();
    }

    public static int myLargeSumSubArraySolution(int[] arr) {

        //Κάνουμε initialize το γενικό μέγιστο στην ελάχιστη τιμή που η Java κλάση Integer μπορεί να μας δώσει
        // για να μην παρεμβληθεί στην πρώτη IF σύγκριση που θα βρούμε με το τοπικό μέγιστο.
        int myTotalFinalMaxSoFar = Integer.MIN_VALUE;

        // Τοπικό μέγιστο αρχικοποιούμε στο μηδέν αλλά στην πραγματικότητα στην πρώτη επανάληψη θα πάρει την τιμή
        // του πρώτου στοιχείου του Array. Η πρώτη επανάληψη δηλαδή θεωρεί ως τοπικό μέγιστό μόνο την πρώτη τιμή
        // του πίνακα.
        int myMaxEndingUntilHere = 0;

        //Μια for loop επανάληψης για να έχουμε γραμμική πολυπλοκότητα χρόνου. Να είναι γραμμικής μορφής δηλαδή
        //η αύξηση τον computations που χρειάζεται να γίνουν (όχι ο χρόνος) εάν αυξηθεί το μέγεθος του πίνακα.
        //Σε αυτή την περίπτωση εάν διπλασιαστεί το μέγεθος του πίνακα, θα διπλασιαστεί και ο αριθμός των υπολογισμών
        // που πρέπει να κάνει ο υπολογιστής για να κάνει τους υπολογισμούς. Γραμμική σχέση της μορφής y = ax
        for (int i = 0; i < arr.length; i++) {
            //Στο πρώτο loop θα πάρει την τιμή του πρώτου στοιχείου του πίνακα, από το δεύτερο και μετά
            // προσθέτει το κάθε στοιχείο του πίνακα.
            myMaxEndingUntilHere = myMaxEndingUntilHere + arr[i];
            //Επειδή σε όλα τα πιθανά sub array μέχρι εδώ, προστίθεται το καινούριο arr[i], αρκεί να
            //κάνουμε τη σύγκριση να δούμε εάν με την πρόσθεση αυτού του στοιχείου, το καινούριο υπό εξέταση
            // array θα είναι το καινούριο max sum subArray. Δηλαδή εάν θα συμπεριλαμβάνετε και
            //αυτό το στοιχείο στο max sum subArray.
            if (myTotalFinalMaxSoFar < myMaxEndingUntilHere)
                //Εαν είναι μεγαλύτερο τότε αυτό παίρνει τη θέση του υποθετικού max. Μπορεί να αλλάξει πάλι
                // εάν π.χ. το επόμενο στοιχείο arr[i] δημιουργεί μεγαλύτερο sum sub Array μέχρι να φτάσουμε στο τέλος
                // του array που εξετάζουμε.
                myTotalFinalMaxSoFar = myMaxEndingUntilHere;
            // Εδώ εάν το subArray μέχρι το σημείο ελέγχου είναι μικρότερο από το μηδέν, το θέτουμε με μηδέν.
            // Εδώ παρουσιάζετε και το πρόβλημα με αυτήν μεθοδολογία για τον αλγόριθμο του Kadane.
            // Μόνο πρόβλημα εδώ, εάν υπάρξουν αρκετοί αρνητικοί αριθμοί έτσι ώστε το άθροισμα του
            // sub-array να είναι αρνητικός αριθμός.
            // Εάν υπάρχουν μόνο αρνητικοί αριθμοί θα επιστρέψει απλά όλο το array ! :-D
            if (myMaxEndingUntilHere < 0)
                myMaxEndingUntilHere = 0;
        }
        return myTotalFinalMaxSoFar;
    }

    /**
     * Alternative way with the use of the Math method that java.lang provides us.
     * @param arr       The given Array
     * @return          The max sum sub array
     */
    public static int myCompactSolution(int[] arr){
        int myMaxEndingUntilHere = Integer.MIN_VALUE;

        //Με τη βοήθεια του java.lang.math που μάθαμε
        for (int i = 0; i < arr.length; i++) {
            myMaxEndingUntilHere = max((myMaxEndingUntilHere + arr[i]) , arr[i]);
        }
        return myMaxEndingUntilHere;
    }

    public static void printSeparator(){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
}
