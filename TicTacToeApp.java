package gr.aueb.cf.ch10miniprojects;

import java.util.Scanner;
/**
 * A simple Tic Tac Toe App.
 * Explanation: Two players play in turns and mark a 3x3 Array with X and O. One symbol for each player.
 * Winner is the one that manages to fill three consecutive elements with his symbol
 * in any dimension of the Array (horizontally,vertically,diagonally). Input with two ints, first for row second
 * for column. example: 2 3 -> second row , third column.
 *
 * @author Kountouris Panagiotis
 */
public class TicTacToeApp {
    final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
//        char[][] board = {{'-', 'O', 'X'}, {'X', 'X', 'O'}, {'O', 'X', 'O'}}; // TIE TEST board ready for you. One move away from a Tie :D
        int[] choice;
        int turnOfPlayerNo = 1;
        int winner = 0;

        printRules();
        do {
            userInputKeysReminder();
            printPlayerTurnReminder(turnOfPlayerNo);
            printBoard(board);

            do{

                choice = playerInput();
            }while(!inputIsValid(choice));

             if(!playerWantsToQuit(choice)){
                 if(moveIsValid(board , choice)){
                     playerMoveOnBoard(board, turnOfPlayerNo, choice);
                 }else{
                     invalidMoveMessage();
                     do{
                         provideValidMoveMessage();
                         do{
                             provideValidInputMessage();
                             choice = playerInput();
                         }while(!inputIsValid(choice) || !playerWantsToQuit(choice));
                     }while(!moveIsValid(board,choice) || !playerWantsToQuit(choice));
                    if(!playerWantsToQuit(choice)){
                        playerMoveOnBoard(board,turnOfPlayerNo,choice);
                    }
                 }
                    //Endgame conditions
                 if(threeConsecutiveCondition(board)){
                     winner = turnOfPlayerNo;
                     winnerMessage(winner);
                     printBoard(board);
                 }else if (checkForTie(board)){
                     tieMessage();
                     printBoard(board);
                 }
             }else{
                choseToExitMessage();
             }
            turnOfPlayerNo = (turnOfPlayerNo == 1) ? 2 : 1;

        } while (!playerWantsToQuit(choice) && !noMoreSpacesForMoves(board) && !threeConsecutiveCondition(board) && (winner == 0) && !checkForTie(board));
        goodbyeMessage();

        in.close();
    }

    /**
     * Returns the player input for the game as an int[] array. int[0] for Vertical , int[1] for horizontal.
     * Checks if the input is valid with state-testing. Prompts message if invalid and consumes the tokens in the lines.
     * @return      One-dimensional array with the two user input ints in positions 0 and 1 of the Array.
     */
    public static int[] playerInput() {
        int[] playerInput = {4 , 4};
        do{
            if(in.hasNextInt()){
                playerInput[0] = in.nextInt();
            }else{
                provideValidInputMessage();
                in.nextLine();
            }

            if(in.hasNextInt()){
                playerInput[1] = in.nextInt();
            }else{
                provideValidInputMessage();
                in.nextLine();
            }
        }while(playerInput[0] == 4 || playerInput[1] ==4 );

        return playerInput;
    }

    /**
     * Returns true if the player input is valid even more specifically for the rules of the game and prompts messages.
     * Takes as input a one-dimensional array with the user input in positions 0 and 1 (int).
     * @param input     The array with user input.
     * @return          True if user input is valid for this game specifically.
     */
    public static boolean inputIsValid(int[] input){
        int input1 = input[0];
        int input2 = input[1];
        boolean validInput = false;

        if( (input1 == 1) || (input1 ==2) || (input1 ==3) || (input1 == 0)){
            if ( (input2 == 1) || (input2 ==2) || (input2 ==3) || (input2 == 0)){
                validInput = true;
            }else{
                System.out.println("Second input was invalid! Try again! Numbers from 1 to 3");
            }
        }else{
            System.out.println("First input was invalid! Try again! Numbers from 1 to 3");
        }

        return validInput;
    }

    /**
     * Returns true if the move is valid depending on the current state of the board.
     * @param board             The board as a two-dimensional array.
     * @param playerInput       The player input as position 0 and 1 of an array.
     * @return                  Returns true if the move is valid
     */
    public static boolean moveIsValid(char[][] board , int[] playerInput){
        boolean validMove = true;
        if(board[playerInput[0] - 1][playerInput[1] - 1] != '-' || board[playerInput[0] - 1][playerInput[1] - 1] == 'X' || board[playerInput[0] - 1][playerInput[1] - 1] == 'O' ){
            validMove = false;
        }

        return validMove;
    }

    /**
     * Marks the array as a player move. Takes as input the board array , the number of current player and the
     * array with the 2 player int choices in position 0 (vertical choice) and position 2 (horizontal choice).
     * @param board         the board array.
     * @param player        number of player.
     * @param choice        Input array with player choice of vertical and horizontal in position 0 and 1.
     */
    public static void playerMoveOnBoard(char[][] board, int player, int[] choice) {
        boolean moveCompleted = false;
        char currentPlayerSymbol = ' ';

        if(player == 1) currentPlayerSymbol = 'X';
        if(player == 2) currentPlayerSymbol = 'O';

        while (!moveCompleted) {
            if (board[choice[0] - 1][choice[1] - 1] != 'X' || board[choice[0] - 1][choice[1] - 1] != 'O') {
                board[choice[0] - 1][choice[1] - 1] = currentPlayerSymbol;
                moveCompleted = true;
            } else {
                System.out.println("Wrong move , square already marked ,please try again");
                printBoard(board);
            }
        }
    }

    /**
     * Returns true if there is no more space in the board array for player moves. Takes as input the
     * two-dimensional board array.
     * @param board     The board array.
     * @return          True if there is no more space for player moves.
     */
    public static boolean noMoreSpacesForMoves(char[][] board){
        final char blank = '-';
        int spacesLeft = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == blank){
                    spacesLeft++;
                }
            }
        }

        return (spacesLeft == 0);
    }

    /**
     * Returns true if board is full and no three consecutive player symbols are found. It is a Tie.
     * @param board     the board array.
     * @return          true if it is a Tie.
     */
    public static boolean checkForTie(char[][] board){
        boolean tie = false;

        if(!threeConsecutiveCondition(board) && noMoreSpacesForMoves(board)){
            tie = true;
        }

        return tie;
    }

    /**
     *  Returns true if three consecutive player symbols (not blanks) , are found in the board , horizontally ,
     *  vertically or the diagonals.
     * @param board     the input array board.
     * @return          true if three consecutive player symbols are found.
     */
    public static Boolean threeConsecutiveCondition(char[][] board) {
        final char[] blank = {'-'};
        boolean winnerFound = false;

        for (int i = 0; i < board.length; i++) {

            if(  (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != blank[0]) ){ //horizontals check without blank check
                winnerFound = true;
            }
            if((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && (board[0][i] != blank[0])){ //vertical check without blank check
                winnerFound = true;
            }
            if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[1][1] != blank[0])){ //top-left to bottom-right diagonal check
                winnerFound = true;
            }
            if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && (board[1][1] != blank[0])){ //top-right to bottom-left diagonal check
                winnerFound = true;
            }
        }
        return winnerFound;
    }

    /**
     * Returns true if the player wants to quit the game. Takes as input an Array that we use for the
     * two input numbers of the user. If he inputs zero it returns true.
     * @param quit  user input Array.
     * @return      true if he wants to quit.
     */
    public static boolean playerWantsToQuit(int[] quit){
        boolean wantsToQuit = false;

        if(quit[0] == 0 || quit[1] == 0){
            wantsToQuit = true;
        }

        return wantsToQuit;
    }

    /**
     * Prints the current state of the two-dimensional array board for the users to see.
     * @param board     the board as a two-dimensional array.
     */
    public static void printBoard(char[][] board) {

        System.out.println("                                        1  2  3");
        for (int i = 0; i < board.length; i++) {
            System.out.printf("                                     %d " , i + 1);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
    }

    /**
     * Prints the rules at the start of the game.
     */
    public static void printRules() {
        System.out.printf("%n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");
        System.out.println("Welcome to a simple Tic Tac Toe Game!");
        System.out.println("A simple Tic Tac Toe App.\n" +
                "Explanation: Two players play in turns and mark a 3x3 Array with X and O. One symbol for each player.\n" +
                "Winner is the one that manages to fill three consecutive elements with his symbol\n" +
                "in any dimension of the Array (horizontally,vertically,diagonally). Input with two ints, first for row second\n" +
                "for column. Numbers from 1 to 3. Example: 2 3 -> second row , third column. Type 0 for Exit\n" +
                "Player 1 starts GO !!!");
    }

    /**
     * Prints a brief reminder of the user input key options.
     */
    public static void userInputKeysReminder(){
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("(Inputs reminder: First int for row number , second int for column (Numbers from 1 to 3). Type 0 0 for Exit)");
    }

    /**
     * Prints a message to remind if it is player 1 or player 2 turn.
     * @param player    the number of current player.
     */
    public static void printPlayerTurnReminder(int player){
        System.out.println("\n                                 IT IS PLAYER " + player + " TURN !!");
    }

    /**
     * Prints a message for the winning player.
     * @param player    the number of winning player.
     */
    public static void winnerMessage(int player) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=  PLAYER " + player + " IS THE WINNER!!!!!!!! CONGRATULATIONS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=") ;
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public static void goodbyeMessage(){
        System.out.println("Thank you! Goodbye!");
    }

    /**
     * Prints a goodbye message if player chooses to exit.
     */
    public static void choseToExitMessage(){
        System.out.println("You chose to exit. Thank you for playing!");
    }

    /**
     * Prints an invalid move message.
     */
    public static void invalidMoveMessage(){
        System.out.println("                                 !!!!!!!!!! Invalid MOVE. please try again!");
        userInputKeysReminder();
    }

    /**
     * Prints a prompt for the player to provide a valid input.
     */
    public static void provideValidInputMessage() {
        System.out.println("Please provide a valid INPUT ");
    }

    /**
     * Prints a prompt for player to play a valid move.
     */
    public static void provideValidMoveMessage() {
        System.out.println("Please provide a valid MOVE.");
    }

    /**
     * Prints a message for players when they TIE.
     */
    public static void tieMessage(){
        System.out.println("                       !!!!!!! It is a TIE ! Everyone Wins !!!!!!!!!");
    }
}












