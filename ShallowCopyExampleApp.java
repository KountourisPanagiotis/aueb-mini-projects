package gr.aueb.cf.ch10miniprojects;

/**
 * Exhibition application for shallow-copy and deep-copy.
 * Shallow-copies a two-dimensional array to a second one. Make changes to the second one and prints on console (std output) the effect on the first one.
 * Deep-copies again the first two-dimensional array to a third one. Make changes to the third one and prints on console the effect on the first one.
 */
public class ShallowCopyExampleApp {

    public static void main(String[] args) {
        int[][] arr1 = {{ 1 , 1 , 1 } ,{ 1 , 1 , 1 },{ 1 , 1 , 1 },{ 1 , 1 , 1 },{ 1 , 1 , 1 },{ 1 , 1 , 1 },{ 1 , 1 , 1 },{ 1 , 1 , 1 },{ 1 , 1 , 1 },{ 1 , 1 , 1 }};
        int[][] arr2;
        int[][] arr3;

        System.out.println("=-=-=-=-=-=- Shallow Copy -=-=-=-=-=-=-=");
        arr2 = shallowCopy(arr1);
        arr2[0][2] = 999;
        printArr(arr1);
        shallowCopyMessage();
        System.out.println("=-=-=-=-=-=-=-=- Deep Copy -=-=-=-=-=-=-=");
        arr3 = deepCopy(arr1);
        arr3[1][2] = 1234;
        printArr(arr1);
        deepCopyMessage();
    }

    /**
     * Returns a shallow copy of a two-dimensional array. Only the references are copied.
     * @param arr       The two-dimensional array to be copied.
     * @return          The shallow copied two-dimensional array.
     */
    public static int[][] shallowCopy(int[][] arr) {
        int[][] arr2 = arr;

        return arr2;
    }


    /**
     * Returns a deep copy of a two-dimensional array. new array initialisation and
     * each primitive int is copied individually.
     * @param arr       The given two-dimensional array.
     * @return          The deep copied two-dimensional array.
     */
    public static int[][] deepCopy(int[][] arr) {
        int[][] arrayDeepCopy = new int[arr.length][arr[1].length];

        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arrayDeepCopy[i][j] = arr[i][j];
            }
        }
        return arrayDeepCopy;
    }

    /**
     * Prints the input two-dimensional array on console std output.
     * @param arr       The given array
     */
    public static void printArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(" [" + arr[i][j] + "]");
            }
        }
    }

    /**
     * Shallow copy message for the user on console std output.
     */
    public static void shallowCopyMessage(){
        System.out.println("\nΕδώ βλέπουμε τον αρχικό πίνακα arr1.\nΜετά το shallow copy και αλλαγές σε στοιχείο του δεύτερου πίνακα (arr2[0][2] = 999), " +
                "\nβλέπουμε πως έχει αλλάξει και ο αρχικός πίνακας arr1.");
    }

    /**
     * Deep copy message for the user on console std output.
     */
    public static void deepCopyMessage(){
        System.out.println("\nΕδώ βλέπουμε πάλι τον αρχικό πίνακα arr1.\nΜετά το deep copy και αλλαγές σε στοιχείο του καινούριου πίνακα (arr3[1][2] = 1234) , " +
                "\nδεν βλέπουμε να έγιναν αυτή την φορά περαιτέρω αλλαγές στον αρχικό πίνακα arr1 (Μόνο την αλλαγή που έκανε το shallow copy προηγουμένως).");
    }

}
