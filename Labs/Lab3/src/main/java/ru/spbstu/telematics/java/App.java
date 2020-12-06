package ru.spbstu.telematics.java;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public ArrayList<Car> cars;
    public TrafficLigth tLigth;

    public void CreateCars()
    {
        cars = new ArrayList<Car>();


        System.out.println("Created cars");
    }

    public void InitTrafficLigth()
    {
        /*
        tLigth = new TrafficLigth();

        for(int i = 0; i < 5; i++)
        {
            tLigth.AddCar(cars.get(i));
            cars.get(i).SetTrafficLigth(tLigth);
            cars.get(i).SetNumber(i);
        }
        //*/
        System.out.println("Tl created");
    }

    public static void main( String[] args )
    {



        System.out.println( "Hello World!" );
    }
}
