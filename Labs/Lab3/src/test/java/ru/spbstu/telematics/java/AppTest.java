package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
    }

    @org.junit.Test
    public void testA()
    {
        App app = new App();
        app.CreateCars();
        app.InitTrafficLigth();

        app.tLigth.run();
        //TrafficLigth tl = new TrafficLigth();

        for(int i = 0; i < 5; i++)
        {
            app.tLigth.AddCar(app.cars.get(i));
            app.cars.get(i).run();
        }
//*/

    }
}
