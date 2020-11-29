package ru.spbstu.telematics.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrafficLigth extends Thread
{
    private ArrayList<Direction[]> directions;
    private ArrayList<Car> cars;
    private int state;

    public TrafficLigth()
    {
        directions = new ArrayList<Direction[]>();

        directions.add(new Direction[] {new Direction('r', 'd'), new Direction('r', 'l')});
        directions.add(new Direction[] {new Direction('u', 'd'), new Direction('d', 'u')});
        directions.add(new Direction[] {new Direction('l', 'r')});

        cars = new ArrayList<Car>();

        state = 0;
/*
        callbacksFree = true;

        callbacks = new boolean[5];
        callbackGiven = new boolean[5];
         */
    }

    public Direction[] GetState()
    {
        return directions.get(state);
    }

    public void AddCar(Car car)
    {
        cars.add(car);
    }

    private boolean[]  callbacks;
    private boolean[] callbacksGiven;

    private void _initCallbacks()
    {
        callbacks = new boolean[cars.size()];
        callbacksGiven = new boolean[cars.size()];

        for(int i = 0; i < callbacks.length; i++)
        {
            callbacks[i] = false;
            callbacksGiven[i] = false;
        }
    }

    public void GiveCallback(int i, boolean state)
    {
        callbacks[i] = state;
        callbacksGiven[i] = true;
    }

    private boolean _callbacksGiven()
    {
        for(int i = 0; i < callbacks.length; i++)
        {
            if(!callbacks[i])
                return false;
        }

        return true;
    }

    public void run()
    {
        System.out.println("");

        for(state = 0; state < cars.size(); state++)
        {
            _initCallbacks();

            while(!_callbacksGiven())
                ;

            int step = 0;

            for(int i = 0; i < cars.size(); i++)
                if(callbacks[i + step] == true)
                {
                    cars.remove(i);

                    for(int j = i; j < cars.size(); j++)
                        cars.get(j).SetNumber(j);

                    i--;
                    step++;
                }
        }
    }

    /*
    private ArrayList<Direction[]> directions;
    private int state;
    private ArrayList<Car> cars;
    private boolean[] callbacks;
    private boolean[] callbackGiven;
    private String[] carNames;
    boolean callbacksFree;
    //private void



    public Direction[] GetState()
    {
        if(state != -1)
            return directions.get(state);
        else
            return null;
    }

    public void AddCar(Car car)
    {
        cars.add(car);
    }

    public void SetCallback(int i, boolean state)
    {
        if(callbacksFree)
        {
            callbacks[i] = state;
            callbackGiven[i] = true;
        }
    }

    private void _clearCallback()
    {
        callbacksFree = false;

        int s = cars.size();

        callbacks = new boolean[s];
        callbackGiven = new boolean[s];

        for(int i = 0; i < s; i++)
        {
            callbacks[i] = false;
            callbackGiven[i] = false;
        }

        callbacksFree = true;
    }

    private boolean _callbacksDone()
    {
        if(callbackGiven == null)
            return false;

        for(int i = 0; i < callbackGiven.length; i++)
            if(!callbackGiven[i])
                return false;

        return true;
    }

    private void _prepareCars()
    {
        int i = 0;
        carNames = new String[cars.size()];

        for(Map.Entry<String, Car> c : cars.entrySet())
        {
            c.getValue().SetNumber(i);
            carNames[i] = c.getKey();
            i++;
        }
    }

    private void _clearCars()
    {
        for(int i = 0; i < callbacks.length; i++)
            if(callbacks[i])
            {
                cars.remove(carNames[i]);
            }
    }

    public void run()
    {
        int tState;
        callbacks = new boolean[5];

        System.out.println( "Traffic ligth turned on" );

        System.out.println(cars);

        for(tState = 0; tState < 3; tState++)
        {
            _prepareCars();
            _clearCallback();

            while ((!_callbacksDone()))
                ;

            _clearCars();

            state = -1;
            state = tState;
        }
    }
    //*/
}