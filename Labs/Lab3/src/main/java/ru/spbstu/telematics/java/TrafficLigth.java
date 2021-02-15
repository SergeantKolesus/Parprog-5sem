package ru.spbstu.telematics.java;

import sun.text.resources.ext.JavaTimeSupplementary_zh_SG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrafficLigth extends Thread
{
    private ArrayList<Direction[]> directions;
    private ArrayList<Car> cars;
    private int state;
    public boolean isAlive;

    public TrafficLigth()
    {
        isAlive = true;
        turnedOn = false;

        directions = new ArrayList<Direction[]>();

        directions.add(new Direction[] {new Direction('r', 'd'), new Direction('r', 'l')});
        directions.add(new Direction[] {new Direction('u', 'd'), new Direction('d', 'u')});
        directions.add(new Direction[] {new Direction('l', 'r')});

        cars = new ArrayList<Car>();

        state = 0;
    }

    public Direction[] GetState()
    {
        return directions.get(state);
    }

    public void CreateCar(char from, char to, TrafficLigth tl, int number)
    {
        cars.add(new Car(from ,to, tl, number));
    }

    public void RunCars()
    {
        for(Car car : cars)
            car.activate();
    }

    private boolean[]  callbacks;
    private boolean[] callbacksGiven;

    private void _initCallbacks()
    {
        callbacks = new boolean[cars.size()];
        callbacksGiven = new boolean[cars.size()];

        System.out.println("Creating callbacks with size " + cars.size());

        for(int i = 0; i < callbacks.length; i++)
        {
            callbacks[i] = false;
            callbacksGiven[i] = false;
        }
    }

    public synchronized void GiveCallback(int i, boolean state)
    {
        if( (callbacks == null) || (i >= callbacks.length) || (i >= callbacksGiven.length) )
        {
            System.out.println("To big i " + i);
            return;
        }

        if(callbacksGiven[i] == false)
            System.out.println("Callback " + i + " recieved");

        callbacks[i] = state;
        callbacksGiven[i] = true;

        System.out.println("Callback recieved " + i);
    }

    private boolean _callbacksGiven()
    {
        counter++;

        for(int i = 0; i < callbacksGiven.length; i++)
        {

            if(!callbacksGiven[i])
                return false;
        }

        return true;
    }

    public boolean turnedOn;

    public void TurnOffCars()
    {
        for(Car car : cars)
            car.isAsleep = true;

        System.out.println("Cars set asleep");
    }

    public  void AwakeCars()
    {
        for(Car car : cars)
            car.isAsleep = false;

        System.out.println("Cars awaken");
    }

    public void Start()
    {
        int step;

        for(state = 0; state < 3; state++)
        {
            TurnOffCars();

            _initCallbacks();

            AwakeCars();

            while(!_callbacksGiven())
                try {
                    Thread.sleep(1000);
                    //System.out.println("Callbacks checked");
                }
            catch (InterruptedException exc) {
                System.out.println(exc);
            }

            TurnOffCars();


            step = 0;

            for(int i = 0; i < cars.size(); i++)
                if(callbacks[i + step] == true)
                {
                    cars.remove(i);

                    for(int j = i; j < cars.size(); j++) {
                        System.out.println("Car number updated from " + (int)(j + 1) + " to " + j);
                        cars.get(j).SetNumber(j);
                    }

                    i--;
                    step++;
                }

            System.out.println("State " + state + " ended");
        }


    }
}