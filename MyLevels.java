
public class MyLevels {

	/**
	 * This class contains the constants used in the Sokoban program. These constants may be changed
	 * when testing. So, your program should use the constants, not the values.
	 * 
	 * AARUSHI GUPTA
	 */

	    /**
	     * Character values for displaying the different statuses of the game board cells.
	     */
	    public static final char EMPTY_CHAR = ' '; // Empty character
	    public static final char BOX_CHAR = '='; // Box character
	    public static final char WALL_CHAR   = '#'; // Wall character
	    public static final char WORKER_CHAR  = '@'; // Worker character
	    public static final char GOAL_CHAR  = '.'; // Goal character
	    public static final char BOX_GOAL_CHAR  = '*'; // Box on a goal character
	    public static final char WORK_GOAL_CHAR  = '+'; // Worker on a goal character

	    /**
	     * Constants for the random processes.
	     */
	    public static final long SEED            = 1234; // The random seed

	    /**
	     * Initial configuration of the levels. Note that the location of the goals are defined in the 
	     * GOALS array which is a parallel array to LEVELS.
	     */
	    public static final char[][][] LEVELS = {
	    		//3x array
	        {
	            //{'.', ' ', ' '},
	            //{'#', '=', '#'},
	            //{'@', ' ', '#'},
	            
	            {GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR},
	            {WALL_CHAR, BOX_CHAR, WALL_CHAR},
	            {WORKER_CHAR, EMPTY_CHAR, WALL_CHAR},
	            
	        },
	        
	        //6x6 array
	        
	        {
	            //{' ', ' ', '.', ' ', ' ', '#'}'
	            //{'#', ' ', ' ', ' ', '#', ' '},
	            //{' ', '#', '=', ' ', ' ', ' '},
	            //{'@', ' ', ' ', ' ', ' ', '#'},
	            //{'#', '#', ' ', '=', ' ', '#'},
	            //{' ', '#', ' ', '#', ' ', '.'}
	            
	            {EMPTY_CHAR, EMPTY_CHAR, GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
	            {WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR},
	            {EMPTY_CHAR, WALL_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
	            {WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
	            {WALL_CHAR, WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR},
	            {EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, GOAL_CHAR}
	        },
	        
	        //9x9 array
	
	        {
	            //{'#', '#', '.', ' ', ' ', ' ','#','#','#'},
	            //{'#', '#', '=', '#', ' ', '=','#','.',' '},
	            //{'@', ' ', ' ', '#', ' ', ' ','#','#',' '},
	            //{' ', '#', ' ', '=', ' ', ' ',' ',' ',' '},
	            //{' ', ' ', '#', ' ', ' ', '#','#','#','#'},
	            //{' ', '#', ' ', ' ', ' ', '#',' ',' ','#'},
	        	//{'#', '#', ' ', '.', ' ', '#',' ','#','#'},
	        	//{'#', '#', ' ', '#', '#', '#','#','#','#'},
	        	//{' ', ' ', ' ', '#', '#', ' ','#','#','#',' '}
	            {WALL_CHAR, WALL_CHAR, GOAL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
	            {WALL_CHAR, WALL_CHAR, BOX_CHAR, WALL_CHAR, EMPTY_CHAR, BOX_CHAR, WALL_CHAR, GOAL_CHAR, EMPTY_CHAR},
	            {WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR},
	            {EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
	            {EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
	            {EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
	            {WALL_CHAR, WALL_CHAR, EMPTY_CHAR, GOAL_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR},
	            {WALL_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
	            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR}
	        
	        }
	        
	        
	    };

	    /**
	     * Position of the goals for each level. Every pair of values represents the row and column of 
	     * a goal. This is a parallel array to LEVELS.
	     */
	    public static final int[][] GOALS = {
	    		{0,0}, //1 goal for 3x3 array
	    {0, 2, 5, 5}, //2 goal for 6x6 array 
	    {0,2,1,7,6,3}}; //3 goal for 9x9 array
	    

	    /**
	     * The characters for the different movement directions and for quiting a game.
	     */
	    public static final char UP_CHAR = '8';
	    public static final char DOWN_CHAR = '2';
	    public static final char LEFT_CHAR = '4';
	    public static final char RIGHT_CHAR = '6';
	    public static final char QUIT_CHAR = 'q';
	}

	




























