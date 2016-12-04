package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * This class describes of the location of an arbitrary point on an arbitrary
 * grid that will be used to describe how points "move" around a grid (Maze).
 *
 * @author AndrewK
 * @version Oct 2, 2013
 */
public class Location
    implements ILocation
{
    //these are the instance fields...we will need to constantly update the
    //x and y coordinates of the location
    private int x;
    private int y;


    // ----------------------------------------------------------
    /**
     * Create a new Location object.
     *
     * @param x
     *            (The x value of the location coordinate)
     * @param y
     *            (The y value of the location coordinate)
     */
    public Location(int x, int y)
    {

        //this basically makes a new location...it's a constructor
        //"this" refers to the instance of this class...meaning the instance
        //variables. This is saying that the instance variable "x" will be
        //updated to the value of x that is passed into as a parameter of
        //this function. This pretty much makes this a constructor method.
        this.x = x;
        this.y = y;

    }


    /**
     * This method sees if loc is empty and if this glass is equivalent to loc's
     * class.
     *
     * @param loc
     *            (an arbitrary location)
     * @return true or false (it's true if location is equal to point then it's
     *         true else it's false).
     */
    //in this case, loc is of type Object...meaning any type.
    //so you can compare any location with...well, pretty much anything
    //what this means is that Location.equals(#anything) will check to see
    //if #something is the same class as the thing we are comparing.

    //so what are we passing into equals...well we're passing in something of
    //type object. Everything in java is of type object, so this can compare
    //anything and tells us if it is a location or not.
    public boolean equals(Object loc)
    {

        //if the location does not equal anything (null) and is of the same
        //class as the location.
        //"this" literally refers to the class...meaning this.x is referring
        //to the location class variable. If THIS location's class is equal to
        //this other location class, then it is equal and we return true;
        if (loc != null && this.getClass() == loc.getClass())
        {
            //we set point equal to the value in loc...we can cast it now
            //that we know that it is of the same class. We do this to
            //make sure it works correctly
            Location point = (Location)loc;
            //here we actually check the members of the point and see if they
            //are equal to the instance variable
            if (point.x() == this.x() && point.y() == this.y())
            {
                //return true if everything is equal
                return true;
            }

        }

        return false;
    }


    // ----------------------------------------------------------
    /**
     * Returns a string that puts loc.x() and loc.y() it into an (x,y) formatted
     * string.
     *
     * @return String (A string that "formats" the location coordinates into
     *         strings)
     */

    //this makes complete sense...this how I would've done it
    public String toString()
    {
        return "(" + this.x() + ", " + this.y() + ")";
    }


    // ----------------------------------------------------------
    /**
     * It returns the x value of the location.
     *
     * @return x (returns the integer value of x)
     */
    //this is how we access the x variable. We cannot just use the instance
    //variable to do what we want
    public int x()
    {
        return x;
    }


    // ----------------------------------------------------------
    /**
     * It returns the y value of the location.
     *
     * @return y (returns the integer value of y)
     */
    public int y()
    {
        return y;
    }


    // ----------------------------------------------------------
    /**
     * Sets a new value of y one less than the original y value which then makes
     * a new coordinate for a Location. It moves one value up on the grid.
     *
     * @return Location (returns the new location after moving north)
     */

    //do not really understand why the north is -1...shouldn't it be +1?
    public Location north()
    {

        return new Location(x, y - 1);
    }


    // ----------------------------------------------------------
    /**
     * Sets a new value of y one more than the original y value which then makes
     * a new coordinate for a Location. It moves one value down on the grid.
     *
     * @return Location (returns the new location after moving south)
     */
    //same as above for this...why the fuck is it +1?
    public Location south()
    {
        return new Location(x, y + 1);
    }


    // ----------------------------------------------------------
    /**
     * Sets a new value of x one more than the original x value which then makes
     * a new coordinate for a Location. It moves one value right on the grid.
     *
     * @return Location (returns the new location after moving east)
     */

    //east and west make complete sense
    public Location east()
    {
        // return new Location(x + 1, y);
        Location loc = new Location(x + 1, y);
        return loc;
    }


    // ----------------------------------------------------------
    /**
     * Sets a new value of x one less than the original x value which then makes
     * a new coordinate for a Location. It moves one value left on the grid.
     *
     * @return Location (returns the new location after moving west)
     */
    public Location west()
    {
        return new Location(x - 1, y);
    }
}
