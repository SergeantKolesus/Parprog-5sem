package ru.spbstu.telematics.java;

import org.apache.commons.collections4.map.*;
import org.apache.commons.collections4.keyvalue.*;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */



public class App
{
    private MultiKey mk;

    private ArrayList<ArrayField> elements;

    public App()
    {
        elements = new ArrayList<ArrayField>();

        //mk = new MultiKey();
    }

    public int Size()
    {
        if(elements == null)
            return 0;

        return elements.size();
    }

    public boolean Contains(MultiKey key)
    {
        for(ArrayField k : elements)
            if(key == k.key)
                return true;

        return false;
    }

    public void Add()
    {

    }

    public void CreateTestSetup()
    {
        ArrayList<String> arls = new ArrayList<String>();

        arls.add("Test");

        for(int i = 0; i < 10; i++)
        {
            arls.add("" + i);

            elements.add(new ArrayField(new MultiKey(arls.get(i), arls.get(i + 1)), i));
        }
    }

    public void Print()
    {
        for(ArrayField f : elements)
        {
            System.out.println(f.key + " " + f.value);
        }
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

