package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.collections4.map.*;
import org.apache.commons.collections4.keyvalue.*;

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
        MultiKeyMap mkMap = new MultiKeyMap();
        MultiKey mk = new MultiKey("Oh", "Hey");

        /*
        mkMap.put("EN","Label_Name","Name");
        mkMap.put("EN", "Label_Name", "Test", "Name");
        mkMap.put("Label_Name", "EN", "Name2");
        System.out.println(mkMap + " size " + mkMap.size());
        System.out.println(mkMap.get("EN", "Label_Name"));
        System.out.println(mkMap.get("Label_Name", "EN"));
        //*/

        App myMultikey = new App();

        myMultikey.CreateTestSetup();
        myMultikey.Print();

        mkMap.put("0", "1", 1);
        mkMap.put("1", "2", 2);
        mkMap.put("2", "3", 3);
        mkMap.put("3", "4", 4);
        mkMap.put("4", "5", 5);
        mkMap.put("5", "6", 6);
        mkMap.put("6", "7", 7);
        mkMap.put("7", "8", 8);
        mkMap.put("8", "9", 9);

        System.out.println(mkMap);

        int a;
        int b;

        a = mkMap.size();
        b = myMultikey.Size();

        if(a != (b - 1) )
            assertTrue(false);
        
        assertTrue( true );
    }
}
