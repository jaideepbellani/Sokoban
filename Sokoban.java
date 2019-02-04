//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sokoban 
// Files: Sokoban.java
// Course: CS 200 Fall 2018
//
// Author: Zeel Doshi 
// Email: zdoshi@wisc.edu 
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Jaideep Bellani
// Partner Email: jbellani@wisc.edu
// Lecturer's Name: Marc Renault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: Team Lab Team Lab materials helped to guide me write loops 
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Sokoban {

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output to the console and the user is prompted
     * again.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        int userInput; // user input expressed as an integer

        System.out.print(prompt); // prints out prompt for user to enter input
        while (sc.hasNextLine()) {
            if (sc.hasNextInt()) {
                userInput = sc.nextInt();
                if (userInput >= min && userInput <= max) { // makes sure input inputed is valid
                    return userInput;
                } else {
                    System.out.println("Invalid value.");
                    System.out.print(prompt);
                    sc.nextLine();
                }
            } else { // if input does not contain an integer, it is invalid and prompts user again
                System.out.println("Invalid value.");
                System.out.print(prompt);
                sc.nextLine();
            }
        }

        return -99;
    }

    /**
     * Prompts the user for a char value by displaying prompt. Note: This method should not be a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        String userStr; // entire user input
        char userChar; // only the first character of user input
        System.out.print(prompt); // prints out prompt for user to enter a character
        userStr = sc.nextLine();
        userStr = userStr.toLowerCase();
        userStr = userStr.trim();
        userChar = userStr.charAt(0);

        if (userChar == '\0') {
            return '\0'; // return null if there are no non-whitespace characters
        } else {
            return userChar; // returns the first character
        }

    }

    /**
     * Prompts the user for a string value by displaying prompt. Note: This method should not be a
     * new line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input, remove any leading
     * and trailing whitespace, and return the input converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and
     *         trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        String userStr;
        System.out.print(prompt);

        userStr = sc.nextLine();
        userStr = userStr.trim();
        userStr = userStr.toLowerCase();

        return userStr;
    }

    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     *
     * 1 - The game board should be created row-by-row. a - For each row, copy the values from the
     * corresponding row in the 2-d array contained at index lvl in levels. b - When the worker is
     * located, it's position should be recorded in the pos parameter. 2 - For each goal described
     * in the array at index lvl of goals, convert the character at the goal coordinate to: -
     * MyLevels.WORK_GOAL_CHAR if it contains the worker - MyLevels.BOX_GOAL_CHAR if it contains a box -
     * MyLevels.GOAL_CHAR otherwise
     * 
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @param pos The starting pos of the worker. A length 2 array, where index 0 is the row and
     *        index 1 is the column.
     * @return A two dimension array representing the initial MyLevelsuration for the given level.
     */
    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
        int goalRow; 
        int goalColumn; 
        // locates coordinates of goals and replaces char with a goal char
        // then copies the array in levels[lvl] into a 2D array
        char[][] lvlBoard = new char[levels[lvl].length][];

        for (int i = 0; i < levels[lvl].length; i++) {
            lvlBoard[i] = new char[levels[lvl][i].length]; // creates the columns 
                                                           // for the 2-d array

            for (int j = 0; j < levels[lvl][i].length; j++) {
                lvlBoard[i][j] = levels[lvl][i][j]; // creates an element 

                if (lvlBoard[i][j] == MyLevels.WORKER_CHAR) { //worker position remembered 
                    for (int z = 0; z < 2; z++) {
                        pos[0] = i;
                        pos[1] = j;
                    }
                }
            }
        }

        
        for (int i = 0; i < goals[lvl].length; i = i + 2) { // Puts the goal chars and goals onto the board
            goalRow = goals[lvl][i];
            goalColumn = goals[lvl][i + 1];

            if (lvlBoard[goalRow][goalColumn] == MyLevels.WORKER_CHAR) {
                lvlBoard[goalRow][goalColumn] = MyLevels.WORK_GOAL_CHAR;
            } else if (lvlBoard[goalRow][goalColumn] == MyLevels.BOX_CHAR) {
                lvlBoard[goalRow][goalColumn] = MyLevels.BOX_GOAL_CHAR;
            } else {
                lvlBoard[goalRow][goalColumn] = MyLevels.GOAL_CHAR;
            }

        }

        return lvlBoard;
    }

    /**
     * Prints out the game board.
     * 
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     * MyLevels.WALL_CHAR with a length equal to that of the first row of board, plus the outer wall
     * to the left and the right. 2 - For each row in board, print out a MyLevels.WALL_CHAR, followed
     * by the contents of the row, followed by a MyLevels.WALL_CHAR. 3 - Finally, print out a sequence
     * of MyLevels.WALL_CHAR with a length equal to that of the last row of board, plus the outer wall
     * to the left and the right.
     *
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */
    public static void printBoard(char[][] board) {
        // prints out wall chars that are equal to length of board at row 0 + 2
        for (int i = 0; i < board[0].length + 2; i++) {
            System.out.print(MyLevels.WALL_CHAR);
        }
        System.out.println("");

        // prints a wall character
        // prints the contents of the board in that row 
        // prints one last wall char
        // and then this is repeated for all rows of the board
        for (int i = 0; i < board.length; i++) {
            System.out.print(MyLevels.WALL_CHAR);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println(MyLevels.WALL_CHAR);
        }
        // prints out wall chars that are equal to length of board at the last row + 2
        for (int i = 0; i < board[board.length - 1].length + 2; i++) {
            System.out.print(MyLevels.WALL_CHAR);
        }
        System.out.println("");

    }

    /**
     * Runs a given level through some basic sanity checks.
     *
     * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is a valid index in
     * levels, that the 2-d array at index lvl exists and that it contains at least 1 row. 3 - lvl
     * is a valid index in goals, the 1-d array at index lvl exists and that it contains an even
     * number of cells. 4 - the number of boxes is more than 0. 5 - the number of boxes equals the
     * number of goals. 6 - the coordinate of each goal is valid for the given lvl and does not
     * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check for duplicate
     * goals.
     *
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containg the goals for the levels.
     * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2 fails: -1 - Test 3
     *         fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 - Test 6 fails: -5 - Test 7 fails: -6
     *         - Test 8 fails: -7
     * 
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {

        // Test 1 - make sure lvl >= 0
        if (lvl < 0) {
            return 0;
        }

        // Test 2 - lvl is a valid index in levels, that the 2D array at index lvl
        // exists and that it contains at least 1 row
        if ((!(levels.length > lvl)) || levels[lvl] == null) {
            return -1;
        }

        // Test 3 - lvl is a valid index in goals, the 1D array at index lvl
        // exists and that it contains an even number of cells
        if (!(goals.length > lvl) || goals[lvl].length % 2 == 1) {
            return -2;
        }

        // Test 4 - number of boxes is more than 0
        int numBox = 0;

        // iterates through each element in levels, counting number of boxes
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == MyLevels.BOX_CHAR) {
                    numBox += 1;
                }
            }
        }
        if (!(numBox > 0)) {
            return -3;
        }

        // Test 5 - number of boxes equals number of goals
        // since goals array contains coordinates, divides by two to find number of goals
        int numGoal = goals[lvl].length / 2;

        if (!(numGoal == numBox)) {
            return -4;
        }

        // Test 6 - coordinate of each goal is valid for the given lvl and
        // does not correspond to a wall cell

        for (int i = 0; i < goals[lvl].length; i = i + 2) {
            int goalRow = goals[lvl][i];
            int goalColumn = goals[lvl][i + 1];

            if (levels[lvl][goalRow][goalColumn] == MyLevels.WALL_CHAR) {
                return -5;
            }

        }

        // Test 7 - the number of workers is exactly 1
        int numWorkers = 0;

        // iterates through each element of levels, counting number of workers
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == MyLevels.WORKER_CHAR) {
                    numWorkers += 1;
                }
            }
        }
        if (!(numWorkers == 1)) {
            return -6;
        }

        // Test 8 -- checking for duplicate goals
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
                // checks to see if each coordinate of the pair of goals matches
                if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
                    return -7;
                }
            }
        }

        return 1;
    }

    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the
     * String parameter.
     *
     * The rules to create the length 2 int array are as follows: - The 1st character of the String
     * represents the direction. - The remaining characters (if there are any) are interpreted as
     * integer and represent the magnitude or the number of steps to take.
     *
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents
     * moving up the rows and a positive value represents moving down the rows.
     *
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents
     * moving left in the columns and a positive value represents moving right in the columns.
     *
     * If the first character of moveStr does not match on of MyLevels.UP_CHAR, MyLevels.DOWN_CHAR,
     * MyLevels.LEFT_CHAR, or MyLevels.RIGHT_CHAR, then return an array with 0 in both cells.
     *
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     *
     * Hint: Use Scanner to parse the magnitude.
     *
     * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would represent moving up
     * by one character. - If the parameter moveStr is "65": An array {0, 5} would`represent moving
     * right by 5 characters.
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {
        moveStr = moveStr.replaceAll(" ", "");
        int[] delta = {0, 0};
        int moveInt;

        // MyLevelsures delta to the correct direction
        // returns delta if the direction is invalid
        if (moveStr.charAt(0) == MyLevels.UP_CHAR) {
            delta[0] = -1;
            delta[1] = 0;
        } else if (moveStr.charAt(0) == MyLevels.DOWN_CHAR) {
            delta[0] = 1;
            delta[1] = 0;
        } else if (moveStr.charAt(0) == MyLevels.RIGHT_CHAR) {
            delta[0] = 0;
            delta[1] = 1;
        } else if (moveStr.charAt(0) == MyLevels.LEFT_CHAR) {
            delta[0] = 0;
            delta[1] = -1;
        } else {
            return delta;
        }

        // changes delta to have the correct magnitude
        moveStr = moveStr.substring(1);
        Scanner sc = new Scanner(moveStr);
        if (sc.hasNextInt()) {
            moveInt = sc.nextInt();
            delta[0] = delta[0] * moveInt;
            delta[1] = delta[1] * moveInt;
        } else {
            return delta;
        }
        sc.close();
        return delta;

    }

    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check: 1 - that pos is valid. 2 - that the
     * character at pos in board is in the valid array. 3 - that the delta is valid. 4 - that the
     * new position is valid and not a wall character. 5 - that the new position is not a box
     * character For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and index 1
     *        is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2, or not on the
     *         board. -2 : if the character at pos is not valid (not in the valid array). -3 : if
     *         delta is null or not length 2. -4 : if the new position is off the board or a wall
     *         character -5 : if the new position is a box character
     */
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        // TEST 1 If pos is null, not length 2, or not on the board

        if (pos == null || pos.length != 2 || pos[0] < 0 || pos[1] < 0 || pos[0] >= board.length
            || pos[1] >= board[pos[0]].length) {
            return -1;
        }

        // TEST 2 If character at pos is not valid (not in the valid array)
        int validWorker = 0;
        for (int i = 0; i < valid.length; i++) {
            if (board[pos[0]][pos[1]] == valid[i]) {
                validWorker++;
            }
        }

        if (validWorker == 0) {
            return -2;
        }

        // TEST 3 If delta is null or not length 2
        if (delta == null || delta.length != 2) {
            return -3;
        }

        // TEST 4 If the new position is off the board or a wall character

        int[] newPosition = new int[2];
        newPosition[0] = pos[0] + delta[0];
        newPosition[1] = pos[1] + delta[1];

        if (newPosition[0] >= board.length || newPosition[0] < 0 || newPosition[1] < 0
            || newPosition[1] >= board[newPosition[0]].length
            || board[newPosition[0]][newPosition[1]] == MyLevels.WALL_CHAR) {
            return -4;
        }

        // TEST 5 If the new position is a box character
        if (board[newPosition[0]][newPosition[1]] == MyLevels.BOX_CHAR
            || board[newPosition[0]][newPosition[1]] == MyLevels.BOX_GOAL_CHAR) {
            return -5;
        }

        return 1;
    }

    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        char posChar;
        posChar = board[pos[0]][pos[1]];

        if (posChar == val) {
            board[pos[0]][pos[1]] = opt1;
        }
        if (posChar != val) {
            board[pos[0]][pos[1]] = opt2;
        }
    }

    /**
     * Moves a box on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent a box. Step 2: Use your togglePos method to correctly change
     * the character at the new position to the appropriate box character. Step 3: Again use your
     * togglePos method to correctly change the character at pos to the the appropriate character
     * without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta) {
        char[] valid = {MyLevels.BOX_CHAR, MyLevels.BOX_GOAL_CHAR, MyLevels.EMPTY_CHAR};
        int checkedDelta = checkDelta(board, pos, delta, valid);

        if (!(checkedDelta == 1)) {
            return checkedDelta;
        }

        int[] newPos = new int[2];
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];

        // user moves either onto the goal or an empty space
        // changes the new position to a box if the existing space isn't a goal
        togglePos(board, newPos, MyLevels.GOAL_CHAR, MyLevels.BOX_GOAL_CHAR, MyLevels.BOX_CHAR);

        // user moves past the goal
        // changes the old position to a goal if the old position was a box goal char
        togglePos(board, pos, MyLevels.BOX_GOAL_CHAR, MyLevels.GOAL_CHAR, MyLevels.EMPTY_CHAR);

        return 1;
    }

    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step. That is, if the delta is {0,
     * -3}, your method should call doMove three times with an argument of {0, -1} for the delta
     * parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove six times with an
     * argument of {1, 0} for the delta parameter of the doMove method.
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If both of the cells of delta are 0, return 0. If the call to doMove returns a value
     *         less than 1, return that value. Otherwise, return 1.
     */
    public static int processMove(char[][] board, int[] pos, int[] delta) {
        int rowMoves = delta[0];
        int columnMoves = delta[1];
        int[] deltaArg = {0, 0}; // used to orient the move
        int doRes; // used to test if the move is valid

        if (rowMoves == 0 && columnMoves == 0) {
            return 0;
        }

        if (rowMoves != 0) {
            if (rowMoves < 0) {
                rowMoves = Math.abs(rowMoves); // creates a magnitude for the move
                deltaArg[0] = -1; // orients the move up
                // executes the move one space at a time
                // returns doRes if the move is invalid
                for (int i = 0; i < rowMoves; i++) {
                    doRes = doMove(board, pos, deltaArg);
                    if (doRes < 1) {
                        return doRes;
                    }
                }
            } else if (rowMoves > 0) {
                rowMoves = Math.abs(rowMoves); // creates a magnitude for the move
                deltaArg[0] = 1; // orients the move down
                // executes the move one space at a time
                // returns doRes if the move is invalid
                for (int i = 0; i < rowMoves; i++) {
                    doRes = doMove(board, pos, deltaArg);
                    if (doRes < 1) {
                        return doRes;
                    }
                }
            }
        }

        if (columnMoves != 0) {
            if (columnMoves < 0) {
                columnMoves = Math.abs(columnMoves); // creates a magnitude for the move
                deltaArg[1] = -1; // orients the move left
                // executes the move one space at a time
                // returns doRes if the move is invalid
                for (int i = 0; i < columnMoves; i++) {
                    doRes = doMove(board, pos, deltaArg);
                    if (doRes < 1) {
                        return doRes;
                    }
                }
            } else if (columnMoves > 0) {
                deltaArg[1] = 1; // orients the move right
                columnMoves = Math.abs(columnMoves); // creates a magnitude for the move
                // executes the move one space at a time
                // returns doRes if the move is invalid
                for (int i = 0; i < columnMoves; i++) {
                    doRes = doMove(board, pos, deltaArg);
                    if (doRes < 1) {
                        return doRes;
                    }
                }
            }
        }

        return 1;
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent the worker. Step 2: If checkDelta returns -5, use your shiftBox
     * method to move the box by delta before moving the worker. Step 3: Use your togglePos method
     * to correctly change the character at the new position to the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the the
     * appropriate character without a worker. Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value. If
     *         checkDelta returns -5 and shiftBox returns a value less than 0, return 0. Otherwise,
     *         return 1.
     */
    public static int doMove(char[][] board, int[] pos, int[] delta) {
        int[] newPosition = new int[2];
        newPosition[0] = pos[0] + delta[0];
        newPosition[1] = pos[1] + delta[1];
        char[] valid = {MyLevels.WORKER_CHAR, MyLevels.WORK_GOAL_CHAR};
        int shiftedBox;

        int checkedDelta = checkDelta(board, pos, delta, valid);

        // the move isn't valid, and the worker doesn't move onto a box
        if ((checkedDelta < 1) && !(checkedDelta == -5)) {
            return checkedDelta;
        }
        // the worker moves onto a box
        // checks to see that the box can be shifted, then moves the worker
        else if (checkedDelta == -5) {
            shiftedBox = shiftBox(board, newPosition, delta);
            //System.out.println(shiftedBox);
            if (shiftedBox < 0) {
                return 0;
            } else {
                // worker moves onto a goal or empty space
                // changes the new position to a worker if the existing space isn't a goal
                togglePos(board, newPosition, MyLevels.GOAL_CHAR, MyLevels.WORK_GOAL_CHAR,
                    MyLevels.WORKER_CHAR);
                // worker moves past a goal
                // changes the old position to a goal if the old position was a work goal char
                togglePos(board, pos, MyLevels.WORK_GOAL_CHAR, MyLevels.GOAL_CHAR, MyLevels.EMPTY_CHAR);
                pos[0] = newPosition[0];
                pos[1] = newPosition[1];
            }
        }
        // the worker moves somewhere not onto a box
        else {
            // same movement mechanism as above
            togglePos(board, newPosition, MyLevels.GOAL_CHAR, MyLevels.WORK_GOAL_CHAR,
                MyLevels.WORKER_CHAR);
            togglePos(board, pos, MyLevels.WORK_GOAL_CHAR, MyLevels.GOAL_CHAR, MyLevels.EMPTY_CHAR);
            pos[0] = newPosition[0];
            pos[1] = newPosition[1];
        }
        return 1;

    }

    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) {
        int i;
        int j;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                // as long as the goal isn't covered by the box, checkWin returns false
                if (board[i][j] == MyLevels.GOAL_CHAR || board[i][j] == MyLevels.WORK_GOAL_CHAR) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using MyLevels.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random randGen = new Random(MyLevels.SEED);
        int maxLvl = MyLevels.LEVELS.length - 1;
        int lvl;
        char playAgain = 'y';
        String userMove;
        System.out.println("Welcome to Sokoban!");

        do {
            lvl = promptInt(sc, "Choose a level between 0 and " + maxLvl + ": ", -1, maxLvl);
            sc.nextLine();
            if (lvl == -1) {
                lvl = randGen.nextInt(MyLevels.LEVELS.length);
            }
            // checks to see if level is valid, and prints level if it is
            // prints out reason for error if invalid
            int lvlChecked = checkLevel(lvl, MyLevels.LEVELS, MyLevels.GOALS);
            if (lvlChecked == 1) {
                System.out.println("Sokoban Level " + lvl);

            } else {
                System.out.println("Error loading level!");
                switch (lvlChecked) {
                    case 0:
                        System.out.println("Level lvl must be 0 or greater!");
                        break;
                    case -1:
                        System.out.println("Error with MyLevels.LEVELS");
                        break;
                    case -2:
                        System.out.println("Error with MyLevels.GOALS");
                        break;
                    case -3:
                        System.out.println("Level lvl does not contain any boxes.");
                        break;
                    case -4:
                        System.out
                            .println("Level lvl does not have the same number of boxes as goals.");
                        break;
                    case -5:
                        System.out.println("Level lvl has a goal location that is a wall.");
                        break;
                    case -6:
                        System.out.println("Level lvl has 0 or more than 1 worker(s).");
                        break;
                    case -7:
                        System.out.println("Level lvl contains duplicate goals.");
                        break;
                    default:
                        System.out.println("Unknown Error");
                        break;
                }
            }

            int[] workerPos = new int[2];
            char[][] gameBoard = null;
            boolean flag = false;
            int xNum = 0; // counter for number of moves
            gameBoard = initBoard(lvl, MyLevels.LEVELS, MyLevels.GOALS, workerPos);
            do {
                do {
                    printBoard(gameBoard);
                    userMove = promptString(sc, ": ");
                    if (userMove.length() != 0) {
                        // breaks out of loop if user enters quit char
                        if (userMove.charAt(0) == MyLevels.QUIT_CHAR) {
                            flag = true;
                            break;
                        }
                    }
                } while (userMove.length() == 0 || userMove == null);

                // breaks out of second loop if user enters quit char
                // thus ends game
                if (flag)
                    break;

                int[] userDelta = calcDelta(userMove);

                int processRes = processMove(gameBoard, workerPos, userDelta);
                //System.out.println(Arrays.toString(userDelta));

                // if the move was valid and was executed, increases move counter
                if (processRes == 1) {
                    if (userDelta[0] == 0) { // increases for horizontal moves
                        xNum = xNum + Math.abs(userDelta[1]);
                    } else { // increases for vertical moves
                        xNum = xNum + Math.abs(userDelta[0]);
                    }
                }

            } while (!checkWin(gameBoard));

            if (checkWin(gameBoard)) {
                System.out.println("Congratulations! You won in " + xNum + " moves!");

                printBoard(gameBoard);
            }

            playAgain = promptChar(sc, "Play again? (y/n) ");
            xNum = 0; // resets move counter

        } while (playAgain == 'y');

        System.out.println("Thanks for playing!");

    }
}
