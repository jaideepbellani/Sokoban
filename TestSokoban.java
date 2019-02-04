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
/**
 * This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when
     * you are ready for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        
        // Milestone 1
        //testCheckLevel();
        
        // Milestone 2
        //testInitBoard();
        //testCheckWin();
        testCalcDelta();
        //testCheckDelta();
       
        // Milestone 3
        //testTogglePos();
        //testShiftBox();
        //testDoMove();
        testProcessMove();
    }
    
    private static void testCheckLevel() {
        int numTests = 2;
        int passed = numTests;
        int res;
        //Test 1
        if((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
            System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }
        
        //Test 2
        char[][][] lvl = new char[2][][];
        if((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
            System.out.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }

        //Test 5

        if((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -2) {
            System.out.println("FAILED: Sokoban.checkLevel Test 5. Expected -4, but value returned " + res);
            passed--;
        }       
        //test 5 Sokoban.java
        //int numOfGoals;
        //numOfGoals = goals[lvl].length / 2;
        //if (!(numOfBoxes == numOfGoals)) {
        //    return -4;
        //}

        //FIXME Add more tests
        
        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if(a == null || b == null)
            return false;
        if(a.length != b.length)
            return false;
        for(int i = 0; i < a.length; i++) {
            if(!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static void testInitBoard() {
        int numTests = 1;
        int passed = numTests;

        //Test 1
        int[] pTest1 = new int[2];
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
        if(!Arrays.equals(pTest1, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call " + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest1 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest1, bCompTest1)){
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);            
            passed--;
        }
        //End of Test 1
        
        //FIXME Add more tests
        
        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testCheckWin() {
        char[][] testboardworking = {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};

        char[][] testboardnotworking = {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        boolean checkertrue = Sokoban.checkWin(testboardworking);
        if (checkertrue) {
            System.out.println("1");
        }
        boolean checkerfalse = Sokoban.checkWin(testboardnotworking);
        if (!checkerfalse) {
            System.out.println("This one failed");
        }
    }
    
    private static void testCalcDelta() {
        String badStr = "7000";
        String goodStr = "24";
        int[] returnFromFalse = Sokoban.calcDelta(badStr);
        int[] returnFromTrue = Sokoban.calcDelta(goodStr);
        int[] newArray1 = {-400, 0};
        int[] newArray2 = {4, 0};
        if (returnFromFalse == newArray1) {
            System.out.print("This is not valid");
        }
        if (returnFromTrue == newArray2) {
            System.out.print("1");
        }
    }
    
    private static void testCheckDelta() {
        //FIXME
    }
    
    private static void testTogglePos() {
        //FIXME
    }

    private static void testShiftBox() {
        //| | | |
        //| |=| |
        //| |@| |
        char[][] board = {
            {Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR}
        };
        int[] pos = new int[] {1,1};
        int[] delta = new int[] {-1, 0};
        int[] n = null;
        Sokoban.printBoard(board);
        System.out.println(Sokoban.shiftBox(board, n, delta));
        Sokoban.printBoard(board);
    }
    

    private static void testDoMove() {
        //FIXME
    }

    private static void testProcessMove() {
        //| | | | |
        //| | | | |
        //| | | | |
        //|@| | | |
        char[][] board = {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR}
        };
        int[] pos = new int[] {3,0};
        int[] delta = new int[] {-2,0};
        Sokoban.printBoard(board);
        int val = Sokoban.processMove(board, pos, delta);
        System.out.println(val);
        Sokoban.printBoard(board);
    }    

}
