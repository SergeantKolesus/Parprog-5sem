package ru.spbstu.telematics.java;

import org.apache.commons.collections4.keyvalue.*;

import java.util.ArrayList;
public class ArrayField<T>
{
    public T value;
    public MultiKey key;

    public ArrayField(MultiKey mKey, T val)
    {
        value = val;
        key = mKey;
    }
}