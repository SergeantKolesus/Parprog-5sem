package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.collections4.map.*;
import org.apache.commons.collections4.keyvalue.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void testN3App()
    {
        /*
        MultiKeyMap mkMap = new MultiKeyMap();
        MultiKey mk = new MultiKey("Oh", "Hey");

        /*
        mkMap.put("EN","Label_Name","Name");
        mkMap.put("EN", "Label_Name", "Test", "Name");
        mkMap.put("Label_Name", "EN", "Name2");
        System.out.println(mkMap + " size " + mkMap.size());
        System.out.println(mkMap.get("EN", "Label_Name"));
        System.out.println(mkMap.get("Label_Name", "EN"));
        ///

        KolesMultiKeyMap myMultikey = new KolesMultiKeyMap();

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
        mkMap.put("7", "8", 9);
        mkMap.put("2", "3", "Hello");


        System.out.println(mkMap);

        int a;
        int b;

        a = mkMap.size();
        b = myMultikey.Size();

        /*
        if(a != (b - 1) )
            assertTrue(false);
///
        System.out.println((int)myMultikey.Get(new MultiKey("3", "4")));

        assertTrue( true );

        //*/

    }

    public void testN1Size()
    {
        System.out.println("================================================================");
        System.out.println("Testing size method");

        KolesMultiKeyMap myMultikey = new KolesMultiKeyMap();
        myMultikey.CreateTestSetup();

        assertTrue(myMultikey.size() == 10);

        System.out.println("================================================================");
    }

    public void testN2Contains()
    {
        System.out.println("================================================================");
        System.out.println("Testing contains method");

        KolesMultiKeyMap myMultikey = new KolesMultiKeyMap();
        String[] keys = new String[2];

        keys[0] = "0";

        myMultikey.CreateTestSetup();

        for(int i = 1; i < 10; i++)
        {
            keys[1] = "" + i;

            if(!myMultikey.contains(new MultiKey(keys[0], keys[1])))
                assertTrue(false);

            keys[0] = keys[1];
        }

        assertTrue(true);

        System.out.println("================================================================");
    }

    public void testN3Get()
    {
        System.out.println("================================================================");
        System.out.println("Testing get method");

        KolesMultiKeyMap myMultikey = new KolesMultiKeyMap();
        String[] keys = new String[2];

        keys[0] = "0";

        myMultikey.CreateTestSetup();

        for(int i = 1; i < 10; i++)
        {
            keys[1] = "" + i;

            if((int)myMultikey.get(new MultiKey(keys[0], keys[1])) != i)
                assertTrue(false);

            keys[0] = keys[1];
        }

        assertTrue(true);

        System.out.println("================================================================");
    }

    public void testN4Add()
    {
        System.out.println("================================================================");
        System.out.println("Testing add method");

        KolesMultiKeyMap myMultikey = new KolesMultiKeyMap();
        String[] keys = new String[2];

        keys[0] = "0";

        for(int i = 1; i < 10; i++)
        {
            keys[1] = "" + i;

            myMultikey.add(new MultiKey(keys[0], keys[1]), i);

            if(!myMultikey.contains(new MultiKey(keys[0], keys[1])))
                assertTrue(false);

            if((int)myMultikey.get(new MultiKey(keys[0], keys[1])) != i)
                assertTrue(false);

            keys[0] = keys[1];
        }

        for(int i = 1; i < 10; i++)
        {
            keys[1] = "" + i;

            myMultikey.add(new MultiKey(keys[0], keys[1]), i * 2);

            if(!myMultikey.contains(new MultiKey(keys[0], keys[1])))
                assertTrue(false);

            if((int)myMultikey.get(new MultiKey(keys[0], keys[1])) != i * 2)
                assertTrue(false);

            keys[0] = keys[1];
        }

        assertTrue(true);

        System.out.println("================================================================");
    }

    public void testN5Remove()
    {
        System.out.println("================================================================");
        System.out.println("Testing remove method");

        KolesMultiKeyMap myMultikey = new KolesMultiKeyMap();
        String[] keys = new String[2];

        keys[0] = "0";

        for(int i = 1; i < 10; i++)
        {
            keys[1] = "" + i;

            myMultikey.add(new MultiKey(keys[0], keys[1]), i);

            keys[0] = keys[1];
        }

        keys[0] = "0";

        for(int i = 0; i < 10; i++)
        {
            keys[1] = "" + i;

            myMultikey.remove(new MultiKey(keys[0], keys[1]));

            if(myMultikey.contains(new MultiKey(keys[0], keys[1])))
                assertTrue(false);

            keys[0] = keys[1];
        }

        assertTrue(true);

        System.out.println("================================================================");
    }
}
