package ru.spbstu.telematics.java;

import org.apache.commons.collections4.map.*;
import org.apache.commons.collections4.keyvalue.*;

import java.util.ArrayList;
import java.util.Iterator;

class CustomIterator<T> implements Iterator<T>
{
    private ArrayList<T> itElements;
    private int itIndex;

    public CustomIterator(ArrayList elements)
            {
            itElements = elements;
            itIndex = 0;
            }

    @Override
    public boolean hasNext() {
            return itIndex < itElements.size();
            }

    @Override
    public T next()
    {
        return itElements.get(itIndex++);
    }
}