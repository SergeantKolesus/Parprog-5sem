package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sun.text.resources.ext.JavaTimeSupplementary_zh_SG;

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
    public void testB()
    {
        TrafficLigth tl = new TrafficLigth();

        System.out.println("Created traffic ligth");

        tl.CreateCar('u', 'd', tl, 0);
        tl.CreateCar('r', 'l', tl, 1);
        tl.CreateCar('r', 'd', tl, 2);
        tl.CreateCar('d', 'u', tl, 3);
        tl.CreateCar('l', 'r', tl, 4);

        System.out.println("Cars created");

        tl.RunCars();

        System.out.println("Cars activated");

        tl.Start();
    }

    public void nontestA()
    {
        App app = new App();
        app.CreateCars();
        app.InitTrafficLigth();

        Runnable task = () -> {
            app.tLigth.run();
            /*
            try {
                app.tLigth.run();
            }
            catch (Exception exc)
            {
                System.out.println(exc);
            }
             //*/
        };

        Thread t = new Thread(task);
        t.start();

        //app.tLigth.start();

        System.out.println("Tl launched");
        //TrafficLigth tl = new TrafficLigth();

        for(int i = 0; i < 5; i++)
        {


//            ts[i] = new Thread(task);
  //          ts[i].start();

            System.out.println("Working with car " + i);

            app.cars.get(i).activate();

          /*
            Car c =app.cars.get(i);

            System.out.println("Starting car");

            //app.tLigth.AddCar(app.cars.get(i));
            c.start();
            //*/
        }
//*/
        while(app.tLigth.isAlive)
            ;
    }
}
