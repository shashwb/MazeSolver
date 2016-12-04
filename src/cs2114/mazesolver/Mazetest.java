package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * This Class is used to see if the methods in the class Maze do indeed do their
 * intended purposes.
 *
 * @author AndrewK
 * @version Oct 3, 2013
 */
public class Mazetest
    extends student.TestCase
{
    private Maze mTest;


    /**
     * initializes mTest to a default value of size 4.
     */
    public void setUp()
    {
        mTest = new Maze(4);

    }


    // ----------------------------------------------------------
    /**
     * Checks to see if the size of the maze returns the correct size.
     */
    public void testSize()
    {
        assertEquals(mTest.size(), 4);
    }


    // ----------------------------------------------------------
    /**
     * Tests to see if the cell acquired by getCell() returns the correct type.
     */
    public void testGetCell()
    {

        ILocation loc = new Location(2, 2);
        assertEquals(mTest.getCell(loc), MazeCell.UNEXPLORED);
        ILocation loc2 = new Location(6, 5);
        assertEquals(mTest.getCell(loc2), MazeCell.INVALID_CELL);
    }


    // ----------------------------------------------------------
    /**
     * Tests to see if the cell set by setCell() is the correct value.
     */
    public void testSetCell()
    {
        ILocation point1 = new Location(0, 0);
        mTest.setCell(point1, MazeCell.WALL);

        assertEquals(mTest.getCell(point1), MazeCell.UNEXPLORED);

        ILocation point2 = new Location(3, 3);
        mTest.setCell(point2, MazeCell.WALL);

        assertEquals(mTest.getCell(point2), MazeCell.UNEXPLORED);

        ILocation point3 = new Location(1, 1);
        mTest.setCell(point3, MazeCell.WALL);

        assertEquals(mTest.getCell(point3), MazeCell.WALL);

    }


    // ----------------------------------------------------------
    /**
     * Tests to see if the getGoalLocation() returns the right location.
     */
    public void testGetGoalLocation()
    {
        ILocation loc = new Location(3, 3);
        assertEquals(mTest.getGoalLocation(), loc);
    }


    // ----------------------------------------------------------
    /**
     * Tests to see if getStartLocation() returns the right location.
     */
    public void testGetStartLocation()
    {
        ILocation loc = new Location(0, 0);
        assertEquals(mTest.getStartLocation(), loc);
    }


    // ----------------------------------------------------------
    /**
     * Tests to see if setGoalLocation() sets the correct type of cell to the
     * goal.
     */
    public void testSetGoalLocation()
    {
        mTest.setCell(new Location(3, 3), MazeCell.WALL);
        assertEquals(
            mTest.getCell(mTest.getGoalLocation()),
            MazeCell.UNEXPLORED);

    }


    // ----------------------------------------------------------
    /**
     * Tests to see if setStartLocation() sets the correct type of cell to the
     * start.
     */
    public void testSetStartLocation()
    {
        mTest.setCell(new Location(0, 0), MazeCell.WALL);
        assertEquals(
            mTest.getCell(mTest.getGoalLocation()),
            MazeCell.UNEXPLORED);
    }


    // ----------------------------------------------------------
    /**
     * Tests to see if the solve() method can solve the maze while also
     * returning the correct location history to get from the start to the goal.
     */

    public void testSolve()
    {
        mTest.setCell(new Location(1, 0), MazeCell.WALL);
        mTest.setCell(new Location(1, 1), MazeCell.WALL);
        mTest.setCell(new Location(0, 1), MazeCell.WALL);
        assertEquals(mTest.solve(), null);
        mTest.setCell(new Location(1, 0), MazeCell.UNEXPLORED);
        assertEquals(
            mTest.solve(),
            "(0, 0) (1, 0) (2, 0) (2, 1) (2, 2) (2, 3) (3, 3)");

        // new test case for north and west.
        mTest = new Maze(4);
        mTest.setCell(new Location(2, 0), MazeCell.WALL);
        mTest.setCell(new Location(0, 1), MazeCell.UNEXPLORED);
        mTest.setGoalLocation(new Location(0, 0));
        mTest.setStartLocation(new Location(1, 1));
        assertEquals(mTest.solve(), "(1, 1) (1, 0) (0, 0)");

    }

}
