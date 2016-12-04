package cs2114.mazesolver;

import java.util.ArrayList;
import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author AndrewK
 * @version Oct 3, 2013
 */

//this is our maze class
public class Maze
    implements IMaze
{
    //first we have to make certain instance variables
    //when considering instance variables we must ask...what do we NEED???

    //We need a variable to represent the size of the maze. This may change
    //and has to be initialized in the constructor.
    private int          size;

    //this is 2 dimensional array that will represent the maze
    private MazeCell[][] mCell;

    //the starting and ending location of the maze
    private ILocation    start;
    private ILocation    goal;


    // ----------------------------------------------------------
    /**
     * This is the constructor for the maze object
     *
     * @param size
     *            (The size of the maze "board")
     */
    //here is where we make the constructor for the maze. We need a size for
    //the maze to initially make a maze in the first place. The size will
    //be passed into the array to make an array
    public Maze(int size)
    {
        //this sets the instance variable size to the size parameter of the maze
        this.size = size;
        //the mazecell above is never instantiated, so we will do that here
        //it basically assigns space to the mCell and make it equal to the size
        //that was input from the parameter.
        mCell = new MazeCell[size][size];
        //and here is where we put in the start and end position...based on
        //the instance variables...let's look into that class
        start = new Location(0, 0);
        goal = new Location(size - 1, size - 1);

        //here we iterate through each part of the 2D array
        //and we originally fill in every space with an unexplored cell
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                mCell[i][j] = MazeCell.UNEXPLORED;
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Returns the size value.
     *
     * @return size
     */

    //here we just return the size
    public int size()
    {
        //very easy...all we have to do is return the amount that the size is
        return size;
    }


    // ----------------------------------------------------------
    /**
     * This method gets the cell of a corresponding location value and checks if
     * it's in bounds(returns mCell[x][y] or not (INVALID_CELL).
     *
     * @param point
     *            (An arbitrary location on the Maze board)
     * @return mCell
     */
    //this method takes a location point that has an x() and a y()...
    //in this method, we will be seeing if the x and y are within bounds of the
    //maze that we are constructing. This will return the point in the array
    //that it is located in.
    public MazeCell getCell(ILocation point)
    {
        //we make variables to hold the coordinate values of the point given
        int x = point.x();
        int y = point.y();

        //as long as the x and y points are within 0 to the size of the array,
        //we know that they are completely within bounds
        if (x >= 0 && x < size && y >= 0 && y < size)
        {
            //because they are completely within bounds, we can return the
            //MazeCell at this location
            return mCell[x][y];

        }
        else
        {
            //else, we know that it is not within proper bounds, meaning
            //by definition, it is an invalid cell
            return MazeCell.INVALID_CELL;
        }

    }


    /**
     * Gets the goal location of the maze and returns it.
     *
     * @return goal (returns the goal location).
     */

    public ILocation getGoalLocation()
    {
        //very easy, it just returns the goal instance variable that is set
        //initially, but may be changed by another method
        return goal;
    }


    /**
     * Gets the starting location of the maze and returns it.
     *
     * @return start (returns the goal location).
     */

    public ILocation getStartLocation()
    {
        //same with the getGoalLocation above
        return start;
    }


    /**
     * sets values of a Location to a corresponding cell value.
     *
     * @param loc
     *            (an arbitrary location on the board)
     * @param cell
     *            (a MazeCell that has not been given a set value yet)
     */

    //this method is used for giving a location and allowing the MazeCell to be
    //set to any MazeCell value in that location
    public void setCell(ILocation loc, MazeCell cell)
    {
        //first thing we do is set the locations that we are looking at to
        //variables. We take the location that we specify and get the
        //coordinates there and put them into variables x and y.
        int x = loc.x();
        int y = loc.y();

        //now...if the MazeCell (called cell by the parameter) that is passed in
        //has a WALL and the location is equal to the start or the end, then
        //we return....WHAT? WHAT ARE WE RETURNING?? SO CONFUSED
        if ((cell == MazeCell.WALL) && (loc.equals(start) || loc.equals(goal)))
        {
            //OKAY, I HAVE A THEORY THAT THIS RETURNS OUT OF THE FUNCTION AND
            //DOES NOTHING....THIS IS A CONVENIENT THING TO HAVE IN A PROGRAM
            return;
        }

        //however...if it is within the bounds, then we will be able to set the
        //locations given and the overall MazeCell (mCell, which is an instance
        //field and how we realize why...because it is going to be changed here)
        //we can set the mCell to the parameter cell, which is what we want to
        //change the cell's value to.
        if (0 <= x && x < size && 0 <= y && y < size)
        {
            mCell[x][y] = cell;
        }

    }


    /**
     * sets the Goal location to a certain Location.
     *
     * @param location
     *            (an arbitrary location on the board)
     */

    public void setGoalLocation(ILocation location)
    {

        //if the location passed into the parameter is a wall, then set it
        //to unexplored...WHY
        if (getCell(location) == MazeCell.WALL)
        {
            setCell(location, MazeCell.UNEXPLORED);

        }
        //basically, just set the goal equal to the location. Because this
        //is the instance variable, this will change goal everywhere
        goal = location;

    }


    /**
     * sets the Starting location to a certain Location.
     *
     * @param location
     *            (an arbitrary location on the board)
     */

    public void setStartLocation(ILocation location)
    {
        //OOOOH okay, so basically what this is saying is that if the location
        //is a wall, we want to make sure that the location is within bounds
        //a wall is the only real place where the MazeCell may not be in bounds
        if (getCell(location) == MazeCell.WALL)
        {
            setCell(location, MazeCell.UNEXPLORED);

        }
        //same shit, set the "start" instance variable to the location passed
        //into the parameter
        start = location;

    }


    // ----------------------------------------------------------
    /**
     * Fills in a list with the values of the stack with correct path values and
     * outputs the list in the form a string.
     *
     * @param stack
     *            (the stack of locations that will eventually be set to
     *            strings)
     * @return String (The string of results that give the right answer)
     */
    public String solveList(Stack<ILocation> stack)
    {
        //here we make an ArrayList called List and make it hold values for
        //an ArrayList of strings
        ArrayList<String> list = new ArrayList<String>();
        //the answer will initially be set to an empty string "". We will
        //add to this and eventually return it
        String answercor = "";

        //while there is something in the stack
        while (!stack.isEmpty())
        {
            //.add is a function of "list", which is why I can use it here
            //pop something off the string and send it to the tostring function
            list.add(0, stack.pop().toString());

        }
        //k is going through the entire list and write the values out
        for (int k = 0; k < list.size(); k++)
        {

            if (k <= list.size() - 2)
            {
                answercor = answercor + list.get(k) + " ";
            }
            else
            {
                answercor = answercor + list.get(k);
            }

        }
        return answercor;

    }


    // ----------------------------------------------------------
    /**
     * This method is the actual "solver" that eventually finds the goal from
     * the starting location, while also recording them.
     *
     * @return String or null
     */
    public String solve()
    {
        // if you find a failed path set that value's coordinates to (-1,-1)
        //we will initialize a stack of location objects called simply, stack
        Stack<ILocation> stack;
        //we will initialize this new stack that we created
        stack = new Stack<ILocation>();

        //first, we will push the "start" location into the stack
        stack.push(start);
        //while the stack is NOT empty...meaning while there is still stuff
        //for this to iterate through...
        while (!stack.empty())
        {
            //we will make a temporary variable called temp which will peek
            //at the top element of the stack and store it's value
            //(THIS BECOMES WHATEVER IS PUSHED ONTO THE STACK BELOW)
            ILocation temp = stack.peek();
            //then we will set the cell...temp is the top of the stack and we
            //will set it's value to CURRENT PATH, meaning we will set the
            //next value in the top of the stack
            setCell(temp, MazeCell.CURRENT_PATH);
            //if temp is eventually equal to the goal, then we are done!
            if (temp.equals(goal))
            {
                //we will return the entire way that we got here
                //with the method we completed above
                return solveList(stack);

            }

            //if the value north of the current stack is unexplored, we have
            //to explore it by pushing it onto the stack...look above at
            //the temporary variable
            if (getCell(temp.north()) == MazeCell.UNEXPLORED)
            {
                stack.push(temp.north());

            }
            else if (getCell(temp.south()) == MazeCell.UNEXPLORED)
            {

                stack.push(temp.south());

            }
            else if (getCell(temp.east()) == MazeCell.UNEXPLORED)
            {

                stack.push(temp.east());

            }
            else if (getCell(temp.west()) == MazeCell.UNEXPLORED)
            {

                stack.push(temp.west());

            }
            else
            {
                setCell(temp, MazeCell.FAILED_PATH);
                stack.pop();
            }

        }
        return null;
    }
}
