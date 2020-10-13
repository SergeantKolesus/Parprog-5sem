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
    public void testApp()
    {
        assertTrue( true );
        System.out.println("3");
    }

    @org.junit.Test
    public void testSimple()
    {
        App myApp = new App();

        System.out.println("================================================================");
        System.out.println("Testing simple numbers");

        assertEquals("2 = 1 * 2", myApp.SimpleDividers(2));
        assertEquals("3 = 1 * 3", myApp.SimpleDividers(3));
        assertEquals("5 = 1 * 5", myApp.SimpleDividers(5));
        assertEquals("7 = 1 * 7", myApp.SimpleDividers(7));
        //assertEquals("2 = 1 ");

        System.out.println("================================================================");
    }

    @org.junit.Test
    public void testComplex()
    {
        App myApp = new App();

        System.out.println("================================================================");
        System.out.println("Testing complex numbers");

        assertEquals("6 = 1 * 2 * 3", myApp.SimpleDividers(6));
        assertEquals("15 = 1 * 3 * 5", myApp.SimpleDividers(15));
        //assertEquals("2 = 1 ");

        System.out.println("================================================================");
    }


}
