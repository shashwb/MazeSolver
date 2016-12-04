package cs2114.mazesolver;

import sofia.graphics.RectangleShape;
import sofia.graphics.Color;
import sofia.app.ShapeScreen;
import android.widget.TextView;

// -------------------------------------------------------------------------
/**
 * This class is used to create a working GUI that works with the class Maze. It
 * creates cells that represent different states of MazeCells and represents
 * them in the GUI.
 *
 * @author Andrew Knittle andrk11
 * @author Seth Balodi  shashwb
 * @author Chris Cornett
 * @version 2013.15.10
 */
public class MazeSolverScreen
    extends ShapeScreen
{
    // ~ Fields ................................................................
    /**
     * size is the field used to reference the size of the maze board.
     */
    public int        size;

    /**
     * cellDim is the field used to reference the dimensions of each side of the
     * cell.
     */
    public float      cellDim;

    private boolean   wall;

    private boolean   erase;

    private boolean   start;

    private boolean   goal;

    private ILocation mazeSt;
    private ILocation mazeGl;
    private ILocation rCell;

    private TextView  infoLabel;
    private Maze      maze1;


    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * This method initializes most of the fields, and creates the board of
     * empty white cells and makes the textView set to a blank string as
     * default.
     */
    public void initialize()
    {
        float x = getWidth();
        float y = getHeight();
        float r = Math.min(x, y);
        size = 6;
        this.maze1 = new Maze(size);
        cellDim = r / size;
        this.infoLabel.setText("");

        for (int i = 0; i < size; i++)
        {
            float top = i * cellDim;
            float bot = (i + 1) * cellDim;
            for (int j = 0; j < size; j++)
            {
                float left = j * cellDim;
                float right = (j + 1) * cellDim;
                RectangleShape rect = new RectangleShape(left, top, right, bot);
                rect.setColor(Color.black);
                rect.setFillColor(Color.white);

                add(rect);
            }

        }

    }


    // ----------------------------------------------------------
    /**
     * This method is used to show when the button drawWalls was clicked.
     */
    public void drawWallsClicked()
    {
        wall = true;
        erase = false;
        start = false;
        goal = false;
    }


    // ----------------------------------------------------------
    /**
     * This method is used to show when the button eraseWalls was clicked.
     */
    public void eraseWallsClicked()
    {
        wall = false;
        erase = true;
        start = false;
        goal = false;
    }


    // ----------------------------------------------------------
    /**
     * This method is used to show when the button setStart was clicked.
     */
    public void setStartClicked()
    {
        wall = false;
        erase = false;
        start = true;
        goal = false;

    }


    // ----------------------------------------------------------
    /**
     * This method is used to show when the button setGoal was clicked.
     */
    public void setGoalClicked()
    {
        wall = false;
        erase = false;
        start = false;
        goal = true;
    }


    // ----------------------------------------------------------
    /**
     * This method is used to show when the button drawWalls was clicked. This
     * method uses the Maze method solve() to solve the maze. It then checks
     * each cell to see if it it a CURRENT_PATH or FAILED_PATH. If the MazeCell
     * is a CURRENT_PATH it sets its corresponding cell on the board to yellow
     * and if the MazeCell is FAILED_PATH it sets its corresponding cell to
     * black. It also keeps the original color for the starting point and goal
     * point to keep it easy to read.
     */
    public void solveClicked()
    {

        String solution = maze1.solve();
        for (int w = 0; w < size; w++)
        {
            for (int h = 0; h < size; h++)
            {
                ILocation tempLoc = new Location(w, h);
                MazeCell placeholder = maze1.getCell(tempLoc);
                if (placeholder == MazeCell.CURRENT_PATH)
                {
                    float pointX = (w * cellDim);
                    float pointY = (h * cellDim);
                    RectangleShape pointcell =
                        getShapes().locatedAt(pointX, pointY)
                            .withClass(RectangleShape.class).front();
                    pointcell.setFillColor(Color.yellow);
                }
                if (placeholder == MazeCell.FAILED_PATH)
                {
                    float pointX = (w * cellDim);
                    float pointY = (h * cellDim);
                    RectangleShape pointcell =
                        getShapes().locatedAt(pointX, pointY)
                            .withClass(RectangleShape.class).front();
                    pointcell.setFillColor(Color.black);
                }

            }
            ILocation temploc2 = maze1.getStartLocation();
            RectangleShape startCell =
                getShapes()
                    .locatedAt((temploc2.x() * cellDim), temploc2.y() * cellDim)
                    .withClass(RectangleShape.class).front();
            startCell.setFillColor(Color.blue);
            ILocation temploc3 = maze1.getGoalLocation();
            RectangleShape goalCell =
                getShapes()
                    .locatedAt((temploc3.x() * cellDim), temploc3.y() * cellDim)
                    .withClass(RectangleShape.class).front();
            goalCell.setFillColor(Color.green);

        }
        if (solution == null)
        {
            solution = "No solution was possible";
            infoLabel.setText(solution);
        }
        else
        {
            infoLabel.setText(solution);
        }
    }


    // ----------------------------------------------------------
    /**
     * If the the buttons drawWalls has been clicked it sets the corresponding
     * cell to red and makes the corresponding cell in the Maze to WALL. If the
     * the button drawWalls has been clicked it sets the corresponding cell to
     * white and makes the corresponding cell in the Maze to UNEXPLORED. If the
     * the button setStart has been clicked it sets the corresponding cell to
     * blue and makes the corresponding location the starting location. If the
     * button setGoal has been clicked it sets the corresponding cell to green
     * and makes the corresponding location the goal location.
     *
     * @param x
     *            the x coordinate in pixels.
     * @param y
     *            the y coordinate in pixels.
     */
    public void processTouch(float x, float y)
    {
        int a = (int)(x / (Math.min(getWidth(), getHeight()) / size));
        int b = (int)(y / (Math.min(getWidth(), getHeight()) / size));
        if (wall)
        {
            rCell = new Location(a, b);
            maze1.setCell(rCell, MazeCell.WALL);
            RectangleShape cell =
                getShapes().locatedAt(x, y).withClass(RectangleShape.class)
                    .front();
            cell.setFillColor(Color.red);
        }
        if (erase)
        {
            rCell = new Location(a, b);
            maze1.setCell(rCell, MazeCell.UNEXPLORED);
            RectangleShape cell =
                getShapes().locatedAt(x, y).withClass(RectangleShape.class)
                    .front();
            cell.setFillColor(Color.white);
        }
        if (start)
        {

            ILocation previousStart = maze1.getStartLocation();
            float preX = (previousStart.x() * (getWidth() / size));
            float preY = (previousStart.y() * (getHeight() / size));

            RectangleShape prestcell =
                getShapes().locatedAt(preX, preY)
                    .withClass(RectangleShape.class).front();
            prestcell.setFillColor(Color.white);
            maze1.setCell(previousStart, MazeCell.UNEXPLORED);
            mazeSt = new Location(a, b);
            maze1.setStartLocation(mazeSt);
            RectangleShape cell =
                getShapes().locatedAt(x, y).withClass(RectangleShape.class)
                    .front();
            cell.setFillColor(Color.blue);
        }
        if (goal)
        {
            ILocation previousGoal = maze1.getGoalLocation();

            float preX = (previousGoal.x() * (getWidth() / size));
            float preY = (previousGoal.y() * (getHeight() / size));
            RectangleShape preglcell =
                getShapes().locatedAt(preX, preY)
                    .withClass(RectangleShape.class).front();
            preglcell.setFillColor(Color.white);
            maze1.setCell(previousGoal, MazeCell.UNEXPLORED);
            mazeGl = new Location(a, b);
            maze1.setGoalLocation(mazeGl);
            RectangleShape cell =
                getShapes().locatedAt(x, y).withClass(RectangleShape.class)
                    .front();
            cell.setFillColor(Color.green);
        }
    }


    // ----------------------------------------------------------
    /**
     * When a cell is pushed down it applies the processTouch method to that
     * Corresponding location.
     *
     * @param x
     *            the x coordinate in pixels.
     * @param y
     *            the y coordinate in pixels.
     */
    public void onTouchDown(float x, float y)
    {
        processTouch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * When a cell is pushed down it applies the processTouch method to that
     * Corresponding location.
     *
     * @param x
     *            the x coordinate in pixels.
     * @param y
     *            the y coordinate in pixels.
     */
    public void onTouchMove(float x, float y)
    {
        processTouch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * Returns the value of the maze.
     *
     * @return maze1 the maze being used.
     */
    public Maze getMaze()
    {
        return maze1;
    }
}
