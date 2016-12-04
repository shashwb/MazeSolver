package cs2114.mazesolver;

import android.widget.Button;
import android.widget.TextView;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * A standard test class for MazeSolverScreen. Tests the methods in
 * MazeSolverScreen to see if they work properly.
 *
 * @author Andrew Knittle
 * @version 2013.10.23
 */
public class MazeSolverScreenTests
    extends student.AndroidTestCase<MazeSolverScreen>
{
    // ~ Fields ................................................................

    private ShapeView shapeView;
    private TextView  infoLabel;

    // This field will store the pixel width/height of a cell in the maze.
    private float     cellSize;
    private Button    drawWalls;
    private Button    eraseWalls;
    private Button    solve;
    private Button    setStart;
    private Button    setGoal;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeSolverScreenTests()
    {
        super(MazeSolverScreen.class);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        float viewSize = Math.min(shapeView.getWidth(), shapeView.getHeight());

        cellSize = (viewSize / 6);

    }


    // ----------------------------------------------------------
    /**
     * Test to see if solveClicked() worked while also making sure other cells
     * are made.
     */
    public void testSolve()
    {
        Maze maze = getScreen().getMaze();
        click(setStart);
        clickCell(0, 0);

        click(drawWalls);
        touchDownCell(2, 2);

        click(eraseWalls);
        touchMoveCell(2, 2);
        touchUp();

        click(setGoal);
        clickCell(1, 1);
        click(solve);

        String str = maze.solve();
        assertEquals(str, this.infoLabel.getText());

    }


    // ----------------------------------------------------------
    /**
     * Tests to see if the path fails and matches the GUI.
     */
    public void testSolveFailed()
    {
        Maze mazeFail = getScreen().getMaze();
        click(setStart);
        clickCell(0, 0);
        click(drawWalls);
        clickCell(1, 1);
        clickCell(0, 1);
        clickCell(1, 0);
        click(setGoal);
        clickCell(2, 2);
        assertEquals(null, mazeFail.solve());
    }


    // ~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates moving the finger instantaneously to the middle of the
     * specified cell in the maze.
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }
}
