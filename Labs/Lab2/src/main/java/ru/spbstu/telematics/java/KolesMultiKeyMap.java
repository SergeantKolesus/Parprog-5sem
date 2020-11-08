package ru.spbstu.telematics.java;

import org.apache.commons.collections4.map.*;
import org.apache.commons.collections4.keyvalue.*;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */



public class KolesMultiKeyMap
{
    private MultiKey mk;

    private ArrayList<ArrayField> elements;

    public KolesMultiKeyMap()
    {
        elements = new ArrayList<ArrayField>();
    }

    public int Size()
    {
        return elements.size();
    }

    public boolean Contains(MultiKey key)
    {
        ArrayField f = new ArrayField(key, 0);

        return elements.contains(f);
    }

    public <T> void Add(MultiKey key, T val)
    {
        ArrayField f = new ArrayField(key, val);

        if(elements.contains(f))
        {
            elements.remove(elements.indexOf(f));
            System.out.println("Match");
        }

        elements.add(f);
    }

    public void Remove(MultiKey key)
    {
        ArrayField f = new ArrayField(key, 0);

        if(elements.contains(f))
            elements.remove(elements.indexOf(f));
    }

    public <T> T Get(MultiKey key)
    {
        ArrayField f = new ArrayField(key, 0);

        if(elements.contains(f))
             return (T)((elements.get(elements.indexOf(f))).value);

        return null;
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

