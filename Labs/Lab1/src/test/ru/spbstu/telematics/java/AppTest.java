package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
//import ru.spbstu.telematics.java.App;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );

        System.out.println("1");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    /*
    public void testApp()
    {
        assertTrue( true );
        System.out.println("3");
    }
    //*/

    @org.junit.Test
    public void testSimple()
    {
        App myApp = new App();

        System.out.println("================================================================");
        System.out.println("Testing string representation of simple numbers");

        assertEquals("2 = 1 * 2", myApp.SimpleDividers(2));
        assertEquals("3 = 1 * 3", myApp.SimpleDividers(3));
        assertEquals("5 = 1 * 5", myApp.SimpleDividers(5));
        assertEquals("7 = 1 * 7", myApp.SimpleDividers(7));
        assertEquals("113 = 1 * 113", myApp.SimpleDividers(113));
        //assertEquals("2 = 1 ");

        System.out.println("================================================================");
    }

    @org.junit.Test
    public void testComplex()
    {
        App myApp = new App();

        System.out.println("================================================================");
        System.out.println("Testing string representation of complex numbers");

        assertEquals("6 = 2 * 3", myApp.SimpleDividers(6));
        assertEquals("15 = 3 * 5", myApp.SimpleDividers(15));
        assertEquals("4 = 2^2", myApp.SimpleDividers(4));
        assertEquals("16 = 2^4", myApp.SimpleDividers(16));
        assertEquals("36 = 2^2 * 3^2", myApp.SimpleDividers(36));
        assertEquals("362880 = 2^7 * 3^4 * 5 * 7", myApp.SimpleDividers(362880));
        //assertEquals("2 = 1 ");

        System.out.println("================================================================");
    }

    @org.junit.Test
    public void testSpecial()
    {
        App myApp = new App();

        System.out.println("================================================================");
        System.out.println("Testing string representation of special ocasion");

        assertEquals("1 = 1", myApp.SimpleDividers(1));
        //assertEquals("2 = 1 ");

        System.out.println("================================================================");
    }

    @org.junit.Test
    public void testArrays()
    {
        App myApp = new App();

        System.out.println("================================================================");
        System.out.println("Testing array represintation");

        for(int i = 1; i < 1000; i++)
            assertEquals(i, myApp.UnpackRepresentation(myApp.SimpleDividersArray(i)));

        System.out.println("================================================================");
    }


}
