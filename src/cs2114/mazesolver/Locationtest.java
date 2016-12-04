package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * A test Class that checks all possible outcomes for the Class Location.
 *
 * @author AndrewK
 * @version Oct 4, 2013
 */
public class Locationtest
    extends student.TestCase
{
    private Location lTest;


    /**
     * initializes lTest to a default.
     */
    public void setUp()
    {
        lTest = new Location(4, 4);
    }


    // ----------------------------------------------------------
    /**
     * Tests the method toString() to see if it returns expected value.
     */
    public void testToString()
    {
        assertEquals(lTest.toString(), "(4, 4)");
    }


    // ----------------------------------------------------------
    /**
     * Tests the method equals() to see if it gives the expected values (true,
     * false, and another case where one of the locations is null).
     */
    public void testEquals()
    {
        Location p1 = new Location(4, 4);
        assertEquals(p1.equals(lTest), true);

        Location p2 = new Location(5, 5);
        assertEquals(p2.equals(lTest), false);

        Location p3 = null;
        assertEquals(lTest.equals(p3), false);

    }


    // ----------------------------------------------------------
    /**
     * Tests to make sure the x values returned from x() are correct.
     */
    public void testX()
    {
        assertEquals(lTest.x(), 4);
    }


    // ----------------------------------------------------------
    /**
     * Similar testX(), this test makes sure the y values returned from y() are
     * correct.
     */
    public void testY()
    {
        assertEquals(lTest.y(), 4);
    }


    // ----------------------------------------------------------
    /**
     * Tests north() that it returns a new location of a y less than one of the
     * original value.
     */
    public void testNorth()
    {
        Location l1 = new Location(4, 3);
        assertEquals(lTest.north(), l1);
    }


    // ----------------------------------------------------------
    /**
     * Tests south() that it returns a new location of a y more than one of the
     * original value.
     */
    public void testSouth()
    {
        Location l2 = new Location(4, 5);
        assertEquals(lTest.south(), l2);
    }


    // ----------------------------------------------------------
    /**
     * Tests east() that it returns a new location of a x more than one of the
     * original value.
     */
    public void testEast()
    {
        Location l3 = new Location(5, 4);
        assertEquals(lTest.east(), l3);
    }


    // ----------------------------------------------------------
    /**
     * Tests west() that it returns a new location of a x less than one of the
     * original value.
     */
    public void testWest()
    {
        Location l4 = new Location(3, 4);
        assertEquals(lTest.west(), l4);
    }
}
